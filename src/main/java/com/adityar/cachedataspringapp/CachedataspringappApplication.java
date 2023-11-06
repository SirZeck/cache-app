package com.adityar.cachedataspringapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CachedataspringappApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				SpringApplication.run(CachedataspringappApplication.class, args);

		//add a shutdown hook
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			context.close();
		}));
	}

}
