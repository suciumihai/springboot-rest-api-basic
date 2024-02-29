package com.serby.springbootrestapi.controller;

import com.serby.springbootrestapi.bean.Student;
import com.serby.springbootrestapi.exception.ErrorDetails;
import com.serby.springbootrestapi.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = Student.builder().id(1).firstName("ion").lastName("gheroghe").build();
        //return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok(student); //mai poti face si header, etc
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(Student.builder().id(1).firstName("ion").lastName("gheroghe").build());
        students.add(Student.builder().id(2).firstName("vasile").lastName("matei").build());
        return ResponseEntity.ok(students);
    }

    //path variable; {id} is uri template variable; /STUDENTS/1
    @GetMapping("/{id}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable int id) {
        return ResponseEntity.ok(Student.builder().id(id).firstName("ion").lastName("gheroghe").build());
    }

    @GetMapping("/{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariables(@PathVariable("id") int studentId,
                                        @PathVariable("first-name") String firstName,
                                        @PathVariable("last-name") String lastName) {
        return ResponseEntity.ok(Student.builder().id(studentId).firstName(firstName).lastName(lastName).build());
    }

    //query pARAM; /students/query?id=1 sau /students/query?id=1&firstName=ceva
    @GetMapping("/query")
    public ResponseEntity<Student> studentReqVar(@RequestParam int id, @RequestParam String firstName) {
        return ResponseEntity.ok(Student.builder().id(id).firstName(firstName).lastName("gheroghe").build());
    }

    //http post request, create new res
    @PostMapping("/create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    //http put req, update existing resource; returneaza 200 ok default
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") int studentId, @RequestBody  Student student) {
        Student student1 = Student.builder().id(1).firstName("ion").lastName("gheroghe").build();
        System.out.println(student1.getFirstName());
        student1.setId(student.getId());
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        return ResponseEntity.ok(student1);
    }

    //delete req
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        return ResponseEntity.ok("stud cu id deleted ok, " + studentId);
    }

    //use this error handling in this controller
    @ExceptionHandler(ResourceNotFoundException.class)
    public  ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .errorCode("STUDENT NOT FOUND")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
