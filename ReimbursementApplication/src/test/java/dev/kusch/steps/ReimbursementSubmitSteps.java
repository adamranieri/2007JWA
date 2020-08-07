package dev.kusch.steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dev.kusch.pages.FormPage;
import dev.kusch.pages.HomePage;
import dev.kusch.pages.LoginPage;
import dev.kusch.runners.ReimRunner;

public class ReimbursementSubmitSteps {
	
	public static HomePage homepage = ReimRunner.homepage;
	public static LoginPage loginpage = ReimRunner.loginpage;
	public static FormPage formpage = ReimRunner.formpage;
	public static WebDriver driver = ReimRunner.driver;
	
	@Given("^the Employee is on the home page$")
	public void the_Employee_is_on_the_home_page() throws Throwable {
		driver.get("file:///C:/Users/Justin/eclipse-workspace/ReimbursementApplication/src/main/resources/frontend/home.html");
	}

	@Then("^the title should be \"([^\"]*)\"$")
	public void the_title_should_be(String arg1) throws Throwable {
		Assert.assertEquals("MPortal", driver.getTitle());
	}
	
	@Given("^the Employee is on the login page$")
	public void the_Employee_is_on_the_login_page() throws Throwable {
		driver.get("file:///C:/Users/Justin/eclipse-workspace/ReimbursementApplication/src/main/resources/frontend/login.html");
	}

	@When("^the Employee types \"([^\"]*)\" into the username field$")
	public void the_Employee_types_into_the_username_field(String arg1) throws Throwable {
	    loginpage.userField.sendKeys(arg1);
	}

	@When("^the Employee types \"([^\"]*)\" into the password field$")
	public void the_Employee_types_into_the_password_field(String arg1) throws Throwable {
	    loginpage.passField.sendKeys(arg1);
	}

	@When("^the Employee clicks the submit button$")
	public void the_Employee_clicks_the_submit_button() throws Throwable {
		loginpage.logBtn.click();
		synchronized (driver) {
			driver.wait(300);
		}
	}
	
	@Then("^the Employee should be on the home page$")
	public void the_Employee_should_be_on_the_home_page() throws Throwable {
	    
	}
	
	@When("^the Employee clicks the create reimbursement button$")
	public void the_Employee_clicks_the_create_reimbursement_button() throws Throwable {
	    homepage.createBtn.click();
	    synchronized (driver) {
	    	driver.wait(300);
	    }
	}
	
	@When("^the Employee types \"([^\"]*)\" into the reason field$")
	public void the_Employee_types_into_the_reason_field(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^the Employee types \"([^\"]*)\" into the amount field$")
	public void the_Employee_types_into_the_amount_field(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^the Employee clicks the send button$")
	public void the_Employee_clicks_the_send_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

}
