package com.userapp.dto;

import lombok.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDetailsDTO {

    @NotBlank(message = "Account number is required")
    @Pattern(regexp = "^[0-9]{10,18}$", message = "Account number must be between 10 and 18 digits")
    private String accountNumber;

    @NotBlank(message = "Account type is required")
    private String accountType;

    @NotBlank(message = "IFSC code is required")
    @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "IFSC code must be in format: AAAA0001234")
    private String IFSCCode;

    @NotBlank(message = "Bank name is required")
    @Size(min = 2, max = 100, message = "Bank name must be between 2 and 100 characters")
    private String bankName;

}
