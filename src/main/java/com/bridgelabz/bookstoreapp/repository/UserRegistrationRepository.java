package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRegistrationRepository extends JpaRepository<UserData,Integer> {
    @Query(value = "select * from user_details where email=:email",nativeQuery = true)
    public UserData findUserDataByEmail(String email);
}
