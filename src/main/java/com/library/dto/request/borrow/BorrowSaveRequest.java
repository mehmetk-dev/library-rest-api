package com.library.dto.request.borrow;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowSaveRequest {

    @NotNull
    private String borrowerName;
    @NotNull
    private String borrowerEmail;
    @NotNull
    private LocalDate borrowingDate;
    @NotNull
    private LocalDate returnDate;
    @NotNull
    private int bookId;
}
