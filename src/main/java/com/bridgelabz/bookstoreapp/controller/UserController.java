package com.bridgelabz.bookstoreapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/bookstore")
@RestController
public class UserController {

    @RequestMapping(value = {"/welcome", "/", ""})
    public String welcomeMessage(){
        return "Welcome to Book Store Application !!!";
    }

}