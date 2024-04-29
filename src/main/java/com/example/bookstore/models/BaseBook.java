package com.example.bookstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/11/2024 11:34
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseBook {
    private Long id;
    private String title;
    private String authorName;
    private String categoryName;
    private String imgUrl;
    private Double price;
}
