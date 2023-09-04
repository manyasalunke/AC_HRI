package net.javaguides.springboot.payloads;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PersonalDetailsDTO {

    private int id;

    private String maritalStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfMarriage;

//    @NotBlank(message = "Employee personal phone number is required")
    private String employeePersonalPhoneNumber;

//    @NotBlank(message = "Employee emergency contact number is required")
    private String employeeEmergencyContactNumber;

//    @NotBlank(message = "Permanent address is required")
    private String permanentAddress;

//    @NotBlank(message = "Permanent address landmark is required")
    private String permanentAddressLandmark;

//    @NotBlank(message = "Permanent address pin code is required")
    private String permanentAddressPinCode;

//    @NotBlank(message = "Permanent address reference mobile number is required")
    private String permanentAddressRefMobileNumber;

    private String permanentAddressRefLandlineNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date permanentAddressStayPeriodFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date permanentAddressStayPeriodTo;

    private boolean permanentAddressStayPeriodTillDate;

//    @NotBlank(message = "Current address landmark is required")
    private String currentAddressLandmark;

//    @NotBlank(message = "Current address pin code is required")
    private String currentAddressPinCode;

//    @NotBlank(message = "Current address reference mobile number is required")
    private String currentAddressRefMobileNumber;

    private String currentAddressRefLandlineNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date currentAddressStayPeriodFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date currentAddressStayPeriodTo;

    private boolean currentAddressStayPeriodTillDate;

    private String employeePhotograph;

    private Integer employeeId;
}