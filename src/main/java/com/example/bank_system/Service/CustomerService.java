package com.example.bank_system.Service;

import com.example.bank_system.Api.ApiException;
import com.example.bank_system.DTO.CustomerIDTO;
import com.example.bank_system.Model.Customer;
import com.example.bank_system.Model.MyUser;
import com.example.bank_system.Repository.AccountRepository;
import com.example.bank_system.Repository.AuthRepository;
import com.example.bank_system.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    public void customerRegister(CustomerIDTO customerIDTO) {
        Customer customer = new Customer();
        customer.setPhoneNumber( customerIDTO.getPhoneNumber());

        String hashPassword = new BCryptPasswordEncoder().encode(customerIDTO.getPassword());

        MyUser user = new MyUser(null,customerIDTO.getUsername(),hashPassword
                ,customerIDTO.getName(),customerIDTO.getEmail(),"CUSTOMER",null,null);
        customer.setUser(user);

        customerRepository.save(customer);
        authRepository.save(user);
    }

    public Customer getMyCustomer(Integer userId) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");
        if(user.getCustomer() == null) throw new ApiException("customer not found");

        return user.getCustomer();
    }

    public void updateCustomer(Integer userId,Integer customerId,CustomerIDTO customerIDTO) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");
        Customer oldCustomer = customerRepository.findCustomerById(customerId);
        if(oldCustomer == null) throw new ApiException("customer not found");

        oldCustomer.setPhoneNumber( customerIDTO.getPhoneNumber());

        customerRepository.save(oldCustomer);
    }

    public void deleteCustomer(Integer userId,Integer customerId) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");
        Customer oldCustomer = customerRepository.findCustomerById(customerId);
        if(oldCustomer == null) throw new ApiException("customer not found");
        oldCustomer.setUser(null);
        customerRepository.delete(oldCustomer);
    }

}
