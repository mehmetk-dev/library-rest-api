package com.library.LibraryREST.business.concretes;

import com.library.LibraryREST.business.abstracts.IAuthorService;
import com.library.LibraryREST.core.config.exception.NotFoundException;
import com.library.LibraryREST.dao.AuthorRepo;
import com.library.LibraryREST.entities.Author;
import com.library.LibraryREST.utilies.MessageConstants;
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
