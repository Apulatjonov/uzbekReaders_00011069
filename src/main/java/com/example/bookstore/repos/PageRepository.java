package com.example.bookstore.repos;

import com.example.bookstore.entities.BookPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/20/2024 23:46
 */


@Repository
public interface PageRepository extends MongoRepository<BookPage, String> {
    Optional<BookPage> findByBookIdAndPageNumber(Long bookId, Long pageNumber);
    List<BookPage> findAllByBookId(Long id);
}
