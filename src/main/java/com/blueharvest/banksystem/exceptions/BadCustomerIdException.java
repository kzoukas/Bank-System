package com.blueharvest.banksystem.exceptions;

import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadCustomerIdException extends Exception {

    public BadCustomerIdException(){

        super("Customer ID malformed.");
    }
}