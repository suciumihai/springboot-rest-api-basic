package com.serby.springbootrestapi.service.impl;

import com.serby.springbootrestapi.dto.UserDto;
import com.serby.springbootrestapi.entity.User;
import com.serby.springbootrestapi.exception.EmailExistsException;
import com.serby.springbootrestapi.exception.ResourceNotFoundException;
import com.serby.springbootrestapi.mapper.UserMapper;
import com.serby.springbootrestapi.repository.UserRepository;
import com.serby.springbootrestapi.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        User userCuModelMapper = modelMapper.map(userDto, User.class);
        UserDto userDtoCuModelMapper = modelMapper.map(userCuModelMapper, UserDto.class);
//mai exista si mapStruct, mai vedem

        Optional<User> optUser = userRepository.findByEmail(userDto.getEmail());
        if (optUser.isPresent())
        {
            throw new EmailExistsException("email exists");
        }
        User user = userRepository.save(UserMapper.mapToUser(userDto));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", userId));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto).toList();
    }

    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return UserMapper.mapToUserDto(userRepository.save(existingUser));
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", userId)
        );
        userRepository.deleteById(userId);
    }
}
