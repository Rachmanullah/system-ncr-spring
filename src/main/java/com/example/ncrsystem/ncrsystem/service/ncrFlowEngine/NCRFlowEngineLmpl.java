package com.example.ncrsystem.ncrsystem.service.ncrFlowEngine;

import com.example.ncrsystem.ncrsystem.common.constant.StatusConstant;
import com.example.ncrsystem.ncrsystem.common.mapper.NCRLogsMapper;
import com.example.ncrsystem.ncrsystem.model.*;
import com.example.ncrsystem.ncrsystem.repository.NCRLogsRepository;
import com.example.ncrsystem.ncrsystem.repository.NCRMatrixApprovalRepository;
import com.example.ncrsystem.ncrsystem.repository.NCRMatrixDetailRepository;
import com.example.ncrsystem.ncrsystem.repository.NCRRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NCRFlowEngineLmpl implements NCRFlowEngineService {
    private final NCRMatrixApprovalRepository matrixApprovalRepository;
    private final NCRLogsMapper ncrLogsMapper;
    private final NCRLogsRepository ncrLogsRepository;
    private final NCRMatrixDetailRepository ncrMatrixDetailRepository;
    private final NCRRequestRepository ncrRequestRepository;
    @Override
    public boolean checkMatrix(BigInteger departmentId){
        Optional<NCRMatrixApproval> existingMatrix =
                matrixApprovalRepository
                        .findByDepartmentDepartmentIdAndDeleted(
                                departmentId,
                                StatusConstant.ACTIVE
                        );
        return existingMatrix.isPresent();
    }

    @Override
    public void startFlow(NCRRequest ncrRequest) {
        Optional<NCRMatrixApproval> matrixApproval =
                matrixApprovalRepository
                        .findByDepartmentDepartmentIdAndDeleted(
                                ncrRequest.getDepartment().getDepartmentId(),
                                StatusConstant.ACTIVE);


        Optional<NCRMatrixDetail> matrixDetail = ncrMatrixDetailRepository
                .findFirstByNcrMatrixApproval_NcrMatrixIdAndDeletedOrderByOrderNumberAsc(
                        matrixApproval.get().getNcrMatrixId(),
                        StatusConstant.ACTIVE
                );

        User firstApprover = matrixDetail.get().getApprover();

        ncrRequest.setRunningNumber(matrixDetail.get().getOrderNumber());
        ncrRequest.setStatusName(StatusConstant.WAITING_APPROVAL_NAME + " By " + firstApprover.getFullname());
        System.out.println(ncrRequest.toString());

         NCRLogs approvalLogs = new NCRLogs();
         approvalLogs.setNcrRequest(ncrRequest);
         approvalLogs.setUser(firstApprover);
         approvalLogs.setOrderNumber(matrixDetail.get().getOrderNumber());
         approvalLogs.setStatusName(StatusConstant.WAITING_APPROVAL_NAME);
         ncrLogsRepository.save(approvalLogs);
    }

    @Override
    public void nextFlow(NCRRequest ncrRequest) {
        Optional<NCRMatrixApproval> matrixApproval =
                matrixApprovalRepository
                        .findByDepartmentDepartmentIdAndDeleted(
                                ncrRequest.getDepartment().getDepartmentId(),
                                StatusConstant.ACTIVE);

        Optional<NCRMatrixDetail> nextDetail = ncrMatrixDetailRepository
                .findFirstByNcrMatrixApproval_NcrMatrixIdAndOrderNumberGreaterThanAndDeletedOrderByOrderNumberAsc(
                        matrixApproval.get().getNcrMatrixId(),
                        ncrRequest.getRunningNumber(),
                        StatusConstant.ACTIVE
                );
        if (nextDetail.isPresent()) {
            NCRMatrixDetail detail = nextDetail.get();
            User nextApprover = detail.getApprover();

            ncrRequest.setRunningNumber(detail.getOrderNumber());
            ncrRequest.setStatusCode(StatusConstant.WAITING_APPROVAL_CODE);
            ncrRequest.setStatusName(StatusConstant.WAITING_APPROVAL_NAME + " By " + nextApprover.getFullname());
            ncrRequestRepository.save(ncrRequest);
            NCRLogs approvalLogs = new NCRLogs();
            approvalLogs.setNcrRequest(ncrRequest);
            approvalLogs.setUser(nextApprover);
            approvalLogs.setOrderNumber(detail.getOrderNumber());
            approvalLogs.setStatusName(StatusConstant.WAITING_APPROVAL_NAME);
            ncrLogsRepository.save(approvalLogs);
        } else {
            ncrRequest.setStatusCode(StatusConstant.APPROVE_CODE);
            ncrRequest.setStatusName(StatusConstant.APPROVE_NAME);
            ncrRequestRepository.save(ncrRequest);
        }
    }

    @Override
    public void previousFlow(NCRRequest ncrRequest) {
        Optional<NCRMatrixApproval> matrixApproval =
                matrixApprovalRepository
                        .findByDepartmentDepartmentIdAndDeleted(
                                ncrRequest.getDepartment().getDepartmentId(),
                                StatusConstant.ACTIVE);
        Optional<NCRMatrixDetail> currentApprover = ncrMatrixDetailRepository
                .findFirstByNcrMatrixApproval_NcrMatrixIdAndOrderNumberAndDeleted(
                        matrixApproval.get().getNcrMatrixId(),
                        BigInteger.valueOf(ncrRequest.getRunningNumber()),
                        StatusConstant.ACTIVE
                );
        NCRLogs log = new NCRLogs();
        Integer rejectTarget = currentApprover.get().getRejectToOrderNumber() != null ?
                currentApprover.get().getRejectToOrderNumber() : ncrRequest.getRunningNumber() - 1;
        if(rejectTarget == null || rejectTarget <= 0){
            ncrRequest.setRunningNumber(0);
            ncrRequest.setStatusCode(StatusConstant.REJECTED_CODE);
            ncrRequest.setStatusName(StatusConstant.REJECTED_NAME + " By " +currentApprover.get().getApprover().getFullname());
            ncrRequestRepository.save(ncrRequest);
            return;
        }

        NCRMatrixDetail targetDetail = ncrMatrixDetailRepository
                .findFirstByNcrMatrixApproval_NcrMatrixIdAndOrderNumberAndDeleted(
                        matrixApproval.get().getNcrMatrixId(),
                        BigInteger.valueOf(rejectTarget),
                        StatusConstant.ACTIVE
                )
                .orElseThrow(() -> new RuntimeException("Target reject (order " + rejectTarget + ") tidak ditemukan di matrix"));
        User targetApprover = targetDetail.getApprover();
        ncrRequest.setRunningNumber(targetDetail.getOrderNumber());
        ncrRequest.setStatusCode(StatusConstant.WAITING_APPROVAL_CODE);
        ncrRequest.setStatusName(StatusConstant.WAITING_APPROVAL_NAME + " " + targetApprover.getFullname());
        log.setNcrRequest(ncrRequest);
        log.setUser(targetApprover);
        log.setOrderNumber(currentApprover.get().getOrderNumber());
        log.setStatusName(StatusConstant.WAITING_APPROVAL_NAME);
        ncrLogsRepository.save(log);
    }
}
