package com.example.repository;

import com.example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
public interface
EmployeeRepository    extends JpaRepository<Employee,Integer> {

    Optional<Employee> findByEmail(String email);
}
