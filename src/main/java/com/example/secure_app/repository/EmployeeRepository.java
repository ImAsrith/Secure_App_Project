package com.example.secure_app.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import com.example.secure_app.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where e.email = :email")
    Optional<Employee> findByEmail(@Param("email") String email);
}