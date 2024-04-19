package com.example.bookstore.endpoint.controller;

import com.example.bookstore.base.BaseController;
import com.example.bookstore.endpoint.BookEndpointV1;
import com.example.bookstore.models.BookDTO;
import com.example.bookstore.models.BookRatingDTO;
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
    public ResponseEntity<?> getBook(Long id, Long userId) {
        return ResponseEntity.ok(bookService.getBook(id, userId));
    }
    @Override
    public ResponseEntity<?> addBook(BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.addBook(bookDTO));
    }
    @Override
    public ResponseEntity<?> search(String word) {
        return ResponseEntity.ok(bookService.search(word));
    }
    @Override
    public ResponseEntity<?> getBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }

    @Override
    public ResponseEntity<?> rateBook(BookRatingDTO dto) {
        return ResponseEntity.ok(bookService.rate(dto));
    }

    @Override
    public ResponseEntity<?> getCurrentlyReading(Long id) {
        return ResponseEntity.ok(bookService.currentlyReading(id));
    }

    @Override
    public ResponseEntity<?> recentlyViewed(Long id) {
        return ResponseEntity.ok(bookService.getRecentlyViewed(id));
    }

    @Override
    public ResponseEntity<?> getRatedBooks(Long id) {
        return ResponseEntity.ok(bookService.getRatedBooks(id));
    }

    @Override
    public ResponseEntity<?> getByCategory(String category) {
        return ResponseEntity.ok(bookService.getByCategory(category));
    }

    @Override
    public ResponseEntity<?> getBook(Long id) {
        return null;
    }
}
