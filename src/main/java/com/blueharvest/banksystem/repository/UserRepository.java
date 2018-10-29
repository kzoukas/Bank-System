package com.blueharvest.banksystem.repository;

import com.blueharvest.banksystem.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    User findUserById(Long id);

}
