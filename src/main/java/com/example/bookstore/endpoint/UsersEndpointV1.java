package com.example.bookstore.endpoint;

import com.example.bookstore.base.BaseURI;
import com.example.bookstore.models.BaseUser;
import com.example.bookstore.models.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/11/2024 11:22
 */


@RequestMapping(BaseURI.api + BaseURI.v1 + BaseURI.user)
public interface UsersEndpointV1 {

    @PostMapping(BaseURI.signUp)
    ResponseEntity<?> signUp(@RequestBody BaseUser user);
    @PostMapping(BaseURI.signIn)
    ResponseEntity<?> singIn(@RequestBody BaseUser user);
    @PostMapping(BaseURI.fillProfile)
    ResponseEntity<?> fillProfile(@RequestBody UserDTO userDTO);
    @DeleteMapping(BaseURI.DeleteProfile + "/{id}")
    ResponseEntity<?> deleteProfile(@PathVariable Long id);
    @GetMapping("/{id}")
    ResponseEntity<?> getProfile(@PathVariable Long id);
}
