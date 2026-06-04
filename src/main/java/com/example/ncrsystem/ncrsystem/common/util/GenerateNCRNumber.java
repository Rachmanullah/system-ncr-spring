package com.example.ncrsystem.ncrsystem.common.util;

import com.example.ncrsystem.ncrsystem.repository.NCRRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class GenerateNCRNumber {
    private final NCRRequestRepository repository;
    public Integer getRunningNumber(LocalDate date) {
        Integer year = date.getYear();
        Integer lastRunningNumber = repository.getLastRunningNumber(year);
        return lastRunningNumber + 1;
    }

    public String generate(LocalDate date) {
        Integer runningNumber = getRunningNumber(date);
        return NCRNumberUtil.generate(
                date,
                runningNumber
        );
    }
}
