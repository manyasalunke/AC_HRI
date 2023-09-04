package net.javaguides.springboot.controller;

import jakarta.validation.Valid;
import net.javaguides.springboot.payloads.ApiResponse;
import net.javaguides.springboot.payloads.SalaryDTO;
import net.javaguides.springboot.services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/salaries")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    // create salary
    @PostMapping("/")
    public ResponseEntity<SalaryDTO> createSalary(@Valid @RequestBody SalaryDTO salaryDTO) {
        SalaryDTO createSalary = this.salaryService.createSalary(salaryDTO);
        return new ResponseEntity<>(createSalary, HttpStatus.CREATED);
    }

    // update salary
    @PutMapping("/{salaryId}")
    public ResponseEntity<SalaryDTO> updateSalary(@Valid @RequestBody SalaryDTO salaryDTO, @PathVariable int salaryId) {
        SalaryDTO updatedSalary = this.salaryService.updateSalary(salaryDTO, salaryId);
        return new ResponseEntity<>(updatedSalary, HttpStatus.CREATED);
    }

    // delete salary
    @DeleteMapping("/{salaryId}")
    public ResponseEntity<ApiResponse> deleteLeave(@PathVariable int salaryId) {
        this.salaryService.deleteSalary(salaryId);
        return new ResponseEntity<>(new ApiResponse("Salary is deleted successfully !!", true),
                HttpStatus.OK);
    }

    // get salary
    @GetMapping("/{salaryId}")
    public ResponseEntity<SalaryDTO> getLeave(@PathVariable int salaryId) {
        SalaryDTO salaryDto = this.salaryService.getSalary(salaryId);
        return new ResponseEntity<>(salaryDto, HttpStatus.OK);
    }

    // get all salaries
    @GetMapping("/")
    public ResponseEntity<List<SalaryDTO>> getLeaves() {
        List<SalaryDTO> leaves = this.salaryService.getSalaries();
        return ResponseEntity.ok(leaves);
    }
}
