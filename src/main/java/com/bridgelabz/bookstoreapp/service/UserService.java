package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements UserServiceImpl {


    @Autowired
    private UserRegistrationRepository userRepository;

    @Override
    public UserData registerUser(UserDTO userDTO) {
        UserData addUser = null;
        addUser = new UserData(userDTO);
        return userRepository.save(addUser);

    }
}
