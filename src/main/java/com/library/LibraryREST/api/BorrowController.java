package com.library.LibraryREST.api;

import com.library.LibraryREST.business.abstracts.IBorrowService;
import com.library.LibraryREST.core.config.modelMapper.IModelMapperService;
import com.library.LibraryREST.core.result.ResultData;
import com.library.LibraryREST.dto.request.borrow.BorrowSaveRequest;
import com.library.LibraryREST.dto.request.borrow.BorrowUpdateRequest;
import com.library.LibraryREST.dto.response.CursorResponse;
import com.library.LibraryREST.dto.response.borrow.BorrowResponse;
import com.library.LibraryREST.entities.BookBorrowing;
import com.library.LibraryREST.utilies.ResultHelper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/borrows")
public class BorrowController {

    private final IBorrowService borrowService;
    private final IModelMapperService modelMapper;

    public BorrowController(IBorrowService borrowService, IModelMapperService modelMapper) {
        this.borrowService = borrowService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BorrowResponse> save(@Valid @RequestBody BorrowSaveRequest borrowSaveRequest){
        BorrowResponse borrowResponse = this.borrowService.save(borrowSaveRequest);
        return ResultHelper.created(borrowResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BorrowResponse> get(@PathVariable("id") int id){
        return ResultHelper.success(this.borrowService.get(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BorrowResponse> update(@Valid @RequestBody BorrowUpdateRequest borrowUpdateRequest){
        return ResultHelper.success(this.borrowService.update(borrowUpdateRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable("id") int id){
        return this.borrowService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BorrowResponse>> cursor(
            @RequestParam(name = "page", required = false,defaultValue = "0")int page,
            @RequestParam(name = "pageSize",required = false, defaultValue = "10")int pageSize
    ){
        Page<BookBorrowing> bookBorrowings = this.borrowService.cursor(page,pageSize);
        Page<BorrowResponse> borrowResponsePage = bookBorrowings
                .map(borrowing -> this.modelMapper.forResponse().map(borrowing, BorrowResponse.class));

        return ResultHelper.cursor(borrowResponsePage);
    }
}
