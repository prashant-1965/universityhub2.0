package com.university.security.services;
import com.university.security.entity.Credentials;
import com.university.security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

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
    public String addCredentials(Credentials credentials) {
        if(this.existByUsername(credentials.getUsername())){
            return "Username already exist";
        }
        String encoded = passwordEncoder.encode(credentials.getPassword());
        credentials.setPassword(encoded);
        userRepo.save(credentials);
        return "Account Created Successfully";
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepo.findByUsername(username).isPresent();
    }
}