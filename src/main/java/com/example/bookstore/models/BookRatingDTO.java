package com.example.bookstore.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/09/2024 16:30
 */

@Getter
@Setter
@NoArgsConstructor
public class BookRatingDTO {
    private Long bookId;
    private Long rating;
    private Long userId;
}
