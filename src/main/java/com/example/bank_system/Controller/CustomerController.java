package com.example.bank_system.Controller;

import com.example.bank_system.Api.ApiResponse;
import com.example.bank_system.DTO.CustomerIDTO;
import com.example.bank_system.Model.MyUser;
import com.example.bank_system.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity registerCustomer(@RequestBody @Valid CustomerIDTO customerIDTO) {
        customerService.customerRegister(customerIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Customer registered successfully"));
    }

    @GetMapping("/get")
    public ResponseEntity getCustomer(@AuthenticationPrincipal MyUser user) {
        return ResponseEntity.status(200).body(customerService.getMyCustomer(user.getId()));
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal MyUser user,@PathVariable Integer customerId, @RequestBody @Valid CustomerIDTO customerIDTO) {
        customerService.updateCustomer(user.getId(),customerId,customerIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Customer updated successfully"));
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal MyUser user,@PathVariable Integer customerId) {
        customerService.deleteCustomer(user.getId(),customerId);
        return ResponseEntity.status(200).body(new ApiResponse("Customer deleted successfully"));
    }


}
