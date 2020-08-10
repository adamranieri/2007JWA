package dev.cosentino.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="usernameField")
	public WebElement username;

	@FindBy(id="passwordField")
	public WebElement password;
	
	@FindBy(id="loginButton")
	public WebElement submit;
}
