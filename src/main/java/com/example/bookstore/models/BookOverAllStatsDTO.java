package com.example.bookstore.models;

import com.example.bookstore.entities.BookStats;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/29/2024 17:54
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookOverAllStatsDTO {

    private Long id;

    private Long bookId;

    private Long totalReadsNumber = 0L;

    private Long totalCurrentlyReading = 0L;

    private Double totalRevenue = Double.valueOf(0);

    private Long totalComments = 0L;

    private Long totalViews = 0L;

    private Long totalRatesNumber = 0L;

    private Double totalCurrentRevenue = Double.valueOf(0);

    private Long totalBooks = 0L;

    public void add(BookStats stats){
        this.setTotalReadsNumber(totalReadsNumber + stats.getReadsNumber());
        this.setTotalCurrentlyReading(totalCurrentlyReading + stats.getCurrentlyReading());
        this.setTotalRevenue(totalRevenue + stats.getTotalRevenue());
        this.setTotalComments(totalComments + stats.getComments());
        this.setTotalViews(totalViews + stats.getViews());
        this.setTotalRatesNumber(totalRatesNumber + stats.getRatesNumber());
        this.setTotalCurrentRevenue(totalCurrentRevenue + stats.getCurrentRevenue());
        this.setTotalBooks(this.getTotalBooks() + 1);
    }

}
