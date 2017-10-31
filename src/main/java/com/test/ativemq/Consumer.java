package com.test.ativemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	@JmsListener(destination = "lwf_msg")
	public void receiveQueue(String text) {
		System.out.println("Consumer: "+text);
	}
}
