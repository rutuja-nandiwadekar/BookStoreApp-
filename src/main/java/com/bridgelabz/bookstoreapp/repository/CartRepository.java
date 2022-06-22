package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.CartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartData, Integer> {
    @Query(value="select * from cart_details where cart_Id =:cartId", nativeQuery =true)
    Optional<CartData> findById (Integer cartId);

}

