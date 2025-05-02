package com.university.security.service;

import com.university.security.entity.Credentials;

public interface UserService {
    boolean addCredentials(Credentials credentials);
    boolean existByUsername(String username);
}
