package com.example.CRUDOperations.Controller;

import com.example.CRUDOperations.Entity.Student;
import com.example.CRUDOperations.com.example.CRUDOperations.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class StudentController {
    @Autowired
    StudentRepo studentRepo;
    @PostMapping("/api/students")
    public ResponseEntity<Student> save(@RequestBody Student student)
    {
         return new ResponseEntity<>(studentRepo.save(student),HttpStatus.CREATED);
    }
    @GetMapping("/api/students")
    public ResponseEntity<List<Student>> getStudents()
    {
        return new ResponseEntity<>(studentRepo.findAll(),HttpStatus.OK);
    }
    @GetMapping("/api/students/{id}")
    public ResponseEntity<Student> getStudents(@PathVariable int id)
    {
        Optional<Student> student=studentRepo.findById(id);
        if(student.isPresent())
            return new ResponseEntity<>(student.get(),HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/api/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id,@RequestBody Student stud)
    {
        Optional<Student> student = studentRepo.findById(id);
        if(student.isPresent())
        {
            student.get().setStudentEmail(stud.getStudentEmail());
            student.get().setStudentAddress(stud.getStudentAddress());
            student.get().setStudentName(stud.getStudentName());
            return new ResponseEntity<>(studentRepo.save(student.get()),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //updating a particular student's email
    @PutMapping("/api/students/update_email/{id}")
    public ResponseEntity<Student> updateEmail(@PathVariable int id,@RequestBody String email)
    {
        Optional<Student> student = studentRepo.findById(id);
        if(student.isPresent())
        {
            student.get().setStudentEmail(email);
            student.get().setStudentAddress(student.get().getStudentAddress());
            student.get().setStudentName(student.get().getStudentName());
            return new ResponseEntity<>(studentRepo.save(student.get()),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/api/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id)
    {
        Optional<Student> student = studentRepo.findById(id);
        if(student.isPresent())
        {
            studentRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
