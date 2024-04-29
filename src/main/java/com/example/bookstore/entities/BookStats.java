package com.example.bookstore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/29/2024 17:11
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BookStats {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @OneToOne
    private Book book;

    @Column(name = "reads_number")
    private Long readsNumber;

    @Column(name = "currently_reading")
    private Long currentlyReading;

    @Column(name = "total_revenue")
    private Double totalRevenue;

    @Column(name = "last_time_read")
    private LocalDateTime lastTimeRead;

    @Column(name = "comments")
    private Long comments;

    @Column(name = "views")
    private Long views;

    @Column(name = "rates_number")
    private Long ratesNumber;

    @Column(name = "current_revenue")
    private Double currentRevenue;
}
