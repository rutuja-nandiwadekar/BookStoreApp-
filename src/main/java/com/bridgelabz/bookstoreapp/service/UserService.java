package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService implements UserServiceImpl {


    @Autowired
    private UserRegistrationRepository userRepository;


    @Override
    public UserData getUserDataById(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(()->new BookStoreException(("User with Id "+id+ "does not exists...")));
    }
    @Override
    public UserData registerUser(UserDTO userDTO) {
        UserData addUser = null;
        addUser = new UserData(userDTO);
        return userRepository.save(addUser);

    }

    @Override
    public UserData updateUserData(Integer id, UserDTO userDTO) {
        UserData updateUser = this.getUserDataById(id);
        updateUser.updateUserData(userDTO);
        return  userRepository.save(updateUser);
    }
    @Override
    public void deleteUserData(Integer id) {
       UserData deleteUser = this.getUserDataById(id);
       userRepository.delete(deleteUser);

    }

    @Override
    public List<UserData> getUserData() {
        return userRepository.findAll();
    }


}
