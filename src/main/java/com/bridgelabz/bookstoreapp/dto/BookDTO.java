package com.bridgelabz.bookstoreapp.dto;


import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public @ToString class BookDTO {

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Book name is Invalid")
    @NotEmpty(message = "Book name cannot be null")
    public String bookName;


    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Author name is Invalid")
    @NotEmpty(message = "Author name cannot be null")
    public String authorName;

    @NotEmpty(message = "Description cannot be null")
    public String description;

    @NotEmpty(message = "Logo cannot be null")
    public String logo;

    @NotNull(message = "Price cannot be null")
    public Integer price;

    @NotNull(message = "Quantity cannot be null")
    public Integer quantity;

    public BookDTO(String bookName, String authorName, String description, String logo, Integer price, Integer quantity) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;
        this.logo = logo;
        this.price = price;
        this.quantity = quantity;
    }
}
