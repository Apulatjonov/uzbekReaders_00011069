package com.example.bookstore.endpoint;

import com.example.bookstore.base.BaseURI;
import com.example.bookstore.models.BookDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/24/2024 14:30
 */

@RequestMapping(BaseURI.api + BaseURI.v1 + BaseURI.book)
public interface BookEndpointV1 {
    @GetMapping(BaseURI.getBook)
    ResponseEntity<?> getBook(@PathVariable Long id);
    @PostMapping(BaseURI.addBook)
    ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO);
    @GetMapping(BaseURI.searchBook + "/{word}")
    ResponseEntity<?> search(@PathVariable String word);
}
