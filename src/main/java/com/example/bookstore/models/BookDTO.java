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
        this.setPublishedDate(ent.getPublishedDate());
    }

    public BookDTO fromEntity(Book book){
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setScore(book.getScore());
        dto.setAuthorName(book.getAuthor().getFullName());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setCategoryName(book.getCategory().getCategory());
        dto.setCategoryId(book.getCategory().getId());
        dto.setTitle(book.getTitle());
        dto.setImgUrl(book.getImgUrl());
        dto.setPrice(book.getPrice());
        dto.setDescription(book.getDescription());
        if (book.getComments() != null){
            List<CommentDTO> comments = new ArrayList<>();
            for (Comment comment : book.getComments()){
                comments.add(new CommentDTO().fromEntity(comment));
            }
            dto.setComments(comments);
        }
        dto.setPublishedDate(book.getPublishedDate());
        return dto;
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
