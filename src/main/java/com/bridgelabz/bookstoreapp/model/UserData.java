package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
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
    private String email;
    private String password;
    private Boolean isVerified=false;

    public UserData(Integer id, String firstName, String lastName, String email, String password, Boolean isVerified) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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
        this.email = userDTO.email;
        this.password = userDTO.password;
    }
}
