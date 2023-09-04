package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    public List<Attendance> findByEmployeeId(int employeeId);


    boolean existsByEmployeeIdAndDateOfAttendance(Integer employeeId, Date dateOfAttendance);
}
