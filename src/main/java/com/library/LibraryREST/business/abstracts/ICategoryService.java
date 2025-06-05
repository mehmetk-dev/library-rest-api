package com.library.LibraryREST.business.abstracts;

import com.library.LibraryREST.entities.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryService {

    Category save(Category category);

    Category get(int id);

    Category update(Category category);

    String delete(int id);

    Page<Category> cursor(int page, int pageSize);

    List<Category> getAllByIds(List<Integer>ids);
}
