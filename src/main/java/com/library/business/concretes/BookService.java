package com.library.business.concretes;

import com.library.business.abstracts.IAuthorService;
import com.library.business.abstracts.IBookService;
import com.library.business.abstracts.ICategoryService;
import com.library.business.abstracts.IPublisherService;
import com.library.core.config.exception.NotFoundException;
import com.library.core.config.modelMapper.IModelMapperService;
import com.library.dao.BookRepo;
import com.library.dto.request.Book.BookSaveRequest;
import com.library.dto.request.Book.BookUpdateRequest;
import com.library.dto.response.book.BookResponse;
import com.library.entities.Author;
import com.library.entities.Book;
import com.library.entities.Category;
import com.library.entities.Publisher;
import com.library.utilies.MessageConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {
    private final IModelMapperService modelMapper;
    private final BookRepo bookRepo;
    private final IAuthorService authorService;
    private final ICategoryService categoryService;
    private final IPublisherService publisherService;

    public BookService(IModelMapperService modelMapper, BookRepo bookRepo, IAuthorService authorService, ICategoryService categoryService, IPublisherService publisherService) {
        this.modelMapper = modelMapper;
        this.bookRepo = bookRepo;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.publisherService = publisherService;
    }

    @Override
    public BookResponse saveBook(BookSaveRequest bookSaveRequest) {
        Book book = modelMapper.forRequest().map(bookSaveRequest, Book.class);

        Author author = authorService.get(bookSaveRequest.getAuthorId());
        book.setAuthor(author);

        Publisher publisher = publisherService.get(bookSaveRequest.getPublisherId());
        book.setPublisher(publisher);

        List<Category> categories = categoryService.getAllByIds(bookSaveRequest.getCategoryIds());
        book.setCategories(categories);

        this.save(book);

        return getBookResponse(book);
    }

    @Override
    public BookResponse update(int id, BookUpdateRequest request) {

        Book book = this.get(id);


        book.setName(request.getName());
        book.setStock(request.getStock());
        book.setPublicationYear(request.getPublicationYear());


        Author author = authorService.get(request.getAuthorId());
        book.setAuthor(author);

        Publisher publisher = publisherService.get(request.getPublisherId());
        book.setPublisher(publisher);

        List<Category> categories = categoryService.getAllByIds(request.getCategoryIds());
        book.setCategories(categories);


        this.save(book);

        return getBookResponse(book);
    }

    @Override
    public Book save(Book book) {
         return this.bookRepo.save(book);
    }

    @Override
    public Book get(int id) {
        return this.bookRepo.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.NOT_FOUND));
    }

    @Override
    public Book update(Book book) {
        this.get(book.getId());
        return this.save(book);
    }

    @Override
    public boolean delete(int id) {
        this.bookRepo.delete(this.get(id));
        return true;
    }

    @Override
    public Page<Book> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.bookRepo.findAll(pageable);
    }

    @Override
    public BookResponse getBookResponse(Book book) {
        List<String> categoryNames = book.getCategories()
                .stream()
                .map(Category::getName)
                .collect(Collectors.toList());

        BookResponse response = new BookResponse();
        response.setName(book.getName());
        response.setStock(book.getStock());
        response.setAuthorName(book.getAuthor().getName());
        response.setPublisherName(book.getPublisher().getName());
        response.setCategories(categoryNames);
        response.setPublicationYear(book.getPublicationYear());
        return response;
    }
}
