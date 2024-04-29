package com.example.bookstore.endpoint;

import com.example.bookstore.base.BaseURI;
import com.example.bookstore.models.CommentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/08/2024 18:02
 */


@RequestMapping(BaseURI.api + BaseURI.v1 + BaseURI.comment)
public interface CommentEndpointV1 {
    @PostMapping
    ResponseEntity<?> addCommentToBook(@RequestBody CommentDTO dto);
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteComment(@PathVariable Long id);
}
