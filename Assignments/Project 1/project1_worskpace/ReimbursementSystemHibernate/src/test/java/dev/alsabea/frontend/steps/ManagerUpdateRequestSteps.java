package dev.alsabea.frontend.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dev.alsabea.frontend.pages.LoginPage;
import dev.alsabea.frontend.runners.PageRunner;

public class ManagerUpdateRequestSteps {

	public static LoginPage loginPage = PageRunner.loginPage;
	public static WebDriver driver = PageRunner.driver;
	public static WebDriverWait wait= PageRunner.wait;

	
	
	@When("^Manager clicks on the name of an employee$")
	public void manager_clicks_on_the_name_of_an_employee() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^Manager gets redirected to the employee requests page$")
	public void manager_gets_redirected_to_the_employee_requests_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Manager fills a reason, chooses Approve, and clicks update on a request$")
	public void manager_fills_a_reason_chooses_Approve_and_clicks_update_on_a_request() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the request moves from pending requests to past requests of the employee$")
	public void the_request_moves_from_pending_requests_to_past_requests_of_the_employee() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	
}
