package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.model.CartData;

import java.util.Optional;

public interface CartServiceImpl {
    CartData insertItems(CartDTO cartdto);

    Optional<CartData> getCartDetailsById(Integer cartId);

    Optional<CartData> deleteCartItemById(Integer cartId);

    CartData updateQuntity(Integer cartId, int quantity);
}
