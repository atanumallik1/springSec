package com.springSec.springSec.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management/api/v1/students")
public class StudentManagementController {
	private static List<Student> students = Arrays.asList(new Student(1, "James Bond"), new Student(2, "Maria Jones"),
			new Student(2, "Anna Smith"));

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
	@GetMapping
	public List<Student> getallStudents() {
		return students;

	}

	@PreAuthorize("hasAuthority('student:write')")
	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		System.out.println("Firing Post on Student " + student);

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
	@DeleteMapping(path = "{studentID}")
	public void deleteStudent(@PathVariable Integer studentID) {
		System.out.println("Firing Delete on Student " + studentID);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
	@PutMapping(path = "{studentID}")
	public void updateStudent(@PathVariable Integer studentID, @RequestBody Student student) {
		System.out.println(String.format("Firing Put on %s %s", studentID, student));
	}
}
