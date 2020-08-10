package dev.cosentino.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployeePage {
	WebDriver driver;
	
	public EmployeePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	 
	@FindBy(id="myBtn")
	public WebElement createReimbursementButton;
	
	@FindBy(id="tableElement")
	public WebElement table;
	
	@FindBy(id="logoutBtn")
	public WebElement logoutButton;
	
	@FindBy(id="descInput")
	public WebElement description;
	
	@FindBy(id="amountInput")
	public WebElement amount;
	
	@FindBy(id="submitBtn")
	public WebElement submitButton;
}
