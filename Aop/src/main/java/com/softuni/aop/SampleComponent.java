package com.softuni.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SampleComponent {
	private Logger LOGGER = LoggerFactory.getLogger(SampleComponent.class);

	public void sayHello() {
		LOGGER.info("Hello, world!");
	}

	public void doSomethingWrong() {
		throw new NullPointerException("Ups.. I made a bug!");
	}

	public void echoSomething(String something) {
		LOGGER.info("What i want to echo is {}", something);
	}

	public String concatTwoStrings(String s1, String s2) {
		return s1 + s2;
	}
}
