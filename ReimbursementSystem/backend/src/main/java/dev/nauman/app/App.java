package dev.nauman.app;

import dev.nauman.controllers.EmployeeController;
import dev.nauman.controllers.LoginController;
import dev.nauman.controllers.ManagerController;
import dev.nauman.controllers.ReimbursementController;
import io.javalin.Javalin;

public class App {

	public static void main(String[] args) {
		Javalin app = Javalin.create(config ->{
			config.enableCorsForAllOrigins();
			config.addStaticFiles("./frontend");
			}).start(7000);
		app.post("/reimbursements/:eId", ReimbursementController.SubmitReimbursement);
		
		//get all
		app.get("/reimbursements/ascendingItem", ReimbursementController.AscendingItem);
		app.get("/reimbursements/ascendingStatus", ReimbursementController.AscendingStatus);
		app.get("/reimbursements/ascendingCategory", ReimbursementController.AscendingCategory);
		app.get("/reimbursements/ascendingAmount", ReimbursementController.AscendingAmount);
		app.get("/reimburslements/ascendingEmployee", ReimbursementController.AscendingEmployee);
		app.get("/reimbursements/ascendingNote", ReimbursementController.AscendingNote);
		app.get("/reimbursements/ascendingDate", ReimbursementController.AscendingDate);
		app.get("/reimbursements", ManagerController.FillManagerTable);
		app.get("/reimbursements/:eId", EmployeeController.FillEmployeeTable);
//		
//		app.put("/reimbursements", null);
//		app.put("/reimbursements/changestatus",null);
//		
//		app.delete("/reimbursements/:rId", null);
		
		app.post("/login", LoginController.createSession);
		app.get("/login", LoginController.getUserInfo);
		
	}
}
