package com.gogibibo.goibibo.controller;


import com.gogibibo.goibibo.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    public List<Student> sampleUser() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Phnai", 30));
        students.add(new Student(2, "sahith", 27));
        students.add(new Student(3, "santhosh", 28));
        return students;
    }


    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> students = sampleUser();
        return students;
    }

    @PostMapping("/students/add")
    public List<Student> addStudent(@RequestBody Student student) {
        List<Student> students = sampleUser();
        students.add(student);
        return students;
    }
}
