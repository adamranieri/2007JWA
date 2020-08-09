package dev.alsabea.frontend.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	
	WebDriver driver;
	
	// Example of Dependency injection where this POM needs a driver
	// The actual driver implementation will change based on the browser
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(id = "usernameInput")
	public WebElement username;
	
	
	@FindBy(id = "passwordInput")
	public WebElement password;
	

	@FindBy(id = "getPerson")
	public WebElement loginButton;
	
}
