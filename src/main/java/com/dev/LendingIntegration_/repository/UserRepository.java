package com.dev.LendingIntegration_.repository;

import com.dev.LendingIntegration_.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User>findByUsername(String username);
}
