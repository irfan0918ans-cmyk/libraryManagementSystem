package com.management.libraryManagementSystem.Controller;

import com.management.libraryManagementSystem.DTO.AuthorDTO.AuthorRequestDTO;
import com.management.libraryManagementSystem.DTO.AuthorDTO.AuthorResponseDTO;
import com.management.libraryManagementSystem.Service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getAuthor")
    public ResponseEntity<AuthorResponseDTO> getAuthorByNameController(@RequestParam String name){
        return  ResponseEntity.status(HttpStatus.OK)
                .body(authorService.findByNameAuthorService(name));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthorController(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorService.getAllAuthorService());
    }

    @PutMapping("/update")
    public ResponseEntity<AuthorResponseDTO> updateAuthorController(@RequestParam String name,
                                                                    @RequestBody AuthorRequestDTO authorRequestDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorService.updateAuthorService(name,authorRequestDTO));
    }

    @PatchMapping("/delete")
    public ResponseEntity<String> softDeleteController(@RequestParam String name){
       authorService.softDeleteService(name);
       return ResponseEntity.status(HttpStatus.OK)
               .body("Author Deleted Successfully");

    }

}
