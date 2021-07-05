package com.springSec.springSec.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static List<Student> students = Arrays.asList(new Student(1, "James Bond"), new Student(2, "Maria Jones"),
	    new Student(2, "Anna Smith"));

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") int studentId) {
	return students.stream().filter(student -> student.getStudentId() == studentId).findFirst()
		.orElseThrow(() -> new IllegalStateException("Student id " + studentId + " could not be found"));
    }

}
