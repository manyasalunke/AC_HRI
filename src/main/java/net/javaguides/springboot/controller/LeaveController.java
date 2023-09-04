package net.javaguides.springboot.controller;

import jakarta.validation.Valid;
import net.javaguides.springboot.payloads.ApiResponse;
import net.javaguides.springboot.payloads.LeaveDTO;
import net.javaguides.springboot.services.AttendanceService;
import net.javaguides.springboot.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/leaves")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;
    @Autowired
    private AttendanceService attendanceService;

    // create leave
    @PostMapping("/")
    public ResponseEntity<LeaveDTO> createLeave(@Valid @RequestBody LeaveDTO leaveDTO) {
        LeaveDTO createLeave = this.leaveService.createLeave(leaveDTO);
        return new ResponseEntity<>(createLeave, HttpStatus.CREATED);
    }

    // update leave
    @PutMapping("/{leaveId}")
    public ResponseEntity<LeaveDTO> updateLeave(@Valid @RequestBody LeaveDTO leaveDTO, @PathVariable int leaveId) {
        LeaveDTO updatedLeave = this.leaveService.updateLeave(leaveDTO, leaveId);
        this.attendanceService.createAttendanceFromLeave(updatedLeave);
        return new ResponseEntity<>(updatedLeave, HttpStatus.CREATED);
    }

    // delete admin
    @DeleteMapping("/{leaveId}")
    public ResponseEntity<ApiResponse> deleteLeave(@PathVariable int leaveId) {
        this.leaveService.deleteLeave(leaveId);
        return new ResponseEntity<>(new ApiResponse("Leave is deleted successfully !!", true),
                HttpStatus.OK);
    }

    // get leave
    @GetMapping("/{leaveId}")
    public ResponseEntity<LeaveDTO> getLeave(@PathVariable int leaveId) {
        LeaveDTO leaveDto = this.leaveService.getLeave(leaveId);
        return new ResponseEntity<>(leaveDto, HttpStatus.OK);
    }

    // get all leaves
    @GetMapping("/")
    public ResponseEntity<List<LeaveDTO>> getLeaves() {
        List<LeaveDTO> leaves = this.leaveService.getLeaves();
        return ResponseEntity.ok(leaves);
    }
}
