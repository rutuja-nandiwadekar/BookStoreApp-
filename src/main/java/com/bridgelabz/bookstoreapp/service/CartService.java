package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.model.CartData;
import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.CartRepository;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CartService implements CartServiceImpl{

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRegistrationRepository userRegistrationRepository;
    @Autowired
    CartRepository cartRepository;

    @Override
    public CartData insertItems(CartDTO cartdto) {
        Optional<BookData> bookData = bookRepository.findById(cartdto.getBookId());
        Optional<UserData> userData = userRegistrationRepository.findById(cartdto.getUserId());
        if (bookData.isPresent() && userData.isPresent()) {
            CartData newCart = new CartData(cartdto.getQuantity(), bookData.get(), userData.get());
            cartRepository.save(newCart);
            return newCart;
        } else {
            throw new BookStoreException("Book or User does not exists");
        }
    }

    @Override
    public Optional<CartData> getCartDetailsById(Integer cartId) {
        Optional<CartData> getCartData=cartRepository.findById(cartId);
        if (getCartData.isPresent()){
            return getCartData;
        }
        else {
            throw new BookStoreException(" Didn't find any record for this particular cartId");
        }
    }

    @Override
    public Optional<CartData> deleteCartItemById(Integer cartId) {
        Optional<CartData> deleteData=cartRepository.findById(cartId);
        if (deleteData.isPresent()){
            cartRepository.deleteById(cartId);
            return deleteData;
        }
        else {
            throw new BookStoreException(" Did not get any cart for specific cart id ");
        }
    }

    @Override
    public CartData updateQuntity(Integer cartId, int quantity) {
        Optional<CartData> cartData=cartRepository.findById(cartId);
        if (cartData.isEmpty()){
            throw new BookStoreException("invalid id please input valid Id");
        }
        cartData.get().setQuantity(quantity);
        cartRepository.save(cartData.get());
        return cartData.get();
    }
}