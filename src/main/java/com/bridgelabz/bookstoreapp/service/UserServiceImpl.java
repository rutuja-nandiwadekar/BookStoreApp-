package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.model.UserData;

import java.util.List;

public interface UserServiceImpl {


    UserData registerUser(UserDTO userDTO);

    UserData updateUserData(Integer id, UserDTO userDTO);

    UserData getUserDataById(Integer id);

    void deleteUserData(Integer id);


    List<UserData> getUserData();
}
