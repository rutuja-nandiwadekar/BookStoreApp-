package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/bookstore")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * @Purpose : To check simple api call
     * @return welcome message
     */
    @RequestMapping(value = {"/welcome", "/", ""})
    public String welcomeMessage(){

        return "Welcome to Book Store Application !!!";
    }

    /**
     * @Purpose : To add/ register user in book store application
     * @Param : UserDTO
     * @return user data and httpStatus
     */
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> userRegistration(@RequestBody UserDTO userDTO){
        UserData userData = userService.registerUser(userDTO);
        ResponseDTO respDTO=new ResponseDTO("User Registered Successfully",userData);
        return new ResponseEntity(respDTO, HttpStatus.OK);
    }

}