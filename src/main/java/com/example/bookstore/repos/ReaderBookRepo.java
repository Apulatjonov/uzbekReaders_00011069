package com.example.bookstore.repos;

import com.example.bookstore.entities.ReaderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/27/2024 23:06
 */


@Repository
public interface ReaderBookRepo extends JpaRepository<ReaderBook, String> {
    Optional<ReaderBook> findByReaderIdAndBookId(Long readerId, Long bookId);
}
