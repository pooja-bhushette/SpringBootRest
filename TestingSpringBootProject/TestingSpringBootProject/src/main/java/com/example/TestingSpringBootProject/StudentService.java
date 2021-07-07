package com.example.TestingSpringBootProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	public StudentRepository repo;

	public Student saveBooks(Student student) {

		return repo.save(student);

	}

	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		List<Student> books = new ArrayList<Student>();
		repo.findAll().forEach(books1 -> books.add(books1));
		return books;

	}

	public Optional<Student> getStudent(int id) {

		System.out.println("in Service");

		return repo.findById(id);

	}

	public Student UpdateStudent(Student student1, int id) {
		// TODO Auto-generated method stub
		System.out.println("Above Existing student");
        
      repo.findById(id)
        		 .orElseThrow(() -> new ResourseNotFoundException("Usessr not found with id " + id));
      

         System.out.println("below Existing student");
        
         student1.setId(id);
		return repo.save(student1);
	}

	public void deleteStudent(int id) {
		// TODO Auto-generated method stub
		 repo.findById(id)
		 .orElseThrow(() -> new ResourseNotFoundException("Usessr not found with id " + id));
		 
		 repo.deleteById(id);
	}

}
