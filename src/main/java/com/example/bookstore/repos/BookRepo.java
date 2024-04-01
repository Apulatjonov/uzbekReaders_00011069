package com.example.bookstore.repos;

import com.example.bookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/23/2024 01:44
 */


@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> searchBooksByAuthorOrCategoryOrTitle(String word, String category, String title);
}
