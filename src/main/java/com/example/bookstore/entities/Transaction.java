package com.example.bookstore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/29/2024 16:06
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Book book;

//    @Column(name = "book_id")
//    private Long bookId;

    @ManyToOne
    private User reader;

//    @Column(name = "reader_id")
//    private Long readerId;

    @ManyToOne
    private User receiver;

//    @Column(name = "receiver_id")
//    private Long receiverId;

    @Column(name = "paid_date")
    private LocalDateTime paidDate;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "overall_payment")
    private Double overallPayment = 0.0;

}
