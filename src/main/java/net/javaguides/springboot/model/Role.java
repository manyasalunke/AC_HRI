package net.javaguides.springboot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "roles", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Employee> employees = new HashSet<>();
}
