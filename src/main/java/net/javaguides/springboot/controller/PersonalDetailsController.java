package net.javaguides.springboot.controller;

import net.javaguides.springboot.payloads.PersonalDetailsDTO;
import net.javaguides.springboot.services.PersonalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/personal-details")
public class PersonalDetailsController {

    private final PersonalDetailsService personalDetailsService;

    @Autowired
    public PersonalDetailsController(PersonalDetailsService personalDetailsService) {
        this.personalDetailsService = personalDetailsService;
    }

    @PostMapping("/")
    public ResponseEntity<PersonalDetailsDTO> savePersonalDetails(@Valid @RequestBody PersonalDetailsDTO personalDetailsDTO) {
        PersonalDetailsDTO savedPersonalDetails = personalDetailsService.savePersonalDetails(personalDetailsDTO);
        return ResponseEntity.ok(savedPersonalDetails);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<PersonalDetailsDTO> getPersonalDetailsByEmployeeId(@PathVariable int employeeId) {
        PersonalDetailsDTO personalDetails = personalDetailsService.getPersonalDetailsByEmployeeId(employeeId);
        if (personalDetails == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personalDetails);
    }

    @GetMapping("/")
    public ResponseEntity<List<PersonalDetailsDTO>> getAllPersonalDetails() {
        List<PersonalDetailsDTO> allPersonalDetails = personalDetailsService.getAllPersonalDetails();
        return ResponseEntity.ok(allPersonalDetails);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<PersonalDetailsDTO> updatePersonalDetails(
            @Valid @RequestBody PersonalDetailsDTO personalDetailsDTO, @PathVariable int employeeId) {
        PersonalDetailsDTO updatedPersonalDetails = personalDetailsService.updatePersonalDetails(personalDetailsDTO, employeeId);
        if (updatedPersonalDetails == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPersonalDetails);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deletePersonalDetailsByEmployeeId(@PathVariable int employeeId) {
        personalDetailsService.deletePersonalDetailsByEmployeeId(employeeId);
        return ResponseEntity.noContent().build();
    }
}
