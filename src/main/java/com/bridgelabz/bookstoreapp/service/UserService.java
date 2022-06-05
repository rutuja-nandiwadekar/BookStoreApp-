package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.dto.UserLoginDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
@Slf4j
public class UserService implements UserServiceImpl {


    @Autowired
    private UserRegistrationRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserData getUserDataById(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(()->new BookStoreException(("User with Id "+id+ "does not exists...")));
    }

    /**
     * @Purpose This method is used to register the user into the data base
     */
    @Override
    public UserData registerUser(UserDTO userDTO) {
        UserData addUser = null;
        addUser = new UserData(userDTO);
        addUser.setPassword(bCryptPasswordEncoder.encode(addUser.getPassword()));
        return userRepository.save(addUser);

    }
    /**
     * @Purpose This method is used to update the user data
     */
    @Override
    public UserData updateUserData(Integer id, UserDTO userDTO) {
        UserData updateUser = this.getUserDataById(id);
        updateUser.updateUserData(userDTO);
        updateUser.setPassword(bCryptPasswordEncoder.encode(updateUser.getPassword()));
        return  userRepository.save(updateUser);
    }

    /**
     * @Purpose This method is used to delete the user data
     */
    @Override
    public void deleteUserData(Integer id) {
       UserData deleteUser = this.getUserDataById(id);
       userRepository.delete(deleteUser);

    }

    /**
     * @Purpose This method is used to get list of all user data
     */
    @Override
    public List<UserData> getUserData() {
        return userRepository.findAll();
    }

    /**
     * @Purpose This method is used to login user with
     * correct email and password
     */
    @Override
    public ResponseDTO loginUser(UserLoginDTO userLoginDTO) {
        ResponseDTO respDTO = new ResponseDTO();
        List<UserData> userList = userRepository.findAll();
        UserData userDataByEmail = userRepository.findUserDataByEmail(userLoginDTO.getEmail());
        if (userList.contains(userDataByEmail)) {
            String password = userDataByEmail.getPassword();

            if (bCryptPasswordEncoder.matches(userLoginDTO.getPassword(), password)) {
                respDTO.setMessage("login SuccessFul");
                respDTO.setData(userDataByEmail);
                return respDTO;
            } else {
                respDTO.setMessage("Sorry! login is Un-successful");
                respDTO.setData("Password in incorrect");
                return respDTO;
            }
        }
        return new ResponseDTO("User not found", "Wrong email");
    }

}
