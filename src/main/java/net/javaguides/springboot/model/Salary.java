package net.javaguides.springboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "salaries")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salaryId;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private Integer year;

    @Column(name = "basicSalary")
    private Double basicSalary;

    @Column(name = "allowances")
    private Double allowances;

    @Column(name = "deduction")
    private Double deduction;

    @Column(name = "netSalary")
    private Double netSalary;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee; // Many-to-One relationship with Employee

}
