package com.management.libraryManagementSystem.DTO.AuthorDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequestDTO {

    @NotBlank(message = "Enter Valid Name")
    private String authorName;

    @NotBlank(message = "Gender is Required")
    private String gender;

    @Min(value = 15, message = "Age should greater than 15")
    private Integer age;

}
