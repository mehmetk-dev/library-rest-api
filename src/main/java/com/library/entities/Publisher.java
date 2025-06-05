package com.library.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private int id;

    @Column(name = "publisher_name")
    private String name;

    private Year establishmentYear;

    private String address;

    @OneToMany(mappedBy = "publisher",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Book> bookList;

}
