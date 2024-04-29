package com.example.bookstore.repos;

import com.example.bookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/23/2024 01:44
 */


@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> searchBooksByAuthorNameContainingIgnoreCaseOrTitleContainingIgnoreCaseOrCategoryNameContainingIgnoreCase(String word, String title, String category);

    List<Book> findAllByCategoryId(Long category);

    List<Book> findAllByAuthorId(Long id);

    List<Book> findAllByUserId(Long id);
}
