package com.management.libraryManagementSystem.DTO.BookDTO;

import com.management.libraryManagementSystem.Entity.Author;
import com.management.libraryManagementSystem.Entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {

    private String name;
    private String Edition;
    private double price;
    private String publishedBy;
    private LocalDateTime createdAt;

    public static BookResponseDTO toBookResponseDTO(Book book){
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setName(book.getName());
        bookResponseDTO.setPrice(book.getPrice());
        bookResponseDTO.setEdition(book.getEdition());
        bookResponseDTO.setPublishedBy(book.getPublishedBy());
        bookResponseDTO.setCreatedAt(LocalDateTime.now());
        return bookResponseDTO;
    }

}
