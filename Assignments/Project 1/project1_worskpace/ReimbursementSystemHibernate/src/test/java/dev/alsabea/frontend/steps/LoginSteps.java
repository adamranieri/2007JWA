package dev.alsabea.frontend.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dev.alsabea.frontend.pages.LoginPage;
import dev.alsabea.frontend.pages.SubmitRequestPage;
import dev.alsabea.frontend.runners.SubmitRequestRunner;
import junit.framework.Assert;

public class LoginSteps {

	public static SubmitRequestPage srPage = SubmitRequestRunner.srPage;
	public static LoginPage loginPage = SubmitRequestRunner.loginPage;
	public static WebDriver driver = SubmitRequestRunner.driver;
	
//	public static LoginPage loginPage = LoginRunner.loginPage;
//	public static WebDriver driver = LoginRunner.driver;
	public static WebDriverWait wait= new WebDriverWait(driver, 2);
	
	@Given ("Employee is on the login page")
	public void Employee_is_on_the_login_page() throws Throwable {
	    driver.get("http://localhost:7000/xyz-loginpage.html");
	}
	
	@When("^Employee enters his credentials and hits login$")
	public void employee_enters_his_credentials_and_hits_login() throws Throwable {
	    loginPage.username.sendKeys("riaUser");
	    loginPage.password.sendKeys("riaPass");
	    loginPage.loginButton.click();
	}
	


	@Then("^Employee gets redirected to his employee web page$")
	public void employee_gets_directed_to_his_employee_web_page() throws Throwable {
		WebDriverWait wait= new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.titleIs("EMP"));
	   Assert.assertEquals ("EMP", driver.getTitle());
	}
	
	


	@Given("^Manager is on the login page$")
	public void manager_is_on_the_login_page() throws Throwable {
		 driver.get("http://localhost:7000/xyz-loginpage.html");
	}

	@When("^Manager enters his credentials and hits login$")
	public void manager_enters_his_credentials_and_hits_login() throws Throwable {
		  loginPage.username.sendKeys("johnUser");
		  loginPage.password.sendKeys("johnPass");
		  loginPage.loginButton.click();
	}

	@Then("^Manager gets redirected to his employee web page$")
	public void manager_gets_directed_to_his_employee_web_page() throws Throwable {
		
		LoginSteps.wait.until(ExpectedConditions.titleIs("MGR"));
	    Assert.assertEquals ("MGR", driver.getTitle());
	}

	
	
	
	@When("^Employee enters wrong credentials and click login$")
	public void employee_enters_wrong_credentials_and_click_login() throws Throwable {
		loginPage.username.sendKeys("gregUserWrong");
		loginPage.password.sendKeys("gregPassWrong");
		loginPage.loginButton.click();
	}

	@SuppressWarnings("deprecation")
	@Then("^Employee gets an alert telling him that credentials inputted are wrong\\.$")
	public void employee_gets_an_alert_telling_him_that_credentials_inputted_are_wrong() throws Throwable {
		
		Assert.assertNotNull(LoginSteps.wait.until(ExpectedConditions.alertIsPresent()))  ;
		driver.switchTo().alert().dismiss();
	}

	
	@When("^Manager enters wrong credentials and click login$")
	public void manager_enters_wrong_credentials_and_click_login() throws Throwable {
		loginPage.username.sendKeys("johnUserWrong");
		loginPage.password.sendKeys("johnPassWrong");
		loginPage.loginButton.click();
	}

	@SuppressWarnings("deprecation")
	@Then("^Manager gets an alert telling him that credentials inputted are wrong\\.$")
	public void manager_gets_an_alert_telling_him_that_credentials_inputted_are_wrong() throws Throwable {
		
		
	Assert.assertNotNull(LoginSteps.wait.until(ExpectedConditions.alertIsPresent()));
	driver.switchTo().alert().dismiss();
	}

	
	
}
