package dev.cosentino.steps;

import org.openqa.selenium.WebDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dev.cosentino.pages.LoginPage;
import dev.cosentino.pages.ManagerPage;
import dev.cosentino.runners.EmployeeRunner;
import junit.framework.Assert;

public class ManagerSteps {

	public static LoginPage loginpage = EmployeeRunner.loginpage;
	public static ManagerPage managerpage = EmployeeRunner.managerpage;
	public static WebDriver driver = EmployeeRunner.driver;
	
	@Given("^The user is on the manager page$")
	public void the_user_is_on_the_manager_page() throws Throwable {
	    driver.get("http://localhost:7000/manager_home.html");
	}

	@When("^The user clicks Update Reimbursement$")
	public void the_user_clicks_Update_Reimbursement() throws Throwable {
	    managerpage.updateButton.click();
	}

	@When("^The user types \"([^\"]*)\" into id$")
	public void the_user_types_into_id(String rId) throws Throwable {
	    managerpage.rId.sendKeys(rId);
	}

	@When("^The user types \"([^\"]*)\" into note$")
	public void the_user_types_into_note(String note) throws Throwable {
	    managerpage.note.sendKeys(note);
	}

	@When("^The user submits items$")
	public void the_user_submits_items() throws Throwable {
	    managerpage.submit.click();
	}

	@Then("^A  reimbursement was updated$")
	public void a_reimbursement_was_updated() throws Throwable {
	    Assert.assertEquals(true, managerpage.table.isDisplayed());
	}
}
