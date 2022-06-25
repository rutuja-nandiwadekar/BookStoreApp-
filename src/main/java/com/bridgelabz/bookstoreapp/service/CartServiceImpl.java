package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.model.CartData;

import java.util.List;
import java.util.Optional;

public interface CartServiceImpl {

    BookData insertItems(String token, Integer bookId);

    List<CartData> getAll();

    CartData getCartDetailsById(Integer cartId);

    CartData deleteCartItemById(Integer cartId);

    CartData updateQuantity(Integer cartId, int quantity);

}
