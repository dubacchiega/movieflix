package com.movieflix.movieflix.user.mapper;

import com.movieflix.movieflix.user.entity.User;
import com.movieflix.movieflix.user.request.UserRequest;
import com.movieflix.movieflix.user.response.UserResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .password(userRequest.password())
                .build();
    }

    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
