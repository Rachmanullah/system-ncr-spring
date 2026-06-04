package com.example.ncrsystem.ncrsystem.common.mapper;

import com.example.ncrsystem.ncrsystem.dto.ncrrequest.NCRRequestDto;
import com.example.ncrsystem.ncrsystem.dto.ncrrequest.NCRRequestResponse;
import com.example.ncrsystem.ncrsystem.dto.ncrrequestdetail.NCRDetailResponse;
import com.example.ncrsystem.ncrsystem.model.*;
import org.springframework.stereotype.Component;
@Component
public class NCRRequestMapper {
    public NCRRequest toEntity(NCRRequestDto ncrRequestDto, User requestor, Department department, User implementationBy, Priority priority){
        NCRRequestDetail ncrRequestDetail = NCRRequestDetail.builder()
                .description(ncrRequestDto.getDetail().getDescription())
                .priority(priority)
                .asIs(ncrRequestDto.getDetail().getAsIs())
                .toBe(ncrRequestDto.getDetail().getToBe())
                .benefit(ncrRequestDto.getDetail().getBenefit())
                .impact(ncrRequestDto.getDetail().getImpact())
                .financialImpact(ncrRequestDto.getDetail().getFinancialImpact())
                .build();

        NCRRequest ncrRequest = NCRRequest.builder()
                .ncrTitle(ncrRequestDto.getNcrTitle())
                .ncrProject(ncrRequestDto.getNcrProject())
                .ncrNumber(ncrRequestDto.getNcrNumber())
                .ncrDate(ncrRequestDto.getNcrDate())
                .requestor(requestor)
                .department(department)
                .implementationBy(implementationBy)
                .statusCode(ncrRequestDto.getStatusCode())
                .statusName(ncrRequestDto.getStatusName())
                .build();

        ncrRequestDetail.setNcrRequest(ncrRequest);
        ncrRequest.setDetail(ncrRequestDetail);

        return ncrRequest;
    }

    public NCRRequestResponse toResponse(NCRRequest entity){
        NCRRequestDetail detail = entity.getDetail();
        return NCRRequestResponse.builder()
                .ncrId(entity.getNcrId())
                .ncrNumber(entity.getNcrNumber())
                .ncrTitle(entity.getNcrTitle())
                .ncrProject(entity.getNcrProject())
                .ncrDate(entity.getNcrDate())
                .requestorId(
                        entity.getRequestor() != null
                                ? entity.getRequestor().getUserId()
                                : null
                )
                .requestorName(
                        entity.getRequestor() != null
                                ? entity.getRequestor().getFullname()
                                : null
                )
                .departmentId(
                        entity.getDepartment() != null
                                ? entity.getDepartment().getDepartmentId()
                                : null
                )
                .departmentName(
                        entity.getDepartment() != null
                                ? entity.getDepartment().getDepartmentName()
                                : null
                )
                .implementationId(
                        entity.getImplementationBy() != null
                                ? entity.getImplementationBy().getUserId()
                                : null
                )
                .implementationName(
                        entity.getImplementationBy() != null
                                ? entity.getImplementationBy().getFullname()
                                : null
                )
                .statusCode(entity.getStatusCode())
                .statusName(entity.getStatusName())
                .runningNumber(entity.getRunningNumber())
                .ncrDetail(
                        detail == null
                                ? null
                                : NCRDetailResponse.builder()
                                .ncrDetailId(
                                        detail.getNcrDetailId() != null
                                                ? detail.getNcrDetailId()
                                                : null
                                )
                                .description(detail.getDescription())
                                .priorityId(
                                        detail.getPriority() != null
                                                ? detail.getPriority().getPriorityId()
                                                : null
                                )
                                .asIs(detail.getAsIs())
                                .toBe(detail.getToBe())
                                .benefit(detail.getBenefit())
                                .impact(detail.getImpact())
                                .financialImpact(detail.getFinancialImpact())
                                .build()
                )
                .build();
    }
}
