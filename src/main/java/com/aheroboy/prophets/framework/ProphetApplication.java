package com.aheroboy.prophets.framework;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@SpringBootApplication
public class ProphetApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ProphetApplication.class);
		Properties properties = new Properties();
		properties.setProperty("spring.resources.staticLocations",
			"classpath:/static/src/, classpath:/newLocation2/");
		app.setDefaultProperties(properties);
		app.run(args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			ActorSystem actorSystem = ctx.getBean(ActorSystem.class);
			actorSystem.init();
			actorSystem.start();
		};
	}

}
