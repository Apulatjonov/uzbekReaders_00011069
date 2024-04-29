package com.example.bookstore.repos;

import com.example.bookstore.entities.BookView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/28/2024 05:38
 */


@Repository
public interface BookViewRepo extends JpaRepository<BookView, Long> {
    Optional<BookView> findByUserIdAndBookId(Long userId, Long bookId);
}
