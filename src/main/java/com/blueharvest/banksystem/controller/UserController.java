package com.blueharvest.banksystem.controller;

import com.blueharvest.banksystem.business.facade.BankSystemService;
import com.blueharvest.banksystem.exceptions.BadAccountException;
import com.blueharvest.banksystem.exceptions.BadCustomerIdException;
import com.blueharvest.banksystem.business.service.UserService;
import com.blueharvest.banksystem.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

   @Autowired
    BankSystemService bankSystemService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public  ResponseEntity<?> createUserInfos(@PathVariable Long id) throws Exception {
        UserInfo userInfo = bankSystemService.createUserInfos(id);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }
}
