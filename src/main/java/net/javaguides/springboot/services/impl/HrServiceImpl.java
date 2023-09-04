package net.javaguides.springboot.services.impl;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.payloads.AttendanceDTO;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.services.HrService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HrServiceImpl implements HrService {
    @Autowired
    private AttendanceServiceImpl attendanceServiceImpl;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public long getAbsentCount(int id) {
        List<AttendanceDTO> attendanceDTOS = attendanceServiceImpl.getAttendanceByEmployeeId(id);
        long count = attendanceDTOS.stream().filter(attendanceDTO -> attendanceDTO.getStatus() == "Absent").count();
        return count;
    }

    public List<Long > getAllEmployeesAbsentCount(){
        List<Employee> employeeList = employeeRepository.findAll();
        List<Long> list= employeeList.stream().map(employee -> getAbsentCount(employee.getId())).collect(Collectors.toList());
        return list;
    }
}

