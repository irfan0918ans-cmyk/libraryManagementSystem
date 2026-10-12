package com.management.libraryManagementSystem.DTO.BookDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {

    private String name;
    private String edition;
    private double price;
    private String publishedBy;

}