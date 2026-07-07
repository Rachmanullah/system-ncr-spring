package com.example.ncrsystem.ncrsystem.service.inbox;

import com.example.ncrsystem.ncrsystem.common.constant.StatusConstant;
import com.example.ncrsystem.ncrsystem.common.mapper.NCRRequestMapper;
import com.example.ncrsystem.ncrsystem.dto.ncrlogs.NCRLogsResponse;
import com.example.ncrsystem.ncrsystem.dto.ncrrequest.NCRRequestDto;
import com.example.ncrsystem.ncrsystem.dto.ncrrequest.NCRRequestResponse;
import com.example.ncrsystem.ncrsystem.model.NCRLogs;
import com.example.ncrsystem.ncrsystem.model.NCRRequest;
import com.example.ncrsystem.ncrsystem.model.User;
import com.example.ncrsystem.ncrsystem.repository.*;
import com.example.ncrsystem.ncrsystem.service.ncrFlowEngine.NCRFlowEngineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InboxLmpl implements InboxService{
    private final NCRRequestRepository ncrRequestRepository;
    private final NCRRequestDetailRepository ncrRequestDetailRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final NCRRequestMapper ncrRequestMapper;
    private final NCRLogsRepository ncrLogsRepository;
    private final NCRFlowEngineService ncrFlowEngineService;

    @Override
    public List<NCRRequestResponse> findAll() {
        return ncrRequestRepository.findAllNcrNeedApprove()
                .stream()
                .map(ncrRequestMapper::toResponse)
                .peek(this::setLastApprover)
                .toList();
    }

    @Override
    @Transactional
    public NCRRequestResponse approve(NCRRequestDto ncrRequestDto) {
        User implementationBy = null;
        NCRRequest ncrRequest = ncrRequestRepository.findByNcrNumber(ncrRequestDto.getNcrNumber()).orElseThrow(() -> new RuntimeException("NCR Not Found"));
        User approver = userRepository.findById(ncrRequestDto.getApprover())
                .orElseThrow(() -> new RuntimeException("Approver not found"));
        boolean existingMatrix = ncrFlowEngineService.checkMatrix(ncrRequestDto.getDepartmentId());

        if (ncrRequestDto.getApproverNotes().isEmpty()){
            throw new RuntimeException(
                    "Approver Notes Required"
            );
        }

        if (!existingMatrix){
            throw new RuntimeException(
                    "Approval Matrix not exists for this department"
            );
        }
        NCRLogs ncrLogs = new NCRLogs();
        ncrLogs.setUser(approver);
        ncrLogs.setOrderNumber(ncrRequestDto.getRunningNumber());
        ncrLogs.setStatusName(StatusConstant.APPROVE_NAME);
        ncrLogs.setNotes(ncrRequestDto.getApproverNotes());
        if (ncrRequestDto.getImplementationId() != null && ncrRequestDto.getImplementationId().compareTo(BigInteger.ZERO) > 0) {
            implementationBy = userRepository.findById(ncrRequestDto.getImplementationId())
                    .orElseThrow(() -> new RuntimeException("Implementation user not found"));
            ncrRequest.setImplementationBy(implementationBy);
        }

        ncrLogs.setNcrRequest(ncrRequest);
        ncrLogsRepository.save(ncrLogs);

        ncrFlowEngineService.nextFlow(ncrRequest);
        NCRRequest saved = ncrRequestRepository.save(ncrRequest);
        return ncrRequestMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public NCRRequestResponse reject(NCRRequestDto ncrRequestDto){
        NCRRequest ncrRequest = ncrRequestRepository.findByNcrNumber(ncrRequestDto.getNcrNumber()).orElseThrow(() -> new RuntimeException("NCR Not Found"));
        User approver = userRepository.findById(ncrRequestDto.getApprover())
                .orElseThrow(() -> new RuntimeException("Approver not found"));
        boolean existingMatrix = ncrFlowEngineService.checkMatrix(ncrRequestDto.getDepartmentId());

        if (ncrRequestDto.getApproverNotes().isEmpty()){
            throw new RuntimeException(
                    "Approver Notes Required"
            );
        }

        if (!existingMatrix){
            throw new RuntimeException(
                    "Approval Matrix not exists for this department"
            );
        }
        NCRLogs ncrLogs = new NCRLogs();
        ncrLogs.setUser(approver);
        ncrLogs.setOrderNumber(ncrRequestDto.getRunningNumber());
        ncrLogs.setStatusName(StatusConstant.REJECTED_NAME);
        ncrLogs.setNotes(ncrRequestDto.getApproverNotes());
        ncrLogs.setNcrRequest(ncrRequest);
        ncrLogsRepository.save(ncrLogs);

        ncrFlowEngineService.previousFlow(ncrRequest);
        NCRRequest saved = ncrRequestRepository.save(ncrRequest);
        return ncrRequestMapper.toResponse(saved);
    }

    private void setLastApprover(NCRRequestResponse response) {
        List<NCRLogsResponse> logs = response.getNcrLogs();
        if (logs == null || logs.isEmpty()) {
            return;
        }

        NCRLogsResponse lastLog = logs.stream()
                .max(Comparator.comparing(NCRLogsResponse::getNcrLogId))
                .orElse(null);

        if (lastLog != null) {
            response.setApprover(lastLog.getUserId());
            response.setApproverNotes(lastLog.getNotes());
        }
    }
}
