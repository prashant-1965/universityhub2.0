package com.university.security.services;

import com.university.security.entity.Credentials;

public interface UserService {
    String addCredentials(Credentials credentials);
    boolean existByUsername(String username);
}
