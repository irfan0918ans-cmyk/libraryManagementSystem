package com.management.libraryManagementSystem.Repository;

import com.management.libraryManagementSystem.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

    Optional<Author> findByAuthorNameContainingIgnoreCaseAndIsDeletedFalse(String name);
    List<Author> findByIsDeletedFalse();
}
