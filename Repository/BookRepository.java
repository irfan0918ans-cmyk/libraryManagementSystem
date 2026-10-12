package com.management.libraryManagementSystem.Repository;

import com.management.libraryManagementSystem.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book,Integer> {

    Optional<Book> findByNameContainingIgnoreCaseAndIsDeletedFalseAndAuthor_IsDeletedFalse(String name);
    List<Book> findByIsDeletedFalseAndAuthor_IsDeletedFalse();

    List<Book> findByAuthorIdAndIsDeletedFalse(Integer id);
}
