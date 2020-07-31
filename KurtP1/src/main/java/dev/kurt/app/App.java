package dev.kurt.app;

import dev.kurt.controllers.EmployeeController;
import dev.kurt.controllers.ManagerController;
import dev.kurt.controllers.ReimbursementController;
import dev.kurt.daos.EmployeeDAOHibernate;
import dev.kurt.daos.ManagerDAOHibernate;
import dev.kurt.daos.ReimbursementDAOHibernate;
import dev.kurt.services.EmployeeService;
import dev.kurt.services.EmployeeServiceImpl;
import dev.kurt.services.ManagerService;
import dev.kurt.services.ManagerServiceImpl;
import dev.kurt.services.ReimbursementService;
import dev.kurt.services.ReimbursementServiceImpl;
import io.javalin.Javalin;

public class App {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config -> {
			config.addStaticFiles("/frontend");
		}).start(7000);
		
		EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOHibernate(), new ReimbursementDAOHibernate());
		ReimbursementService reimbursementService = new ReimbursementServiceImpl(new ReimbursementDAOHibernate());
		ManagerService managerService = new ManagerServiceImpl(new ManagerDAOHibernate());
		
		EmployeeController employeeController = new EmployeeController(employeeService);
		ReimbursementController reimbursementController = new ReimbursementController(employeeService, reimbursementService);
		ManagerController managerController = new ManagerController(managerService);
		
		
		app.post("/employees",employeeController.createEmployee);
		app.post("/employees/:eid/reimbursements",reimbursementController.createReimbursement);
		app.post("/managers",managerController.createManager);
		// app.post(/login)
		// DTO 
		// let logincreds={
		// user: user,
		// pass: pass
		// }
		// dev.kurt.dtos
		// dont relate directly to entity
		/*
		 * 
		 * 
		 * public class loginDTO {
		 * 		private string user
		 * 		private string password
		 *  	make a new controller and write a specific handler for it
		 * }
		 * 
		 * */
		
		
		
		app.get("/employees", employeeController.getAllEmployees);
		app.get("/employees/:eid", employeeController.getEmployeeById);
		app.get("/employees/:eid/reimbursements", reimbursementController.getReimbursementsByEmployee);
		app.get("/employees/:eid/reimbursements/:rid", reimbursementController.getReimbursementById);
		app.get("/reimbursements", reimbursementController.getAllReimbursements);
		app.get("/reimbursements/:rid",reimbursementController.getReimbursementById);
		app.get("/managers", managerController.getAllManagers);
		app.get("/managers/:mid", managerController.getManagerById);
		// ctx.redirect
		
		app.put("/employees",employeeController.updateEmployee);
		app.put("/employees/:eid/reimbursements", reimbursementController.updateReimbursement);
		app.put("/managers", managerController.updateManager);
		
		app.delete("/employees/:eid", employeeController.deleteEmployee);
		app.delete("/employees/:eid/reimbursements/:rid", reimbursementController.deleteReimbursement);
		app.delete("/managers/:mid", managerController.deleteManager);
		
	}
}
