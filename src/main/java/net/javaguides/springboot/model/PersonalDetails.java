package net.javaguides.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personal_details")
public class PersonalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "date_of_marriage")
    private Date dateOfMarriage;

    @Column(name = "employee_personal_phone_number", nullable = false)
    private String employeePersonalPhoneNumber;

    @Column(name = "employee_emergency_contact_number", nullable = false)
    private String employeeEmergencyContactNumber;

    @Column(name = "permanent_address", nullable = false)
    private String permanentAddress;

    @Column(name = "permanent_address_landmark", nullable = false)
    private String permanentAddressLandmark;

    @Column(name = "permanent_address_pin_code", nullable = false)
    private String permanentAddressPinCode;

    @Column(name = "permanent_address_ref_mobile_number", nullable = false)
    private String permanentAddressRefMobileNumber;

    @Column(name = "permanent_address_ref_landline_number")
    private String permanentAddressRefLandlineNumber;

    @Column(name = "permanent_address_stay_period_from")
    private Date permanentAddressStayPeriodFrom;

    @Column(name = "permanent_address_stay_period_to")
    private Date permanentAddressStayPeriodTo;

    @Column(name = "permanent_address_stay_period_till_date")
    private boolean permanentAddressStayPeriodTillDate;

    @Column(name = "current_address_landmark", nullable = false)
    private String currentAddressLandmark;

    @Column(name = "current_address_pin_code", nullable = false)
    private String currentAddressPinCode;

    @Column(name = "current_address_ref_mobile_number", nullable = false)
    private String currentAddressRefMobileNumber;

    @Column(name = "current_address_ref_landline_number")
    private String currentAddressRefLandlineNumber;

    @Column(name = "current_address_stay_period_from")
    private Date currentAddressStayPeriodFrom;

    @Column(name = "current_address_stay_period_to")
    private Date currentAddressStayPeriodTo;

    @Column(name = "current_address_stay_period_till_date")
    private boolean currentAddressStayPeriodTillDate;

    @Column(name = "employee_photograph")
    private String employeePhotograph;

  /*  @OneToOne(cascade = CascadeType.ALL) // Add cascade for proper cascading operations
    @JoinColumn(name = "employee_id")
    private Employee employee; // Assuming this is the relationship to the Employee entity*/
    private Integer employeeId;
}