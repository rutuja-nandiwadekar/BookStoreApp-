package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartDTO {

    // public Integer cartId;
    private Integer userId;
    private Integer bookId;
    @NotNull(message = "Book quantity yet to be provided")
    private Integer quantity;
}


