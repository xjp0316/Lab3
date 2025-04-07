package com.isep.testjpa.repository;

import com.isep.testjpa.model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpRepository extends JpaRepository<Emp, Long> {
    List<Emp> findAll();
}
