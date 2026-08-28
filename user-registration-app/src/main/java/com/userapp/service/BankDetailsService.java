package com.userapp.service;

import com.userapp.dto.BankDetailsDTO;
import com.userapp.model.BankDetails;
import com.userapp.model.User;
import com.userapp.repository.BankDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BankDetailsService {

    private final BankDetailsRepository bankDetailsRepository;

    public BankDetails saveBankDetails(User user, BankDetailsDTO detailsDTO) {
        Optional<BankDetails> existingDetails = bankDetailsRepository.findByUser(user);

        BankDetails details;
        if (existingDetails.isPresent()) {
            details = existingDetails.get();
            details.setAccountNumber(detailsDTO.getAccountNumber());
            details.setAccountType(detailsDTO.getAccountType());
            details.setIFSCCode(detailsDTO.getIFSCCode());
            details.setBankName(detailsDTO.getBankName());
        } else {
            details = new BankDetails();
            details.setUser(user);
            details.setAccountNumber(detailsDTO.getAccountNumber());
            details.setAccountType(detailsDTO.getAccountType());
            details.setIFSCCode(detailsDTO.getIFSCCode());
            details.setBankName(detailsDTO.getBankName());
        }

        return bankDetailsRepository.save(details);
    }

    public Optional<BankDetails> getBankDetails(User user) {
        return bankDetailsRepository.findByUser(user);
    }

}
