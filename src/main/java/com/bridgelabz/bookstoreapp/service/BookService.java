package com.bridgelabz.bookstoreapp.service;


import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.BookData;

import com.bridgelabz.bookstoreapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements BookServiceImpl{


    @Autowired
    private BookRepository bookRepository;


    @Override
    public BookData addBook(BookDTO bookDTO) {
        BookData bookData = null;
        bookData = new BookData(bookDTO);
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
                .orElseThrow(()->new BookStoreException("Person with bookId "+bookId+" doesnot exists"));
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
}
