package com.example.bookstore.models;

import com.example.bookstore.entities.BookStats;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/29/2024 17:44
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookStatsDTO {

    private Long id;

    private Long bookId;

    private String title;

    private String imgUrl;

    private String author;

    private Long readsNumber;

    private Long currentlyReading;

    private Double totalRevenue;

    private LocalDateTime lastTimeRead;

    private Long comments;

    private Long views;

    private Long ratesNumber;;

    private Double currentRevenue;

    public BookStatsDTO(BookStats ent){
        this.setId(ent.getId());
        this.setBookId(ent.getBook().getId());
        this.setReadsNumber(ent.getReadsNumber());
        this.setCurrentlyReading(ent.getCurrentlyReading());
        this.setTotalRevenue(ent.getTotalRevenue());
        this.setLastTimeRead(ent.getLastTimeRead());
        this.setComments(ent.getComments());
        this.setViews(ent.getViews());
        this.setRatesNumber(ent.getRatesNumber());
        this.setCurrentRevenue(ent.getCurrentRevenue());
        this.setTitle(ent.getBook().getTitle());
        this.setAuthor(ent.getBook().getAuthor().getFullName());
        this.setImgUrl(ent.getBook().getImgUrl());
    }


}
