package com.library.dto.request.Book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveRequest {

    @NotEmpty
    private String name;
    private Year publicationYear;
    private Integer stock;
    private Integer authorId;
    private Integer publisherId;
    private List<Integer> categoryIds;
}
