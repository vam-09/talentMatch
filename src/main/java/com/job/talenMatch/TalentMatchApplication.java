package com.job.talenMatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TalentMatchApplication {

	public static void main(String[] args) {
		log.info("inside the function");


		SpringApplication.run(TalentMatchApplication.class, args);
	}

}
