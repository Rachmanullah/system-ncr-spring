package com.example.ncrsystem.ncrsystem.service.ncrrequest;

import com.example.ncrsystem.ncrsystem.common.constant.StatusConstant;
import com.example.ncrsystem.ncrsystem.common.mapper.NCRLogsMapper;
import com.example.ncrsystem.ncrsystem.common.mapper.NCRRequestMapper;
import com.example.ncrsystem.ncrsystem.common.util.GenerateNCRNumber;
import com.example.ncrsystem.ncrsystem.dto.ncrrequest.NCRRequestDto;
import com.example.ncrsystem.ncrsystem.dto.ncrrequest.NCRRequestResponse;

import com.example.ncrsystem.ncrsystem.model.*;
import com.example.ncrsystem.ncrsystem.repository.*;
import com.example.ncrsystem.ncrsystem.service.ncrFlowEngine.NCRFlowEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NCRLmpl implements NCRService{
    private final NCRRequestRepository ncrRequestRepository;
    private final NCRRequestDetailRepository ncrRequestDetailRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final NCRRequestMapper ncrRequestMapper;
    private final GenerateNCRNumber generateNCRNumber;
    private final NCRLogsRepository ncrLogsRepository;
    private final NCRFlowEngine ncrFlowEngine;

    @Override
    public List<NCRRequestResponse> findAll() {
        return ncrRequestRepository.findAllNcr()
                .stream()
                .map(ncrRequestMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public NCRRequestResponse create(NCRRequestDto request) {
        User requestor = userRepository.findById(request.getRequestorId())
                .orElseThrow(() -> new RuntimeException("Requestor not found"));
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        boolean existingMatrix = ncrFlowEngine.checkMatrix(request.getDepartmentId());
        User implementationBy = null;
        NCRLogs ncrLogs = new NCRLogs();
        ncrLogs.setUser(requestor);
        ncrLogs.setOrderNumber(0);
        if (request.getImplementationId() != null && request.getImplementationId().compareTo(BigInteger.ZERO) > 0) {
            implementationBy = userRepository.findById(request.getImplementationId())
                    .orElseThrow(() -> new RuntimeException("Implementation user not found"));
        }
        System.out.println(request.toString());
        NCRRequest ncrRequest = ncrRequestMapper.toEntity(
                request,
                requestor,
                department,
                implementationBy
        );

        if(!StringUtils.hasText(ncrRequest.getNcrNumber())){
            LocalDate localDateNcr = request.getNcrDate().toLocalDate();
            String ncrNumber = generateNCRNumber.generate(localDateNcr);
            ncrRequest.setNcrNumber(ncrNumber);
        }

        if (request.getAction().equals("Create")){
            ncrRequest.setStatusCode(StatusConstant.DRAFT_CODE);
            ncrRequest.setStatusName(StatusConstant.DRAFT_NAME);
            ncrLogs.setStatusName(StatusConstant.DRAFT_NAME);
        } else if (request.getAction().equals("Submit")){
            if (!existingMatrix){
                throw new RuntimeException(
                        "Approval Matrix not exists for this department"
                );
            }
            ncrRequest.setStatusCode(StatusConstant.WAITING_APPROVAL_CODE);
            ncrRequest.setStatusName(StatusConstant.WAITING_APPROVAL_NAME);
            ncrLogs.setStatusName(StatusConstant.SUBMIT);
        }

        System.out.println(ncrRequest);
        NCRRequest saved = ncrRequestRepository.save(ncrRequest);
        ncrLogs.setNcrRequest(saved);
        ncrLogsRepository.save(ncrLogs);

        if (request.getAction().equals("Submit")){
            ncrFlowEngine.startFlow(saved);
        }

        return ncrRequestMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public NCRRequestResponse update(BigInteger ncrId, NCRRequestDto request) {
        System.out.println("header : "+ request.toString());
        User implementationBy = null;
        NCRRequest ncrRequest = ncrRequestRepository.findById(ncrId).orElseThrow(() -> new RuntimeException("NCR Not Found"));
        NCRRequestDetail ncrRequestDetail = ncrRequestDetailRepository
                .findByNcrRequest_NcrId(ncrId)
                .orElseThrow(() -> new RuntimeException("NCR Detail Not Found"));
        User requestor = userRepository.findById(request.getRequestorId())
                .orElseThrow(() -> new RuntimeException("Requestor not found"));
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        boolean existingMatrix = ncrFlowEngine.checkMatrix(request.getDepartmentId());

        NCRLogs ncrLogs = new NCRLogs();
        ncrLogs.setUser(requestor);
        ncrLogs.setOrderNumber(0);
        ncrLogs.setStatusName(StatusConstant.DRAFT_NAME);
        if (request.getImplementationId() != null) {
            implementationBy = userRepository.findById(request.getImplementationId())
                    .orElseThrow(() -> new RuntimeException("Implementation User not found"));
        }

        ncrRequest.setNcrTitle(request.getNcrTitle());
        ncrRequest.setNcrProject(request.getNcrProject());
        ncrRequest.setDepartment(department);
        ncrRequest.setImplementationBy(implementationBy);
        ncrRequest.setNcrImplementationDate(request.getNcrImplementationDate());
        if (request.getAction().equals("Submit")){
            if (!existingMatrix){
                throw new RuntimeException(
                        "Approval Matrix not exists for this department"
                );
            }
            ncrRequest.setStatusCode(StatusConstant.WAITING_APPROVAL_CODE);
            ncrRequest.setStatusName(StatusConstant.WAITING_APPROVAL_NAME);
            ncrLogs.setStatusName(StatusConstant.SUBMIT);
        } else if (request.getAction().equals("Cancel")) {
            ncrRequest.setStatusCode(StatusConstant.CANCEL_CODE);
            ncrRequest.setStatusName(StatusConstant.CANCEL_NAME);
            ncrLogs.setStatusName(StatusConstant.CANCEL_NAME);
        }
        ncrRequest.setStatusCode(ncrRequest.getStatusCode());
        ncrRequest.setStatusName(ncrRequest.getStatusName());
        ncrRequestDetail.setDescription(request.getDetail().getDescription());
        ncrRequestDetail.setPriority(request.getDetail().getPriority());
        ncrRequestDetail.setAsIs(request.getDetail().getAsIs());
        ncrRequestDetail.setToBe(request.getDetail().getToBe());
        ncrRequestDetail.setImpact(request.getDetail().getImpact());
        ncrRequestDetail.setFinancialImpact(request.getDetail().getFinancialImpact());
        ncrRequest.setDetail(ncrRequestDetail);

        NCRRequest updated = ncrRequestRepository.save(ncrRequest);
        ncrLogs.setNcrRequest(updated);
        ncrLogsRepository.save(ncrLogs);

        if (request.getAction().equals("Submit")){
            ncrFlowEngine.startFlow(updated);
        }

        return ncrRequestMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public NCRRequestResponse delete(BigInteger ncrId) {
        NCRRequest ncrRequest = ncrRequestRepository
                .findById(ncrId)
                .orElseThrow(() -> new RuntimeException("NCR Not Found"));
        NCRRequestDetail ncrRequestDetail = ncrRequestDetailRepository
                .findByNcrRequest_NcrId(ncrId)
                .orElseThrow(() -> new RuntimeException("NCR Detail Not Found"));

        ncrRequest.setDeleted(StatusConstant.DELETED);
        ncrRequestDetail.setDeleted(StatusConstant.DELETED);
        ncrRequest.setDetail(ncrRequestDetail);
        NCRRequest deleted = ncrRequestRepository.save(ncrRequest);
        return ncrRequestMapper.toResponse(deleted);
    }
}
