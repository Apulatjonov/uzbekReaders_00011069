package com.example.bookstore.repos;

import com.example.bookstore.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/28/2024 04:03
 */


@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
}
