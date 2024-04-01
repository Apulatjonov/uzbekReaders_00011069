package com.example.bookstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/23/2024 01:42
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDTO{
    private Long id;
    private Long userId;
    private String pan;
    private String panMask;
    private String expiryDate;
    private Double balance;
    private List<PaymentDTO> payments;

}
