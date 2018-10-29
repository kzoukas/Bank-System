package com.blueharvest.banksystem.business.service.impl;

import com.blueharvest.banksystem.exceptions.UserNotFoundException;
import com.blueharvest.banksystem.model.Account;
import com.blueharvest.banksystem.model.User;
import com.blueharvest.banksystem.repository.AccountRepository;
import com.blueharvest.banksystem.repository.UserRepository;
import com.blueharvest.banksystem.business.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;

    /**
     * If the user exists save the account.
     * @param account
     * @return Saved account
     * @throws UserNotFoundException
     */
    @Override
    public Account addAccount(Account account) throws Exception{

        Long id = account.getCustomerID();
        Optional<User> user = Optional.ofNullable(userRepository.findUserById(id));

        if (user.isPresent()) {
            return accountRepository.save(account);
        }else{
            throw new UserNotFoundException(String.valueOf(id));
        }
    }

}
