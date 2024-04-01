package com.example.bookstore.endpoint.controller;

import com.example.bookstore.base.BaseController;
import com.example.bookstore.base.BaseURI;
import com.example.bookstore.endpoint.UsersEndpointV1;
import com.example.bookstore.models.BaseUser;
import com.example.bookstore.models.UserDTO;
import com.example.bookstore.serivces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/11/2024 11:20
 */

@RestController
@RequiredArgsConstructor
public class UserController extends BaseController implements UsersEndpointV1 {
    final private UserService service;

    @Override
    public ResponseEntity<?> signUp(BaseUser user) {
        return ResponseEntity.ok(service.signUp(user));
    }

    @Override
    public ResponseEntity<?> singIn(BaseUser user) {
        return ResponseEntity.ok(service.signIn(user));
    }

    @Override
    public ResponseEntity<?> fillProfile(UserDTO userDTO) {
        return ResponseEntity.ok(service.fillUserProfile(userDTO));
    }

    @Override
    public ResponseEntity<?> deleteProfile(Long id) {
        return ResponseEntity.ok(service.deleteProfile(id));
    }
}
