package com.blueharvest.banksystem;

import com.blueharvest.banksystem.model.User;
import com.blueharvest.banksystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner runner (UserRepository repository){
		User user1 = new User(1L,"Konstantinos","Zoukas");
		User user2 = new User(2L,"Dimitris","Papadopoulos");
		User user3 = new User(3L,"Andreas","Papanikolaou");
		return args -> {
			repository.save( new User(user1.getId(),user1.getFirstName(),user1.getLastName()));
			repository.save( new User(user2.getId(),user2.getFirstName(),user2.getLastName()));
			repository.save( new User(user3.getId(),user3.getFirstName(),user3.getLastName()));
		};
	}
}
