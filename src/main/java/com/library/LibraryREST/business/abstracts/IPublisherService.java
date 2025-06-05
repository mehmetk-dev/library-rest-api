package com.library.LibraryREST.business.abstracts;

import com.library.LibraryREST.entities.Publisher;
import org.springframework.data.domain.Page;

public interface IPublisherService {

    Publisher save(Publisher publisher);

    Publisher get(int id);

    Publisher update(Publisher publisher);

    String delete(int id);

    Page<Publisher> cursor(int page, int pageSize);
}
