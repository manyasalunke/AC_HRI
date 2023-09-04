package net.javaguides.springboot.payloads;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

//@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AttendanceDTO {
    private Integer attendanceId;

    @NotNull(message = "Attendance date is required in this format \"yyyy-MM-dd\"")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Attendance date must be in the present or future day in this format \"yyyy-MM-dd\"")
    private Date dateOfAttendance;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date timeIn; // Time In for the attendance.

    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date timeOut; // Time Out for the attendance.

    // Status of the attendance, such as "Present," "Absent," "Late," or "Early Departure."
    @NotBlank(message = "status is required")
    @Pattern(regexp = "^(Present|Absent|LeaveGrant|Early Departure)$", message = "Invalid status. Allowed values: Present, Absent, LeaveGrant, Early Departure")
    private String status;

    @NotBlank(message = "remark is required")
    private String remark;

    //User or admin who created the attendance record.
    @NotBlank(message = "created By is required")
    @Pattern(regexp = "^(Admin|Employee)$", message = "Invalid status. Allowed values: Admin, Employee")
    private String createdBy;

    private Integer employeeId; // Add employeeId field to hold the foreign key to Employee entity
}
