package dev.alsabea.frontend.steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dev.alsabea.frontend.pages.ManagerUpdateRequestPage;
import dev.alsabea.frontend.runners.PageRunner;

public class ManagerUpdateRequestSteps {

	ManagerUpdateRequestPage urPage= PageRunner.updateRequestPage;
	public static WebDriver driver = PageRunner.driver;
	public static WebDriverWait wait= PageRunner.wait;

	private int initialPendingTbSize;
	private int initialJudgedTbSize ;
	
	@When("^Manager clicks on the name of an employee$")
	public void manager_clicks_on_the_name_of_an_employee() throws Throwable {
	   urPage.secondRow.click();
	}

	@Then("^Manager gets redirected to the employee requests page$")
	public void manager_gets_redirected_to_the_employee_requests_page() throws Throwable {
	   wait.until(ExpectedConditions.titleIs("employee_requests"));
	   
	   	//I have to put the below lines here here.
	    this.initialPendingTbSize= urPage.pendingRequestsTableSize();
	    this.initialJudgedTbSize= urPage.judgedRequestsTableSize();
	}

	@When("^Manager fills a reason, chooses Approve, and clicks update on a request$")
	public void manager_fills_a_reason_chooses_Approve_and_clicks_update_on_a_request() throws Throwable {
	    urPage.reasonTextAreaApprove.sendKeys("filled from Selenium this is a reason APPROVE");
	    Select selector = new Select(urPage.optionsIdApprove);
	    
	    selector.selectByVisibleText("PENDING");
	    selector.selectByVisibleText("APPROVED");

	    urPage.updateBtnApprove.click();
	}
	
	@When("^Manager fills a reason, chooses Denied and clicks update on a request$")
	public void manager_fills_a_reason_chooses_Denied_and_clicks_update_on_a_request() throws Throwable {
		 urPage.reasonTextAreaDeny.sendKeys("filled from Selenium this is a reason  DENY");
		    Select selector = new Select(urPage.optionsIdDeny);
		    
		    selector.selectByVisibleText("PENDING");
		    selector.selectByVisibleText("DENIED");

		    urPage.updateBtnDeny.click();
	}
	
	
	@Then("^Page gets reloaded$")
	public void page_gets_reloaded() throws Throwable {
		
		//there will be no alert, this is just a trick to force selenium to wait without 
		// conditions
		try {
			wait.until(ExpectedConditions.alertIsPresent()); //there will be no alert
		} catch (Exception e) {
			//do nothing
		}
		
		
	}
	
	

	@Then("^the request moves from pending requests to past requests of the employee$")
	public void the_request_moves_from_pending_requests_to_past_requests_of_the_employee() throws Throwable {
	   Assert.assertTrue(this.initialPendingTbSize > urPage.pendingRequestsTableSize());
	   Assert.assertTrue(this.initialJudgedTbSize < urPage.judgedRequestsTableSize());
	}

	
	

	
}
