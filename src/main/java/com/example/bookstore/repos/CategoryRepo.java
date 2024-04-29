package com.example.bookstore.repos;

import com.example.bookstore.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/28/2024 18:08
 */


@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    List<Category> findAllByCategory(String word);
}
