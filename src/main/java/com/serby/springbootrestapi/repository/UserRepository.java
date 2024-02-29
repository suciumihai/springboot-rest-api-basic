package com.serby.springbootrestapi.repository;

import com.serby.springbootrestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //deci toate metodele sunt by default tranzactional

    Optional<User> findByEmail(String email);
}
