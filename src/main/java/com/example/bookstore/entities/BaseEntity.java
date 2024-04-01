package com.example.bookstore.entities;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/23/2024 01:27
 */


@Getter
@Setter
public class BaseEntity {
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}
