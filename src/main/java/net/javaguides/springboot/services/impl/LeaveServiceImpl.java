package net.javaguides.springboot.services.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Leave;
import net.javaguides.springboot.payloads.LeaveDTO;
import net.javaguides.springboot.repository.LeaveRepository;
import net.javaguides.springboot.services.LeaveService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailService emailService; // Add this line

    @Autowired
    public LeaveServiceImpl(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }


    @Override
    public LeaveDTO createLeave(LeaveDTO leaveDTO) {
        Leave leave = this.modelMapper.map(leaveDTO, Leave.class);
        Leave addedLeave = this.leaveRepository.save(leave);
        return this.modelMapper.map(addedLeave, LeaveDTO.class);
    }

    @Override
    public LeaveDTO updateLeave(LeaveDTO leaveDTO, Integer leaveId) {
        Leave leave = this.leaveRepository.findById(leaveId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave ", "Leave Id", leaveId));

        leave.setStartDate(leaveDTO.getStartDate());
        leave.setEndDate(leaveDTO.getEndDate());
        leave.setLeaveReason(leaveDTO.getLeaveReason());
        leave.setLeaveType(leaveDTO.getLeaveType());
        leave.setLeaveStatus(leaveDTO.getLeaveStatus());

        Leave updatedLeave = this.leaveRepository.save(leave);
     /*   //Send email notification to the employee
        String employeeEmail = "amol.chilka@gmail.com"; // You need to adjust this according to your data structure
        String subject = "Leave Request Approved";
        String text = "Your leave request has been approved. Enjoy your time off!";
        emailService.sendLeaveApprovalEmail(employeeEmail, subject, text);*/
        return this.modelMapper.map(updatedLeave, LeaveDTO.class);
    }

    @Override
    public void deleteLeave(Integer leaveId) {
        Leave leave = this.leaveRepository.findById(leaveId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave ", "Leave id", leaveId));
        this.leaveRepository.delete(leave);
    }

    @Override
    public LeaveDTO getLeave(Integer leaveId) {
        Leave leave = this.leaveRepository.findById(leaveId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave", "Leave id", leaveId));
        return this.modelMapper.map(leave, LeaveDTO.class);
    }

    @Override
    public List<LeaveDTO> getLeaves() {
        List<Leave> leaves = this.leaveRepository.findAll();
        List<LeaveDTO> leaveDto = leaves.stream().map((leave) -> this.modelMapper.map(leave, LeaveDTO.class))
                .collect(Collectors.toList());
        return leaveDto;
    }
}
