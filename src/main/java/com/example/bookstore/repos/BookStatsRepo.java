package com.example.bookstore.repos;

import com.example.bookstore.entities.BookStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/29/2024 17:22
 */


@Repository
public interface BookStatsRepo extends JpaRepository<BookStats, Long> {
    Optional<BookStats> findByBookId(Long bookId);
}
