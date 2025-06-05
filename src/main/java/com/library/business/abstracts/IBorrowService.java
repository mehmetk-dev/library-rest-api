package com.library.business.abstracts;

import com.library.dto.request.borrow.BorrowSaveRequest;
import com.library.dto.request.borrow.BorrowUpdateRequest;
import com.library.dto.response.borrow.BorrowResponse;
import com.library.entities.BookBorrowing;
import org.springframework.data.domain.Page;


public interface IBorrowService {
    BorrowResponse save(BorrowSaveRequest borrowSaveRequest);
    BorrowResponse get(int id);
    BorrowResponse update(BorrowUpdateRequest borrowUpdateRequest);
    String delete(int id);
    Page<BookBorrowing> cursor(int page, int pageSize);
}
