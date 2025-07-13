package com.example.secure_app.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.secure_app.model.Employee;
import com.example.secure_app.repository.EmployeeRepository;

@RestController @RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeRepository repo;

    public EmployeeController(EmployeeRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Employee> all() { return repo.findAll(); }

    @PostMapping
    public ResponseEntity<Employee> create(@Validated @RequestBody Employee e) {
        Employee saved = repo.save(e);
        return ResponseEntity.created(URI.create("/api/employees/" + saved.getId()))
                .body(saved);
    }

    @GetMapping("/{id}")
    public Employee one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id,
                           @Validated @RequestBody Employee e) {
        e.setId(id);
        return repo.save(e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}
