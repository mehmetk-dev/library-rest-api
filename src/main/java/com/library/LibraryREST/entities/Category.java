package com.library.LibraryREST.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;

    @Column(name = "category_name")
    private String name;

    private String description;

    @ManyToMany(mappedBy = "categories",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Book> bookList;

}
