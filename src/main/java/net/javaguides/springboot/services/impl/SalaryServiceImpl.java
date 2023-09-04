package net.javaguides.springboot.services.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Salary;
import net.javaguides.springboot.payloads.SalaryDTO;
import net.javaguides.springboot.repository.SalaryRepository;
import net.javaguides.springboot.services.SalaryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public SalaryServiceImpl(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }


    @Override
    public SalaryDTO createSalary(SalaryDTO salaryDTO) {
        Salary salary = this.modelMapper.map(salaryDTO, Salary.class);
        Salary addedSalary = this.salaryRepository.save(salary);
        return this.modelMapper.map(addedSalary, SalaryDTO.class);
    }

    @Override
    public SalaryDTO updateSalary(SalaryDTO salaryDTO, Integer salaryId) {
        Salary salary = this.salaryRepository.findById(salaryId)
                .orElseThrow(() -> new ResourceNotFoundException("Salary ", "Salary Id", salaryId));

        salary.setMonth(salaryDTO.getMonth());
        salary.setYear(salaryDTO.getYear());
        salary.setBasicSalary(salaryDTO.getBasicSalary());
        salary.setAllowances(salaryDTO.getAllowances());
        salary.setDeduction(salaryDTO.getDeduction());
        salary.setNetSalary(salaryDTO.getNetSalary());

        Salary UpdatedSalary = this.salaryRepository.save(salary);
        return this.modelMapper.map(UpdatedSalary, SalaryDTO.class);
    }

    @Override
    public void deleteSalary(Integer salaryId) {
        Salary salary = this.salaryRepository.findById(salaryId)
                .orElseThrow(() -> new ResourceNotFoundException("Salary ", "Salary id", salaryId));
        this.salaryRepository.delete(salary);
    }

    @Override
    public SalaryDTO getSalary(Integer salaryId) {
        Salary salary = this.salaryRepository.findById(salaryId)
                .orElseThrow(() -> new ResourceNotFoundException("Salary", "Salary id", salaryId));
        return this.modelMapper.map(salary, SalaryDTO.class);
    }

    @Override
    public List<SalaryDTO> getSalaries() {
        List<Salary> salaries = this.salaryRepository.findAll();
        List<SalaryDTO> salaryDto = salaries.stream().map((salary) -> this.modelMapper.map(salary, SalaryDTO.class))
                .collect(Collectors.toList());
        return salaryDto;
    }
}
