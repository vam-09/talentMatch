package com.job.talenMatch;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class TalentMatchApplication {

//	private static final Logger log = LoggerFactory.getLogger(TalentMatchApplication.class);

	public static void main(String[] args) {
		log.info("inside the function");
		SpringApplication.run(TalentMatchApplication.class, args);
	}

}
