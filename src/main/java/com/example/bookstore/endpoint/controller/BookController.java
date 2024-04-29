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
    public ResponseEntity<?> getBook(Long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @Override
    public ResponseEntity<?> getPages(Long userId, Long id) {
        return ResponseEntity.ok(bookService.getPages(userId, id));
    }

    @Override
    public ResponseEntity<?> getNext(Long userId, Long id) {
        return ResponseEntity.ok(bookService.next(userId, id));
    }

    @Override
    public ResponseEntity<?> getPrev(Long userId, Long id) {
        return ResponseEntity.ok(bookService.prev(userId, id));
    }

    @Override
    public ResponseEntity<?> getMyBooks(Long id) {
        return ResponseEntity.ok(bookService.getMyBooks(id));
    }

    @Override
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(bookService.getCategories());
    }

    @Override
    public ResponseEntity<?> quitReading(Long id, Long userId) {
        return ResponseEntity.ok(bookService.quitReading(id, userId));
    }

    @Override
    public ResponseEntity<?> finishReading(Long id, Long userId) {
        return ResponseEntity.ok(bookService.finishReading(id, userId));
    }

    @Override
    public ResponseEntity<?> getStats(Long bookId) {
        return ResponseEntity.ok(bookService.getStatsByBook(bookId));
    }

    @Override
    public ResponseEntity<?> getUserStats(Long id) {
        return ResponseEntity.ok(bookService.getStatsOfUser(id));
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
    public ResponseEntity<?> getByCategory(Long id) {
        return ResponseEntity.ok(bookService.getByCategory(id));
    }

    @Override
    public ResponseEntity<?> getBookSignedIn(Long id, Long userId) {
        return ResponseEntity.ok(bookService.getBookSignedIn(id, userId));
    }
}
