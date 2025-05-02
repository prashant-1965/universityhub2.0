package com.university.security.service;

import com.university.security.Repository.UserRepo;
import com.university.security.entity.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Credentials credentials = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Credentials not found"));
        return new User(
                credentials.getUsername(),
                credentials.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + credentials.getRole()))
        );
    }

    @Override
    public boolean addCredentials(Credentials credentials) {
        userRepo.save(credentials);
        return true;
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepo.findByUsername(username).isPresent();
    }
}
