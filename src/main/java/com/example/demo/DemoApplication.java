package com.example.demo;

import com.example.demo.fsm.config.RulesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		RulesConfiguration configuration = context.getBean(RulesConfiguration.class);
		System.out.println();
	}

}
