package com.example.bookstore.entities;

import com.example.bookstore.models.PaymentDTO;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/23/2024 01:37
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity{
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "amount")
    private Double amount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payments")
    private Card card;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Payment(PaymentDTO dto) {
        this.amount = dto.getAmount();
        this.setCreatedAt(LocalDateTime.now());
        this.setLastUpdated(LocalDateTime.now());
    }

    public PaymentDTO toDto() {
        PaymentDTO dto = new PaymentDTO();
        dto.setCreatedAt(this.getCreatedAt());
        dto.setAmount(this.getAmount());
        dto.setUserId(this.getUser().getId());
        dto.setCardId(this.getCard().getId());
        return dto;
    }
}
