package com.example.TestingSpringBootProject;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@Autowired
	public StudentService studentService;

	@GetMapping("Test")
	public String Test() {
		return "tested";
	}

	@GetMapping("/stuent")
	public List<Student> findAllStudent() {
		return studentService.getAllStudent();
	}

	@PostMapping("/student")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		studentService.saveBooks(student);

		return new ResponseEntity(HttpStatus.OK);
		
	}

	@GetMapping("/students/id")
	public Student findStudent(@RequestParam int id) {

		System.out.println("controller");
		return studentService.getStudent(id)
				.orElseThrow(() -> new ResourseNotFoundException("User not found with id " + id));
	}

	@PutMapping("students/{id}")
	public Student UpdateStudent(@RequestBody Student student, @PathVariable int id) {

		return studentService.UpdateStudent(student, id);

	}

	@DeleteMapping("students/{id}")
	public void deleteStudent(@PathVariable int id) {

		studentService.deleteStudent(id);
	}

	@GetMapping("demo/{id}")
	public Optional<Student> checkValid(@PathVariable int id) {
		System.out.println("in check valid");
		return studentService.getStudent(id);
	}

}
