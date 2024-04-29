package com.example.bookstore.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/09/2024 16:40
 */

@Getter
@Setter
public class RateBookDTO {
    private Long userId;
    private Long bookId;
    private Long score;
    private Long avgScore;
}
