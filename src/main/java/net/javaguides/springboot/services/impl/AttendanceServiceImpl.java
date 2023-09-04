package net.javaguides.springboot.services.impl;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import net.javaguides.springboot.exception.DuplicateAttendanceException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Attendance;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.payloads.AttendanceDTO;
import net.javaguides.springboot.payloads.EmployeeDTO;
import net.javaguides.springboot.payloads.LeaveDTO;
import net.javaguides.springboot.repository.AttendanceRepository;
import net.javaguides.springboot.services.AttendanceService;
import net.javaguides.springboot.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service()
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final EmployeeService employeeService;

    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, ModelMapper modelMapper, EmployeeService employeeService) {
        this.attendanceRepository = attendanceRepository;
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
    }

    @Override
    public AttendanceDTO createAttendance(AttendanceDTO attendanceDTO) {
        // Fetch the associated Employee by its ID
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(attendanceDTO.getEmployeeId());
        if (employeeDTO == null) {
            throw new ResourceNotFoundException("Employee", "Id", attendanceDTO.getEmployeeId());
        }

        if (attendanceRepository.existsByEmployeeIdAndDateOfAttendance(
                attendanceDTO.getEmployeeId(), attendanceDTO.getDateOfAttendance())) {
            throw new DuplicateAttendanceException("Attendance record already exists for this date.");
        }

        Attendance attendance = this.modelMapper.map(attendanceDTO, Attendance.class);

        // Convert EmployeeDTO to Employee and set it for the Attendance
        Employee employee = this.modelMapper.map(employeeDTO, Employee.class);
        attendance.setEmployeeId(employee.getId());

        Attendance addedAttendance = this.attendanceRepository.save(attendance);
        return this.modelMapper.map(addedAttendance, AttendanceDTO.class);
    }




    @Override
    public AttendanceDTO updateAttendance(AttendanceDTO attendanceDTO, Integer attendanceId) {
        Attendance attendance = this.attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance ", "Attendance Id", attendanceId));

        attendance.setDateOfAttendance(attendanceDTO.getDateOfAttendance());
        attendance.setStatus(attendanceDTO.getStatus());
        attendance.setRemark(attendanceDTO.getRemark());
        attendance.setCreatedBy(attendanceDTO.getCreatedBy());
        attendance.setTimeIn(attendanceDTO.getTimeIn()); // Set Time In from DTO
        attendance.setTimeOut(attendanceDTO.getTimeOut()); // Set Time Out from DTO


        Attendance UpdatedAttendance = this.attendanceRepository.save(attendance);
        return this.modelMapper.map(UpdatedAttendance, AttendanceDTO.class);
    }

    @Override
    public void deleteAttendance(Integer attendanceId) {
        Attendance attendance = this.attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance ", "Attendance id", attendanceId));
        this.attendanceRepository.delete(attendance);
    }

    @Override
    public AttendanceDTO getAttendance(Integer attendanceId) {
        Attendance attendance = this.attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance", "Attendance id", attendanceId));
        return this.modelMapper.map(attendance, AttendanceDTO.class);
    }

    @Override
    public List<AttendanceDTO> getAttendances() {
        List<Attendance> Attendances = this.attendanceRepository.findAll();
        List<AttendanceDTO> attendanceDto = Attendances.stream().map((attendance) -> this.modelMapper.map(attendance, AttendanceDTO.class))
                .collect(Collectors.toList());
        return attendanceDto;
    }


    @Override
    public void createAttendanceFromLeave(LeaveDTO updatedLeave) {
        Date startDate = updatedLeave.getStartDate();
        Date endDate = updatedLeave.getEndDate();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        while (!calendar.getTime().after(endDate)) {
            AttendanceDTO attendanceDTO = AttendanceDTO.builder()
                    .dateOfAttendance(calendar.getTime()) // Pass a Date object here
                    .createdBy("Employee")
                    .status("LeaveGranted")
                    .employeeId(updatedLeave.getEmployeeId())
                    .build();

            createAttendance(attendanceDTO); // Call the method to save the attendance
            calendar.add(Calendar.DATE, 1); // Move to the next day
        }
    }





    public List<AttendanceDTO> getAttendanceByEmployeeId(Integer employeeId){

        List<Attendance> attendanceList = this.attendanceRepository.findByEmployeeId(employeeId);
        List<AttendanceDTO> attendanceDto = attendanceList.stream().map((attendance) -> this.modelMapper.map(attendance, AttendanceDTO.class))
                .collect(Collectors.toList());
        return attendanceDto;
    }
}
