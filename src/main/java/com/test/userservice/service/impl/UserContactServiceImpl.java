package com.test.userservice.service.impl;

import com.test.userservice.entity.User;
import com.test.userservice.entity.UserContact;
import com.test.userservice.exception.ResourceNotFoundException;
import com.test.userservice.exception.UserServiceAPIException;
import com.test.userservice.payload.UserContactDto;
import com.test.userservice.repository.UserContactRepository;
import com.test.userservice.repository.UserRepository;
import com.test.userservice.service.UserContactService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserContactServiceImpl implements UserContactService {
    private UserContactRepository userContactRepository;
    private UserRepository userRepository;

    public UserContactServiceImpl(UserContactRepository userContactRepository, UserRepository userRepository) {
        this.userContactRepository = userContactRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserContactDto createUserContact(UUID userId, UserContactDto userContactDto) {
        UserContact userContact = mapToEntity(userContactDto);

        User user = userRepository.findById(String.valueOf(userId)).orElseThrow(
                () -> new ResourceNotFoundException("User","id",userId));
        userContact.setUser(user);

        UserContact newUserContact = userContactRepository.save(userContact);
        return mapToDto(newUserContact);
    }

    @Override
    public List<UserContactDto> getUserContactsByUserId(UUID userId) {
        List<UserContact> userContacts = userContactRepository.findByUserId(String.valueOf(userId));
        return userContacts.stream().map(userContact -> mapToDto(userContact)).collect(Collectors.toList());
    }

    @Override
    public UserContactDto getUserContactById(UUID userId, UUID userContactId) {
        User user = userRepository.findById(String.valueOf(userId)).orElseThrow(
                () -> new ResourceNotFoundException("User","id",userId));

        UserContact userContact = userContactRepository.findById(String.valueOf(userContactId)).orElseThrow(
                () -> new ResourceNotFoundException("User Contact","id",userContactId));

        if (!userContact.getUser().getId().equals(user.getId())){
            throw new UserServiceAPIException(HttpStatus.BAD_REQUEST,"User contact not belong to User");
        }
        return mapToDto(userContact);
    }

    private UserContactDto mapToDto(UserContact userContact){
        UserContactDto userContactDto = new UserContactDto();
        userContactDto.setId(userContact.getId());
        userContactDto.setAddress(userContact.getAddress());

        return userContactDto;
    }

    private UserContact mapToEntity(UserContactDto userContactDto){
        UserContact userContact = new UserContact();
        userContact.setId(userContactDto.getId());
        userContact.setAddress(userContactDto.getAddress());

        return userContact;
    }
}
