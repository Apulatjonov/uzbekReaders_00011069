package com.example.bookstore.models;

import com.example.bookstore.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/29/2024 12:00
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private String name;
    private Long id;

    public CategoryDTO(Category category) {
        this.setName(category.getCategory());
        this.setId(category.getId());
    }
}
