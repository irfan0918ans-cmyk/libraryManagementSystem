package com.management.libraryManagementSystem.Service;


import com.management.libraryManagementSystem.DTO.BookDTO.BookRequestDTO;
import com.management.libraryManagementSystem.DTO.BookDTO.BookResponseDTO;
import com.management.libraryManagementSystem.Entity.Author;
import com.management.libraryManagementSystem.Entity.Book;
import com.management.libraryManagementSystem.Exception.AuthorNotFoundException;
import com.management.libraryManagementSystem.Exception.BookNotFoundException;
import com.management.libraryManagementSystem.Repository.AuthorRepository;
import com.management.libraryManagementSystem.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public BookResponseDTO saveBookService(String authorName, BookRequestDTO bookRequestDTO){
        Author author = authorRepository.findByAuthorNameContainingIgnoreCaseAndIsDeletedFalse(authorName)
                        .orElseThrow(()-> new AuthorNotFoundException("Author not Found"));

        Book book = new Book();
        book.setName(bookRequestDTO.getName());
        book.setPrice(bookRequestDTO.getPrice());
        book.setEdition(bookRequestDTO.getEdition());
        book.setPublishedBy(bookRequestDTO.getPublishedBy());
        book.setAuthor(author);
        book.setCreatedAt(LocalDateTime.now());
        bookRepository.save(book);

        return BookResponseDTO.toBookResponseDTO(book);
    }

    public BookResponseDTO getBookByBookNameService(String name){
        Book book = bookRepository.findByNameContainingIgnoreCaseAndIsDeletedFalseAndAuthor_IsDeletedFalse(name)
                .orElseThrow(()-> new BookNotFoundException("Book Not Found"));

        return BookResponseDTO.toBookResponseDTO(book);
    }

    public List<BookResponseDTO> getBookByAuthorNameService(String name){
        Author author = authorRepository.findByAuthorNameContainingIgnoreCaseAndIsDeletedFalse(name)
                .orElseThrow(()-> new AuthorNotFoundException("Author Not Found"));

        List<Book> book = bookRepository.findByAuthorIdAndIsDeletedFalse(author.getId());

        return book
                .stream()
                .map(books -> BookResponseDTO.toBookResponseDTO(books))
                .toList();
    }

    public List<BookResponseDTO> getAllBookService(){
        return bookRepository.findByIsDeletedFalseAndAuthor_IsDeletedFalse()
                .stream()
                .map(book -> BookResponseDTO.toBookResponseDTO(book))
                .toList();
    }

    public BookResponseDTO updateBookService(String name, BookRequestDTO bookRequestDTO){
        Book book = bookRepository.findByNameContainingIgnoreCaseAndIsDeletedFalseAndAuthor_IsDeletedFalse(name)
                .orElseThrow(()-> new BookNotFoundException("Book Not Found"));

        book.setName(bookRequestDTO.getName());
        book.setPrice(bookRequestDTO.getPrice());
        book.setEdition(bookRequestDTO.getEdition());
        book.setPublishedBy(bookRequestDTO.getPublishedBy());
        book.setUpdatedAt(LocalDateTime.now());

        bookRepository.save(book);

        return BookResponseDTO.toBookResponseDTO(book);
    }

    public void softDeleteBookService(String name){
        Book book = bookRepository.findByNameContainingIgnoreCaseAndIsDeletedFalseAndAuthor_IsDeletedFalse(name)
                .orElseThrow(()-> new BookNotFoundException("Book Not Found"));

        book.setIsDeleted(true);
        book.setDeletedAt(LocalDateTime.now());
        bookRepository.save(book);
    }

    public void deleteBookService(String name){
        Book book = bookRepository.findByNameContainingIgnoreCaseAndIsDeletedFalseAndAuthor_IsDeletedFalse(name)
                .orElseThrow(()-> new BookNotFoundException("Book Not Found"));

        bookRepository.deleteById(book.getId());
    }
}
