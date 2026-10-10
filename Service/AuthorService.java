package com.management.libraryManagementSystem.Service;

import com.management.libraryManagementSystem.DTO.AuthorDTO.AuthorRequestDTO;
import com.management.libraryManagementSystem.DTO.AuthorDTO.AuthorResponseDTO;
import com.management.libraryManagementSystem.Entity.Author;
import com.management.libraryManagementSystem.Repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public AuthorResponseDTO saveAuthorService(AuthorRequestDTO authorRequestDTO){
        Author author = new Author();
        author.setAuthorName(authorRequestDTO.getAuthorName());
        author.setAge(authorRequestDTO.getAge());
        author.setGender(authorRequestDTO.getGender());
        author.setCreatedAt(LocalDateTime.now());

        authorRepository.save(author);

        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
        authorResponseDTO.setAuthorName(author.getAuthorName());
        authorResponseDTO.setGender(author.getGender());
        authorResponseDTO.setAge(author.getAge());
        authorResponseDTO.setCreatedAt(author.getCreatedAt());

        return  authorResponseDTO;
    }
}
