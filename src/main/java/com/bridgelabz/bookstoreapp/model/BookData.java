package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Data
@NoArgsConstructor
@Table(name = "book_details")
public class BookData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private Integer bookID;

    private String bookName;

    private String authorName;

    private String description;

    private String logo;

    private Integer price;

    private Integer quantity;

    public BookData(Integer bookID, String bookName, String authorName,
                    String description, String logo, Integer price, Integer quantity) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;
        this.logo = logo;
        this.price = price;
        this.quantity = quantity;
    }

    public BookData(BookDTO bookDTO) {
        this.updateBookData(bookDTO);

    }

    public void updateBookData(BookDTO bookDTO){
        this.bookName = bookDTO.bookName;
        this.authorName = bookDTO.authorName;
        this.description = bookDTO.description;
        this.logo = bookDTO.logo;
        this.price = bookDTO.price;
        this.quantity = bookDTO.quantity;

    }
}
