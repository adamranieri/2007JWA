package dev.kusch.app;

import dev.kusch.controllers.EmployeeController;
import dev.kusch.controllers.ManagerController;
import dev.kusch.controllers.ReimbursementController;
import io.javalin.Javalin;

public class App {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);
		
		// GET
		app.get("/employees/:eid", EmployeeController.getEmployee);
		app.get("/managers/:mid", ManagerController.getManager);
		app.get("/reimbursements", ReimbursementController.getAllReimbursements);
		app.get("/reimbursements/:rid", ReimbursementController.getReimbursementById);
		
		// PUT
		app.put("/reimbursements", ReimbursementController.createReimbursement);
		
		// POST
		app.post("/employees/:eid", EmployeeController.updateEmployee);
		app.post("/managers/:mid", ManagerController.updateManager);
		app.post("/reimbursements/:rid", ReimbursementController.updateReimbursement);
		
		// DELETE
		app.delete("/reimbursements", ReimbursementController.deleteReimbursement);
	}

}
