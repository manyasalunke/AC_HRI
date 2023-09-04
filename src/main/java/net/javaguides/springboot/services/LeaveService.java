package net.javaguides.springboot.services;

import net.javaguides.springboot.payloads.LeaveDTO;

import java.util.List;

public interface LeaveService {

    // create
    LeaveDTO createLeave(LeaveDTO leaveDTO);

    // update
    LeaveDTO updateLeave(LeaveDTO leaveDTO, Integer leaveId);

    // delete
    void deleteLeave(Integer leaveId);

    // get
    LeaveDTO getLeave(Integer leaveId);

    // get All
    List<LeaveDTO> getLeaves();
}
