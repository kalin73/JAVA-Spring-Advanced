package com.softuni.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Sample1Aspect {
	private final Logger LOGGER = LoggerFactory.getLogger(Sample1Aspect.class);

	@Pointcut("execution(* com.softuni.aop.sample1.SampleComponent.*(..))")
	void allSampleComponentMethods() {

	}

	@Pointcut("execution(* com.softuni.aop.sample1.SampleComponent.concatTwoStrings(..))")
	void concat() {

	}

	@Around("concat() && args(s1, s2)")
	Object aroundConcat(ProceedingJoinPoint pjp, String s1, String s2) throws Throwable {
		LOGGER.info("concat method was called with arguments {}, {}.", s1, s2);
		String m1 = "(" + s1 + ")";
		String m2 = "(" + s2 + ")";

		Object result = pjp.proceed(new Object[] { m1, m2 });

		// what is below this line is AFTER the method is called

		return "[" + result + "]";

	}

	@AfterThrowing(pointcut = "allSampleComponentMethods()", throwing = "ex")
	void afterThrowing(JoinPoint joinPoint, Exception ex) {
		LOGGER.info("After the moethod {} boomed. Exception is {}", joinPoint.getSignature(), ex.getClass().getName());
	}

	@Before("allSampleComponentMethods()")
	public void beforeEachMethod(JoinPoint joinpoint) {
		LOGGER.info("Before calling a method {}", joinpoint.getSignature());
	}

	@Pointcut("execution(* com.softuni.aop.sample1.SampleComponent.echoSomething(..))")
	void echoMethod() {

	}

//	@After("echoMethod()")
//	void afterEcho() {
//		LOGGER.info("After echo!");
//	}
}
