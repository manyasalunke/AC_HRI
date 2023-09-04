package net.javaguides.springboot.payloads;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO {

    private int id;

    @NotBlank(message = "First name is required")
    @Size(min = 4, message = "employee first name must be min of 4 characters !!")
    private String firstName;

    @NotBlank(message = "Second name is required")
    @Size(min = 1, message = "employee Second name must be min of 4 characters !!")
    private String secondName;

    @NotBlank(message = "Last name is required")
    @Size(min = 4, message = "employee last name must be min of 4 characters !!")
    private String lastName;

    @NotBlank(message = "User name is required")
    @Size(min = 4, message = "employee user name must be min of 4 characters !!")
    private String userName;

    @Email(message = "Email address is not valid !!")
    @NotEmpty(message = "Email is required !!")
    private String emailId;

    @NotBlank
    @Size(min = 3, max = 15, message = "Password must be min of 3 chars and max of 15 chars !!")
    private String password;

    @NotBlank(message = "Phone number is required")
    @Digits(integer = 10, fraction = 0, message = "Phone number must be an integer")
    private String phoneNumber;

    private String address;

    @NotNull(message = "Date of birth is required in this format \"yyyy-MM-dd\"")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Date of birth must be in the past in this format \"yyyy-MM-dd\"")
    private Date dateOfBirth;

    @NotNull(message = "Date of joining is required in this format \"yyyy-MM-dd\"")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Date of joining must be in the past in this format \"yyyy-MM-dd\"")
    private Date dateOfJoining;

    @NotBlank(message = "Reporting Manager name is required")
    @Size(min = 1, message = "Reporting Manager name must be min of 1 characters !!")
    private String reportingManager;

    @Email(message = "Reporting Manager Email address is not valid !!")
    @NotEmpty(message = "Reporting Manager Email is required !!")
    private String reportingManagerMail;

    private String shift_code;

    private String businessUnit;

    @NotBlank(message = "Location name is required")
    @Size(min = 1, message = "Location name must be min of 1 characters !!")
    private String location;

    @NotBlank(message = "Type of Employment is required")
    @Pattern(regexp = "^(Full Time Employee|Consultant|Contractor)$", message = "Invalid status. Allowed values: Full Time Employee, Consultant, Contractor")
    private String typeOfEmployment;

//    @NotBlank(message = "Is Reporting Manager is required")
    @Pattern(regexp = "^(Yes|No)$", message = "Invalid status. Allowed values: Yes, No")
    private String repoManager; //    private String isReportingManager;

 //   @NotBlank(message = "Is Reporting Manager is required")
 //   @Pattern(regexp = "^(Yes|No)$", message = "Invalid status. Allowed values: Yes, No")
//    private String isReportingManager;

    @NotBlank(message = "Employee Status is required")
    @Pattern(regexp = "^(Probation|ProbationExtended|Confirmed)$", message = "Invalid status. Allowed values: Probation, ProbationExtended, Confirmed")
    private String employeeStatus;

    @NotNull(message = "Date of Confirmation is required in this format \"yyyy-MM-dd\"")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Date of Confirmation must be in the past in this format \"yyyy-MM-dd\"")
    private Date dateOfConfirmation;


    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password=password;
    }

    private int departmentId;

    private int roleId;
}

