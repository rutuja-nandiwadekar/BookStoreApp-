package com.bridgelabz.bookstoreapp.service;


import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.BookData;

import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import com.bridgelabz.bookstoreapp.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService implements BookServiceImpl {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookData bookData;

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenGenerator tokenGenerator;


    @Override
    public BookData addBook(BookDTO bookDTO, String token) {
        Integer id = Integer.valueOf(tokenGenerator.decodeJWT(token));
        UserData userData = userRegistrationRepository.findById(id)
                .orElseThrow(() -> new BookStoreException("User not found", BookStoreException.ExceptionType.USER_NOT_FOUND));

        System.out.println(userData.getEmail());

        Optional<BookData> searchByName = bookRepository.findByBookName(bookDTO.getBookName());
        if (searchByName.isPresent()) {
            throw new BookStoreException("Book already present...", BookStoreException.ExceptionType.BOOK_ALREADY_PRESENT);
        }

        bookData = new BookData(bookDTO);
        bookData.setUserId(id);
        return bookRepository.save(bookData);
    }

    @Override
    public List<BookData> getAllBookData() {
        return bookRepository.findAll();
    }

    @Override
    public BookData getBookDataById(int bookId) {
        return bookRepository
                .findById(bookId)
                .orElseThrow(() -> new BookStoreException("Person with bookId " + bookId + " doesnot exists"));
    }

    @Override
    public BookData updateBookData(int bookId, BookDTO bookDTO) {
        BookData bookData = this.getBookDataById(bookId);
        bookData.updateBookData(bookDTO);
        return bookRepository.save(bookData);
    }

    @Override
    public void deleteBookData(int bookId) {
        BookData bookData = this.getBookDataById(bookId);
        bookRepository.delete(bookData);
    }

    @Override
    public int getTotalBooksCount() {
        return bookRepository.findAll().size();
    }

    @Override
    public List<BookData> searchByName(String name) {
        String name1 = name.toLowerCase();
        List<BookData> bookDetailsModels = getAllBookData();
        List<BookData> collect = bookDetailsModels.stream()
                .filter(bookData -> bookData.getBookName().toLowerCase().contains(name1))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<BookData> getBookByAscendingPrice() {
        List<BookData> bookDataList = bookRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(bookDetails -> bookDetails.getPrice()))
                .collect(Collectors.toList());
        return bookDataList;
    }

    @Override
    public List<BookData> getBookByDescendingPrice() {
        List<BookData> bookDataList = bookRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(bookDetails -> bookDetails.getPrice()))
                .collect(Collectors.toList());
        Collections.reverse(bookDataList);
        return bookDataList;
    }
}
