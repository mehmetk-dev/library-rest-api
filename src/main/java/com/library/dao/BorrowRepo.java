package com.library.dao;

import com.library.entities.BookBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepo extends JpaRepository<BookBorrowing,Integer> {
    boolean existsByBookId(int bookId);
    @Query("SELECT b.borrowerEmail FROM BookBorrowing b WHERE b.id = :id")
    String findEmailById(@Param("id") int id);
}
