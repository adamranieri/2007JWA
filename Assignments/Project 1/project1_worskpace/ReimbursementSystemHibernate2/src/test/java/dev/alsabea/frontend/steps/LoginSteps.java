package dev.alsabea.frontend.steps;

import org.openqa.selenium.WebDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dev.alsabea.frontend.pages.LoginPage;
import dev.alsabea.frontend.runners.LoginRunner;

public class LoginSteps {

	public static LoginPage loginPage = LoginRunner.loginPage;
	public static WebDriver driver = LoginRunner.driver;
	
	
	@Given ("Employee is on the login page")
	public void Employee_is_on_the_login_page() throws Throwable {
	    driver.get("https://www.wikipedia.org/");
	}
	
	@When("^Employee enters his credentials and hits login$")
	public void employee_enters_his_credentials_and_hits_login() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^Employee gets directed to his employee web page$")
	public void employee_gets_directed_to_his_employee_web_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^Manager is on the login page$")
	public void manager_is_on_the_login_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Manager enters his credentials and hits login$")
	public void manager_enters_his_credentials_and_hits_login() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^Manager gets directed to his employee web page$")
	public void manager_gets_directed_to_his_employee_web_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	
	
	
	
	
	
	
	
}
