package dev.edwin.app;


import dev.edwin.contollers.AccountController;
import dev.edwin.contollers.CustomerController;
import io.javalin.Javalin;

public class App {

	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);
			
// REST APIs should support at a minimum the basic CRUD operations
				// read operations
//				app.get("/schools", SchoolController.getAllSchools);// this method returns all schools	
//				app.get("/schools/:sid", SchoolController.getSchoolById);// this returns a specific school based on the id			
				// create operation
//				app.post("/schools", SchoolController.createSchool);			
				//update operation
//				app.put("/schools", SchoolController.updateSchool);			
				//delete operation
//				app.delete("/schools/:sid", SchoolController.deleteSchool);			
//				app.get("/schools/:id/students", StudentController.getAllStudentsFromSchoolId);//returns All the students in that school			
//				app.get("/schools/:id/students/:stuid", StudentController.getStudentById);//returns a student with that id
//				app.get("/students/:stuid", StudentController.getStudentById); // also correct			
//				app.post("/schools/:id/students", StudentController.createStudent);//Add a new student to that school	
//				app.put("/schools/:id/students", StudentController.updateStudent);// update a student at that school		
//				app.delete("/schools/:id/students/:stuid", StudentController.deleteStudent);// delete this student at that school
		
//		CUSTOMERS HTTP REQUESTS       //
//		GET
		app.get("/customers", CustomerController.getAllCustomers);//Can have queries appended
		app.get("/customers/:cid", CustomerController.getCustomerById);	
//		POST
		app.post("/customers", CustomerController.signUpNewCustomer);	
//		UPDATE
		app.put("/customers", CustomerController.updateCustomer);	
//		DELETE
		app.delete("/customers/:cid", CustomerController.deleteCustomer);
		
		
		
//		ACCOUNT HTTP REQUESTS    //
//		GET
		app.get("/customers/:cid/accounts", AccountController.getAllCustomerAccounts);//Can have queries appended
		app.get("/customers/:cid/accounts/:aid", AccountController.getAccount);
		app.get("/accounts/:aid",  AccountController.getAccount);
//		POST
		app.post("/customers/:cid/accounts", AccountController.createNewAccount);
//		UPDATE
		app.put("/customers/:cid/accounts", AccountController.updateAccount);
		app.put("/customers/:cid/accounts/:aid/deposit", AccountController.makeDeposit);
		app.put("/customers/:cid/accounts/:aid/withdraw", AccountController.makeWithdraw);
//		DELETE
		app.delete("/customers/:cid/accounts/:aid", AccountController.closeAccount);
	}

}
