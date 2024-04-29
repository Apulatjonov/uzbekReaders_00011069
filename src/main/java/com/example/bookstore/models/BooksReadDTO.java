package com.example.bookstore.models;

import com.example.bookstore.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/28/2024 06:11
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksReadDTO extends BookViewDTO{
    private Long currentPage;

    public BooksReadDTO setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public BooksReadDTO(Book ent) {
        this.setId(ent.getId());
        this.setTitle(ent.getTitle());
        this.setAuthorName(ent.getAuthor().getFullName());
        this.setAuthorId(ent.getAuthor().getId());
        this.setScore(ent.getScore());
        this.setCategoryName(ent.getCategory().getCategory());
        this.setImgUrl(ent.getImgUrl());
        this.setPrice(ent.getPrice());
        this.setDescription(ent.getDescription());
        this.setPublishedDate(ent.getPublishedDate());
    }
}
