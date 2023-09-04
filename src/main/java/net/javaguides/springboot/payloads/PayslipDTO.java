package net.javaguides.springboot.payloads;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PayslipDTO {

    private int payslipId;

    @NotBlank(message = "month reason is required")
    private String month;

    @NotBlank(message = "year reason is required")
    private String year;

    @NotNull(message = "Basic Salary is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Basic Salary must be a positive decimal value")
    private Double basicSalary;

    @NotNull(message = "Allowances Salary is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Allowances Salary must be a positive decimal value")
    private Double allowances;

    @NotNull(message = "Deduction Salary is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Deduction Salary must be a positive decimal value")
    private Double deduction;

    @NotNull(message = "Net Salary is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Net Salary must be a positive decimal value")
    private Double netSalary;

    @NotNull(message = "Gross Salary is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Gross Salary must be a positive decimal value")
    private Double grossSalary;

    @NotBlank(message = "PaySlip Number is required")
    private String paySlipNumber;

    @NotNull(message = "Issue Date is required in this format \"yyyy-MM-dd\"")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date issueDate;
}
