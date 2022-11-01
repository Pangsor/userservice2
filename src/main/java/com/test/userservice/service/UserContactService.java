package com.test.userservice.service;

import com.test.userservice.payload.UserContactDto;

import java.util.List;
import java.util.UUID;

public interface UserContactService {
    UserContactDto createUserContact(UUID userId, UserContactDto userContactDto);
    List<UserContactDto> getUserContactsByUserId(UUID userId);
    UserContactDto getUserContactById(UUID userId, UUID userContactId);
}
