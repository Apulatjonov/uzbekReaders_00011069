package com.example.bookstore.endpoint;

import com.example.bookstore.base.BaseURI;
import com.example.bookstore.models.PaymentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/24/2024 14:02
 */

@RequestMapping(BaseURI.api + BaseURI.v1 + BaseURI.payment)
public interface PaymentEndpointV1 {
    @PostMapping(BaseURI.pay)
    ResponseEntity<?> pay(@RequestBody PaymentDTO paymentDTO);

    @PostMapping(BaseURI.fillBalance)
    ResponseEntity<?> fillCard(PaymentDTO paymentDTO);
}
