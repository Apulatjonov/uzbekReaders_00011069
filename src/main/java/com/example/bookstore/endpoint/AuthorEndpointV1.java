package com.example.bookstore.endpoint;

import com.example.bookstore.base.BaseURI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/28/2024 17:50
 */


@RequestMapping(BaseURI.api + BaseURI.v1 + BaseURI.author)
public interface AuthorEndpointV1 {

    @GetMapping(BaseURI.getAll)
    ResponseEntity<?> getAll();

    @PostMapping(BaseURI.add)
    ResponseEntity<?> addAuthor(@RequestBody String name);

    @GetMapping(BaseURI.getBooks + "/{id}")
    ResponseEntity<?> getAllBooks(@PathVariable Long id);
}
