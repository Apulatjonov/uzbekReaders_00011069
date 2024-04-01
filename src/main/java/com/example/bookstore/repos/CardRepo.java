package com.example.bookstore.repos;

import com.example.bookstore.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/11/2024 14:14
 */


@Repository
public interface CardRepo extends JpaRepository<Card, Long> {
    Optional<Card> findCardByPan(String pan);
}
