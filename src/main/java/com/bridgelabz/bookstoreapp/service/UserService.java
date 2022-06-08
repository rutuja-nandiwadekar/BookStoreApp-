package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.dto.UserLoginDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import com.bridgelabz.bookstoreapp.util.OtpGenerator;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;


@Service
@Slf4j
public class UserService implements UserServiceImpl {

    @Autowired
    private OtpGenerator otpGenerator;

    @Autowired
    UserData userData;

    @Autowired
    private UserRegistrationRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private ModelMapper modelMapper;

    Long otp;


    /**
     * @Purpose This method is used to check user is present or not by Id
     */
    @Override
    public UserData getUserDataById(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(()->new BookStoreException(("User with Id " +id+ " does not exists...")));
    }

    /**
     * @Purpose This method is used to register user
     */
    @Override
    public ResponseDTO registerUser(UserDTO userDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        UserData user = userRepository.findUserDataByEmail(userDTO.getEmail());
        if (user == null) {

            userData = modelMapper.map(userDTO, UserData.class);

            String epassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
            userData.setPassword(epassword);
            System.out.println("Password is " + epassword);

            userData = userRepository.save(userData);
            otp = otpGenerator.generateOTP();
            String requestUrl = "http://localhost:8080/bookstoreApi/verify/email/" + otp;
            System.out.println("the generated otp is " + otp);
            try {
                emailSenderService.sendEmail(
                        userDTO.getEmail(),
                        "Your Registration is successful",
                        requestUrl+"\n your generated otp is "
                                +otp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            responseDTO.setMessage("User Created successfully...Check your mail for verification");
            responseDTO.setData(userData);
        } else {
            responseDTO.setMessage("user not created");
            responseDTO.setData("user with " + userDTO.getEmail() + " is already exists");
        }
        return responseDTO;
    }

    /**
     * @Purpose This method is used to verify otp while user registration
     */
    @Override
    public ResponseDTO verifyOtp(Long otp) {
        if (otp.equals(otp)&&userData.getIsVerified().equals(false)) {
            userData.setIsVerified(true);
            userRepository.save(userData);
            return new ResponseDTO("otp verified", userData);
        }
        return new ResponseDTO("Invalid otp", "please enter correct otp");
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

    /**
     * @Purpose This method is used to generate otp for forgot password request
     */
    @Override
    public String forgotPasswordRequest(String email) {
        userData = userRepository.findUserDataByEmail(email);
        if (userData == null) {
            throw new BookStoreException("User Not Found");
        }
        otp = otpGenerator.generateOTP();
        String generatedOtp = "Otp is generated \n" + otp;
        emailSenderService.sendEmail(userData.getEmail(), "Your otp is ", generatedOtp);
        return "Reset Password otp Has Been Sent To Your Email Address " + userData.getEmail();
    }

    /**
     * @Purpose This method is used to reset password
     */
    @Override
    public String resetPassword(String password, Long mailOtp) {
        String ePassword = bCryptPasswordEncoder.encode(password);
        if (!mailOtp.equals(otp)) {
            throw new BookStoreException("Enter Correct OTP");
        }
        userData.setPassword(ePassword);
        userRepository.save(userData);
        return "password Reset successfully";
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










}


