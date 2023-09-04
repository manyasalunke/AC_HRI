package net.javaguides.springboot.services;

import net.javaguides.springboot.payloads.DepartmentDTO;

import java.util.List;

public interface DepartmentService {

    // create
    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);

    // update
    DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, Integer departmentId);

    // delete
    void deleteDepartment(Integer departmentId);

    // get
    DepartmentDTO getDepartment(Integer departmentId);

    // get All
    List<DepartmentDTO> getDepartments();
}
