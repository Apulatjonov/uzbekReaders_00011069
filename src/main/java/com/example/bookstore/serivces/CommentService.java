package com.example.bookstore.serivces;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.BookStats;
import com.example.bookstore.entities.Comment;
import com.example.bookstore.entities.User;
import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.models.CommentDTO;
import com.example.bookstore.repos.BookRepo;
import com.example.bookstore.repos.BookStatsRepo;
import com.example.bookstore.repos.CommentRepo;
import com.example.bookstore.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 04/09/2024 11:19
 */

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepo repo;
    private final BookRepo bookRepo;
    private final UserRepo userRepo;
    private final BookStatsRepo bookStatsRepo;

    public CommentDTO addComment(CommentDTO dto) {
        Optional<Book> book = bookRepo.findById(dto.getBookId());
        if (book.isEmpty()){
            throw new RuntimeException();
        }
        Optional<User> user = userRepo.findById(dto.getUserId());
        if (user.isEmpty()){
            throw new RuntimeException();
        }
        Comment comment = new Comment();
        comment.setBody(dto.getBody());
        comment.setUser(user.get().getFirstName() + " " + user.get().getLastName());
        comment.setBook(book.get());
        comment.setUserId(user.get().getId());

        Optional<BookStats> statsEnt = bookStatsRepo.findByBookId(book.get().getId());
        BookStats stats = statsEnt.get();
        stats.setComments(stats.getComments() + 1);
        bookStatsRepo.save(stats);

        return new CommentDTO().fromEntity(repo.save(comment));
    }

    public void deleteComment(Long id) {
        Optional<Comment> ent = repo.findById(id);
        if (ent.isEmpty()){
            throw new NotFoundException("Comment not found!", 404);
        }
        Optional<BookStats> statsEnt = bookStatsRepo.findByBookId(ent.get().getBook().getId());
        BookStats stats = statsEnt.get();
        stats.setComments(stats.getComments() - 1);
        bookStatsRepo.save(stats);
        repo.deleteById(ent.get().getId());
    }
}
