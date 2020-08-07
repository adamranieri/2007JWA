package dev.kusch.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import dev.kusch.daos.EmployeeDAO;
import dev.kusch.daos.EmployeeDAOHibernate;
import dev.kusch.daos.ReimbursementDAO;
import dev.kusch.daos.ReimbursementDAOHibernate;
import dev.kusch.entities.Employee;
import dev.kusch.entities.Reimbursement;
import dev.kusch.services.EmployeeService;
import dev.kusch.services.EmployeeServiceImpl;
import dev.kusch.services.ReimbursementService;
import dev.kusch.services.ReimbursementServiceImpl;
import io.javalin.http.Handler;

public class ReimbursementController {
	
	private static ReimbursementDAO rdao = ReimbursementDAOHibernate.getReimbursementDAOHibernate();
	private static EmployeeDAO edao = EmployeeDAOHibernate.getEmployeeDAOHibernate();
	
	private static ReimbursementService rserv = new ReimbursementServiceImpl(rdao);
	private static EmployeeService eserv = new EmployeeServiceImpl(edao);
	
	private static Gson gson = new Gson();
	
	public static Handler createReimbursement = (ctx) -> {
		System.out.println("here");
		String reimJson = ctx.body();
		Reimbursement reim = gson.fromJson(reimJson, Reimbursement.class);
		Employee emp = eserv.getEmployeeById(Integer.parseInt(ctx.pathParam("eid")));
		System.out.println(emp);
		reim.setEmployee(emp);
		reim = rserv.createReimbursement(reim);
		ctx.status(201);
		ctx.result(gson.toJson(reim));
	};
	
	public static Handler getAllReimbursements = (ctx) -> {
		List<Reimbursement> reims = rserv.getAllReimbursements();
		ctx.result(gson.toJson(reims));
	};
	
	public static Handler getReimbursementsByUser = (ctx) -> {
		Employee emp = eserv.getEmployeeById(Integer.parseInt(ctx.pathParam("eid")));
		System.out.println(emp.getReimbursements());
		List<Reimbursement> reims = rserv.getReimbursementsByEmployee(emp);
		ctx.result(gson.toJson(reims));
	};
	
	public static Handler getReimbursementById = (ctx) -> {
		Reimbursement reim = rserv.getReimbursement(Integer.parseInt(ctx.pathParam("rid")));
		if (reim == null) {
			ctx.status(404);
		} else {
			ctx.status(200);
		}
		ctx.result(gson.toJson(reim));
	};
	
	public static Handler updateReimbursement = (ctx) -> {
		String reimJson = ctx.body();
		Reimbursement reim = gson.fromJson(reimJson, Reimbursement.class);
		System.out.println(reim);
		reim = rserv.updateReimbursement(reim);
		System.out.println(reim);
		if (reim == null) {
			ctx.status(404);
		} else {
			ctx.status(200);
		}
		ctx.result(gson.toJson(reim));
	};
	
	public static Handler deleteReimbursement = (ctx) -> {
		String reimJson = ctx.body();
		Reimbursement reim = gson.fromJson(reimJson, Reimbursement.class);
		boolean result = rserv.deleteReimbursement(reim);
		if (result == false) {
			ctx.status(404);
		} else {
			ctx.status(200);
		}
	};

}
