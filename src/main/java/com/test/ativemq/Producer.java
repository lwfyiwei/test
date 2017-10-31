package com.test.ativemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer implements CommandLineRunner {
	
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Override
	public void run(String... arg0) throws Exception {
		send("Sample message");
		System.out.println("Message was sent to the Queue");
	}
	
	public void send(String msg) {
		this.jmsMessagingTemplate.convertAndSend("lwf_msg", msg);
	}

}
