package com.library.LibraryREST.dto.request.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest {
    private String name;
    private Year publicationYear;
    private int stock;
    private int authorId;
    private int publisherId;
    private List<Integer> categoryIds;
}
