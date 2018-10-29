package com.blueharvest.banksystem.business.service.impl;

import com.blueharvest.banksystem.model.Transaction;
import com.blueharvest.banksystem.model.User;
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

import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransactionServiceImpl.class)
public class TransactionServiceImplTest {

   @Autowired
    TransactionServiceImpl transactionService;

    @MockBean
    TransactionRepository transactionRepository;

    @MockBean
    UserRepository userRepository;

    @Test
    public void Given_Tansaction_When_UserExists_Then_SaveTransaction() throws Exception {

        Transaction transaction = new Transaction();

        transaction.setCustomerID(1L);
        transaction.setAccountID(1l);
        transaction.setMoney(100F);
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Date date = new java.sql.Date(currentDate.getTime());
        transaction.setCurrentDate(date);

        User user = new User();
        user.setId(1L);
        user.setFirstName("Kostas");

        Mockito.when(userRepository.findUserById(user.getId())).thenReturn(user);
        Mockito.when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction expectedTransaction = transactionService.addTransaction(transaction);
        Assert.assertEquals(expectedTransaction, transaction);

        Mockito.verify(userRepository).findUserById(1L);
        Mockito.verify(transactionRepository).save(transaction);

        Mockito.verifyNoMoreInteractions(userRepository);
        Mockito.verifyNoMoreInteractions(transactionRepository);
    }

    @Test
    public void Given_Tansaction_When_UserNotExists_Then_ThrowUserNotFoundException() {

        Transaction transaction = new Transaction();

        transaction.setCustomerID(1L);
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
            Transaction expecteTransaction = transactionService.addTransaction(transaction);
        }catch (Exception e){
            Assert.assertTrue(e.getMessage().contains("User with id 1 not found.") );
        }
        Mockito.verify(userRepository).findUserById(1L);
        Mockito.verifyNoMoreInteractions(userRepository);
    }

}
