package com.test;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
@EnableJms
public class Main {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);
		log.debug("Tomcat bind port: "+ctx.getEnvironment().getProperty("server.port"));
	}
	
	@Bean
	public Queue queue() {
		return new ActiveMQQueue();
	}

}
