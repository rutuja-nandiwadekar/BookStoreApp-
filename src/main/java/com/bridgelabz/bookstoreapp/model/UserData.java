package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@Component
@Entity
@Data
@NoArgsConstructor
@Table(name = "user_details")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;
    private String firstName;
    private String lastName;
    private String kyc;
    private LocalDate dateOfBirth;
//    private LocalDate registeredDate;
//    private LocalDate updatedDate;
    private String phoneNumber;
    private String email;
    private String password;
    private Boolean isVerified=false;

    public UserData(Integer id, String firstName, String lastName, String kyc, LocalDate dateOfBirth,
                    String phoneNumber, String email, String password, Boolean isVerified) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.kyc = kyc;
        this.dateOfBirth = dateOfBirth;
//        this.registeredDate = registeredDate;
//        this.updatedDate = updatedDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.isVerified = isVerified;
    }

    public UserData(UserDTO userDTO) {
        this.updateUserData(userDTO);

    }

    public void updateUserData(UserDTO userDTO) {
        this.firstName = userDTO.firstName;
        this.lastName = userDTO.lastName;
        this.kyc = userDTO.kyc;
        this.dateOfBirth = userDTO.dateOfBirth;
        this.phoneNumber = userDTO.phoneNumber;
        this.email = userDTO.email;
        this.password = userDTO.password;
    }
}
