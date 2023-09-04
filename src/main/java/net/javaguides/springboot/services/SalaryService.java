package net.javaguides.springboot.services;

import net.javaguides.springboot.payloads.SalaryDTO;
import java.util.List;

public interface SalaryService {

    // create
    SalaryDTO createSalary(SalaryDTO salaryDTO);

    // update
    SalaryDTO updateSalary(SalaryDTO salaryDTO, Integer salaryId);

    // delete
    void deleteSalary(Integer salaryId);

    // get
    SalaryDTO getSalary(Integer salaryId);

    // get All
    List<SalaryDTO> getSalaries();
}
