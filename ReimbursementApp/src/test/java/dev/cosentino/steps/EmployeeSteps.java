package dev.cosentino.steps;

import org.openqa.selenium.WebDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dev.cosentino.pages.EmployeePage;
import dev.cosentino.runners.EmployeeRunner;
import junit.framework.Assert;
 
public class EmployeeSteps {
	
	public static EmployeePage employeepage = EmployeeRunner.employeepage;
	public static WebDriver driver = EmployeeRunner.driver;

	@Given("^The user is on the employee page$")
	public void the_user_is_on_the_employee_page() throws Throwable {
	    driver.get("http://localhost:7000/employee_home.html");
	}
	
	@When("^The user clicks Create New Reimbursement$")
	public void the_user_clicks_Create_New_Reimbursement() throws Throwable {
	    employeepage.createReimbursementButton.click();
	}
	
	@When("^The user types \"([^\"]*)\" into description$")
	public void the_user_types_into_description(String desc) throws Throwable {
	    employeepage.description.sendKeys(desc);
	}
	
	@When("^The user types \"([^\"]*)\" into amount$")
	public void the_user_types_into_amount(String amount) throws Throwable {
	    employeepage.description.sendKeys(amount);
	}
	@When("^The user clicks submit$")
	public void the_user_clicks_submit() throws Throwable {
	    employeepage.submitButton.click();
	}
	
	@Then("^A new reimbursement was created$")
	public void a_new_reimbursement_was_created() throws Throwable {
	    Assert.assertEquals(true, employeepage.table.isDisplayed());
	}

}
