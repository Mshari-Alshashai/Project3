package com.example.bank_system.Service;

import com.example.bank_system.Api.ApiException;
import com.example.bank_system.Model.MyUser;
import com.example.bank_system.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = authRepository.findMyUserByUsername(username);
        if (user == null) throw new ApiException("username or password is incorrect");
        return user;
    }
}
