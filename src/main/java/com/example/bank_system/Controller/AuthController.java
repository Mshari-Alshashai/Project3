package com.example.bank_system.Controller;

import com.example.bank_system.Api.ApiResponse;
import com.example.bank_system.DTO.CustomerIDTO;
import com.example.bank_system.DTO.EmployeeIDTO;
import com.example.bank_system.Model.MyUser;
import com.example.bank_system.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

}
