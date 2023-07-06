package com.v3.furry_frined_chat_cud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FurryFrinedChatCudApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurryFrinedChatCudApplication.class, args);
	}

}
