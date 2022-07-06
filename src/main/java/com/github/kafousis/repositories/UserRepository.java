package com.github.kafousis.repositories;

import com.github.kafousis.entities.Role;
import com.github.kafousis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);
    List<User> findByRole(Role role);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
