package com.blueharvest.banksystem.repository;

import com.blueharvest.banksystem.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, String> {

    List<Account> findAccountByCustomerID(Long customerID);

}
