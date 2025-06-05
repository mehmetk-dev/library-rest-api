package com.library.LibraryREST.dto.response.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private String name;
    private Year publicationYear;
    private String authorName;
    private int stock;
    private String publisherName;
    private List<String> categories ;

}

