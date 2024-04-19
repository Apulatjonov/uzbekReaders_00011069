package com.example.bookstore.serivces;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.BookRatedUsers;
import com.example.bookstore.entities.User;
import com.example.bookstore.exceptions.AlreadyRatedException;
import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.exceptions.UserNotFoundException;
import com.example.bookstore.models.BookDTO;
import com.example.bookstore.models.BookRatingDTO;
import com.example.bookstore.models.RateBookDTO;
import com.example.bookstore.repos.BookRatedUsersRepo;
import com.example.bookstore.repos.BookRepo;
import com.example.bookstore.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/24/2024 03:36
 */

@CrossOrigin
@Service
@AllArgsConstructor
public class BookService {
    private final BookRepo bookRepo;
    private final UserRepo userRepo;
    private final BookRatedUsersRepo rateRepo;

    public BookDTO getBook(Long id, Long userId) {
        Optional<User> userEn = userRepo.findById(userId);
        if (userEn.isEmpty()) {
            throw new UserNotFoundException(userId.toString(), 404);
        }
        User user = userEn.get();
        Optional<Book> entity = bookRepo.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException("Book not found!", 404);
        }
        Book book = entity.get();
        user.getRecentlyViewedBooks().add(book);
        userRepo.save(user);
        return new BookDTO().fromEntity(book);
    }

    public BookDTO addBook(BookDTO dto) {
        Book book = dto.toEntity();
        Book saved = bookRepo.save(book);
        return new BookDTO().fromEntity(saved);
    }

    public List<BookDTO> search(String search) {
        List<Book> list = bookRepo.searchBooksByAuthorOrCategoryOrTitle(search, search, search);
        List<BookDTO> books = new ArrayList<>();
        for (Book book : list) {
            books.add(new BookDTO().fromEntity(book));
        }
        return books;
    }

    public List<BookDTO> getBooks() {
        Sort sort = Sort.by(Sort.Direction.ASC, "score");
        Page<Book> books = bookRepo.findAll(PageRequest.of(0, 10, sort));
        List<BookDTO> collect = books.stream()
                .map(ent -> new BookDTO(ent))
                .collect(Collectors.toList());
        return collect;
    }

    public RateBookDTO rate(BookRatingDTO dto) {
        Optional<User> userEnt = userRepo.findById(dto.getUserId());
        if (userEnt.isEmpty()) {
            throw new NotFoundException(dto.getUserId().toString(), 404);
        }
        User user = userEnt.get();
        Optional<Book> ent = bookRepo.findById(dto.getBookId());
        if (ent.isEmpty()) {
            throw new NotFoundException(dto.getBookId().toString(), 404);
        }
        Book book = ent.get();
        Optional<BookRatedUsers> rateEnt = rateRepo.findByUserIdAndBookId(dto.getUserId(), dto.getBookId());
        if (rateEnt.isPresent()) {
            throw new AlreadyRatedException("Already rated!", 402);
        }
        BookRatedUsers rating = new BookRatedUsers();
        rating.setBookId(book.getId());
        rating.setUserId(dto.getUserId());
        rating.setScore(dto.getRating());
        BookRatedUsers saved = rateRepo.save(rating);
        int size = rateRepo.findAllByBookId(dto.getBookId()).size();
        long newScore = dto.getRating();
        if (size > 0) {
            long allScore = book.getScore() * size + dto.getRating();
            newScore = allScore / (size + 1);
        }
        book.setScore(newScore);
        Book save = bookRepo.save(book);
        user.getRatedBooks().add(save);
        userRepo.save(user);
        return saved.toDTO(newScore);
    }

    public List<BookDTO> currentlyReading(Long id) {
        Optional<User> userEnt = userRepo.findById(id);
        if (userEnt.isEmpty()) {
            throw new UserNotFoundException(id.toString(), 404);
        }
        User user = userEnt.get();
        List<BookDTO> books = user.getBooks().stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());
        return books;
    }

    public List<BookDTO> getRecentlyViewed(Long id) {
        Optional<User> userEnt = userRepo.findById(id);
        if (userEnt.isEmpty()) {
            throw new UserNotFoundException(id.toString(), 404);
        }
        User user = userEnt.get();
        List<BookDTO> books = user.getRecentlyViewedBooks().stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());
        return books;
    }

    public List<BookDTO> getRatedBooks(Long id) {
        Optional<User> userEnt = userRepo.findById(id);
        if (userEnt.isEmpty()) {
            throw new UserNotFoundException(id.toString(), 404);
        }
        User user = userEnt.get();
        List<BookDTO> books = user.getRatedBooks().stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());
        return books;
    }

    public List<BookDTO> getByCategory(String category) {
        return bookRepo.findAllByCategory(category).stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());
    }
}
