package com.example.bookstore.endpoint.controller;

import com.example.bookstore.endpoint.CommentEndpointV1;
import com.example.bookstore.models.CommentDTO;
import com.example.bookstore.serivces.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/09/2024 11:18
 */

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class CommentController implements CommentEndpointV1 {
    private final CommentService service;
    @Override
    public ResponseEntity<?> addCommentToBook(CommentDTO dto) {
        return ResponseEntity.ok(service.addComment(dto));
    }

    @Override
    public ResponseEntity<?> deleteComment(Long id) {
        service.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}
