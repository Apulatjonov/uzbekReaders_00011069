package com.example.bookstore.serivces;

import com.example.bookstore.entities.User;
import com.example.bookstore.exceptions.InvalidEmailAddressException;
import com.example.bookstore.exceptions.PasswordIncorrectException;
import com.example.bookstore.exceptions.UserAlreadyExistException;
import com.example.bookstore.exceptions.UserNotFoundException;
import com.example.bookstore.models.BaseUser;
import com.example.bookstore.models.UserDTO;
import com.example.bookstore.repos.UserRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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


    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
        this.pattern = Pattern.compile("^(.+)@(.+)$");
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
}
