package net.javaguides.springboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "departments")
@NoArgsConstructor
@Getter@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departmentId;

    @Column(name="departmentName",length = 15,nullable = false)
    private String departmentName;

    @Column(name="description")
    private String departmentDescription;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees; // One-to-Many relationship with Employee
}
