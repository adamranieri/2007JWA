package dev.ranieri.aspects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class SecurityAspect {
	
	@Around("securityJP()") // At around is the moist powerful type of advice method
	// It completely surrounds whatever method is being executed.
	// We can control the parameters sent into the method as well as the return of that method
	public Object authenticate(ProceedingJoinPoint pjp) throws Throwable { //pjp is the method being passed into this method
		
		// Me intercepting the Http Request and Response 
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();	   
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		
		String auth = request.getHeader("Authorization");
		// validate that the http request has the right header
		if(auth != null && auth.equals("pa$$word")) {
			Object obj = pjp.proceed(); // the method can now continue executing
			// the obj being returned is the return of that method call
			return obj;
		}else {
			response.sendError(401);
		}
			
		return null;
	}

	@Pointcut("@annotation(dev.ranieri.aspects.Authorized)")
	private void securityJP() {};

}
