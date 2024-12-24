package com.example.bank_system.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerIDTO {

    @NotEmpty(message = "username is required")
    @Size(min = 4, max = 10, message = "username must be between 4 and 10")
    private String username;

    @Size(min = 6,message = "password size must be more than 6")
    @NotEmpty(message = "password is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])[A-Za-z\\d@#$%^&+=!]{8,}$",message = "write 8 characters and one uppercase and one lowercase and a digit and a special character")
    private String password;

    @Size(min = 2, max = 20, message = "name size must be between 2 and 20")
    @NotEmpty(message = "name is required")
    private String name;

    @Email(message = "Email must be in the right form")
    private String email;

    @NotEmpty(message = "phone number is required")
    @Pattern(regexp = "^05\\d{8}$",message = "phone number should be 10 digits and starts with 05")
    private String phoneNumber;
}
