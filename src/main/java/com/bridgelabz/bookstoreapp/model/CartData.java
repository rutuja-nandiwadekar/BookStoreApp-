package com.bridgelabz.bookstoreapp.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Data
@Entity
@Table(name = "cart_details")
public class CartData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer cartId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserData userData;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private BookData bookData;
    private Integer quantity;


    public CartData() {
        super();
    }

    public CartData(Integer quantity, BookData bookData, UserData userData) {
        this.quantity = quantity;
        this.bookData = bookData;
        this.userData = userData;
    }
}
