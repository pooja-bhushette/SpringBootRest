package com.example.userservice.transformer;

import com.example.userservice.entity.User;
import com.example.userservice.model.CreateUserRequest;
import com.example.userservice.model.UserResponse;

public class UserTransformer {

    public static UserResponse transformToUserUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static User transformToUser(CreateUserRequest createUserRequest) {
        return User.builder()
                .name(createUserRequest.getName())
                .email(createUserRequest.getEmail())
                .build();
    }
}
