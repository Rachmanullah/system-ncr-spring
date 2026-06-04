package com.example.ncrsystem.ncrsystem.common.util;

import java.time.LocalDate;

public class NCRNumberUtil {
    private NCRNumberUtil() {}

    public static String generate(
            LocalDate date,
            Integer runningNumber
    ) {

        return String.format(
                "NCR-%02d-%02d-%04d-%04d",
                date.getDayOfMonth(),
                date.getMonthValue(),
                date.getYear(),
                runningNumber
        );
    }
}
