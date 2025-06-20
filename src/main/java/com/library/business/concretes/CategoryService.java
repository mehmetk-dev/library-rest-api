package com.library.business.concretes;

import com.library.business.abstracts.ICategoryService;
import com.library.core.config.exception.NotFoundException;
import com.library.dao.CategoryRepo;
import com.library.entities.Category;
import com.library.utilies.MessageConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public Category get(int id) {
        return this.categoryRepo.findById(id).orElseThrow(()->new NotFoundException(MessageConstants.NOT_FOUND));
    }

    @Override
    public Category update(Category category) {
        this.get(category.getId());
        return this.categoryRepo.save(category);
    }

    @Override
    public String delete(int id) {
        Category category = this.get(id);
        if (!category.getBookList().isEmpty()) {
            return "Bu kategoriye ait kitaplar var, silinemez.";
        }else{
            this.categoryRepo.delete(this.get(id));
            return "Kategori Silindi";
        }
    }

    @Override
    public Page<Category> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.categoryRepo.findAll(pageable);
    }

    @Override
    public List<Category> getAllByIds(List<Integer> ids) {
        List<Category> categories = this.categoryRepo.findAllById(ids);

        if (categories.size() != ids.size()) {
            throw new NotFoundException("Bazı kategori ID'leri geçersiz: " + ids);
        }

        return categories;
    }
}
