package com.library.business.concretes;

import com.library.business.abstracts.IBookService;
import com.library.business.abstracts.IBorrowService;
import com.library.core.config.exception.BookAlreadyBorrowedException;
import com.library.core.config.exception.BookUnavailableException;
import com.library.core.config.exception.NotFoundException;
import com.library.core.config.modelMapper.IModelMapperService;
import com.library.dao.BorrowRepo;
import com.library.dto.request.borrow.BorrowSaveRequest;
import com.library.dto.request.borrow.BorrowUpdateRequest;
import com.library.dto.response.borrow.BorrowResponse;
import com.library.entities.Book;
import com.library.entities.BookBorrowing;
import com.library.utilies.MessageConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowService implements IBorrowService {

    private final BorrowRepo borrowRepo;
    private final IModelMapperService modelMapper;
    private final IBookService bookService;

    public BorrowService(BorrowRepo borrowRepo, IModelMapperService modelMapper, IBookService bookService) {
        this.borrowRepo = borrowRepo;
        this.modelMapper = modelMapper;
        this.bookService = bookService;
    }

    @Override
    public BorrowResponse get(int id) {
        BookBorrowing borrow = this.borrowRepo.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.NOT_FOUND));
        return getBorrowResponse(borrow);
    }

    @Override
    public BorrowResponse update(BorrowUpdateRequest borrowUpdateRequest) {
        BookBorrowing borrowing = this.modelMapper.forRequest().map(borrowUpdateRequest, BookBorrowing.class);
        borrowing.setBorrowerEmail(borrowRepo.findEmailById(borrowing.getId()));
        this.borrowRepo.save(borrowing);
        return getBorrowResponse(borrowing);
    }

    @Override
    public String delete(int id) {
        BookBorrowing borrowing = this.borrowRepo.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.NOT_FOUND));
        if (borrowing.getReturnDate().isAfter(LocalDate.now())){
            return "Kitabın iade süresi henüz dolmamış, silinemez.";
        }else{
            this.borrowRepo.delete(borrowing);
        }
        return "Kiralama silindi.";
    }

    @Override
    public BorrowResponse save(BorrowSaveRequest borrowSaveRequest) {

        Book book = this.bookService.get(borrowSaveRequest.getBookId());
        validateBookAvailability(book);

        BookBorrowing borrow = new BookBorrowing();
        borrow.setBorrowerName(borrowSaveRequest.getBorrowerName());
        borrow.setBorrowerEmail(borrowSaveRequest.getBorrowerEmail());
        borrow.setBorrowingDate(borrowSaveRequest.getBorrowingDate());
        borrow.setReturnDate(borrowSaveRequest.getReturnDate());
        borrow.setBook(book);

        this.borrowRepo.save(borrow);

        return getBorrowResponse(borrow);
    }

    public BorrowResponse getBorrowResponse(BookBorrowing borrowing){
        BorrowResponse borrow = new BorrowResponse();

        borrow.setBorrowerName(borrowing.getBorrowerName());
        borrow.setBorrowerEmail(borrowing.getBorrowerEmail());
        borrow.setBorrowingDate(borrowing.getBorrowingDate());
        borrow.setReturnDate(borrowing.getReturnDate());
        borrow.setBookName(borrowing.getBook().getName());
        borrow.setId(borrowing.getId());

        return borrow;
    }

    private void validateBookAvailability(Book book) {
        if (this.borrowRepo.existsByBookId(book.getId())) {
            throw new BookAlreadyBorrowedException("Kitap zaten kiralanmış.");
        }

        if (book.getStock() <= 0) {
            throw new BookUnavailableException("Kitabın stoğu tükenmiş.");
        }
    }

    @Override
    public Page<BookBorrowing> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.borrowRepo.findAll(pageable);
    }
}
