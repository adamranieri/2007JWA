package dev.kusch.pages;

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
	
	@FindBy(id="userField")
	public WebElement userField;
	
	@FindBy(id="passField")
	public WebElement passField;
	
	@FindBy(id="logBtn")
	public WebElement logBtn;
}
