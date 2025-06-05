package com.library.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.Year;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @NotNull
    @Column(name = "book_name")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "publication_year")
    private Year publicationYear;

    @Positive
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_author_id",referencedColumnName = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_publisher_id",referencedColumnName = "publisher_id")
    private Publisher publisher;

    @OneToMany(mappedBy = "book", cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true)
    private List<BookBorrowing> borrowings;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book2category",
            joinColumns = {@JoinColumn(name = "book2category_book_id")},
            inverseJoinColumns = {@JoinColumn(name = "book2category_category_id")}
    )
    List<Category> categories;
}
