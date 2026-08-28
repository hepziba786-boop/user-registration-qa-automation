package com.userapp.controller;

import com.userapp.dto.PersonalDetailsDTO;
import com.userapp.dto.BankDetailsDTO;
import com.userapp.dto.AddressDetailsDTO;
import com.userapp.model.User;
import com.userapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/details")
public class DetailsController {

    private final UserService userService;
    private final PersonalDetailsService personalDetailsService;
    private final BankDetailsService bankDetailsService;
    private final AddressDetailsService addressDetailsService;

    private User getCurrentUser(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            throw new IllegalStateException("User not logged in");
        }
        return userService.getUserById(userId);
    }

    @GetMapping("/personal")
    public String showPersonalDetailsPage(Model model, HttpSession session) {
        try {
            User user = getCurrentUser(session);
            var existingDetails = personalDetailsService.getPersonalDetails(user);

            if (existingDetails.isPresent()) {
                PersonalDetailsDTO dto = new PersonalDetailsDTO();
                dto.setFirstName(existingDetails.get().getFirstName());
                dto.setLastName(existingDetails.get().getLastName());
                dto.setDateOfBirth(existingDetails.get().getDateOfBirth());
                dto.setGender(existingDetails.get().getGender());
                model.addAttribute("personalDetailsDTO", dto);
            } else {
                model.addAttribute("personalDetailsDTO", new PersonalDetailsDTO());
            }
            return "personal-details";
        } catch (IllegalStateException e) {
            return "redirect:/login";
        }
    }

    @PostMapping("/personal")
    public String savePersonalDetails(@Valid @ModelAttribute("personalDetailsDTO") PersonalDetailsDTO detailsDTO,
                                      BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "personal-details";
        }

        try {
            User user = getCurrentUser(session);
            personalDetailsService.savePersonalDetails(user, detailsDTO);
            model.addAttribute("successMessage", "Personal details saved");
            return "redirect:/dashboard";
        } catch (IllegalStateException e) {
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessages", e.getMessage());
            return "personal-details";
        }
    }

    @GetMapping("/bank")
    public String showBankDetailsPage(Model model, HttpSession session) {
        try {
            User user = getCurrentUser(session);
            var existingDetails = bankDetailsService.getBankDetails(user);

            if (existingDetails.isPresent()) {
                BankDetailsDTO dto = new BankDetailsDTO();
                dto.setAccountNumber(existingDetails.get().getAccountNumber());
                dto.setAccountType(existingDetails.get().getAccountType());
                dto.setIFSCCode(existingDetails.get().getIFSCCode());
                dto.setBankName(existingDetails.get().getBankName());
                model.addAttribute("bankDetailsDTO", dto);
            } else {
                model.addAttribute("bankDetailsDTO", new BankDetailsDTO());
            }
            return "bank-details";
        } catch (IllegalStateException e) {
            return "redirect:/login";
        }
    }

    @PostMapping("/bank")
    public String saveBankDetails(@Valid @ModelAttribute("bankDetailsDTO") BankDetailsDTO detailsDTO,
                                  BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "bank-details";
        }

        try {
            User user = getCurrentUser(session);
            bankDetailsService.saveBankDetails(user, detailsDTO);
            model.addAttribute("successMessage", "Bank details saved");
            return "redirect:/dashboard";
        } catch (IllegalStateException e) {
            return "redirect:/login";
        }
    }

    @GetMapping("/address")
    public String showAddressDetailsPage(Model model, HttpSession session) {
        try {
            User user = getCurrentUser(session);
            var existingDetails = addressDetailsService.getAddressDetails(user);

            if (existingDetails.isPresent()) {
                AddressDetailsDTO dto = new AddressDetailsDTO();
                dto.setStreet(existingDetails.get().getStreet());
                dto.setCity(existingDetails.get().getCity());
                dto.setState(existingDetails.get().getState());
                dto.setPincode(existingDetails.get().getPincode());
                dto.setCountry(existingDetails.get().getCountry());
                model.addAttribute("addressDetailsDTO", dto);
            } else {
                model.addAttribute("addressDetailsDTO", new AddressDetailsDTO());
            }
            return "address-details";
        } catch (IllegalStateException e) {
            return "redirect:/login";
        }
    }

    @PostMapping("/address")
    public String saveAddressDetails(@Valid @ModelAttribute("addressDetailsDTO") AddressDetailsDTO detailsDTO,
                                     BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "address-details";
        }

        try {
            User user = getCurrentUser(session);
            addressDetailsService.saveAddressDetails(user, detailsDTO);
            model.addAttribute("successMessage", "Address details saved");
            return "redirect:/dashboard";
        } catch (IllegalStateException e) {
            return "redirect:/login";
        }
    }

}
