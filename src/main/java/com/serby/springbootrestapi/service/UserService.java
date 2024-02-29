package com.serby.springbootrestapi.service;
import com.serby.springbootrestapi.dto.UserDto;
import com.serby.springbootrestapi.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user);

    void deleteUser(Long userId);

}
