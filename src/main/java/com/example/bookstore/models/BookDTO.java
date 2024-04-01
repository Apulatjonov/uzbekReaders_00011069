package com.example.bookstore.models;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    public BookDTO fromEntity(Book book){
        this.setId(book.getId());
        this.setScore(book.getScore());
        this.setAuthor(book.getAuthor());
        this.setCategory(book.getCategory());
        this.setTitle(book.getTitle());
        List<CommentDTO> comments = new ArrayList<>();
        for (Comment comment : book.getComments()){
            comments.add(new CommentDTO().fromEntity(comment));
        }
        this.setComments(comments);
        return this;
    }
    public Book toEntity(){
        Book book = new Book();
        book.setScore(this.getScore());
        book.setAuthor(this.getAuthor());
        book.setTitle(this.getTitle());
        book.setCategory(this.getCategory());
        return book;
    }
}
