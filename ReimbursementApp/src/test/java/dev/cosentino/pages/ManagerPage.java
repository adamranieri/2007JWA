package dev.cosentino.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerPage {
	WebDriver driver;
	
	public ManagerPage(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="managerBtn")
	public WebElement updateButton;
	
	@FindBy(id="tableElement")
	public WebElement table;
	
	@FindBy(id="rId")
	public WebElement rId;
	
	@FindBy(id="note")
	public WebElement note;
	
	@FindBy(id="submitBtn")
	public WebElement submit;
}
