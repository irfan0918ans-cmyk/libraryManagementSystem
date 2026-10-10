package com.management.libraryManagementSystem.DTO.AuthorDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequestDTO {

    private String authorName;
    private String gender;
    private Integer age;

}
