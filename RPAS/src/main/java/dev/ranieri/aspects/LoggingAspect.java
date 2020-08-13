package dev.ranieri.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	private int counter = 0;
	
	@Before("logJP()")// Advice method that executes right BEFORE the method is called
	public void logRequest() {
		this.logger.info("Total amount of http calls " + ++counter);

	}
	
	@Before("logAssociateJP()")
	public void logAssociate() {
		System.out.println("An associate method was called");
	}	
	
	// join point hook method
	// Pointcut expression says where this method should run
	@Pointcut("execution(* dev.ranieri.controllers.AssociateController.*(..))")
	private void logAssociateJP() {}
	
	@Pointcut("within(dev.ranieri.controllers..*)")
	private void logJP() {}

}
