package com.management.libraryManagementSystem.DTO.AuthorDTO;

import com.management.libraryManagementSystem.Entity.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponseDTO {

    private String authorName;
    private String gender;
    private Integer age;
    private LocalDateTime createdAt;

    public static AuthorResponseDTO toResponseDTO(Author author){
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
        authorResponseDTO.setAuthorName(author.getAuthorName());
        authorResponseDTO.setGender(author.getGender());
        authorResponseDTO.setAge(author.getAge());
        authorResponseDTO.setCreatedAt(author.getCreatedAt());
        return authorResponseDTO;
    }

}
