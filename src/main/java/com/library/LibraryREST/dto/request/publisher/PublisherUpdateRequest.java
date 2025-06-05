package com.library.LibraryREST.dto.request.publisher;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherUpdateRequest {

    @Positive(message = "ID 1'den küçük olamaz")
    private int id;
    private String name;
    private Year establishmentYear;
    private String address;

}