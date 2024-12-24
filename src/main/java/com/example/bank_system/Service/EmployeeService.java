package com.example.bank_system.Service;

import com.example.bank_system.Api.ApiException;
import com.example.bank_system.DTO.EmployeeIDTO;
//import com.example.bank_system.Model.Employee;
import com.example.bank_system.Model.Employee;
import com.example.bank_system.Model.MyUser;
import com.example.bank_system.Repository.AuthRepository;
import com.example.bank_system.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;

    public void employeeRegister(EmployeeIDTO employeeIDTO) {
        Employee employee = new Employee();
        employee.setPosition(employeeIDTO.getPosition());
        employee.setSalary(employeeIDTO.getSalary());

        String hashPassword = new BCryptPasswordEncoder().encode(employeeIDTO.getPassword());

        MyUser user = new MyUser(null,employeeIDTO.getUsername(),hashPassword
                ,employeeIDTO.getName(),employeeIDTO.getEmail(),"EMPLOYEE",null,null);
        employee.setUser(user);

        employeeRepository.save(employee);
        authRepository.save(user);
    }

    public Employee getMyEmployee(Integer userId) {
        MyUser user = authRepository.findMyUserById(userId);
        if (user == null) throw new ApiException("user not found");
        if (user.getEmployee() == null) throw new ApiException("employee not found");

        return user.getEmployee();
    }

    public void updateEmployee(Integer userId,Integer employeeId, EmployeeIDTO employeeIDTO) {
        MyUser user = authRepository.findMyUserById(userId);
        Employee oldEmployee = employeeRepository.findEmployeeById(employeeId);
        if(oldEmployee == null)throw new ApiException("Employee not found");

        oldEmployee.setSalary(employeeIDTO.getSalary());
        oldEmployee.setPosition(employeeIDTO.getPosition());

        employeeRepository.save(oldEmployee);

    }

    public void deleteEmployee(Integer userId,Integer employeeId) {
        MyUser user = authRepository.findMyUserById(userId);
        Employee employee = employeeRepository.findEmployeeById(employeeId);
        if(employee == null)throw new ApiException("Employee not found");
        employeeRepository.delete(employee);
    }

}
