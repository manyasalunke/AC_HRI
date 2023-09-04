package net.javaguides.springboot.controller;

import jakarta.validation.Valid;
import net.javaguides.springboot.config.AppConstants;
import net.javaguides.springboot.payloads.ApiResponse;
import net.javaguides.springboot.payloads.EmployeeDTO;
import net.javaguides.springboot.payloads.EmployeeResponse;
import net.javaguides.springboot.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // POST-create employee
    @PostMapping("/")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployeeDto = this.employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(createdEmployeeDto, HttpStatus.CREATED);
    }

    // PUT- update employee
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable int id) {
        EmployeeDTO updatedEmployee = this.employeeService.updateEmployee(employeeDTO, id);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.CREATED);
    }

    // DELETE -delete user
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id) {
        this.employeeService.deleteEmployee(id);
        return new ResponseEntity<>(new ApiResponse("Employee deleted Successfully", true), HttpStatus.OK);
    }

    // GET - employee get all
    @GetMapping("/")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<EmployeeResponse> getAllEmployees(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir)
    {
        EmployeeResponse employeeResponse = this.employeeService.getAllEmployees(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<EmployeeResponse>(employeeResponse, HttpStatus.OK);
    }

    // GET - employee get
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getSingleEmployee(@PathVariable int id) {
        return ResponseEntity.ok(this.employeeService.getEmployeeById(id));
    }

    // Search employee
    // search
    @GetMapping("/search/{keywords}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<EmployeeDTO>> searchPostByTitle(@PathVariable("keywords") String keywords) {
        System.out.println(keywords);
        List<EmployeeDTO> result = this.employeeService.searchEmployee(keywords);
        return new ResponseEntity<List<EmployeeDTO>>(result, HttpStatus.OK);
    }
}
