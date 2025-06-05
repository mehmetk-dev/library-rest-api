package com.library.business.concretes;

import com.library.business.abstracts.IAuthorService;
import com.library.core.config.exception.NotFoundException;
import com.library.dao.AuthorRepo;
import com.library.entities.Author;
import com.library.utilies.MessageConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorService implements IAuthorService {

    private final AuthorRepo authorRepo;

    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author save(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public Author get(int id) {
        return this.authorRepo.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.NOT_FOUND));
    }

    @Override
    public Author update(Author author) {
        this.get(author.getId());
        return this.save(author);
    }

    @Override
    public boolean delete(int id) {
        this.authorRepo.delete(this.get(id));
        return true;
    }

    @Override
    public Page<Author> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return  this.authorRepo.findAll(pageable);
    }
}
