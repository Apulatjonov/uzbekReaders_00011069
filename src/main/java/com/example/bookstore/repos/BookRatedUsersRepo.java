package com.example.bookstore.repos;

import com.example.bookstore.entities.BookRatedUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/09/2024 16:39
 */


@Repository
public interface BookRatedUsersRepo extends JpaRepository<BookRatedUsers, Long> {
    Optional<BookRatedUsers> findByUserIdAndBookId(Long userId, Long bookId);
    List<BookRatedUsers> findAllByBookId(Long bookId);
}
