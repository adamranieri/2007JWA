package dev.nauman.controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import com.google.gson.Gson;

import dev.nauma.utils.ConnectionUtil;
import dev.nauman.entities.Customer;
import dev.nauman.services.CustomerService;
import dev.nauman.services.CustomerServiceImpl;
import io.javalin.http.Handler;

public class CustomerController {
	
	private static CustomerService cserv = new CustomerServiceImpl();
	private static Gson gson = new Gson();
	
	public static void setUp() {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "CALL set_up_projectzerodb()";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Handler createCustomer = (ctx) ->{
		String customerJson = ctx.body();
		Customer customer = gson.fromJson(customerJson, Customer.class);
		customer = cserv.createCustomer(customer);
		customerJson = gson.toJson(customer);
		ctx.result(customerJson);
		ctx.status(201);
	};
	public static Handler getAllCustomers = (ctx) ->{
		Set<Customer> customers = cserv.getAllCustomers();
		String customersJson = gson.toJson(customers);
		ctx.result(customersJson);
	};
	public static Handler getCustomerByCId = (ctx) ->{
		String id = ctx.pathParam("cId");
		Customer customer = cserv.getCustomerByCId(Integer.parseInt(id));
		String customerJson = gson.toJson(customer);
		ctx.result(customerJson);
	};
	public static Handler updateCustomer = (ctx) ->{
		String customerJson = ctx.body();
		Customer customer = gson.fromJson(customerJson, Customer.class);
		customer = cserv.updateCustomer(customer);
		customerJson = gson.toJson(customer);
		ctx.result(customerJson);
	};
	public static Handler changeUsername = (ctx) ->{
		String newUsername = ctx.body();
		String id = ctx.pathParam("cId");
		Customer customer = cserv.changeUsername(Integer.parseInt(id), newUsername);
		ctx.result(gson.toJson(customer));
	};
	public static Handler changePassword = (ctx) ->{
		String newPassword = ctx.body();
		String id = ctx.pathParam("cId");
		Customer customer = cserv.changePassword(Integer.parseInt(id), newPassword);
		ctx.result(gson.toJson(customer));
	};
	public static Handler deleteCustomerByCId = (ctx) ->{
		String id = ctx.pathParam("cId");
		cserv.deleteCustomerByCId(Integer.parseInt(id));		
	};
}
