package dev.kusch.controllers;

import com.google.gson.Gson;

import dev.kusch.daos.EmployeeDAO;
import dev.kusch.daos.EmployeeDAOHibernate;
import dev.kusch.daos.ManagerDAO;
import dev.kusch.daos.ManagerDAOHibernate;
import dev.kusch.dtos.LoginDTO;
import dev.kusch.entities.Employee;
import dev.kusch.entities.Manager;
import dev.kusch.services.EmployeeService;
import dev.kusch.services.EmployeeServiceImpl;
import dev.kusch.services.ManagerService;
import dev.kusch.services.ManagerServiceImpl;
import io.javalin.http.Handler;

public class LoginController {
	
	public static Handler loginHandler = (ctx) ->{
		EmployeeDAO edao = EmployeeDAOHibernate.getEmployeeDAOHibernate();
		ManagerDAO mdao = ManagerDAOHibernate.getManagerDAOHibernate();
		
		EmployeeService eserv = new EmployeeServiceImpl(edao);
		ManagerService mserv = new ManagerServiceImpl(mdao);
		
		Gson gson = new Gson();
		LoginDTO loginInfo=  gson.fromJson(ctx.body(), LoginDTO.class); 
		Manager man = mserv.loginManager(loginInfo);
		Employee emp = eserv.loginEmployee(loginInfo);
		
		if (man != null) {
			ctx.result(gson.toJson(man));
		} else if (emp != null){
			ctx.result(gson.toJson(emp));
		} else {
			ctx.status(404);
		}
		
	};

}
