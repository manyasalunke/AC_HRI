package net.javaguides.springboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payslips")
public class Payslip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payslipId;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "basicSalary")
    private Double basicSalary;

    @Column(name = "allowances")
    private Double allowances;

    @Column(name = "deduction")
    private Double deduction;

    @Column(name = "netSalary")
    private Double netSalary;

    @Column(name = "grossSalary")
    private Double grossSalary;

    @Column(name = "paySlipNumber")
    private String paySlipNumber;

    @Column(name = "issueDate")
    private Date issueDate;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee; // Many-to-One relationship with Employee

    @OneToOne
    @JoinColumn(name = "salaryId")
    private Salary salary; // One-to-Many relationship with Salary
}
