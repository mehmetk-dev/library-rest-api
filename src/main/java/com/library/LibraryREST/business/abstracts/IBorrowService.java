package com.library.LibraryREST.business.abstracts;

import com.library.LibraryREST.dto.request.borrow.BorrowSaveRequest;
import com.library.LibraryREST.dto.request.borrow.BorrowUpdateRequest;
import com.library.LibraryREST.dto.response.borrow.BorrowResponse;
import com.library.LibraryREST.entities.BookBorrowing;
import org.springframework.data.domain.Page;


public interface IBorrowService {
    BorrowResponse save(BorrowSaveRequest borrowSaveRequest);
    BorrowResponse get(int id);
    BorrowResponse update(BorrowUpdateRequest borrowUpdateRequest);
    String delete(int id);
    Page<BookBorrowing> cursor(int page, int pageSize);
}
