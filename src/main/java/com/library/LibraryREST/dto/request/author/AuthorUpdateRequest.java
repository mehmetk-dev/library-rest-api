package com.library.LibraryREST.dto.request.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateRequest {

    private int id;
    private String name;
    private String country;
    private LocalDate birthDate;
}