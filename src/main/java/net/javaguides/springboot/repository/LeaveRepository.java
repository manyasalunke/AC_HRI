package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {
}
