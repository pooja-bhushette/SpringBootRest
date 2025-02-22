package com.example.userservice.controller;

import com.example.userservice.model.CreateUserRequest;
import com.example.userservice.model.UpdateUserRequest;
import com.example.userservice.model.UserResponse;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-profiles")
public class UserController {

	@Autowired
	public UserService userService;

	@GetMapping("/users")
	public List<UserResponse> findAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping("/users")
	public UserResponse addUser(@RequestBody CreateUserRequest createUserRequest) {
		return userService.addUser(createUserRequest);
	}

	@GetMapping("/users/{id}")
	public UserResponse findUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}

	@PutMapping("users/{id}")
	public UserResponse UpdateUsers(@PathVariable int id, @RequestBody UpdateUserRequest updateUserRequest) {
		return userService.UpdateStudent(updateUserRequest, id);
	}

	@DeleteMapping("users/{id}")
	public void deleteUsers(@PathVariable int id) {
		userService.deleteUser(id);
	}
}
