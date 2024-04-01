package com.example.bookstore.serivces;

import com.example.bookstore.entities.Book;
import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.models.BookDTO;
import com.example.bookstore.repos.BookRepo;
import com.example.bookstore.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/24/2024 03:36
 */

@Service
@AllArgsConstructor
public class BookService {
    private final UserRepo userRepo;
    private final BookRepo bookRepo;
    public BookDTO getBook(Long id){
        Optional<Book> entity = bookRepo.findById(id);
        if (entity.isEmpty()){
            throw new NotFoundException("Book not found!", 404);
        }
        Book book = entity.get();
        return new BookDTO().fromEntity(book);
    }
    public BookDTO addBook(BookDTO dto){
        Book book = dto.toEntity();
        Book saved = bookRepo.save(book);
        return new BookDTO().fromEntity(saved);
    }
    public List<BookDTO> search(String search){
        List<Book> list = bookRepo.searchBooksByAuthorOrCategoryOrTitle(search, search, search);
        List<BookDTO> books = new ArrayList<>();
        for (Book book : list){
            books.add(new BookDTO().fromEntity(book));
        }
        return books;
    }
}
