package com.neo.userAPI.controller;

import com.neo.userAPI.intr.UserServiceInt;
import com.neo.userAPI.model.UserRequest;
import com.neo.userAPI.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UserServiceInt userServiceInterface;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<UserResponse> authenticate(@RequestBody UserRequest userRequest) {
        System.out.println(userRequest.getUsername() + " :: Attempting login");
        UserResponse userResponse = userServiceInterface.validateUser(userRequest);
        System.out.println(userResponse);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);

    }



}
