package com.library.LibraryREST.business.abstracts;


import com.library.LibraryREST.dto.request.Book.BookSaveRequest;
import com.library.LibraryREST.dto.request.Book.BookUpdateRequest;
import com.library.LibraryREST.dto.response.book.BookResponse;
import com.library.LibraryREST.entities.Author;
import com.library.LibraryREST.entities.Book;
import com.library.LibraryREST.entities.Category;
import org.springframework.data.domain.Page;

import java.util.List;

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
