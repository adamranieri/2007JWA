package dev.kusch.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import dev.kusch.entities.Manager;
import dev.kusch.services.ManagerService;
import dev.kusch.services.ManagerServiceImpl;
import io.javalin.http.Handler;

public class ManagerController {

	private static ManagerService mserv = new ManagerServiceImpl();
	private static Gson gson = new Gson();

	public static Handler getManager = (ctx) -> {
		
		String userQuery = ctx.queryParam("username");
		String passQuery = ctx.queryParam("password");
		if (userQuery != null) {
			if (passQuery != null) {
				boolean result = mserv.loginManager(userQuery, passQuery);
				ctx.result(gson.toJson(result));
				return;
			}
			List<Manager> emps = new ArrayList<Manager>();
			emps = mserv.getManagerByUser(userQuery);
			ctx.result(gson.toJson(emps));
			return;
		} else {
			Manager emp = mserv.getManagerById(Integer.parseInt(ctx.pathParam("mid")));
			if (emp == null) {
				ctx.status(404);
			} else {
				ctx.status(200);
			}
			ctx.result(gson.toJson(emp));
			return;
		}
		
	};
	
	public static Handler updateManager = (ctx) -> {
		String managerJson = ctx.body();
		Manager emp = gson.fromJson(managerJson, Manager.class);
		emp = mserv.updateManager(emp);
		if (emp == null) {
			ctx.status(404);
		} else {
			ctx.status(200);
		}
		ctx.result(gson.toJson(emp));
	};
}
