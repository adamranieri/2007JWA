package dev.ranieri.AssociateApp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RegisterController {
	
	@Autowired
	RestTemplate rt; // allows us to make http calls
	
	@Autowired
	JavaMailSender mailer;
	
	@RequestMapping(value = "/register/{name}/{email}", method = RequestMethod.GET)
	public String registerNewEmployee(@PathVariable String name, @PathVariable String email){
		
		Employee emp = new Employee(0,name,"new enrollee");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); // setting the header that his request
		// body is going to be a json when sent
		HttpEntity<Employee> entity = new HttpEntity<Employee>(emp,headers);
		
		String result = this.rt.exchange
				("http://localhost:8080/employees",HttpMethod.POST,entity,String.class).getBody();
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Employee Registration");
		message.setText("Thank your registering");
		mailer.send(message);
		
		return result;
	}

}
