package net.javaguides.springboot.controller;

import jakarta.validation.Valid;
import net.javaguides.springboot.payloads.ApiResponse;
import net.javaguides.springboot.payloads.DepartmentDTO;
import net.javaguides.springboot.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // create department
    @PostMapping("/")
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO createDepartment = this.departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(createDepartment, HttpStatus.CREATED);
    }

    // update department
    @PutMapping("/{deptId}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@Valid @RequestBody DepartmentDTO departmentDTO, @PathVariable int deptId) {
        DepartmentDTO updatedDepartment = this.departmentService.updateDepartment(departmentDTO, deptId);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.CREATED);
    }

    // delete department
    @DeleteMapping("/{deptId}")
    public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable int deptId) {
        this.departmentService.deleteDepartment(deptId);
        return new ResponseEntity<>(new ApiResponse("Department is deleted successfully !!", true),
                HttpStatus.OK);
    }

    // get department
    @GetMapping("/{deptId}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable int deptId) {
        DepartmentDTO departmentDto = this.departmentService.getDepartment(deptId);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    // get all departments
    @GetMapping("/")
    public ResponseEntity<List<DepartmentDTO>> getDepartments() {
        List<DepartmentDTO> departments = this.departmentService.getDepartments();
        return ResponseEntity.ok(departments);
    }

}
