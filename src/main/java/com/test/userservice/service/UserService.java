package com.test.userservice.service;

import com.test.userservice.payload.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto createUser(UserDto userDto);
    List<UserDto> getAllUser();
    UserDto getUserById(UUID id);
    UserDto updateUser(UserDto userDto, UUID id);
    void deleteUserById(UUID id);
}
