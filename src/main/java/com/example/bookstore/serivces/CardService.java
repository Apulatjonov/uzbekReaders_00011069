package com.example.bookstore.serivces;

import com.example.bookstore.entities.Card;
import com.example.bookstore.entities.User;
import com.example.bookstore.exceptions.CardAlreadyExistException;
import com.example.bookstore.exceptions.CardNotFoundException;
import com.example.bookstore.exceptions.IncorrectCardDetailsException;
import com.example.bookstore.exceptions.UserNotFoundException;
import com.example.bookstore.models.CardDTO;
import com.example.bookstore.repos.CardRepo;
import com.example.bookstore.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/23/2024 01:47
 */

@Service
@AllArgsConstructor
public class CardService {
    private final UserRepo userRepo;
    private final CardRepo cardRepo;
    public CardDTO addCard(CardDTO dto){
        Optional<User> user = userRepo.findById(dto.getUserId());
        if (user.isEmpty()){
            throw new UserNotFoundException("User not found!", 404);
        }
        Optional<Card> cardByPan = cardRepo.findCardByPan(dto.getPan());
        if (cardByPan.isPresent() && cardByPan.get().getUser().getId().equals(dto.getUserId())){
            throw new CardAlreadyExistException("This card has already been added!", 401);
        }
        if(dto.getPan().length() != 16){
            throw new IncorrectCardDetailsException("Card details are invalid!", 401);
        }
        Card card = new Card(dto);
        card.setUser(user.get());
        CardDTO cardDTO = cardRepo.save(card).toDto();
        cardDTO.setUserId(user.get().getId());
        return cardDTO;
    }

    public CardDTO getOne(Long id){
        Optional<Card> cardEntity = cardRepo.findById(id);
        if (cardEntity.isEmpty()){
            throw new CardNotFoundException("Card not found!", 404);
        }
        CardDTO card = cardEntity.get().toDto();
        card.setUserId(cardEntity.get().getUser().getId());
        return card;
    }

    public CardDTO update(CardDTO dto){
        Optional<Card> cardEntity = cardRepo.findById(dto.getId());
        if (cardEntity.isEmpty()){
            throw new CardNotFoundException("Card not found!", 404);
        }
        Card card = new Card(dto);
        Card saved = cardRepo.save(card);
        return saved.toDto();
    }

    public Boolean delete(Long id){
        Optional<Card> cardEntity = cardRepo.findById(id);
        if (cardEntity.isEmpty()){
            throw new CardNotFoundException("Card not found!", 404);
        }
        cardRepo.deleteById(id);
        return true;
    }

}
