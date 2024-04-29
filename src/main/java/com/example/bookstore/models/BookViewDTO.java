package com.example.bookstore.models;

import com.example.bookstore.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/28/2024 05:28
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookViewDTO extends BaseBook {
    private Long score;
    private List<CommentDTO> comments;
    private String description;
    private LocalDateTime publishedDate;
    private List<PageDTO> pages;
    private Long authorId;
    private LocalDateTime accessedAt;

    public BookViewDTO(Book ent, LocalDateTime accessedAt) {
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
        this.setAccessedAt(accessedAt);
    }

    public BookViewDTO(Book ent) {
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
