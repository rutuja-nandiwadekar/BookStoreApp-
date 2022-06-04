package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.model.UserData;

public interface UserServiceImpl {


    UserData registerUser(UserDTO userDTO);

}
