package com.example.bookstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/23/2024 01:40
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private LocalDateTime createdAt = LocalDateTime.now();
    private Double amount;
    private Long cardId;
    private Long userId;
}
