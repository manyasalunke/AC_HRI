package net.javaguides.springboot.services.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Department;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.payloads.EmployeeDTO;
import net.javaguides.springboot.payloads.EmployeeResponse;
import net.javaguides.springboot.repository.DepartmentRepository;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.repository.RoleRepository;
import net.javaguides.springboot.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDto) {
        Employee employee = this.dtoToEmployee(employeeDto);
        // Get the Role entity based on the roleId from the DTO
        Role role = roleRepository.findById(employeeDto.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role", "Id", employeeDto.getRoleId()));

        // Add the role to the roles set of the employee
        employee.getRoles().add(role);
        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(employeeDto.getPassword());
        employee.setPassword(encodedPassword);

        Employee savedEmployee = this.employeeRepository.save(employee);
        return this.employeeToDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDto, Integer employeeId) {
        Employee employee = this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId));

        // Update employee details
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setSecondName(employeeDto.getSecondName()); // Add this line to update secondName
        employee.setUserName(employeeDto.getUserName());
        employee.setEmailId(employeeDto.getEmailId());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.SetReportingManager(employeeDto.getReportingManager());
        employee.setReportingManagerMail(employeeDto.getReportingManagerMail());
        employee.setShift_code(employeeDto.getShift_code());
        employee.setBusinessUnit(employeeDto.getBusinessUnit());
        employee.setLocation(employeeDto.getLocation());
        employee.setTypeOfEmployment(employeeDto.getTypeOfEmployment());
        employee.setEmployeeStatus(employeeDto.getEmployeeStatus());
        employee.setRepoManager(employeeDto.getRepoManager());
//        employee.setIsReportingManager(employeeDto.getIsReportingManager());


        // Check if the password is changed, and then encode and update the password
        if (!employeeDto.getPassword().equals(employee.getPassword())) {
            String encodedPassword = passwordEncoder.encode(employeeDto.getPassword());
            employee.setPassword(encodedPassword);
        }

        employee.setAddress(employeeDto.getAddress());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());


        employee.setDateOfJoining(employeeDto.getDateOfJoining());
        employee.setDateOfConfirmation(employeeDto.getDateOfConfirmation());

        // Get the Department entity based on the departmentId from the DTO
        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", "Id", employeeDto.getDepartmentId()));

        // Get the Role entity based on the roleId from the DTO
        Role role = roleRepository.findById(employeeDto.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role", "Id", employeeDto.getRoleId()));

        // Update the department and role of the employee
        employee.setDepartment(department);
        employee.setRole(role);

        // Update the roles set of the associated Role entities
        Set<Role> rolesToUpdate = new HashSet<>();
        rolesToUpdate.add(role);
        for (Role existingRole : employee.getRoles()) {
            if (!existingRole.equals(role)) {
                existingRole.getEmployees().remove(employee);
                rolesToUpdate.add(existingRole);
            }
        }

        // Save the changes to the roles
        roleRepository.saveAll(rolesToUpdate);

        Employee updatedEmployee = this.employeeRepository.save(employee);
        return this.employeeToDTO(updatedEmployee);
    }



    @Override
    public EmployeeDTO getEmployeeById(Integer employeeId) {
        Employee employee = this.employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", employeeId));
        return this.employeeToDTO(employee);
    }

    @Override
    public EmployeeResponse getAllEmployees(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable paging = PageRequest.of(pageNumber, pageSize, sort);
        Page<Employee> pagesEmployee = this.employeeRepository.findAll(paging);
        List<Employee> employees = pagesEmployee.getContent();
        List<EmployeeDTO> EmployeesDto = employees.stream().map(employee -> this.employeeToDTO(employee)).collect(Collectors.toList());

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeData(EmployeesDto);
        employeeResponse.setPageNumber(pagesEmployee.getNumber());
        employeeResponse.setPageSize(pagesEmployee.getSize());
        employeeResponse.setTotalElements(pagesEmployee.getTotalElements());
        employeeResponse.setTotalPages(pagesEmployee.getTotalPages());
        employeeResponse.setLastPage(pagesEmployee.isLast());

        return employeeResponse;
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        // Find the employee by ID
        Employee employee = this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId));

        // Remove the employee from the employee_role table
        Set<Role> roles = employee.getRoles();
        for (Role role : roles) {
            role.getEmployees().remove(employee);
        }

        // Clear the employee's roles
        employee.getRoles().clear();

        // Save the changes to the role and employee_role tables
        this.roleRepository.saveAll(roles);

        // Delete the employee from the employee table
        this.employeeRepository.delete(employee);
    }


    private Employee dtoToEmployee(EmployeeDTO employeeDto) {
        Employee employee = this.modelMapper.map(employeeDto, Employee.class);
        return employee;
    }

    private EmployeeDTO employeeToDTO(Employee employee) {
        EmployeeDTO employeeDto = this.modelMapper.map(employee, EmployeeDTO.class);
        return employeeDto;
    }

    @Override
    public EmployeeDTO assignDepartment(int departmentId, int employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        Department department = departmentRepository.findById(departmentId).orElse(null);

        if (employee != null && department != null) {
            employee.setDepartment(department);
            employeeRepository.save(employee);
        }
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> searchEmployee(String keyword) {
        List<Employee> employees = this.employeeRepository.searchByfirstName("%" + keyword + "%");
        System.out.println("Data is: " +employees);
        List<EmployeeDTO> employeeDTO = employees.stream().map((employee) -> this.modelMapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
        System.out.println(employeeDTO);
        return employeeDTO;
    }
}
