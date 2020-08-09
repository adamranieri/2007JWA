package dev.alsabea.frontend.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dev.alsabea.frontend.pages.LoginPage;
import dev.alsabea.frontend.pages.SubmitRequestPage;
import dev.alsabea.frontend.runners.SubmitRequestRunner;
import junit.framework.Assert;

public class SubmitRequestSteps {

	
	public static SubmitRequestPage srPage = SubmitRequestRunner.srPage;
	public static LoginPage loginPage = SubmitRequestRunner.loginPage;
	public static WebDriver driver = SubmitRequestRunner.driver;
	public static WebDriverWait wait= new WebDriverWait(driver, 2);
	
	private static int rowCount = srPage.countRequestRows();

	@When("^Employee clicks submit new request$")
	public void employee_clicks_submit_new_request() throws Throwable {
	    srPage.createANewRequestBtn.click();
	    wait.until(ExpectedConditions.titleIs("Reimbursement Request"));
	}
	
	@Then("^Employee gets redirected to the Reimbursement Request Form$")
	public void employee_gets_redirected_to_the_Reimbursement_Request_Form() throws Throwable {
	   Assert.assertEquals("Reimbursement Request", driver.getTitle());
	}
	
	@When("^Employee fills the form and clicks submit$")
	public void employee_fills_the_form_and_clicks_submit() throws Throwable {
	    srPage.textArea.sendKeys("request filled from SELENIUM (I Made it)");
	    srPage.submitButton.click();
	}
	
	
	@Then("^Employee gets redirected to the employee web page\\.$")
	public void employee_gets_redirected_to_the_employee_web_page() throws Throwable {
	    wait.until(ExpectedConditions.titleIs("EMP"));
	}
	
	
	@Then("^The New request is visible$")
	public void the_New_request_is_visible() throws Throwable {
		
	   Assert.assertEquals(rowCount + 1, srPage.countRequestRows()  );

	  
	}

	
}













