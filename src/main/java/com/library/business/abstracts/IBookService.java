package com.library.business.abstracts;


import com.library.dto.request.Book.BookSaveRequest;
import com.library.dto.request.Book.BookUpdateRequest;
import com.library.dto.response.book.BookResponse;
import com.library.entities.Book;
import org.springframework.data.domain.Page;

public interface IBookService{

    BookResponse saveBook(BookSaveRequest bookSaveRequest);

    Book save(Book book);

    Book get(int id);

    Book update(Book book);

    BookResponse update(int id, BookUpdateRequest request);

    boolean delete(int id);

    Page<Book> cursor(int page, int pageSize);

    BookResponse getBookResponse(Book book);
}
