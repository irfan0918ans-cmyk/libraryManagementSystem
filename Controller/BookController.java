package com.management.libraryManagementSystem.Controller;

import com.management.libraryManagementSystem.DTO.BookDTO.BookRequestDTO;
import com.management.libraryManagementSystem.DTO.BookDTO.BookResponseDTO;
import com.management.libraryManagementSystem.Service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book/api")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping("/save")
    public ResponseEntity<BookResponseDTO> saveBookController(@RequestParam String name,
                                                              @RequestBody BookRequestDTO bookRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.saveBookService(name,bookRequestDTO));

    }

    @GetMapping("/getByBooKName")
    public ResponseEntity<BookResponseDTO> getBookByBookNameController(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.getBookByBookNameService(name));
    }

    @GetMapping("/getByAuthorName")
    public ResponseEntity<List<BookResponseDTO>> getBookByAuthorNameController(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.getBookByAuthorNameService(name));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BookResponseDTO>> getAllBookController(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.getAllBookService());
    }
}
