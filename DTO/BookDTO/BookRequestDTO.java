package com.management.libraryManagementSystem.DTO.BookDTO;

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
public class BookRequestDTO {

    @NotBlank(message = "Enter Valid Book Name")
    private String name;

    @NotBlank(message = "Book Edition should not Blank")
    private String edition;

    @Min(value = 0, message = "Please enter Valid Price")
    private double price;

    @NotBlank(message = "Publisher name can't be Empty")
    private String publishedBy;

}