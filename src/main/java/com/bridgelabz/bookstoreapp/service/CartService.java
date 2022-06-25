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

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartService implements CartServiceImpl {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookData bookData;

    @Autowired
    private UserData userData;

    @Autowired
    private CartData cartData;

    @Override
    public BookData insertItems(String token, Integer bookId) {

        Optional<CartData> cartModel = cartRepository.findBookById(bookId);
        if (cartModel.isPresent()) {
            throw new BookStoreException("Book already present..", BookStoreException.ExceptionType.BOOK_ALREADY_PRESENT);
        }
        bookData = bookService.getBookDataById(bookId);
        userData = userService.getUserDataById(bookData.getUserId());
        cartData.setBookData(bookData);
        cartData.setUserData(userData);
        cartData.setQuantity(bookData.getQuantity());
        cartRepository.save(cartData);
        return bookData;
    }

    @Override
    public List<CartData> getAll() {
        List<CartData> cartDataList = cartRepository.findAll();
        return cartDataList;
    }

    @Override
    public CartData getCartDetailsById(Integer cartId) {
        Optional<CartData> getCartData = cartRepository.findById(cartId);
        if (getCartData.isPresent()) {

            return getCartData.get();
        } else {
            throw new BookStoreException(" Didn't find any record for this particular cartId");
        }
    }

    @Override
    public CartData deleteCartItemById(Integer cartId) {
        Optional<CartData> deleteData = cartRepository.findById(cartId);
        if (deleteData.isPresent()) {

            cartRepository.delete(cartData);
            return cartData;
        } else {
            throw new BookStoreException(" Did not get any cart for specific cart id ");
        }
    }

    @Override
    public CartData updateQuantity(Integer cartId, int quantity) {
        Optional<CartData> cartData = cartRepository.findById(cartId);
        if (cartData.isEmpty()) {
            throw new BookStoreException("invalid id please input valid Id");
        }
        cartData.get().setQuantity(quantity);
        cartRepository.save(cartData.get());
        return cartData.get();
    }

}