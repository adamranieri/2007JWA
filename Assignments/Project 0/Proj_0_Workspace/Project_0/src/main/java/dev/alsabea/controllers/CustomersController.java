package dev.alsabea.controllers;

import java.util.List;

import com.google.gson.Gson;

import dev.alsabea.entities.Customer;
import dev.alsabea.services.CustomerServices;
import dev.alsabea.services.impl.CustomerServicesImpl;
import io.javalin.http.Handler;

public class CustomersController {

	//cServices is a class that contains services made for the Customer class
	private static CustomerServices cServices = CustomerServicesImpl.getCustomerServicesInstance();
	
	//gs is used mainly to transform an object to JSON, or a JSON to an object
	private static Gson gs= new Gson();
	
	
	/**
	 * has an optional query named username
	 * seen in the browser tab as "?username = (value)"
	 * if used 
	 * @returns list of customers with the same username
	 * if not used
	 * @returns all customers
	 */
	public static Handler retrieveAllCustomers = (ctx) ->{
		
		//get the query value (either there is a value or null)
		String usernameQ = ctx.queryParam("username");
		
		// if query is not null, proceed
		if(usernameQ != null) {
			//get all customers with the same username
			List<Customer> list= cServices.retrieveByUsername(usernameQ);

			if (list != null) {
				//transform the list of customers to json, then send the value to the website.
				ctx.result(gs.toJson(list));
			} else {
				ctx.status(404);
				ctx.result("There is no customer with this username");
			}

		//if query is null
		}else {
			//get all customers in the database
			List<Customer> customers = cServices.retrieveAll();	
			
			//transform the list of customers to json, then send the value to the webpage.
			ctx.result( gs.toJson(customers));	
		}
			
	};
	
	/**
	 * reads a customer object as json from the web, then adds it to the database 
	 */
	public static Handler addACustomer = (ctx) -> {
		//read the customer object as json
		String custAsJson = ctx.body();
		//transform the json String to a Customer object
		Customer c= gs.fromJson(custAsJson, Customer.class);
		//the create service returns the generated customer_id of the object inserted into the database
		//we store this customer_id.
		int id = cServices.create(c);
		
		//use the retrieve service to access the database again and fetch the customer we inserted
		Customer customerGottenFromDB= cServices.retrieveById(id);
		//specify that status of the request as success since we were able to get it after we have 
		// created it
		ctx.status(201); 
		// send it to the webpage
		ctx.result(gs.toJson(customerGottenFromDB));
	};
	
	/**
	 * deletes a customer
	 */
	public static Handler deleteCustomer = (ctx) -> {
		//get the id of the customer to delete
		String id = ctx.pathParam("id");
		//delete service returns either true (when it is executed successfully), 
		//or false (when it does not delete)
		//if the operation succeeds
		if (cServices.delete(Integer.parseInt(id))) {
			//status 200 means that the operation has executed successfully
			ctx.status(200);
			//specify that the operation is a success through a message 
			ctx.result("delete operation executed successfully");
		} else {
			ctx.status (404);
			ctx.result("Delete Failed, no such element exist with this customer_id");
		}
		
	};
	
	/**
	 * updates a customer
	 * @returns the updated object
	 */
	public static Handler updateCustomer = (ctx) ->{
		
		
		
		int id = Integer.parseInt(ctx.pathParam("id"));
		
		//cust is used to check if there is an actual customer object with this id in the DB
		Customer cust= cServices.retrieveById(id);
		if(cust!=null) {
			// reads the new customer object as a JSON.
			String custAsJson = ctx.body();
			Customer c= gs.fromJson(custAsJson, Customer.class);
			/*
			 * assign the new customer object, the customer_id gotten from the path
			 * this is just to make the code work in case a user did not provide a customer_id
			 * when he inputted the object 
			 * in case he enters  {username: val, password : val} 
			 * instead of  {cid: val, username:val, password:val}
			 */
			c.setCustomerId(id);
			//update service returns true in case the update executed successfully
			if (cServices.update(c)) {
				ctx.status(200);
				ctx.result(gs.toJson(cServices.retrieveById(c.getCustomerId())));
			} else {
				ctx.status(404);
				ctx.result("an error with the update service or dao");
			}
		}
		//if there is no such object in the database
		// do nothing and return a message, and an error status code.
		else {
			ctx.status(404);
			ctx.result("no customer with such customer id");
		}
	};
	
	
	public static Handler retrieveACustomer = (ctx) ->{
		int id = Integer.parseInt(ctx.pathParam("id"));
		Customer c = cServices.retrieveById(id);
		
		//access the database, retrieve all accounts associated with the customer, then
		// attach them to the customer
		if (c ==  null ) {
			ctx.status(404);
			ctx.result("there is no customer with such id");
			return;
		}
		c.setAccounts(cServices.getCustomerAccounts(id)); 
		ctx.status(200);
		//send them to the web page.
		ctx.result(gs.toJson(c));
		
		
		
	};
	
	
	
	
	
}