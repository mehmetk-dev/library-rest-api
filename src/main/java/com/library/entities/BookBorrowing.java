package com.library.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "borrows")
public class BookBorrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id")
    private Integer id;

    @Column(name = "borrower_name")
    private String borrowerName;

    @Temporal(TemporalType.DATE)
    @Column(name = "borrow_date")
    private LocalDate borrowingDate;

    @Column(name = "borrower_email")
    private String borrowerEmail;

    @Temporal(TemporalType.DATE)
    @Column(name = "return_date")
    private LocalDate returnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;
}
