package com.example.bookstore.endpoint;

import com.example.bookstore.base.BaseURI;
import com.example.bookstore.models.CardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/24/2024 14:13
 */

@RequestMapping(BaseURI.api + BaseURI.v1 + BaseURI.card)
public interface CardEndpointV1 {
    @PostMapping(BaseURI.addCard)
    ResponseEntity<?> addCard(@RequestBody CardDTO cardDTO);
    @GetMapping(BaseURI.getCard + "/{id}")
    ResponseEntity<?> getCard(@PathVariable Long id);
    @PutMapping(BaseURI.updateCard)
    ResponseEntity<?> updateCard(@RequestBody CardDTO cardDTO);
    @DeleteMapping(BaseURI.delete + "/{id}")
    ResponseEntity<?> deleteCard(@PathVariable Long id);
}
