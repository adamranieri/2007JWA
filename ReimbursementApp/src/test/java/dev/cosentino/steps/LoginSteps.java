package dev.cosentino.steps;

import org.junit.runner.Runner;
import org.openqa.selenium.WebDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dev.cosentino.pages.LoginPage;
import dev.cosentino.runners.EmployeeRunner;
import junit.framework.Assert;

public class LoginSteps {

	public static LoginPage loginpage = EmployeeRunner.loginpage;
	public static WebDriver driver = EmployeeRunner.driver;
	
	@Given("^The user is on the login page$")
	public void the_user_is_on_the_login_page() throws Throwable {
	    driver.get("http://localhost:7000/login.html");
	}
	
	@When("^The user types \"([^\"]*)\" into username field$")
	public void the_user_types_into_username_field(String user) throws Throwable {
	    loginpage.username.sendKeys(user);
	}
	@When("^The user types \"([^\"]*)\" into password field$")
	public void the_user_types_into_password_field(String password) throws Throwable {
	    loginpage.password.sendKeys(password);
	}
	@When("^The user submits credentials$")
	public void the_user_submits_credentials() throws Throwable {
	    loginpage.submit.click();
	}

	@Then("^The user should be on employee page$")
	public void the_user_should_be_on_employee_page() throws Throwable {
	    Assert.assertEquals("http://localhost:7000/login.html", driver.getCurrentUrl());
	}
	
	@Then("^The user should be on manager page$")
	public void the_user_should_be_on_manager_page() throws Throwable {
		Assert.assertEquals("http://localhost:7000/login.html", driver.getCurrentUrl());
	}
	
}
