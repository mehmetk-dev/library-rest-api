package com.library.dto.request.publisher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherSaveRequest {

    private String name;
    private Year establishmentYear;
    private String address;

}
