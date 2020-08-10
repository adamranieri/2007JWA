package dev.cosentino.app;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import dev.cosentino.controllers.EmployeeController;
import dev.cosentino.controllers.LoginController;
import dev.cosentino.controllers.ReimbursementController;
import io.javalin.Javalin;

public class App {

	public static void main(String[] args) {
		Javalin app = Javalin.create(config->{
			config.addStaticFiles("/frontend");
		}).start(7000);
		
		// login
		// works
		app.get("/login", LoginController.loginUser);
		// works
		app.get("/userinfo", LoginController.getUserInfo);

		
		// manager view
		// works 
		app.get("/managers/:m_id/reimbursements", ReimbursementController.getAllReimbursements);
		// works
		app.get("/managers/:m_id/reimbursements/:r_id", ReimbursementController.getReimbursementById);
		// works
		app.get("/managers/:m_id/employees", EmployeeController.getAllEmployees);
		// works
		app.get("/managers/:m_id/employees/:e_id", EmployeeController.getEmployeeById);
		//works
		app.put("/managers/:m_id/reimbursements/:r_id", ReimbursementController.updateReimbursement);		
		// works
		app.get("/managers/:m_id/employees/:e_id/reimbursements", ReimbursementController.getReimbursementByEmpId);
		
		// employee view
		// works
		app.post("/employees/:e_id/reimbursements", ReimbursementController.createReimbursement);
		// works
		app.get("/employees/:e_id/reimbursements", ReimbursementController.getReimbursementByEmpId);
		// works
		app.get("/employees/:e_id/reimbursements/:r_id", ReimbursementController.getReimbursementById);
		// works
		app.get("/employees/:e_id", EmployeeController.getEmployeeById); 

	}

}
