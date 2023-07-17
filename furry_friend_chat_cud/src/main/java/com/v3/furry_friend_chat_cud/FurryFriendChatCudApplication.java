package com.v3.furry_friend_chat_cud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FurryFriendChatCudApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurryFriendChatCudApplication.class, args);
	}

}
