package com.library.LibraryREST.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private int id;

    @Column(name = "author_name")
    private String name;

    @Temporal(TemporalType.DATE)
    private LocalDate birthdate;

    private String country;

    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY ,cascade = CascadeType.PERSIST)
    private List<Book> bookList = new ArrayList<>();
}

