package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    /**
     * @Purpose : To get list of all user in book store application
     * @return  user data list and httpStatus
     */
    @RequestMapping(value = {"", "/", "/getall"})
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(){
        List<UserData> userDataList = null;
        userDataList = userService.getUserData();
        ResponseDTO respDTO = new ResponseDTO("Get Call Successful", userDataList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    /**
     * @Purpose : To update user data in book store application
     * @Param : id, UserDTO
     * @return updated user data and httpStatus
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateUserData(@PathVariable("id") Integer id, @RequestBody UserDTO userDTO){
        UserData userData = userService.updateUserData(id, userDTO);
        ResponseDTO respDTO=new ResponseDTO("User Updated Successfully",userData);
        return new ResponseEntity(respDTO, HttpStatus.OK);
    }

    /**
     * @Purpose : To delete user data by in book store application
     * @Param : id
     * @return response and status
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable ("id") Integer id){
        userService.deleteUserData(id);
        ResponseDTO respDTO = new ResponseDTO("Deleted successfully","Deleted id: "+id);
        return new  ResponseEntity<>(respDTO,HttpStatus.OK);
    }
    
}