package com.library.LibraryREST.business.concretes;

import com.library.LibraryREST.business.abstracts.IPublisherService;
import com.library.LibraryREST.core.config.exception.NotFoundException;
import com.library.LibraryREST.dao.PublisherRepo;
import com.library.LibraryREST.entities.Publisher;
import com.library.LibraryREST.utilies.MessageConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PublisherService implements IPublisherService {
    private final PublisherRepo publisherRepo;

    public PublisherService(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public Publisher save(Publisher publisher) {
        return this.publisherRepo.save(publisher);
    }

    @Override
    public Publisher get(int id) {
        return this.publisherRepo.findById(id).orElseThrow(()-> new NotFoundException(MessageConstants.NOT_FOUND));
    }

    @Override
    public Publisher update(Publisher publisher) {
        this.get(publisher.getId());
        return this.publisherRepo.save(publisher);
    }

    @Override
    public String delete(int id) {
         this.publisherRepo.delete(this.get(id));
         return "";
    }

    @Override
    public Page<Publisher> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.publisherRepo.findAll(pageable);
    }
}
