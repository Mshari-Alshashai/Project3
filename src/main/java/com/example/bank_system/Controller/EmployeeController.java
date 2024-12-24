package com.example.bank_system.Controller;

import com.example.bank_system.Api.ApiResponse;
import com.example.bank_system.DTO.EmployeeIDTO;
import com.example.bank_system.Model.Employee;
import com.example.bank_system.Model.MyUser;
import com.example.bank_system.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity registerEmployee(@RequestBody @Valid EmployeeIDTO employeeIDTO) {
        employeeService.employeeRegister(employeeIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Employee registered successfully"));
    }

    @GetMapping("/get")
    public ResponseEntity getEmployees(@AuthenticationPrincipal MyUser user) {
        return ResponseEntity.status(200).body(employeeService.getMyEmployee(user.getId()));
    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity updateEmployee(@AuthenticationPrincipal MyUser user,@PathVariable Integer employeeId, @RequestBody @Valid EmployeeIDTO employeeIDTO) {
        employeeService.updateEmployee(user.getId(), employeeId, employeeIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Employee updated successfully"));
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity deleteEmployee(@AuthenticationPrincipal MyUser user, @PathVariable Integer employeeId) {
        employeeService.deleteEmployee(user.getId(), employeeId);
        return ResponseEntity.status(200).body(new ApiResponse("Employee deleted successfully"));
    }


}
