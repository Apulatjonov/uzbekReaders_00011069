package com.example.bookstore.models;

import com.example.bookstore.entities.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/24/2024 03:44
 */

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String user;
    private String body;
    private Long bookId;
    public CommentDTO fromEntity(Comment comment){
        this.setId(comment.getId());
        this.setUser(comment.getUser());
        this.setBody(comment.getBody());
        this.setBookId(comment.getId());
        return this;
    }
}
