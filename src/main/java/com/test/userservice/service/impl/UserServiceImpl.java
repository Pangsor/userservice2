package com.test.userservice.service.impl;

import com.test.userservice.entity.User;
import com.test.userservice.exception.ResourceNotFoundException;
import com.test.userservice.payload.UserDto;
import com.test.userservice.repository.UserRepository;
import com.test.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = mapToEntity(userDto);
        User newUser = userRepository.save(user);

        UserDto userResponse = mapToDto(newUser);

        return userResponse;
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> mapToDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(UUID id) {
        User user = userRepository.findById(String.valueOf(id)).orElseThrow(() -> new ResourceNotFoundException("User","id", id));
        return mapToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, UUID id) {
        User user = userRepository.findById(String.valueOf(id)).orElseThrow(() -> new ResourceNotFoundException("User","id", id));
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());

        User updatedUser = userRepository.save(user);
        return mapToDto(updatedUser);
    }

    @Override
    public void deleteUserById(UUID id) {
        User user = userRepository.findById(String.valueOf(id)).orElseThrow(() -> new ResourceNotFoundException("User","id", id));
        userRepository.delete(user);
    }

    //  convert entity into DTO
    private UserDto mapToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setAge(user.getAge());
        return userDto;
    }

    //  convert Dto into entity
    private User mapToEntity(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        return user;
    }
}
