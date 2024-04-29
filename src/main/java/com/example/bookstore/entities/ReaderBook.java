package com.example.bookstore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/27/2024 23:04
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ReaderBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "reader_id_ref")
    private Long readerId;

    @ManyToOne
    private User reader;

    @Column(name = "book_id_ref")
    private Long bookId;

    @ManyToOne
    private Book book;

    @Column(name = "current_page")
    private String currentPage;
}
