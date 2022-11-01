package com.test.userservice.controller;

import com.test.userservice.payload.UserContactDto;
import com.test.userservice.service.UserContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/")
public class UserContactController {
    private UserContactService userContactService;

    public UserContactController(UserContactService userContactService) {
        this.userContactService = userContactService;
    }

    @PostMapping("/user/{userId}/user-contact")
    public ResponseEntity<UserContactDto> createUserContact(@PathVariable(name = "userId") UUID userId,
                                                            @RequestBody UserContactDto userContactDto){
        return new ResponseEntity<>(userContactService.createUserContact(userId, userContactDto), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/user-contact")
    public List<UserContactDto> getUserContactsByUserId(@PathVariable UUID userId){
        return userContactService.getUserContactsByUserId(userId);
    }

    @GetMapping("/user/{userId}/user-contact/{userContactId}")
    public ResponseEntity<UserContactDto> getUserContactById(@PathVariable UUID userId,
                                                             @PathVariable UUID userContactId){
        UserContactDto userContactDto = userContactService.getUserContactById(userId,userContactId);
        return new ResponseEntity<>(userContactDto, HttpStatus.OK);
    }
}
