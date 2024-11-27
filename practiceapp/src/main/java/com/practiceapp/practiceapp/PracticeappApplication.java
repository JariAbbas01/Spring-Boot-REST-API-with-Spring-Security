package com.practiceapp.practiceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PracticeappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeappApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager falana(MongoDatabaseFactory mongodbfactor){
		return new MongoTransactionManager(mongodbfactor);
	}



}
