package dev.dave.controllers;

import com.google.gson.Gson;

import dev.dave.entities.Customers;
import dev.dave.services.CustomersServices;
import dev.dave.services.CustomersServicesImpl;
import io.javalin.http.Handler;

public class CustomersController {
	
	public static CustomersServices cserv = CustomersServicesImpl.getCustomersServices();
	
	private static Gson gson = new Gson(); // instantiates a gson object to convert objects to jsons and viceversa
	
	// CREATE
	
	public static Handler createCustomer= (ctx)->{
		
		String customerJson = ctx.body(); // the body of the http request
		Customers customer = gson.fromJson(customerJson, Customers.class); // to convert request from Json to a customer object
		cserv.CustomerSignUp(customer); //we call our service to create object customer
		ctx.status(201); // 201 is the status code to return if you successfully add something
		ctx.result(gson.toJson(customer)); // we now convert our newly created object into a json to return it
	};
	
	// READ
	
	public static Handler getCustomerByID= (ctx)->{
		
		String id = ctx.pathParam("cid"); //end-user will enter certain ID to retrieve a customer
		Customers customer = cserv.GetCustInfo(Integer.parseInt(id));
		
		//we have been able to retrieve a customer object, however we need to have it returned as a json
		
		if (customer == null) { // first off, if no customer is found with the cID provided
			ctx.status(404);	// we need to return error 404
		}else {
			String json = gson.toJson(customer); // otherwise, we'll convert our object to json
			ctx.result(json); // and have it returned
		}
	};
	
	// UPDATE
	
	public static Handler updateCustomer= (ctx)->{
		
		String customerJson = ctx.body(); // request from frontend to modify a customer object
		Customers customer = gson.fromJson(customerJson, Customers.class); // we convert the json to object
		cserv.updateCustomer(customer); // we call our service to perform the update
		ctx.result(gson.toJson(customer)); // updated object is converted back to json and sent to the frontend
	};
	
	// DELETE
	
	public static Handler deleteCustomer= (ctx) ->{
		
		String id = ctx.pathParam("cid"); //request to delete a customer object
		Boolean result = cserv.DeleteProfile(Integer.parseInt(id)); //we parse through the json to find the cid and call the service to delete the customer with that cID
		ctx.result(result.toString());
	};
	
	// QUERY PARAM CONTROLLER
	
	public static Handler getCustByName= (ctx) ->{
		
		String username = ctx.pathParam("username");
		Customers customer = cserv.SearchCustomerByName(username);
		
		if (customer == null) {
			ctx.status(404);	
		}else {
			String json = gson.toJson(customer); 
			ctx.result(json); 
		}
	};
}
