package net.javaguides.springboot.services.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Department;
import net.javaguides.springboot.payloads.DepartmentDTO;
import net.javaguides.springboot.repository.DepartmentRepository;
import net.javaguides.springboot.services.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = this.modelMapper.map(departmentDTO, Department.class);
        Department addedDepartment = this.departmentRepository.save(department);
        return this.modelMapper.map(addedDepartment, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, Integer departmentId) {
        Department department = this.departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department ", "Department Id", departmentId));

        department.setDepartmentName((departmentDTO.getDepartmentName()));
        department.setDepartmentDescription(departmentDTO.getDepartmentDescription());

        Department updatedDepartment = this.departmentRepository.save(department);
        return this.modelMapper.map(updatedDepartment, DepartmentDTO.class);
    }

    @Override
    public void deleteDepartment(Integer departmentId) {
        Department department = this.departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department ", "Department id", departmentId));
        this.departmentRepository.delete(department);
    }

    @Override
    public DepartmentDTO getDepartment(Integer departmentId) {
        Department department = this.departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "Department id", departmentId));
        return this.modelMapper.map(department, DepartmentDTO.class);
    }

    @Override
    public List<DepartmentDTO> getDepartments() {
        List<Department> departments = this.departmentRepository.findAll();
        List<DepartmentDTO> departmentsDto = departments.stream().map((department) -> this.modelMapper.map(department, DepartmentDTO.class))
                .collect(Collectors.toList());
        return departmentsDto;
    }
}
