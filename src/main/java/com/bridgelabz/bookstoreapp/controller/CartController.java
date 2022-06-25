package com.bridgelabz.bookstoreapp.controller;


import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.model.CartData;
import com.bridgelabz.bookstoreapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    /**
     * @return book data and httpStatus
     * @Purpose : To insert book in cart
     * @Param : token and bookId
     */
    @PostMapping("/insert/{bookId}")
    public ResponseEntity<ResponseDTO> insertItem(@RequestHeader String token, @PathVariable Integer bookId) {
        BookData addToCart = cartService.insertItems(token, bookId);
        ResponseDTO respDTO = new ResponseDTO("Book added successfully to cart ", addToCart);
        return new ResponseEntity(respDTO, HttpStatus.OK);
    }

    /**
     * @return cart details and httpStatus
     * @Purpose : To get cart details by cartID
     * @Param : cartId
     */
    @GetMapping("/getById/{cartId}")
    public ResponseEntity<ResponseDTO> getCartDetailsById(@PathVariable Integer cartId) {
        CartData cartData = cartService.getCartDetailsById(cartId);
        ResponseDTO respDTO = new ResponseDTO("Cart details are...", cartData);
        return new ResponseEntity(respDTO, HttpStatus.ACCEPTED);
    }

    /**
     * @return all cart details and httpStatus
     * @Purpose : To get all cart details
     */
    @GetMapping("/getAllCart")
    public ResponseEntity<ResponseDTO> getAllCart() {
        List<CartData> cartDataList = cartService.getAll();
        ResponseDTO respDTO = new ResponseDTO("all carts....", cartDataList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     * @return : Response httpStatus
     * @Purpose : To delete cart by cartID
     * @Param : cartId
     */
    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ResponseDTO> deleteCartById(@PathVariable Integer cartId) {
        CartData deleteData = cartService.deleteCartItemById(cartId);
        ResponseDTO respDTO = new ResponseDTO("Cart delete successfully", deleteData);
        return new ResponseEntity(respDTO, HttpStatus.OK);
    }

    /**
     * @return : updated book data and httpStatus
     * @Purpose : To update book quantity in cart by cartID
     * @Param : cartId quantity
     */
    @PutMapping("/UpdateQuantity/{cartId}/{quantity}")
    public ResponseEntity<ResponseDTO> updateQuantityData(@PathVariable Integer cartId, @PathVariable int quantity) {
        CartData updateQuantity = cartService.updateQuantity(cartId, quantity);
        ResponseDTO dto = new ResponseDTO("Cart quntity update Successfully ", updateQuantity);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

}
