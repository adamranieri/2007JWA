package dev.kusch.app;

import dev.kusch.controllers.EmployeeController;
import dev.kusch.controllers.LoginController;
import dev.kusch.controllers.ManagerController;
import dev.kusch.controllers.ReimbursementController;
import io.javalin.Javalin;

public class App {
	
	public static void main(String[] args) {		
		Javalin app = Javalin.create(config -> {
			
			config.enableCorsForAllOrigins();
			
		}).start(7000);
		
		// GET
		app.get("/employees/:eid", EmployeeController.getEmployee);
		app.get("/managers/:mid", ManagerController.getManager);
		app.get("/reimbursements", ReimbursementController.getAllReimbursements);
		app.get("/employees/:eid/reimbursements", ReimbursementController.getReimbursementsByUser);
		app.get("/reimbursements/:rid", ReimbursementController.getReimbursementById);
		
		// PUT
		app.put("/reimbursements", ReimbursementController.updateReimbursement);
		app.put("/employees", EmployeeController.updateEmployee);
		app.put("/managers", ManagerController.updateManager);
		
		// POST
		app.post("/employees/:eid/reimbursements", ReimbursementController.createReimbursement);
		
		// DELETE
		app.delete("/reimbursements", ReimbursementController.deleteReimbursement);
		
		app.post("/login", LoginController.loginHandler);
		app.get("/userinfo", LoginController.getUserInfo);
	}

}
