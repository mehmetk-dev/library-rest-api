package com.library.LibraryREST.dto.response.borrow;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowResponse {

    private int id;

    private String borrowerName;

    private LocalDate borrowingDate;

    private String borrowerEmail;

    private LocalDate returnDate;

    private String bookName;
}
