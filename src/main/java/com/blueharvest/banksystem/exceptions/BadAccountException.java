package com.blueharvest.banksystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadAccountException extends Exception {

    public BadAccountException(){

        super("Malformed Account.");
    }
}