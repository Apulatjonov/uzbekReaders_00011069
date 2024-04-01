package com.example.bookstore.endpoint.controller;

import com.example.bookstore.base.BaseController;
import com.example.bookstore.endpoint.BookEndpointV1;
import com.example.bookstore.models.BookDTO;
import com.example.bookstore.serivces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/24/2024 14:36
 */

@RestController
@RequiredArgsConstructor
public class BookController extends BaseController implements BookEndpointV1 {
    private final BookService bookService;
    @Override
    public ResponseEntity<?> getBook(Long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @Override
    public ResponseEntity<?> addBook(BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.addBook(bookDTO));
    }

    @Override
    public ResponseEntity<?> search(String word) {
        return ResponseEntity.ok(bookService.search(word));
    }
}
