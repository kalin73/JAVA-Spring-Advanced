package com.softuni.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Sample1 implements CommandLineRunner {
	private final SampleComponent sampleComponent;
	private final Logger LOGGER = LoggerFactory.getLogger(Sample1Aspect.class);

	public Sample1(SampleComponent sampleComponent) {
		this.sampleComponent = sampleComponent;
	}

	@Override
	public void run(String... args) throws Exception {
//		sampleComponent.sayHello();
//		sampleComponent.echoSomething("I'm a demo");
//
//		try {
//			sampleComponent.doSomethingWrong();
//		} catch (Exception e) {
//			LOGGER.error("What i want to echo is {}", e.getMessage());
//		}
		final String s1 = "string1";
		final String s2 = "string2";

		String result = sampleComponent.concatTwoStrings(s1, s2);

		LOGGER.info("Concatenating {} and {}. Result is {}", s1, s2, result);
	}

}
