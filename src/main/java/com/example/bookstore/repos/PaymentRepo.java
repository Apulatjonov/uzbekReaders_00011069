package com.example.bookstore.repos;

import com.example.bookstore.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/23/2024 01:46
 */


@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
}
