package com.blueharvest.banksystem.repository;


import com.blueharvest.banksystem.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, String> {

    List<Transaction> findTransactionByCustomerID(Long customerID);

}
