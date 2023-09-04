package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Payslip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayslipRepository extends JpaRepository<Payslip, Integer> {
}
