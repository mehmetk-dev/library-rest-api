package com.library.api;

import com.library.business.abstracts.IAuthorService;
import com.library.business.abstracts.IBookService;
import com.library.business.abstracts.ICategoryService;
import com.library.business.abstracts.IPublisherService;
import com.library.core.config.modelMapper.IModelMapperService;
import com.library.core.result.Result;
import com.library.core.result.ResultData;
import com.library.dto.request.Book.BookSaveRequest;
import com.library.dto.request.Book.BookUpdateRequest;
import com.library.dto.response.CursorResponse;
import com.library.dto.response.book.BookResponse;
import com.library.entities.Book;
import com.library.utilies.ResultHelper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final IBookService bookService;
    private final IModelMapperService modelMapper;
    private final IAuthorService authorService;
    private final ICategoryService categoryService;
    private final IPublisherService publisherService;


    public BookController(IBookService bookService,
                          IModelMapperService modelMapper,
                          IAuthorService authorService,
                          ICategoryService categoryService,
                          IPublisherService publisherService) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.publisherService = publisherService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest){
        BookResponse bookResponse = this.bookService.saveBook(bookSaveRequest);
        return ResultHelper.created(bookResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> get(@PathVariable("id")int id){
        BookResponse bookResponse = this.bookService.getBookResponse(this.bookService.get(id));
        return ResultHelper.success(bookResponse);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> update(@PathVariable int id, @Valid @RequestBody BookUpdateRequest request) {
        BookResponse response = bookService.update(id, request);
        return ResultHelper.success(response);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable int id) {
        this.bookService.delete(id);
        return new Result(true,"Kayıt Silindi","200");
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize
    ) {
        Page<Book> books = this.bookService.cursor(page, pageSize);
        Page<BookResponse> bookResponsePage = books
                .map(this.bookService::getBookResponse);

        return ResultHelper.cursor(bookResponsePage);
    }
}
