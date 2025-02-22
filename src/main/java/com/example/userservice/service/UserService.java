package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.exception.NotFoundException;
import com.example.userservice.model.CreateUserRequest;
import com.example.userservice.model.UpdateUserRequest;
import com.example.userservice.model.UserResponse;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.userservice.transformer.UserTransformer.transformToUser;
import static com.example.userservice.transformer.UserTransformer.transformToUserUserResponse;

@Service
public class UserService {
    @Autowired
    public UserRepository repository;

    public UserResponse addUser(CreateUserRequest createUserRequest) {
        User user = repository.save(transformToUser(createUserRequest));
        return transformToUserUserResponse(user);
    }

    public List<UserResponse> getAllUsers() {
       return repository.findAll()
                .stream()
                .map(UserTransformer::transformToUserUserResponse)
               .collect(Collectors.toList());
           }

    public UserResponse getUserById(int id) {
        return repository.findById(id).map(UserTransformer :: transformToUserUserResponse)
                .orElseThrow(() -> new NotFoundException("User not found with id " + id));
    }

    public UserResponse UpdateStudent(UpdateUserRequest updateUserRequest, int id) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id " + id));
        existingUser.setName(updateUserRequest.getName());
        existingUser.setEmail(updateUserRequest.getEmail());
        return transformToUserUserResponse(repository.save(existingUser));
    }

    public void deleteUser(int id) {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id " + id));
        repository.deleteById(id);
    }

}
