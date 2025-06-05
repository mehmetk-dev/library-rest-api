package com.library.dto.response.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {

    private String name;
    private String country;
    private LocalDate birthDate;
}
