package com.mysite.sbb.domain.user.service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.domain.user.UserRole;
import com.mysite.sbb.domain.user.model.User;
import com.mysite.sbb.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public User create(String username, String email, String password){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);

        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }
    public User getUser(String username){
        Optional<User> optional_user = this.userRepository.findByUsername(username);
        if(optional_user.isPresent()){
            return optional_user.get();
        }
        else{
            throw new DataNotFoundException("user not found");
        }
    }

}
