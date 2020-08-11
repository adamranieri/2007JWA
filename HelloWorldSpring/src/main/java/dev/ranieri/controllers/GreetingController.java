package dev.ranieri.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Component
@Controller // stereotype. Specific type of component. It tells spring when it reads this class
// I am a controller designed to handle http requests and give back responses
public class GreetingController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET) // the routing
	@ResponseBody // it returns a string not a Rendered HTML page
	public String sayHello() {
		return "Hello from the Hello Controller";
	}
	
	@RequestMapping(value ="/hello/{fname}/{lname}/{amount}",method = RequestMethod.GET  )
	@ResponseBody
	public String customHello(@PathVariable String fname, @PathVariable String lname, @PathVariable int amount) { 
		// @PathVariable will match the url param to a method param named the same
		String a = "Hello" +fname +lname;
		for(int i = 0; i<amount;i++) {
			a = a +a;
		}

		return a;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET )
	@ResponseBody
	public String queryParam(@RequestParam(required = false) String something) {
		return "you serached for" + something;
	}
	
}
