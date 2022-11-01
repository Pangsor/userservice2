package com.test.userservice.repository;

import com.test.userservice.entity.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserContactRepository extends JpaRepository<UserContact, String> {
    List<UserContact> findByUserId(String userId);
}
