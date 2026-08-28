package com.userapp.service;

import com.userapp.dto.AddressDetailsDTO;
import com.userapp.model.AddressDetails;
import com.userapp.model.User;
import com.userapp.repository.AddressDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressDetailsService {

    private final AddressDetailsRepository addressDetailsRepository;

    public AddressDetails saveAddressDetails(User user, AddressDetailsDTO detailsDTO) {
        Optional<AddressDetails> existingDetails = addressDetailsRepository.findByUser(user);

        AddressDetails details;
        if (existingDetails.isPresent()) {
            details = existingDetails.get();
            details.setStreet(detailsDTO.getStreet());
            details.setCity(detailsDTO.getCity());
            details.setState(detailsDTO.getState());
            details.setPincode(detailsDTO.getPincode());
            details.setCountry(detailsDTO.getCountry());
        } else {
            details = new AddressDetails();
            details.setUser(user);
            details.setStreet(detailsDTO.getStreet());
            details.setCity(detailsDTO.getCity());
            details.setState(detailsDTO.getState());
            details.setPincode(detailsDTO.getPincode());
            details.setCountry(detailsDTO.getCountry());
        }

        return addressDetailsRepository.save(details);
    }

    public Optional<AddressDetails> getAddressDetails(User user) {
        return addressDetailsRepository.findByUser(user);
    }

}
