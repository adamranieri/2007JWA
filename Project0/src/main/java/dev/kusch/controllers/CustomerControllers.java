package dev.kusch.controllers;

import com.google.gson.Gson;

import dev.kusch.services.CustomerServices;
import dev.kusch.services.CustomerServicesImpl;
import io.javalin.http.Handler;

public class CustomerControllers {
	public static CustomerServices sserv = new CustomerServicesImpl();
	private static Gson gson = new Gson();
	
	public static Handler getAllCustomers = (ctx) -> {
		
	};
	
	public static Handler getCustomerById = (ctx) -> {
		
	};
	
	public static Handler createCustomer = (ctx) -> {
		
	};
	
	public static Handler updateCustomer = (ctx) -> {
		
	};
	
	public static Handler deleteCustomer = (ctx) -> {
		
	};
	
	
}
