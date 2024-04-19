package com.example.bookstore.entities;

import com.example.bookstore.models.CardDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/11/2024 13:59
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cards")
    private User user;
    @Column(name = "pan")
    private String pan;
    @Column(name = "expiry_date")
    private String expiryDate;
    @Column(name = "balance")
    private Double balance = 0.0;
    @OneToMany(mappedBy = "card")
    @Column(name = "payments")
    private List<Payment> payments;

    public Card(CardDTO dto) {
        this.pan = dto.getPan();
        this.expiryDate = dto.getExpiryDate();
        this.balance = Double.valueOf((Math.random() * ((1000 - 50) + 1)) + 50);
    }

    public CardDTO toDto() {
        CardDTO dto = new CardDTO();
        dto.setId(this.getId());
        dto.setBalance(this.getBalance());
        dto.setPan(this.getPan());
        dto.setExpiryDate(this.expiryDate);
        dto.setPanMask(this.pan.substring(0, 4) + "**** **** **" + this.pan.substring(14));
        return dto;
    }
}
