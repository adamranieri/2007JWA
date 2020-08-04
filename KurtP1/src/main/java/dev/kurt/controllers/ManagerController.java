package dev.kurt.controllers;

import java.util.List;
import com.google.gson.Gson;

import dev.kurt.entities.Employee;
import dev.kurt.entities.Manager;
import dev.kurt.services.ManagerService;
import dev.kurt.services.ManagerServiceImpl;
import io.javalin.http.Handler;

public class ManagerController {
	
	private ManagerService manServ;
	private Gson gson = new Gson();
	
	public ManagerController(ManagerService manServ) {
		super();
		this.manServ = manServ;
	}
	
	
	public Handler getAllManagers = (ctx) ->{
		String userQ = ctx.queryParam("username");
		String passQ = ctx.queryParam("password");
		
		switch ((userQ != null) + "-" + (passQ != null)) {
	    case "false-false":
	    	List<Manager> managers = manServ.getAllManagers();
	    	ctx.result(gson.toJson(managers));
	        break;
	    case "false-true":
	        break;
	    case "true-false":
	    	break;
	    case "true-true":
	    	Manager manager = manServ.getManagerByLogin(userQ, passQ);
	    	ctx.result(gson.toJson(manager));
		    break;
	    default:
	        throw new RuntimeException("404: Something Broke");
		}
	};
	
	public Handler createManager = (ctx) ->{
		String managerJson = ctx.body();
		Manager manager = gson.fromJson(managerJson, Manager.class);  
		manServ.createManager(manager);
		ctx.status(201); 
		ctx.result(gson.toJson(manager));
	};
	
	
	public Handler getManagerById = (ctx) ->{
		String id = ctx.pathParam("cid");
		Manager manager = manServ.getManagerById(Integer.parseInt(id));	
		String json = gson.toJson(manager);
		ctx.result(json);	
	};
	
	
	
	public Handler updateManager = (ctx) ->{
		String managerJson = ctx.body();
		Manager manager = gson.fromJson(managerJson, Manager.class);
		manServ.updateManager(manager);	
		ctx.result(gson.toJson(manager));
	};
	
	
	public Handler deleteManager = (ctx) ->{
		String id = ctx.pathParam("mid");
		Manager manager = manServ.getManagerById(Integer.parseInt(id));
		Boolean result = manServ.deleteManager(manager);
		ctx.result(result.toString());
	};

}
