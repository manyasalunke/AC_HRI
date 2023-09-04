package net.javaguides.springboot.services;

import net.javaguides.springboot.payloads.PersonalDetailsDTO;

import java.util.List;

public interface PersonalDetailsService {
    PersonalDetailsDTO savePersonalDetails(PersonalDetailsDTO personalDetailsDTO);
    PersonalDetailsDTO getPersonalDetailsByEmployeeId(int employeeId);

    List<PersonalDetailsDTO> getAllPersonalDetails();

    PersonalDetailsDTO updatePersonalDetails(PersonalDetailsDTO personalDetailsDTO, int employeeId);

    void deletePersonalDetailsByEmployeeId(int employeeId);
    // Add other methods as needed
}