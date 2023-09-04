package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {
}
