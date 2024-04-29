package com.example.bookstore.serivces;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.User;
import com.example.bookstore.exceptions.InvalidEmailAddressException;
import com.example.bookstore.exceptions.PasswordIncorrectException;
import com.example.bookstore.exceptions.UserAlreadyExistException;
import com.example.bookstore.exceptions.UserNotFoundException;
import com.example.bookstore.models.BaseUser;
import com.example.bookstore.models.BookOverAllStatsDTO;
import com.example.bookstore.models.FinStatsDTO;
import com.example.bookstore.models.UserDTO;
import com.example.bookstore.repos.BookRepo;
import com.example.bookstore.repos.BookStatsRepo;
import com.example.bookstore.repos.UserRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/11/2024 14:14
 */

@Service
public class UserService {
    private final UserRepo userRepo;
    private final Pattern pattern;
    private final BookStatsRepo bookStatsRepo;
    private final BookRepo bookRepo;


    public UserService(UserRepo userRepo, BookStatsRepo bookStatsRepo, BookRepo bookRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
        this.pattern = Pattern.compile("^(.+)@(.+)$");
        this.bookStatsRepo = bookStatsRepo;
    }
    public BaseUser signUp(BaseUser userDTO){
        Optional<User> entity = userRepo.findByEmail(userDTO.getEmail());
        if (entity.isPresent()){
            throw new UserAlreadyExistException("User with this email already exist!", 401);
        }
        if (!pattern.matcher(userDTO.getEmail()).matches()){
            throw new InvalidEmailAddressException("Email is not valid!", 401);
        }
        User user = new User(userDTO);
        user.setCreatedAt(LocalDateTime.now());
        user.setLastUpdated(LocalDateTime.now());
        return userRepo.save(user).toBaseUser();
    }
    public BaseUser signIn(BaseUser userDto){
        Optional<User> entity = userRepo.findByEmail(userDto.getEmail());
        if (entity.isEmpty()){
            throw new UserNotFoundException("User not found!", 404);
        }
        User user = entity.get();
        if (!userDto.getPassword().equals(user.getPassword())){
            throw new PasswordIncorrectException("Password incorrect", 401);
        }
        return user.toBaseUser();
    }
    public BaseUser fillUserProfile(UserDTO userDto){
        Optional<User> entity = userRepo.findByEmail(userDto.getEmail());
        if (entity.isEmpty()){
            throw new UserNotFoundException("User not found!", 404);
        }
        User user = entity.get();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setLastUpdated(LocalDateTime.now());
        User saved = userRepo.save(user);
        return saved.toBaseUser();
    }

    public Boolean deleteProfile(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()){
            throw new UserNotFoundException("User not found!", 404);
        }
        userRepo.deleteById(id);
        return true;
    }

    public UserDTO getProfile(Long id) {
        Optional<User> entity = userRepo.findById(id);
        if (entity.isEmpty()){
            throw new UserNotFoundException(id.toString(), 404);
        }
        User user = entity.get();
        return new UserDTO().fromEntity(user);
    }

    public BookOverAllStatsDTO getStats(Long id) {
        BookOverAllStatsDTO dto = new BookOverAllStatsDTO();
        Optional<User> userEnt = userRepo.findById(id);
        if (userEnt.isEmpty()){
            throw new UserNotFoundException("User not found!", 404);
        }
        User user = userEnt.get();
        bookRepo.findAllByUserId(user.getId()).stream()
                .forEach(book -> dto.add(bookStatsRepo.findByBookId(book.getId()).get()));
        return dto;
    }
}
