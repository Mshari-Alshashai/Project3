package com.example.bank_system.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountIDTO {

    @NotEmpty(message = "account number is required")
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$")
    private String accountNumber;

    @NotNull(message = "balance is required")
    @Positive(message = "balance must be positive")
    private Integer balance;
}
