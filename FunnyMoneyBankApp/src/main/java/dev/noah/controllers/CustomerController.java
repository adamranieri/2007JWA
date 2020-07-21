package dev.noah.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.noah.entities.Customer;
import dev.noah.services.CustomerService;
import dev.noah.services.CustomerServiceImpl;
import io.javalin.http.Handler;

public class CustomerController {

	private static CustomerService cserv = new CustomerServiceImpl();
	private static Gson gson = new Gson();

	public static Handler createCustomer = (ctx) -> {
		Customer cus = gson.fromJson(ctx.body(), Customer.class);
		cserv.createCustomer(cus);
		ctx.result(gson.toJson(cus));

		if(cus == null) {
			ctx.status(404);
		}else {
			ctx.status(201);
		}
	};

	public static Handler getCustomerById = (ctx) -> {
		String id = ctx.pathParam("cid");
		Customer cus = cserv.getCustomerByCId(Integer.parseInt(id));
		ctx.result(gson.toJson(cus));
		if (cus == null) {
			ctx.status(404);
		} else {
			ctx.status(200);
		}
	};

	public static Handler getAllCustomers = (ctx) -> {
		if (ctx.queryParam("username") != null) {
			Customer customer = cserv.getCustomerByUsername(ctx.queryParam("username"));
			ctx.result(gson.toJson(customer));
			
			if(customer == null) {
				ctx.status(404);
			}
			else {
				ctx.status(200);
			}
		} else {
			Set<Customer> getCustomers = cserv.getAllCustomers();
			ctx.result(gson.toJson(getCustomers));
			
			if (getCustomers == null) {
				ctx.status(404);
			}
			else {
				ctx.status(200);
			}
		}

	};

	public static Handler updateCustomer = (ctx) -> {
		Customer cus = gson.fromJson(ctx.body(), Customer.class);
		cserv.updateCustomer(cus);
		ctx.result(gson.toJson(cus));
		
		if(cus == null) {
			ctx.status(404);
		}
		else {
		ctx.status(200);
		}

	};

	public static Handler deleteCustomer = (ctx) -> {
		String id = ctx.pathParam("cid");
		boolean result = cserv.deleteCustomerByCId(Integer.parseInt(id));
		if(result == false) {
			ctx.status(404);
		} else {
		ctx.status(200);
		}
	};
}
