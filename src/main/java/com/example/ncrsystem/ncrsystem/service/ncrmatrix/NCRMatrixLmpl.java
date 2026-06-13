package com.example.ncrsystem.ncrsystem.service.ncrmatrix;

import com.example.ncrsystem.ncrsystem.common.constant.StatusConstant;
import com.example.ncrsystem.ncrsystem.common.mapper.NCRMatrixMapper;
import com.example.ncrsystem.ncrsystem.common.util.GenerateNCRMatrixCode;
import com.example.ncrsystem.ncrsystem.dto.ncrmatrixapproval.NCRMatrixRequest;
import com.example.ncrsystem.ncrsystem.dto.ncrmatrixapproval.NCRMatrixResponse;
import com.example.ncrsystem.ncrsystem.model.Department;
import com.example.ncrsystem.ncrsystem.model.NCRMatrixApproval;
import com.example.ncrsystem.ncrsystem.model.NCRMatrixDetail;
import com.example.ncrsystem.ncrsystem.model.User;
import com.example.ncrsystem.ncrsystem.repository.DepartmentRepository;
import com.example.ncrsystem.ncrsystem.repository.NCRMatrixApprovalRepository;
import com.example.ncrsystem.ncrsystem.repository.NCRMatrixDetailRepository;
import com.example.ncrsystem.ncrsystem.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NCRMatrixLmpl implements NCRMatrixService{
    private final NCRMatrixApprovalRepository matrixApprovalRepository;
    private final NCRMatrixMapper matrixMapper;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final GenerateNCRMatrixCode generateNCRMatrixCode;
    private final NCRMatrixDetailRepository detailRepository;

    public NCRMatrixLmpl(NCRMatrixApprovalRepository matrixApprovalRepository, NCRMatrixMapper matrixMapper, UserRepository userRepository, DepartmentRepository departmentRepository, GenerateNCRMatrixCode generateNCRMatrixCode, NCRMatrixDetailRepository detailRepository) {
        this.matrixApprovalRepository = matrixApprovalRepository;
        this.matrixMapper = matrixMapper;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.generateNCRMatrixCode = generateNCRMatrixCode;
        this.detailRepository = detailRepository;
    }


    @Override
    public List<NCRMatrixResponse> findAll() {

        return matrixApprovalRepository.findByDeleted(StatusConstant.ACTIVE)
                .stream()
                .filter(item ->
                        StatusConstant.ACTIVE.equals(
                                item.getDeleted()
                        )
                )
                .map(matrixMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public NCRMatrixResponse create(
            NCRMatrixRequest request
    ) {

        Department department =
                departmentRepository.findById(
                        request.getDepartmentId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Department not found"
                        ));

        if (!StringUtils.hasText(
                request.getNcrMatrixCode()
        )) {

            request.setNcrMatrixCode(
                    generateNCRMatrixCode.generate(
                            LocalDate.now(),
                            department.getDepartmentCode()
                    )
            );
        }

        NCRMatrixApproval header =
                matrixMapper.toHeader(
                        request,
                        department
                );

        header =
                matrixApprovalRepository.save(
                        header
                );

        Map<BigInteger, User> approverMap =
                buildApproverMap(request);

        List<NCRMatrixDetail> details =
                matrixMapper.toDetails(
                        header,
                        request,
                        approverMap
                );

        details =
                detailRepository.saveAll(
                        details
                );

        header.setDetails(details);

        return matrixMapper.toResponse(
                header
        );
    }

    @Override
    @Transactional
    public NCRMatrixResponse update(
            BigInteger ncrMatrixId,
            NCRMatrixRequest request
    ) {

        NCRMatrixApproval header =
                matrixApprovalRepository
                        .findByNcrMatrixIdAndDeleted(
                                ncrMatrixId,
                                StatusConstant.ACTIVE
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Matrix not found"
                                ));

        Department department =
                departmentRepository.findById(
                        request.getDepartmentId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Department not found"
                        ));
        Set<BigInteger> approverIds = new HashSet<>();

        for (NCRMatrixRequest.Approver item : request.getApprover()) {
            if (!approverIds.add(item.getApproverId())) {
                throw new RuntimeException(
                        "Duplicate approver : "
                                + item.getApproverId()
                );
            }
        }
        header.setNcrMatrixCode(
                generateNCRMatrixCode.generate(
                        LocalDate.now(),
                        department.getDepartmentCode()
                )
        );
        header.setDepartment(
                department
        );

        matrixApprovalRepository.save(
                header
        );

        detailRepository.deleteByNcrMatrixApproval(
                header
        );

        Map<BigInteger, User> approverMap =
                buildApproverMap(request);

        List<NCRMatrixDetail> details =
                matrixMapper.toDetails(
                        header,
                        request,
                        approverMap
                );

        details =
                detailRepository.saveAll(
                        details
                );
        header.getDetails().clear();
        header.getDetails().addAll(details);
        System.out.println(header.toString());
        return matrixMapper.toResponse(
                header
        );
    }

    @Override
    @Transactional
    public NCRMatrixResponse delete(
            BigInteger ncrMatrixId
    ) {

        NCRMatrixApproval header =
                matrixApprovalRepository.findById(
                        ncrMatrixId
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Matrix Not Found"
                        ));

        header.setDeleted(
                StatusConstant.DELETED
        );

        header = matrixApprovalRepository.save(
                header
        );

        List<NCRMatrixDetail> details =
                detailRepository
                        .findByNcrMatrixApproval(
                                header
                        );

        details.forEach(item ->
                item.setDeleted(
                        StatusConstant.DELETED
                )
        );

        detailRepository.saveAll(
                details
        );

        header.setDetails(
                details
        );

        return matrixMapper.toResponse(
                header
        );
    }
    private Map<BigInteger, User> buildApproverMap(
            NCRMatrixRequest request
    ) {

        return request.getApprover()
                .stream()
                .collect(Collectors.toMap(
                        NCRMatrixRequest.Approver::getApproverId,
                        item -> {

                            User user =
                                    userRepository.findById(
                                            item.getApproverId()
                                    ).orElseThrow(() ->
                                            new RuntimeException(
                                                    "Approver not found : "
                                                            + item.getApproverId()
                                            ));

                            if (StatusConstant.DELETED.equals(
                                    user.getDeleted()
                            )) {
                                throw new RuntimeException(
                                        "Approver "
                                                + user.getFullname()
                                                + " is inactive"
                                );
                            }

                            return user;
                        }
                ));
    }
}
