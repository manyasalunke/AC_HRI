package net.javaguides.springboot.services.impl;

import net.javaguides.springboot.model.PersonalDetails;
import net.javaguides.springboot.payloads.PersonalDetailsDTO;
import net.javaguides.springboot.repository.PersonalDetailsRepository;
import net.javaguides.springboot.services.PersonalDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalDetailsServiceImpl implements PersonalDetailsService {

    private final PersonalDetailsRepository personalDetailsRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PersonalDetailsServiceImpl(PersonalDetailsRepository personalDetailsRepository, ModelMapper modelMapper) {
        this.personalDetailsRepository = personalDetailsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PersonalDetailsDTO savePersonalDetails(PersonalDetailsDTO personalDetailsDTO) {
        PersonalDetails personalDetails = modelMapper.map(personalDetailsDTO, PersonalDetails.class);
        PersonalDetails savedPersonalDetails = personalDetailsRepository.save(personalDetails);
        return modelMapper.map(savedPersonalDetails, PersonalDetailsDTO.class);
    }

    @Override
    public PersonalDetailsDTO getPersonalDetailsByEmployeeId(int employeeId) {
        PersonalDetails personalDetails = personalDetailsRepository.findByEmployeeId(employeeId);

        if (personalDetails == null) {
            throw new EntityNotFoundException("Personal details not found for employee with ID: " + employeeId);
        }

        return modelMapper.map(personalDetails, PersonalDetailsDTO.class);
    }

    @Override
    public List<PersonalDetailsDTO> getAllPersonalDetails() {
        List<PersonalDetails> allPersonalDetails = personalDetailsRepository.findAll();
        return allPersonalDetails.stream()
                .map(personalDetails -> modelMapper.map(personalDetails, PersonalDetailsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PersonalDetailsDTO updatePersonalDetails(PersonalDetailsDTO personalDetailsDTO, int employeeId) {
        PersonalDetails existingPersonalDetails = personalDetailsRepository.findByEmployeeId(employeeId);

        if (existingPersonalDetails == null) {
            throw new EntityNotFoundException("Personal details not found for employee with ID: " + employeeId);
        }

        // Update the fields from the DTO
        existingPersonalDetails.setMaritalStatus(personalDetailsDTO.getMaritalStatus());
        existingPersonalDetails.setDateOfMarriage(personalDetailsDTO.getDateOfMarriage());
        existingPersonalDetails.setEmployeePersonalPhoneNumber(personalDetailsDTO.getEmployeePersonalPhoneNumber());
        existingPersonalDetails.setEmployeeEmergencyContactNumber(personalDetailsDTO.getEmployeeEmergencyContactNumber());
        // ... update other fields

        // Save the updated personal details
        PersonalDetails updatedPersonalDetails = personalDetailsRepository.save(existingPersonalDetails);

        return modelMapper.map(updatedPersonalDetails, PersonalDetailsDTO.class);
    }

    @Override
    public void deletePersonalDetailsByEmployeeId(int employeeId) {
        PersonalDetails existingPersonalDetails = personalDetailsRepository.findByEmployeeId(employeeId);

        if (existingPersonalDetails == null) {
            throw new EntityNotFoundException("Personal details not found for employee with ID: " + employeeId);
            // You can also return an error response instead of throwing an exception
            // For example: throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personal details not found for employee with ID: " + employeeId);
        }

        personalDetailsRepository.delete(existingPersonalDetails);
    }

    // Implement other methods as needed
}
