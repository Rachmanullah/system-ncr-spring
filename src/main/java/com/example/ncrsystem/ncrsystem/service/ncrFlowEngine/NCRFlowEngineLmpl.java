package com.example.ncrsystem.ncrsystem.service.ncrFlowEngine;

import com.example.ncrsystem.ncrsystem.common.constant.StatusConstant;
import com.example.ncrsystem.ncrsystem.common.mapper.NCRLogsMapper;
import com.example.ncrsystem.ncrsystem.dto.ncrrequest.NCRRequestDto;
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
public class NCRFlowEngineLmpl implements NCRFlowEngine{
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
        ncrRequest.setStatusName(StatusConstant.WAITING_APPROVAL_NAME + " " + firstApprover.getFullname());
        System.out.println(ncrRequest.toString());
//        ncrRequestRepository.save(ncrRequest);

         NCRLogs approvalLogs = new NCRLogs();
         approvalLogs.setNcrRequest(ncrRequest);
         approvalLogs.setUser(firstApprover);
         approvalLogs.setOrderNumber(matrixDetail.get().getOrderNumber());
         approvalLogs.setStatusName(StatusConstant.WAITING_APPROVAL_NAME);
         ncrLogsRepository.save(approvalLogs);
    }
}
