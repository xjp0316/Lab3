package com.isep.testjpa.controller;

import com.isep.testjpa.model.Emp;
import com.isep.testjpa.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class SimpleController {

    @Autowired
    private EmpRepository empRepository;

    // GET all employees
    @GetMapping
    public List<Emp> getAllEmployees() {
        return empRepository.findAll();
    }

    // GET employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Emp> getEmployeeById(@PathVariable Long id) {
        Optional<Emp> emp = empRepository.findById(id);
        return emp.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST create employee
    @PostMapping
    public Emp addEmployee(@RequestBody Emp emp) {
        return empRepository.save(emp);
    }

    // PUT update employee
    @PutMapping("/{id}")
    public ResponseEntity<Emp> updateEmployee(@PathVariable Long id, @RequestBody Emp newEmp) {
        return empRepository.findById(id).map(emp -> {
            emp.setEname(newEmp.getEname());
            emp.setEfirst(newEmp.getEfirst());
            emp.setJob(newEmp.getJob());
            emp.setMgr(newEmp.getMgr());
            emp.setSal(newEmp.getSal());
            return ResponseEntity.ok(empRepository.save(emp));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (empRepository.existsById(id)) {
            empRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
