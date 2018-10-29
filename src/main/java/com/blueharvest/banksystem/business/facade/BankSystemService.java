package com.blueharvest.banksystem.business.facade;

import com.blueharvest.banksystem.business.service.AccountService;
import com.blueharvest.banksystem.business.service.TransactionService;
import com.blueharvest.banksystem.business.service.UserService;
import com.blueharvest.banksystem.exceptions.BadAccountException;
import com.blueharvest.banksystem.exceptions.BadCustomerIdException;
import com.blueharvest.banksystem.model.Account;
import com.blueharvest.banksystem.model.Transaction;
import com.blueharvest.banksystem.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Service
@Transactional(rollbackFor = Exception.class)
public class BankSystemService {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserService userService;

    /**
     * Add Account. If initial credit is not 0 a transaction will be sent to the new account.
     * @param account
     * @return saved account
     * @throws Exception
     */
    public Account addAccount(Account account) throws Exception {

        //Once the endpoint is called, a new account will be opened connected to the user whose ID is customerID
        Account savedAccount = accountService.addAccount(account);

        //if initialCredit is not 0, a transaction will be sent to the new account.
        if (savedAccount.getInitialCredit() > 0) {
            Transaction newTransaction = new Transaction();
            newTransaction.setMoney(savedAccount.getInitialCredit());
            newTransaction.setCustomerID(savedAccount.getCustomerID());
            newTransaction.setAccountID(savedAccount.getId());

            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            newTransaction.setCurrentDate(date);

            transactionService.addTransaction(newTransaction);
        }
        return savedAccount;
    }

    /**
     * Get user informationslike firstname, lastname, balance, and transactions of his accounts.
     * @param id
     * @return user informations
     * @throws Exception
     */
    public UserInfo createUserInfos(Long id) throws Exception {
        return userService.createUserInfos(id);
    }
}
