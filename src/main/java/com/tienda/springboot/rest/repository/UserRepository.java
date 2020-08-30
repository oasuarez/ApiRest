package com.tienda.springboot.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.springboot.rest.entity.User;

/**
 * User repository for CRUD operations.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUser(String username);
}
