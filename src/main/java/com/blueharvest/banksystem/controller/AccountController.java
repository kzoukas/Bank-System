package com.blueharvest.banksystem.controller;

import com.blueharvest.banksystem.business.facade.BankSystemService;
import com.blueharvest.banksystem.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    BankSystemService bankSystemService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addAccount(@RequestBody @NotNull Account account) throws Exception {
        Account accountAdded = bankSystemService.addAccount(account);
        return new ResponseEntity<>(accountAdded, HttpStatus.OK);
    }
}
