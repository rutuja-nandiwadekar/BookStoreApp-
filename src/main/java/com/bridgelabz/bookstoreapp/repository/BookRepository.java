package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.BookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookData, Integer> {

    Optional<BookData> findByBookName(String bookName);

    @Query(value = "select * from book_details b where b.book_name like %:keyword%", nativeQuery = true)
    List<BookData> findByKeyWord(@Param("keyword") String keyword);

}
