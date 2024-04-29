package com.example.bookstore.endpoint.controller;

import com.example.bookstore.base.BaseController;
import com.example.bookstore.endpoint.AuthorEndpointV1;
import com.example.bookstore.serivces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/28/2024 17:49
 */

@RestController
@RequiredArgsConstructor
public class AuthorController extends BaseController implements AuthorEndpointV1 {
    private final BookService service;

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAuthors());
    }

    @Override
    public ResponseEntity<?> addAuthor(String name) {
        return ResponseEntity.ok(service.addAuthor(name));
    }

    @Override
    public ResponseEntity<?> getAllBooks(Long id) {
        return ResponseEntity.ok(service.getBooksOfAuthor(id));
    }
}
