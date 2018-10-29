package com.blueharvest.banksystem.business.service.impl;

import com.blueharvest.banksystem.model.Account;
import com.blueharvest.banksystem.model.Transaction;
import com.blueharvest.banksystem.model.User;
import com.blueharvest.banksystem.model.UserInfo;
import com.blueharvest.banksystem.repository.AccountRepository;
import com.blueharvest.banksystem.repository.TransactionRepository;
import com.blueharvest.banksystem.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceImpl.class)
public class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    public void Given_CustomerID_When_UserExists_Then_CreateAndReturnUserInfos() throws Exception {
        User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setId(1L);

        Account account = new Account();
        Account account2 = new Account();
        account.setInitialCredit(100F);
        account.setCustomerID(1L);
        account.setId(1L);

        account2.setInitialCredit(150F);
        account2.setCustomerID(1L);
        account2.setId(2L);

        List<Account> accountList = Arrays.asList(account, account2);

        Transaction transaction = new Transaction();
        Transaction transaction2 = new Transaction();
        transaction.setCustomerID(1L);
        transaction2.setCustomerID(1L);

        List<Transaction> transactions = Arrays.asList(transaction, transaction2);

        Mockito.when(userRepository.findUserById(1L)).thenReturn(user);
        Mockito.when(accountRepository.findAccountByCustomerID(1L)).thenReturn(accountList);
        Mockito.when(transactionRepository.findTransactionByCustomerID(1L)).thenReturn(transactions);

        UserInfo userInfo = userService.createUserInfos(1L);

        Assert.assertTrue(userInfo.getTransactions().size() == 2);

        Mockito.verify(userRepository).findUserById(1L);
        Mockito.verify(accountRepository).findAccountByCustomerID(1L);
        Mockito.verify(transactionRepository).findTransactionByCustomerID(1L);

        Mockito.verifyNoMoreInteractions(userRepository);
        Mockito.verifyNoMoreInteractions(accountRepository);
        Mockito.verifyNoMoreInteractions(transactionRepository);

    }
    @Test
    public void Given_CustomerID_When_UserNotExists_Then_ThrowUserNotFoundException() {

        Account account = new Account();
        account.setCustomerID(1L);
        account.setInitialCredit(10.0F);

        Transaction transaction = new Transaction();
        transaction.setCustomerID(2L);
        transaction.setAccountID(1l);
        transaction.setMoney(100F);
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Date date = new java.sql.Date(currentDate.getTime());
        transaction.setCurrentDate(date);

        User user = new User();
        user.setId(1L);
        user.setFirstName("Kostas");

        Mockito.when(userRepository.findUserById(user.getId())).thenReturn(null);

        try{
            UserInfo userInfo = userService.createUserInfos(1L);
        }catch (Exception e){
            Assert.assertTrue(e.getMessage().contains("User with id 1 not found.") );
        }
        Mockito.verify(userRepository).findUserById(1L);
        Mockito.verifyNoMoreInteractions(userRepository);
    }
}
