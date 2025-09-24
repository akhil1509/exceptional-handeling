package com.example.exception_handling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exception_handling.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
