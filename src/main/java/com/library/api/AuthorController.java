package com.library.api;

import com.library.business.abstracts.IAuthorService;
import com.library.core.config.modelMapper.IModelMapperService;
import com.library.core.result.Result;
import com.library.core.result.ResultData;
import com.library.dto.request.author.AuthorSaveRequest;
import com.library.dto.request.author.AuthorUpdateRequest;
import com.library.dto.response.CursorResponse;
import com.library.dto.response.author.AuthorResponse;
import com.library.entities.Author;
import com.library.utilies.MessageConstants;
import com.library.utilies.ResultHelper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {

    private final IAuthorService authorService;
    private final IModelMapperService modelMapper;

    public AuthorController(IAuthorService authorService, IModelMapperService modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AuthorResponse> save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest){
        Author author = this.modelMapper.forRequest().map(authorSaveRequest,Author.class);
        this.authorService.save(author);
        return ResultHelper.created(this.modelMapper.forResponse().map(author,AuthorResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> get(@PathVariable("id") int id){
        Author author = this.authorService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(author,AuthorResponse.class));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> update(@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest){
        Author author = this.modelMapper.forRequest().map(authorUpdateRequest,Author.class);
        this.authorService.update(author);
        return ResultHelper.success(this.modelMapper.forResponse().map(author,AuthorResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AuthorResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize
    ) {
        Page<Author> authorPage = this.authorService.cursor(page, pageSize);
        Page<AuthorResponse> authorResponsePage = authorPage
                .map(authors -> this.modelMapper.forResponse().map(authors, AuthorResponse.class));

        return ResultHelper.cursor(authorResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.authorService.delete(id);
        return new Result(true, MessageConstants.SUCCESS,"200");
    }
}
