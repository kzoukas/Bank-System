package com.blueharvest.banksystem.business.facade;

import com.blueharvest.banksystem.business.service.AccountService;
import com.blueharvest.banksystem.business.service.TransactionService;
import com.blueharvest.banksystem.business.service.UserService;
import com.blueharvest.banksystem.model.Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BankSystemService.class)
public class BankSystemServiceTest {

    @Autowired
    BankSystemService bankSystemService;

    @MockBean
    AccountService accountService;

    @MockBean
    TransactionService transactionService;

    @MockBean
    UserService userService;

    @Test
    public void Given_Account_When_InitialCreditIsBiggerThanZero_Then_Return_SavedAccount() throws Exception {

        Account account = new Account();
        account.setInitialCredit(100F);
        account.setCustomerID(1L);
        account.setId(1L);

        Mockito.when(accountService.addAccount(account)).thenReturn(account);

        Account expectedAccount = accountService.addAccount(account);
        Assert.assertEquals(expectedAccount, account);

        Mockito.verify(accountService).addAccount(account);


    }
}
