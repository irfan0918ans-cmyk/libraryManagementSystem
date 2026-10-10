package com.management.libraryManagementSystem.Controller;

import com.management.libraryManagementSystem.DTO.AuthorDTO.AuthorRequestDTO;
import com.management.libraryManagementSystem.DTO.AuthorDTO.AuthorResponseDTO;
import com.management.libraryManagementSystem.Service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author/api")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostMapping("/save")
    public ResponseEntity<AuthorResponseDTO> saveAuthorController(@RequestBody AuthorRequestDTO authorRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authorService.saveAuthorService(authorRequestDTO));
    }

}
