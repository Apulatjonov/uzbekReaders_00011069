package com.example.bookstore.endpoint.controller;

import com.example.bookstore.base.BaseController;
import com.example.bookstore.endpoint.CardEndpointV1;
import com.example.bookstore.models.CardDTO;
import com.example.bookstore.serivces.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/24/2024 14:21
 */

@RestController
@RequiredArgsConstructor
public class CardController extends BaseController implements CardEndpointV1 {
    private final CardService cardService;
    @Override
    public ResponseEntity<?> addCard(CardDTO cardDTO) {
        return ResponseEntity.ok(cardService.addCard(cardDTO));
    }

    @Override
    public ResponseEntity<?> getCard(Long id) {
        return ResponseEntity.ok(cardService.getOne(id));
    }

    @Override
    public ResponseEntity<?> updateCard(CardDTO cardDTO) {
        return ResponseEntity.ok(cardService.update(cardDTO));
    }

    @Override
    public ResponseEntity<?> deleteCard(Long id) {
        return ResponseEntity.ok(cardService.delete(id));
    }
}
