package com.bridgelabz.bookstoreapp.controller;


import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * @Purpose : To add book in book store application
     * @Param : BookDTO
     * @return book data and httpStatus
     */
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addBookDetails(@Valid @RequestBody BookDTO bookDTO){
        BookData bookData = bookService.addBook(bookDTO);
        ResponseDTO respDTO = new ResponseDTO("Book data added Successfully: ",bookData);
        return new ResponseEntity(respDTO, HttpStatus.OK);
    }

    @RequestMapping(value = {"", "/", "/getall"})
    public ResponseEntity<ResponseDTO> getAllBookData() {
        List<BookData> bookDataList = bookService.getAllBookData();

        ResponseDTO respDTO = new ResponseDTO("Get Call Successful", bookDataList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/get_by_id/{bookId}")
    public ResponseEntity<ResponseDTO> getBookDataById(@PathVariable(value = "bookId") int bookId) {
        BookData bookData = bookService.getBookDataById(bookId);
        ResponseDTO respDTO = new ResponseDTO("Get Call Success for id: " +bookId ,bookData);
        return new ResponseEntity<>(respDTO,HttpStatus.OK);
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<ResponseDTO> updateBookData(@PathVariable int bookId, @Valid @RequestBody BookDTO bookDTO){
        BookData bookData = bookService.updateBookData(bookId, bookDTO);
        ResponseDTO respDTO = new ResponseDTO("Updated book Data : ",bookData);
        return new ResponseEntity<>(respDTO,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ResponseDTO> deleteBookData(@PathVariable("bookId") int bookId) {
        bookService.deleteBookData(bookId);
        ResponseDTO respDTO = new ResponseDTO("Deleted successfully","Deleted id: "+bookId);
        return new ResponseEntity<>(respDTO,HttpStatus.OK);
    }




}
