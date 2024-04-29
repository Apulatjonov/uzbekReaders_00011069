package com.example.bookstore.repos;

import com.example.bookstore.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/29/2024 16:12
 */


@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByReaderIdAndBookId(Long readerId, Long bookId);
}
