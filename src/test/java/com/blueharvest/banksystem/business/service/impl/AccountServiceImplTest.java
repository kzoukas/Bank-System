package com.blueharvest.banksystem.business.service.impl;

import com.blueharvest.banksystem.model.Account;
import com.blueharvest.banksystem.model.User;
import com.blueharvest.banksystem.repository.AccountRepository;
import com.blueharvest.banksystem.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountServiceImpl.class)
public class AccountServiceImplTest {

    @Autowired
    AccountServiceImpl accountService;

    @MockBean
    AccountRepository accountRepository;

    @MockBean
    UserRepository userRepository;

    @Test
    public void Given_Account_When_UserExists_Then_SaveAccount() throws Exception {
        Account account = new Account();
        account.setCustomerID(1L);
        account.setInitialCredit(10.0F);

        User user = new User();
        user.setId(1L);
        user.setFirstName("John");

        Mockito.when(userRepository.findUserById(1L)).thenReturn(user);
        Mockito.when(accountRepository.save(account)).thenReturn(account);

        Account expectedAccount = accountService.addAccount(account);
        Assert.assertEquals(expectedAccount, account);

        Mockito.verify(userRepository).findUserById(1L);
        Mockito.verify(accountRepository).save(account);

        Mockito.verifyNoMoreInteractions(userRepository);
        Mockito.verifyNoMoreInteractions(accountRepository);
    }

    @Test
    public void Given_Account_When_UserNotExists_Then_ThrowUserNotFoundException(){
        Account account = new Account();
        account.setCustomerID(1L);
        account.setInitialCredit(10.0F);

        User user = new User();
        user.setId(1L);
        user.setFirstName("John");

        Mockito.when(userRepository.findUserById(1L)).thenReturn(null);
        try {
            Account expectedAccount = accountService.addAccount(account);
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("User with id 1 not found.") );
        }
        Mockito.verify(userRepository).findUserById(1L);
        Mockito.verifyNoMoreInteractions(userRepository);
    }
}
