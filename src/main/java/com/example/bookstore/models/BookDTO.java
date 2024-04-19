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

    public BookDTO(Book ent) {
        this.setId(ent.getId());
        this.setTitle(ent.getTitle());
        this.setAuthor(ent.getAuthor());
        this.setScore(ent.getScore());
        this.setCategory(ent.getCategory());
        this.setImgUrl(ent.getImgUrl());
        this.setPrice(ent.getPrice());
        this.setDescription(ent.getDescription());
        this.setPublishedDate(ent.getPublishedDate());
    }

    public BookDTO fromEntity(Book book){
        this.setId(book.getId());
        this.setScore(book.getScore());
        this.setAuthor(book.getAuthor());
        this.setCategory(book.getCategory());
        this.setTitle(book.getTitle());
        this.setImgUrl(book.getImgUrl());
        this.setPrice(book.getPrice());
        this.setDescription(book.getDescription());
        List<CommentDTO> comments = new ArrayList<>();
        for (Comment comment : book.getComments()){
            comments.add(new CommentDTO().fromEntity(comment));
        }
        this.setComments(comments);
        this.setPublishedDate(book.getPublishedDate());
        return this;
    }
    public Book toEntity(){
        Book book = new Book();
        book.setScore(this.getScore());
        book.setAuthor(this.getAuthor());
        book.setTitle(this.getTitle());
        book.setCategory(this.getCategory());
        book.setPrice(this.getPrice());
        book.setDescription(this.getDescription());
        book.setPublishedDate(LocalDateTime.now());
        return book;
    }
}
