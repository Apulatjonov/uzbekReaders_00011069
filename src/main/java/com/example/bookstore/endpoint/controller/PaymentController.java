package com.example.bookstore.endpoint.controller;

import com.example.bookstore.base.BaseController;
import com.example.bookstore.endpoint.PaymentEndpointV1;
import com.example.bookstore.models.PaymentDTO;
import com.example.bookstore.serivces.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/24/2024 14:06
 */

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class PaymentController extends BaseController implements PaymentEndpointV1 {
    private final PaymentService paymentService;

    @Override
    public ResponseEntity<?> pay(PaymentDTO paymentDTO) {
        return ResponseEntity.ok(paymentService.pay(paymentDTO));
    }

    @Override
    public ResponseEntity<?> fillCard(PaymentDTO paymentDTO) {
        return ResponseEntity.ok(paymentService.fillCard(paymentDTO));
    }
}
