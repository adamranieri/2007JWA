package dev.kusch.steps;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dev.kusch.pages.FormPage;
import dev.kusch.pages.HomePage;
import dev.kusch.runners.ReimRunner;
import junit.framework.Assert;

public class ReimbursementApproveSteps {

	public static HomePage homepage = ReimRunner.homepage;
	public static FormPage formpage = ReimRunner.formpage;
	public static WebDriver driver = ReimRunner.driver;
	
	@When("^the Manager clicks the approve/deny button$")
	public void the_Manager_clicks_the_approve_deny_button() throws Throwable {
	   homepage.approveBtn.click();
	    synchronized (driver) {
	    	driver.wait(500);
	    }
	}

	@When("^the Manager types \"([^\"]*)\" into the message field$")
	public void the_Manager_types_into_the_message_field(String arg1) throws Throwable {
	    formpage.acceptMessageField.sendKeys("Approved, hope the conference went well");
	}

	@When("^the Manager clicks the approve button$")
	public void the_Manager_clicks_the_approve_button() throws Throwable {
	    formpage.approveBtn.click();
	    synchronized (driver) {
	    	driver.wait(500);
	    }
	}

	@Then("^the last reimbursement should be \"([^\"]*)\"$")
	public void the_last_reimbursement_should_be(String arg1) throws Throwable {
	    String[] messageHalves = homepage.approvedReimbursement.getText().split(",");
	    String message = messageHalves[0];
	    Assert.assertEquals(message, "Approved");
	}

}
