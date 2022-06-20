package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.model.BookData;

import java.util.List;

public interface BookServiceImpl {
    BookData addBook(BookDTO bookDTO);

    List<BookData> getAllBookData();

    BookData getBookDataById(int bookId);

    BookData updateBookData(int bookId, BookDTO bookDTO);

    void deleteBookData(int bookId);

    List<BookData> searchByName(String name);

    List<BookData> getBookByAscendingPrice();

    List<BookData> getBookByDescendingPrice();

    int getTotalBooksCount();
}
