package com.management.libraryManagementSystem.DTO.AuthorDTO;

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

}
