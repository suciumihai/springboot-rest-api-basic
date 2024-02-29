package com.serby.springbootrestapi.mapper;

import com.serby.springbootrestapi.dto.UserDto;
import com.serby.springbootrestapi.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user){
        return UserDto.builder()
                .email(user.getEmail())
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public static User mapToUser(UserDto user){
        return User.builder()
                .email(user.getEmail())
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
