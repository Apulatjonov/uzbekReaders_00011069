package com.example.bookstore.serivces;

import com.example.bookstore.entities.Card;
import com.example.bookstore.entities.Payment;
import com.example.bookstore.entities.User;
import com.example.bookstore.exceptions.CardNotFoundException;
import com.example.bookstore.exceptions.InsufficientCardBalanceException;
import com.example.bookstore.exceptions.UserNotFoundException;
import com.example.bookstore.models.CardDTO;
import com.example.bookstore.models.PaymentDTO;
import com.example.bookstore.repos.CardRepo;
import com.example.bookstore.repos.PaymentRepo;
import com.example.bookstore.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/23/2024 02:11
 */

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepo paymentRepo;
    private final UserRepo userRepo;
    private final CardRepo cardRepo;
    public PaymentDTO pay(PaymentDTO dto){
        Optional<User> userEntity = userRepo.findById(dto.getUserId());
        if (userEntity.isEmpty()){
            throw new UserNotFoundException("User not found!", 404);
        }
        Optional<Card> cardEntity = cardRepo.findById(dto.getCardId());
        if (cardEntity.isEmpty()){
            throw new CardNotFoundException("Card not found!", 404);
        }
        Card card = cardEntity.get();
        if (card.getBalance() - dto.getAmount() < 0.0){
            throw new InsufficientCardBalanceException("Insufficient card balance", 401);
        }
        card.setBalance(card.getBalance() - dto.getAmount());
        int months = dto.getAmount().intValue() / 5;
        User user = userEntity.get();
        if (user.getPaidTill().isBefore(LocalDateTime.now())){
            user.setPaidTill(LocalDateTime.now().plusMonths(months));
        }else{
            user.setPaidTill(user.getPaidTill().plusMonths(months));
        }
        user = userRepo.save(user);
        card = cardRepo.save(card);
        Payment payment = new Payment(dto);
        payment.setCard(card);
        payment.setUser(user);
        return paymentRepo.save(payment).toDto();
    }
    public CardDTO fillCard(PaymentDTO dto){
        Optional<User> userEntity = userRepo.findById(dto.getUserId());
        if(userEntity.isEmpty()){
            throw new UserNotFoundException("User not found!", 404);
        }
        Optional<Card> cardEntity = cardRepo.findById(dto.getCardId());
        if (cardEntity.isEmpty()){
            throw new CardNotFoundException("Card not found!", 404);
        }
        Card card = cardEntity.get();
        card.setBalance(card.getBalance() + dto.getAmount());
        return cardRepo.save(card).toDto();
    }
}
