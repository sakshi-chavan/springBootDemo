package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class AspectConfig {

	Logger logger = LoggerFactory.getLogger(getClass()); 
	
	//this intercepts all the methods with 0 and many parameters.
	// when = before, which = com.example.controller.*.*
	
	/*	@Before(value = "execution(* com.example.controller.*.*(..))")    // 1st .* id for all the classes and 3nd .* is for all the methods.
	public void beforeAdvice(JoinPoint joinPoint) {
		
		logger.info("Inside Before Advice");
		
	} 
 	*/
	
	
	//this intercepts all the methods with exactly one parameter
	/* @Before(value = "execution(* com.example.controller.*.*(..)) and args(object)")    // 1st .* id for all the classes and 3nd .* is for all the methods.
	public void beforeAdvice(JoinPoint joinPoint, Object object) {
		
		logger.info("Request = " + object);
		
	}
	*/
	
	
	// when = after, which = com.example.controller.*.*
	/* @After(value = "execution(* com.example.controller.*.*(..)) and args(object)")    // 1st .* id for all the classes and 3nd .* is for all the methods.
	public void afterAdvice(JoinPoint joinPoint, Object object) {
		
		logger.info("Request = " + object);
		
	}
	*/
	
	// when = after returning response, which = com.example.controller.*.*
	/* 
	@AfterReturning(value = "execution(* com.example.controller.*.*(..)) and args(object)",
			returning = "returningObject")    // 1st .* id for all the classes and 3nd .* is for all the methods.
	public void afterReturningAdvice(JoinPoint joinPoint, Object object, Object returningObject) {
		
		logger.info("Response = " + object);
		
	}
	*/
	
	// when = before and after returning advice, which = com.example.controller.*.*
	@Around(value = "execution(* com.example.controller.*.*(..)) and args(object)")    // 1st .* id for all the classes and 3nd .* is for all the methods.
	public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint, Object object) {
		
		logger.info("Request = " + object);
		Object returningObject = null;
		
		try {
			returningObject = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("Response = " + returningObject);
		
	}
	
	
}
