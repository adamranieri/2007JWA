package dev.kusch.steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import dev.kusch.pages.HomePage;
import dev.kusch.runners.ReimRunner;

public class ReimbursementSubmitSteps {
	
	public static HomePage homepage = ReimRunner.homepage;
	public static WebDriver driver = ReimRunner.driver;
	
	@Given("^the Employee is on the home page$")
	public void the_Employee_is_on_the_home_page() throws Throwable {
		driver.get("file:///C:/Users/Justin/eclipse-workspace/ReimbursementApplication/src/main/resources/frontend/home.html");
	}

	@Then("^the title should be \"([^\"]*)\"$")
	public void the_title_should_be(String arg1) throws Throwable {
	    Assert.assertEquals("MPortal", driver.getTitle());
	}

}
