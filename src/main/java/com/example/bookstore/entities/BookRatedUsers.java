package com.example.bookstore.entities;

import com.example.bookstore.models.RateBookDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/09/2024 16:36
 */

@Entity(name = "book_ratings")
@Getter
@Setter
@NoArgsConstructor
public class BookRatedUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "score")
    private Long score;

    public RateBookDTO toDTO(Long avgScore) {
        RateBookDTO dto = new RateBookDTO();
        dto.setUserId(this.getUserId());
        dto.setBookId(this.getBookId());
        dto.setScore(this.getScore());
        dto.setAvgScore(avgScore);
        return dto;
    }
}
