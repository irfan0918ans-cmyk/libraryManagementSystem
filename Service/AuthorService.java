package com.management.libraryManagementSystem.Service;

import com.management.libraryManagementSystem.DTO.AuthorDTO.AuthorRequestDTO;
import com.management.libraryManagementSystem.DTO.AuthorDTO.AuthorResponseDTO;
import com.management.libraryManagementSystem.Entity.Author;
import com.management.libraryManagementSystem.Exception.AuthorNotFoundException;
import com.management.libraryManagementSystem.Repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

        return AuthorResponseDTO.toResponseDTO(author);
    }

    public AuthorResponseDTO findByNameAuthorService(String name){
        Author author = authorRepository.findByAuthorNameContainingIgnoreCaseAndIsDeletedFalse(name)
                .orElseThrow(()-> new AuthorNotFoundException("Author Not Found"));

        return AuthorResponseDTO.toResponseDTO(author);
    }

    public List<AuthorResponseDTO> getAllAuthorService(){
       return authorRepository.findByIsDeletedFalse()
                .stream()
                .map(author -> AuthorResponseDTO.toResponseDTO(author))
                .toList();
    }

    public AuthorResponseDTO updateAuthorService(String name, AuthorRequestDTO authorRequestDTO){
        Author author = authorRepository.findByAuthorNameContainingIgnoreCaseAndIsDeletedFalse(name)
                .orElseThrow(()-> new AuthorNotFoundException("Author Not Found"));

        author.setAuthorName(authorRequestDTO.getAuthorName());
        author.setAge(authorRequestDTO.getAge());
        author.setGender(authorRequestDTO.getGender());
        author.setUpdatedAt(LocalDateTime.now());

        authorRepository.save(author);

        return AuthorResponseDTO.toResponseDTO(author);
    }

    public void softDeleteService(String name){
        Author author = authorRepository.findByAuthorNameContainingIgnoreCaseAndIsDeletedFalse(name)
                .orElseThrow(()-> new AuthorNotFoundException("Author Not Found"));

        author.setIsDeleted(true);
        author.setDeletedAt(LocalDateTime.now());
        authorRepository.save(author);
    }
}
