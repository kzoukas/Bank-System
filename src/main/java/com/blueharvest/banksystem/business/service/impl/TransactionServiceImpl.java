package com.blueharvest.banksystem.business.service.impl;

import com.blueharvest.banksystem.exceptions.UserNotFoundException;
import com.blueharvest.banksystem.model.Transaction;
import com.blueharvest.banksystem.model.User;
import com.blueharvest.banksystem.repository.TransactionRepository;
import com.blueharvest.banksystem.repository.UserRepository;
import com.blueharvest.banksystem.business.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;

    /**
     * If the user exist create a DTO with user information like firstname, lastname, balance, and transactions of his accounts.
     * @param transaction
     * @return Saved transaction
     * @throws UserNotFoundException
     */
    @Override
    public Transaction addTransaction(Transaction transaction) throws Exception{

        Long id = transaction.getCustomerID();
        Optional<User> user = Optional.ofNullable(userRepository.findUserById(id));

        if (user.isPresent()) {
            return transactionRepository.save(transaction);
        }else{
            throw new UserNotFoundException(String.valueOf(id));
        }
    }

}
