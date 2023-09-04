package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails, Long> {
    PersonalDetails findByEmployeeId(int employeeId);
}
