package com.userapp.service;

import com.userapp.dto.PersonalDetailsDTO;
import com.userapp.model.PersonalDetails;
import com.userapp.model.User;
import com.userapp.repository.PersonalDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonalDetailsService {

    private final PersonalDetailsRepository personalDetailsRepository;

    public PersonalDetails savePersonalDetails(User user, PersonalDetailsDTO detailsDTO) {
        if (detailsDTO.getDateOfBirth().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }

        Optional<PersonalDetails> existingDetails = personalDetailsRepository.findByUser(user);

        PersonalDetails details;
        if (existingDetails.isPresent()) {
            details = existingDetails.get();
            details.setFirstName(detailsDTO.getFirstName());
            details.setLastName(detailsDTO.getLastName());
            details.setDateOfBirth(detailsDTO.getDateOfBirth());
            details.setGender(detailsDTO.getGender());
        } else {
            details = new PersonalDetails();
            details.setUser(user);
            details.setFirstName(detailsDTO.getFirstName());
            details.setLastName(detailsDTO.getLastName());
            details.setDateOfBirth(detailsDTO.getDateOfBirth());
            details.setGender(detailsDTO.getGender());
        }

        return personalDetailsRepository.save(details);
    }

    public Optional<PersonalDetails> getPersonalDetails(User user) {
        return personalDetailsRepository.findByUser(user);
    }

}
