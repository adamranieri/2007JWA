package dev.kusch.controllers;

import java.util.List;

import com.google.gson.Gson;

import dev.kusch.entities.Reimbursement;
import dev.kusch.services.ReimbursementService;
import dev.kusch.services.ReimbursementServiceImpl;
import io.javalin.http.Handler;

public class ReimbursementController {
	
	private static ReimbursementService rserv = new ReimbursementServiceImpl();
	private static Gson gson = new Gson();
	
	public static Handler createReimbursement = (ctx) -> {
		String reimJson = ctx.body();
		Reimbursement reim = gson.fromJson(reimJson, Reimbursement.class);
		rserv.createReimbursement(reim);
		ctx.status(201);
		ctx.result(gson.toJson(reim));
	};
	
	public static Handler getAllReimbursements = (ctx) -> {
		List<Reimbursement> reims = rserv.getAllReimbursements();
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
		reim = rserv.updateReimbursement(reim);
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
