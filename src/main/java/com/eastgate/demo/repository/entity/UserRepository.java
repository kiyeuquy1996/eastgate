package com.eastgate.demo.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findBySecretKey(String secretKey);
    List<User> findByUsernameOrderByCreatedDateAsc(String username);
    void deleteByCreatedDateBefore(java.time.LocalDateTime expiryTime);
}
