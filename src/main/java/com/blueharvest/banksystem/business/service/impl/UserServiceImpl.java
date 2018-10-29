package com.blueharvest.banksystem.business.service.impl;

import com.blueharvest.banksystem.exceptions.UserNotFoundException;
import com.blueharvest.banksystem.model.Account;
import com.blueharvest.banksystem.model.Transaction;
import com.blueharvest.banksystem.model.User;
import com.blueharvest.banksystem.model.UserInfo;
import com.blueharvest.banksystem.repository.AccountRepository;
import com.blueharvest.banksystem.repository.TransactionRepository;
import com.blueharvest.banksystem.repository.UserRepository;
import com.blueharvest.banksystem.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * If the user exist create a DTO with user information like firstname, lastname, balance, and transactions of his accounts
     * If not throw an Exception
     * @param id
     * @return a list of {@link UserInfo}
     * @throws Exception
     */
    @Override
    public UserInfo createUserInfos(Long id) throws Exception {


        Optional<User> user = Optional.ofNullable(userRepository.findUserById(id));
        if (user.isPresent()) {

            //Find and store user's firstname and lastName to DTO
            UserInfo userInfo = new UserInfo();
            userInfo.setFirstName(user.get().getFirstName());
            userInfo.setLastName(user.get().getLastName());

            //Find user's accounts, add all initial credits and store the balance to DTO
            List<Account> accountList =  accountRepository.findAccountByCustomerID(id);
            float balance = 0;
            for(int i=0; i< accountList.size();i++){
                balance= balance + accountList.get(i).getInitialCredit();
            }
            userInfo.setBalance(balance);

            //Find user's transactions and store them to DTO
            List<Transaction> transactionList = transactionRepository.findTransactionByCustomerID(id);
            userInfo.setTransactions(transactionList);

            return userInfo;

        }else{
            throw new UserNotFoundException(String.valueOf(id));
        }
    }

}
