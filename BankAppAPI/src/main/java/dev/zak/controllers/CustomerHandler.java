package dev.zak.controllers;

import com.google.gson.Gson;
import dev.zak.entities.AppError;
import dev.zak.entities.Customer;
import dev.zak.services.CustomerService;
import dev.zak.services.CustomerServiceInterface;
import io.javalin.http.Handler;

public class CustomerHandler {

	static private CustomerServiceInterface cServ = new CustomerService();
	static private Gson gson = new Gson();
	static private AppError err; 
	public CustomerHandler() {
		super();
	}
	
	private static boolean RequestNotvalid(Customer c) {
		if(c.getUsername() == null || c.getPassword() == null) {
			CustomerHandler.err = new AppError(1, "JSON object is not valid");
			return true;
		}
		return false;
	}
	
	public static Handler getAllCustomers = (ctx)->{
		String result, username;
		username = ctx.queryParam("username");
		if(username != null) {
			result = gson.toJson(
						cServ.getCustomerByUserName(username)
					);
			if(gson.fromJson(result, Customer.class) == null) {
				result = "Customer "+username+" was not found";
			}
		}
		else {
			result = gson.toJson(cServ.getAllCustomers());
		}
		ctx.result(result);
	};
	
	public static Handler getCustomerById = (ctx)->{
		String cid = ctx.pathParam(":cid");
		String result = gson.toJson(
							cServ.getCustomerById(
								Integer.parseInt(
										cid)
								)
							);
		if(gson.fromJson(result, Customer.class) == null) {
			err = new AppError(3, "Customer "+cid+" was not found");
			result = gson.toJson(err);
			ctx.status(404);
		}
		ctx.result(result);
	};

	public static Handler createCustomer = (ctx)->{
		Customer c = gson.fromJson(ctx.body(), Customer.class);
		if(CustomerHandler.RequestNotvalid(c)) {
			ctx.result(gson.toJson(CustomerHandler.err));
			ctx.status(400);
		}else {
			String result = gson.toJson(cServ.createCustomer(c));
			if(result == null) {
				err = new AppError(5, "Account cannot be created");
				result = gson.toJson(err);
				ctx.status(500);
				ctx.result(result);
			}else {
				ctx.status(201);
				ctx.result(result);
			}
		}
	};

	public static Handler updateCustomer = (ctx)->{
		Customer c = gson.fromJson(ctx.body(), Customer.class);
		if(c.getcId() != Integer.parseUnsignedInt(ctx.pathParam("cid"))) {
			err = new AppError(2, "Inconsistent Custommer data: Check IDs provided");
			ctx.result(gson.toJson(err));
			ctx.status(400);
		}
		else if(RequestNotvalid(c)) {
			ctx.result(gson.toJson(err));
			ctx.status(400);
		}
		else{
			String result = gson.toJson(cServ.updateCustomer(c));
			if(gson.fromJson(result, Customer.class) == null) {
				err = new AppError(3, "Customer "+c.getcId()+" was not found");
				result = gson.toJson(err);
				ctx.status(404);
			}
			ctx.result(result);
		}
	};
	
	public static Handler deleteCustomer = (ctx)->{
		int id = Integer.parseInt(ctx.pathParam(":cid"));
		int deletionStatus = cServ.deleteCustomerById(id);
		String result ="";
		if(deletionStatus == 1) {
			result = "Customer "+id+" is deleted";
			ctx.status(200);
		}
		else if(deletionStatus == -1) {
			err = new AppError(4, "Customer "+id+" has accounts and CANNOT BE DELETED");
			result = gson.toJson(err);
			ctx.status(403);
		}
		else if(deletionStatus == 0) {
			err = new AppError(3, "Customer "+id+" was not found");
			result = gson.toJson(err);
			ctx.status(404);
		}
		ctx.result(result);
	};
	
	public static Handler deleteCustomerWithAllAccounts = (ctx)->{
		boolean isDeleted = cServ.deleteCustomerByIdWithAllAccount(
								Integer.parseInt(
									ctx.pathParam(":cid"))
							);
		if(isDeleted) {
			ctx.result("Customer Deleted with Success");
			ctx.status(200);
		}
		else {
			err = new AppError(3, "Customer "+ctx.pathParam("cid")+" was not found");
			String result = gson.toJson(err);
			ctx.result(result);
			ctx.status(404);
		}
	};

}
