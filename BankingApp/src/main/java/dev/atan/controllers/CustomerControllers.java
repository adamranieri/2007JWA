package dev.atan.controllers;

import java.util.List;
import com.google.gson.Gson;
import dev.atan.entities.Customer;
import dev.atan.services.CustomerService;
import dev.atan.services.CustomerServiceImpl;
import io.javalin.http.Handler;


public class CustomerControllers {
	
	
	public static CustomerService cserv = new CustomerServiceImpl();
	private static Gson gson = new Gson();
	
	
	public static Handler createCustomer= (ctx) ->{
		String customerJSON = ctx.body();
		Customer customer = gson.fromJson(customerJSON, Customer.class);
		for(Customer cust : cserv.getAllCustomers()) {
			if(customer.getUserName().equals(cust.getUserName())) {
				ctx.result("There is already Customer with this User Name!");
				ctx.status(400);
			}
			else{
				cserv.createCustomer(customer);
				ctx.result(gson.toJson(customer));
				ctx.status(201);
			}
		}
		

	};
	
	
	public static Handler getCustomerById = (ctx)->{
		String id = ctx.pathParam("cID");
		Customer customer = cserv.getCustomerById(Integer.parseInt(id));
		
		if(customer == null) {
			ctx.status(404);
			ctx.result("There is no Customer with this ID");
		}else {
			String json = gson.toJson(customer);
			ctx.result(json);	
		} 
	};
	
	public static Handler getAllCustomers = (ctx) ->{
		List<Customer> customers = cserv.getAllCustomers();		
		String json = gson.toJson(customers);
		String userQ = ctx.queryParam("userName");
		if(userQ != null) {
			Customer customerByUserName = cserv.getCustomerByUserName(userQ);
			ctx.result(gson.toJson(customerByUserName));
		}else {
			ctx.result(json);	
		}
	};
	
	public static Handler getCustomerByUserName = (ctx)->{
		String userName = ctx.pathParam("userName");
		Customer customer = cserv.getCustomerByUserName(userName);
		
		if(customer == null) {
			ctx.status(404);
			ctx.result("There is no Customer with this User Name");
		}else {
			String json = gson.toJson(customer);
			ctx.result(json);	
		} 
	};
	
	public static Handler updateCustomer = (ctx)->{
		String customerJSON = ctx.body();
		Customer customer = gson.fromJson(customerJSON, Customer.class);
		
		if(cserv.getCustomerById(customer.getcID()) == null){
			ctx.status(404);
			ctx.result("There is no Customer with this ID");
		} else {
			cserv.updateCustomer(customer);
			ctx.result(gson.toJson(customer));}
	};
	
	public static Handler renameCustomer = (ctx)->{
		String oldName = ctx.pathParam("userName");
		Customer customer = cserv.getCustomerByUserName(oldName);
		
		if(cserv.getCustomerByUserName(oldName) == null){
			ctx.status(404);
			ctx.result("There is no Customer with this User Name");
		} else {
			cserv.updateCustomer(customer);
			ctx.result(gson.toJson(customer));}
	};
	
	public static Handler deleteCustomer = (ctx) ->{
		String id = ctx.pathParam("cID");
		Boolean result = cserv.deleteCustomerById(Integer.parseInt(id));
		
		if(result == false) {
			ctx.status(404);
			ctx.result("There is no Customer with this ID or there is open account for this user");
		}else {
			ctx.result(result.toString());
		}
	};
		
	public static Handler deleteCustomerByUserName = (ctx) ->{
		String userName = ctx.pathParam("userName");
		Boolean result = cserv.deleteCustomerByUserName(userName);
		
		if(result == false) {
			ctx.status(404);
			ctx.result("There is no Customer with this UserName or there is open account for this user");
		}else {
			ctx.result(result.toString());
		} 
		
	};
	

}
