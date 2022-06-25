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

@CrossOrigin("*")

@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * @return book data and httpStatus
     * @Purpose : To add book in book store application
     * @Param : BookDTO
     */

    @PostMapping("/add")
    //@RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> addBookDetails(@Valid @RequestBody BookDTO bookDTO, @RequestHeader String token) {
        BookData bookData = bookService.addBook(bookDTO, token);
        ResponseDTO respDTO = new ResponseDTO("Book data added Successfully: ", bookData);
        return new ResponseEntity(respDTO, HttpStatus.OK);
    }

    /**
     * @return book data and httpStatus
     * @Purpose : To get all books in book store application
     */
    @RequestMapping(value = {"", "/", "/getall"})
    public ResponseEntity<ResponseDTO> getAllBookData() {
        List<BookData> bookDataList = bookService.getAllBookData();
        ResponseDTO respDTO = new ResponseDTO("Get Call Successful", bookDataList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    /**
     * @return book data and httpStatus
     * @Purpose : To get book by ID in book store application
     * @Param : BookId
     */
    @GetMapping("/get_by_id/{bookId}")
    //@RequestMapping(value = "/get_by_id/{bookId}", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getBookDataById(@PathVariable(value = "bookId") int bookId) {
        BookData bookData = bookService.getBookDataById(bookId);
        ResponseDTO respDTO = new ResponseDTO("Get Call Success for id: " + bookId, bookData);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     * @return : updated book data and httpStatus
     * @Purpose : To update book by ID in book store application
     * @Param : BookId bookDTO
     */
    @PutMapping("/update/{bookId}")
    // @RequestMapping(value = "/update/{bookId}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseDTO> updateBookData(@PathVariable int bookId, @Valid @RequestBody BookDTO bookDTO) {
        BookData bookData = bookService.updateBookData(bookId, bookDTO);
        ResponseDTO respDTO = new ResponseDTO("Updated book Data : ", bookData);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     * @return : Response httpStatus
     * @Purpose : To delete book by ID in book store application
     * @Param : BookId
     */
    @DeleteMapping("/delete/{bookId}")
    // @RequestMapping(value = "/delete/{bookId}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDTO> deleteBookData(@PathVariable("bookId") int bookId) {
        bookService.deleteBookData(bookId);
        ResponseDTO respDTO = new ResponseDTO("Deleted successfully", "Deleted id: " + bookId);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     * @return : Response httpStatus
     * @Purpose : To search book by name in book store application
     * @Param : name
     */
    @GetMapping("/searchByName")
    public ResponseEntity<ResponseDTO> searchByName(@RequestParam String name) {
        List<BookData> bookDataList = bookService.searchByName(name);
        ResponseDTO respDTO = new ResponseDTO("Books are ....", bookDataList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     * @return : Response httpStatus
     * @Purpose : To show total Book Count in book store application
     */
    @GetMapping("/totalBookCount")
    public ResponseEntity<ResponseDTO> getTotalBookCount() {
        int totalCount = bookService.getTotalBooksCount();
        return new ResponseEntity<>(new ResponseDTO("Total books are  : ", totalCount), HttpStatus.OK);
    }

    /**
     * @return : Response httpStatus
     * @Purpose : To get Book By Ascending Price
     */
    @GetMapping("/getBookByAscendingPrice")
    public ResponseEntity<ResponseDTO> getBookByAscendingPrice() {
        List<BookData> bookDataList = bookService.getBookByAscendingPrice();
        ResponseDTO respDTO = new ResponseDTO("Books in ascending order...", bookDataList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     * @return : Response httpStatus
     * @Purpose : To get Book By Descending Price
     */
    @GetMapping("/getBookByDescendingPrice")
    public ResponseEntity<ResponseDTO> getBookByDescendingPrice() {
        List<BookData> bookDataList = bookService.getBookByDescendingPrice();
        ResponseDTO respDTO = new ResponseDTO("Books in descending order...", bookDataList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
}

