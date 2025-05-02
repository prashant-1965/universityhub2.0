package com.university.security.Repository;

import com.university.security.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Credentials, String>
{
    Optional<Credentials> findByUsername(String username);
}
