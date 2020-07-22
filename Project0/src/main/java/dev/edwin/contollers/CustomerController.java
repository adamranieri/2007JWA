package dev.edwin.contollers;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import dev.edwin.entities.Customer;
import dev.edwin.services.CustomerService;
import dev.edwin.services.CustomerServiceImp;
import io.javalin.http.Handler;

public class CustomerController 
{
//	Customer signUpNewCustomer(Customer customer);
//	Customer getCustomerById(int cId);
//	List<Customer> getAllCustomers();
//	List<Customer> searchByUsername(String username);
//	Customer updateCustomer(Customer customer);
//	boolean deleteCustomer(int cId);
//	boolean deleteCustomer(Customer customer);
//	Customer updatePassword(Customer customer);
//	Customer updateUsername(Customer customer);
	
	
	private static CustomerService cserv = CustomerServiceImp.getCustomerService();
	private static Gson gson = new Gson();
	
	public static Handler signUpNewCustomer = (ctx) -> {
		String body = ctx.body();
		
		try
		{
			Customer customer = gson.fromJson(body, Customer.class);
			if(customer != null) {
				Customer returned = cserv.signUpNewCustomer(customer);
				ctx.result(gson.toJson(returned));
				ctx.status(201);
			}
			else
				ctx.status(404);
			
			
		}catch (Exception e)
		{
			ctx.status(404);
			e.printStackTrace();
		}

		
		
	};
	
	public static Handler getCustomerById = (ctx) -> {
		String id = ctx.pathParam("cid");
		
		try
		{
			Customer customer = cserv.getCustomerById(Integer.parseInt(id));
			String json = gson.toJson(customer);		
			ctx.result(json);	
			ctx.status(200);
		} catch(NumberFormatException e)
		{
			e.printStackTrace();
			ctx.status(404);
		}
		
	};
	
	public static Handler getAllCustomers = (ctx) -> {
//		Can have query to search 
		String username = ctx.queryParam("username");
		List<Customer> customers = new ArrayList<Customer>();
		
		if(username != null)
		{
			customers = cserv.searchByUsername(username);
		}
		else
		{
			customers = cserv.getAllCustomers();
		}
		
		String json = gson.toJson(customers);
		ctx.result(json);
		ctx.status(200);
	};
	
	
	public static Handler updateCustomer = (ctx) -> {
		String body = ctx.body();
		Customer customer = gson.fromJson(body, Customer.class);
		Customer result = cserv.updateCustomer(customer);
		
		ctx.result(gson.toJson(result));
		ctx.status(202);
		
	};
	
	public static Handler deleteCustomer = (ctx) -> {
		String id = ctx.pathParam("cid");
		try
		{
			boolean result = cserv.deleteCustomer(Integer.parseInt(id));
			
			 if(result == true)
				 ctx.status(200);
			 else
				 ctx.status(404);
		}catch(NumberFormatException e)
		{
			e.printStackTrace();
		}
	};
}
