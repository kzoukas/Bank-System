package com.blueharvest.banksystem.business.service;

import com.blueharvest.banksystem.model.Transaction;


public interface TransactionService {

    Transaction addTransaction(Transaction transaction) throws Exception;

}
