package com.example.ncrsystem.ncrsystem.dto.ncrmatrixapproval;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigInteger;
import java.util.List;

@Data
public class NCRMatrixRequest {
    @Length(max = 20)
    private String ncrMatrixCode;
    @NotNull(message = "Department Required")
    private BigInteger departmentId;
    @Valid
    @NotEmpty(message = "Approver Required")
    private List<Approver> approver;

    @Data
    public static class Approver {
        @NotNull(message = "Approver Required")
        private BigInteger approverId;

        @NotNull(message = "Order Number Required")
        @Min(1)
        private Integer orderNumber;
        private Integer approveToOrderNumber;
        private Integer rejectToOrderNumber;
    }
}
