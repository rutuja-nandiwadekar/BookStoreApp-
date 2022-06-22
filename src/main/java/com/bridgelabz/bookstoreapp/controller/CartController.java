package com.bridgelabz.bookstoreapp.controller;


import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.CartData;
import com.bridgelabz.bookstoreapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertItem(@RequestBody CartDTO cartdto) {
        CartData newCart = cartService.insertItems(cartdto);
        ResponseDTO responseDTO = new ResponseDTO("Book added successfully to cart !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getById/{cartId}")
    public ResponseEntity<ResponseDTO> getCartDetailsById(@PathVariable Integer cartId){
        Optional<CartData> specificCartDetail=cartService.getCartDetailsById(cartId);
        ResponseDTO responseDTO=new ResponseDTO("Cart details retrieved successfully",specificCartDetail);
        return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ResponseDTO> deleteCartById(@PathVariable Integer cartId) {
        Optional<CartData> delete = cartService.deleteCartItemById(cartId);
        ResponseDTO responseDTO = new ResponseDTO("Cart delete successfully", delete);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/UpdateQunatity/{cartId}/{quantity}")
    public ResponseEntity<ResponseDTO> updateQuntityData(@PathVariable Integer cartId, @PathVariable int quantity) {
        CartData updateQuntity = cartService.updateQuntity(cartId, quantity);
        ResponseDTO dto = new ResponseDTO("Cart quntity update Successfully " ,updateQuntity);
        return  new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
    }

}
