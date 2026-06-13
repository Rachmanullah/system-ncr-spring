package com.example.ncrsystem.ncrsystem.common.util;

import com.example.ncrsystem.ncrsystem.repository.NCRMatrixApprovalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class GenerateNCRMatrixCode {
    private final NCRMatrixApprovalRepository repository;
    public Integer getRunningNumber(LocalDate date, String deptCode) {
        Integer year = date.getYear();
        Integer lastRunningNumber = repository.getLastRunningNumber(year, deptCode);
        return lastRunningNumber + 1;
    }
    public String generate(LocalDate date, String deptCode) {
        Integer runningNumber = getRunningNumber(date, deptCode);
        return NCRNumberUtil.generateMatrixCode(
                date,
                runningNumber,
                deptCode
        );
    }
}
