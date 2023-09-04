package net.javaguides.springboot.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class LeaveDTO {

    private int leaveId;

    @NotBlank(message = "leave reason is required")
    @Size(min = 10, max = 50, message = "leave reason must be min of 10 and max of 50 characters !!")
    private String leaveReason;

    @NotNull(message = "Leave start date is required in this format \"yyyy-MM-dd\"")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull(message = "Leave end date is required in this format \"yyyy-MM-dd\"")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @NotBlank(message = "leave type is required")
    @Pattern(regexp = "^(Medical Leave|Paid Leave|CompOff Leave)$", message = "Invalid status. Allowed values: Medical Leave, Paid Leave, CompOff Leave")
    private String leaveType;

    @NotBlank(message = "leave status is required")
    @Pattern(regexp = "^(Approved|Rejected|Pending)$", message = "Invalid status. Allowed values: Approved, Rejected, Pending)")
    private String leaveStatus = "Pending"; // Default value is set to "Pending"

    private Integer employeeId; // Add employeeId field to hold the foreign key to Employee entity
}
