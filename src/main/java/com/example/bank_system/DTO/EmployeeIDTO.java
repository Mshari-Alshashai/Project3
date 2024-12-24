package com.example.bank_system.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeIDTO {

    @NotEmpty(message = "username is required")
    @Size(min = 4, max = 10,message = "username must be between 4 and 10")
    private String username;

    @Size(min = 6,message = "password size must be more than 6")
    @NotEmpty(message = "password is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])[A-Za-z\\d@#$%^&+=!]{8,}$")
    private String password;

    @Size(min = 2, max = 20,message = "name size must be between 2 and 20")
    @NotEmpty(message = "name is required")
    private String name;

    @Email(message = "Email must be in the right form")
    private String email;

    @NotEmpty(message = "position is required")
    private String position;

    @NotNull(message = "salary is required")
    @Positive(message = "salary must be positive")
    private Integer salary;
}
