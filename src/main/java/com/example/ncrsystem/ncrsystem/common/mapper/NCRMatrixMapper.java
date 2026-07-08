package com.example.ncrsystem.ncrsystem.common.mapper;

import com.example.ncrsystem.ncrsystem.dto.ncrmatrixapproval.NCRMatrixRequest;
import com.example.ncrsystem.ncrsystem.dto.ncrmatrixapproval.NCRMatrixResponse;
import com.example.ncrsystem.ncrsystem.model.Department;
import com.example.ncrsystem.ncrsystem.model.NCRMatrixApproval;
import com.example.ncrsystem.ncrsystem.model.NCRMatrixDetail;
import com.example.ncrsystem.ncrsystem.model.User;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Component
public class NCRMatrixMapper {
    public NCRMatrixApproval toHeader(
            NCRMatrixRequest request,
            Department department
    ) {

        return NCRMatrixApproval.builder()
                .ncrMatrixCode(request.getNcrMatrixCode())
                .department(department)
                .build();
    }

    public List<NCRMatrixDetail> toDetails(
            NCRMatrixApproval header,
            NCRMatrixRequest request,
            Map<BigInteger, User> approvers
    ) {

        return request.getApprover()
                .stream()
                .map(item ->
                        NCRMatrixDetail.builder()
                                .ncrMatrixApproval(header)
                                .approver(
                                        approvers.get(
                                                item.getApproverId()
                                        )
                                )
                                .orderNumber(
                                        item.getOrderNumber()
                                )
                                .approveToOrderNumber(item.getApproveToOrderNumber())
                                .rejectToOrderNumber(item.getRejectToOrderNumber())
                                .build()
                )
                .toList();
    }

    public NCRMatrixResponse toResponse(
            NCRMatrixApproval entity
    ) {

        NCRMatrixResponse response =
                new NCRMatrixResponse();

        response.setNcrMatrixId(
                entity.getNcrMatrixId()
        );

        response.setNcrMatrixCode(
                entity.getNcrMatrixCode()
        );

        if (entity.getDepartment() != null) {

            response.setDepartmentId(
                    entity.getDepartment()
                            .getDepartmentId()
            );

            response.setDepartmentCode(
                    entity.getDepartment()
                            .getDepartmentCode()
            );

            response.setDepartmentName(
                    entity.getDepartment()
                            .getDepartmentName()
            );
        }

        response.setStatus(
                entity.getStatus().intValue()
        );

        if (entity.getDetails() != null) {

            List<NCRMatrixResponse.ApproverResponse>
                    approvers =
                    entity.getDetails()
                            .stream()
                            .map(detail -> {

                                NCRMatrixResponse.ApproverResponse a =
                                        new NCRMatrixResponse.ApproverResponse();

                                a.setApproverId(
                                        detail.getApprover()
                                                .getUserId()
                                );

                                a.setApproverName(
                                        detail.getApprover()
                                                .getFullname()
                                );

                                a.setApproverPosition(
                                        detail.getApprover()
                                                .getPosition()
                                );

                                a.setOrderNumber(
                                        detail.getOrderNumber()
                                );
                                a.setApproveToOrderNumber(detail.getApproveToOrderNumber());
                                a.setRejectToOrderNumber(detail.getRejectToOrderNumber());
                                return a;
                            })
                            .toList();

            response.setApprover(
                    approvers
            );
        }

        return response;
    }

    public List<NCRMatrixResponse> toResponseList(
            List<NCRMatrixApproval> entities
    ) {

        return entities.stream()
                .map(this::toResponse)
                .toList();
    }
}
