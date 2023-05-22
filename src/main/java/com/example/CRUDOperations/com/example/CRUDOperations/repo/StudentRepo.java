package com.example.CRUDOperations.com.example.CRUDOperations.repo;

import com.example.CRUDOperations.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Integer> {

}
