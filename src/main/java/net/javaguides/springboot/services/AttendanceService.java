package net.javaguides.springboot.services;

import net.javaguides.springboot.payloads.AttendanceDTO;
import net.javaguides.springboot.payloads.LeaveDTO;

import java.util.List;


public interface AttendanceService {
    // create
    AttendanceDTO createAttendance(AttendanceDTO attendanceDTO);

    // update
    AttendanceDTO updateAttendance(AttendanceDTO attendanceDTO, Integer attendanceId);

    // delete
    void deleteAttendance(Integer attendanceId);

    // get
    AttendanceDTO getAttendance(Integer attendanceId);

    // get All
    List<AttendanceDTO> getAttendances();

    void createAttendanceFromLeave(LeaveDTO updatedLeave);
}
