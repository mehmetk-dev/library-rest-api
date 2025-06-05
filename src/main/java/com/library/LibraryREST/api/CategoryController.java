package com.library.LibraryREST.api;

import com.library.LibraryREST.business.abstracts.ICategoryService;
import com.library.LibraryREST.core.config.modelMapper.IModelMapperService;
import com.library.LibraryREST.core.result.Result;
import com.library.LibraryREST.core.result.ResultData;
import com.library.LibraryREST.dto.request.category.CategorySaveRequest;
import com.library.LibraryREST.dto.request.category.CategoryUpdateRequest;
import com.library.LibraryREST.dto.response.CursorResponse;
import com.library.LibraryREST.dto.response.category.CategoryResponce;
import com.library.LibraryREST.entities.Category;
import com.library.LibraryREST.utilies.MessageConstants;
import com.library.LibraryREST.utilies.ResultHelper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
    private final ICategoryService categoryService;
    private final IModelMapperService modelMapper;

    public CategoryController(ICategoryService categoryService, IModelMapperService modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponce> save(@Valid @RequestBody CategorySaveRequest saveRequestRequest){
        Category category = this.modelMapper.forRequest().map(saveRequestRequest,Category.class);
        this.categoryService.save(category);
        return ResultHelper.created(this.modelMapper.forResponse().map(category, CategoryResponce.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponce> get(@PathVariable("id")int id){
        Category category = this.categoryService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(category, CategoryResponce.class));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponce> update(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest){
        Category category = this.modelMapper.forRequest().map(categoryUpdateRequest,Category.class);
        this.categoryService.save(category);
        return ResultHelper.success(this.modelMapper.forResponse().map(category, CategoryResponce.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable("id")int id){
        return this.categoryService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CategoryResponce>> cursor(
            @RequestParam(name = "page",required = false,defaultValue = "0")int page,
            @RequestParam(name = "pageSize",required = false,defaultValue = "3")int pageSize
    ){
        Page<Category> categoryPage = this.categoryService.cursor(page,pageSize);
        Page<CategoryResponce> categoryResponcePage = categoryPage
                .map(category -> this.modelMapper.forResponse().map(category, CategoryResponce.class));
        return ResultHelper.cursor(categoryResponcePage);
    }
}
