package com.example.bookstore.models;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/24/2024 03:38
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO extends BaseBook{
    private Long score;
    private List<CommentDTO> comments;
    private String description;
    private LocalDateTime publishedDate;
    private List<PageDTO> pages;
    private Long authorId;
    private Long categoryId;
    private CategoryDTO category;
    private AuthorDTO author;
    private Long userId;
    private Long totalPages;

    public BookDTO(Book ent) {
        this.setId(ent.getId());
        this.setTitle(ent.getTitle());
        this.setAuthorName(ent.getAuthorName());
        this.setAuthorId(ent.getAuthor().getId());
        this.setCategoryId(ent.getCategory().getId());
        this.setCategoryName(ent.getCategory().getCategory());
        this.setScore(ent.getScore());
        this.setCategoryName(ent.getCategory().getCategory());
        this.setImgUrl(ent.getImgUrl());
        this.setPrice(ent.getPrice());
        this.setDescription(ent.getDescription());
        if (ent.getComments() != null){
            List<CommentDTO> comments = new ArrayList<>();
            for (Comment comment : ent.getComments()){
                comments.add(new CommentDTO().fromEntity(comment));
            }
            this.setComments(comments);
        }
        this.setPublishedDate(ent.getPublishedDate());
        this.setTotalPages(ent.getTotalPages());

    }

    public Book toEntity(){
        Book book = new Book();
        book.setScore(this.getScore());
        book.setAuthorName(this.getAuthorName());
        book.setTitle(this.getTitle());
        book.setPrice(this.getPrice());
        book.setDescription(this.getDescription());
        book.setPublishedDate(LocalDateTime.now());
        book.setImgUrl(this.getImgUrl());
        return book;
    }
}
