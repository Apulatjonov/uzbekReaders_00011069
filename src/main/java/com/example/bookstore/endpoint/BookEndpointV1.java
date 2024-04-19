package com.example.bookstore.endpoint;

import com.example.bookstore.base.BaseURI;
import com.example.bookstore.models.BookDTO;
import com.example.bookstore.models.BookRatingDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/24/2024 14:30
 */

@RequestMapping(BaseURI.api + BaseURI.v1 + BaseURI.book)
public interface BookEndpointV1 {
    @GetMapping(BaseURI.getBook + "/{id}/userId/{userId}")
    ResponseEntity<?> getBook(@PathVariable Long id, @PathVariable Long userId);

    @PostMapping(BaseURI.addBook)
    ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO);

    @GetMapping(BaseURI.searchBook + "/{word}")
    ResponseEntity<?> search(@PathVariable String word);

    @GetMapping(BaseURI.getBooks)
    ResponseEntity<?> getBooks();

    @PostMapping(BaseURI.rate)
    ResponseEntity<?> rateBook(@RequestBody BookRatingDTO dto);

    @GetMapping(BaseURI.currentlyReading + "/{id}")
    ResponseEntity<?> getCurrentlyReading(@PathVariable Long id);

    @GetMapping(BaseURI.recentlyViewed + "/{id}")
    ResponseEntity<?> recentlyViewed(@PathVariable Long id);

    @GetMapping(BaseURI.rated + "/{id}")
    ResponseEntity<?> getRatedBooks(@PathVariable Long id);

    @GetMapping(BaseURI.category + "/{category}")
    ResponseEntity<?> getByCategory(@PathVariable String category);
    @GetMapping(BaseURI.book + "/id")
    ResponseEntity<?> getBook(@PathVariable Long id);

}
