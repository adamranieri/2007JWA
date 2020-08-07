package dev.kusch.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormPage {
	WebDriver driver;
	
	public FormPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="reasonField")
	public WebElement reasonField;
	
	@FindBy(id="amountField")
	public WebElement amountField;
	
	@FindBy(id="createBtnTwo")
	public WebElement sendReimBtn;
	
	@FindBy(id="messageField")
	public WebElement acceptMessageField;
	
	@FindBy(id="approveBtn")
	public WebElement approveBtn;
	
	@FindBy(id="denyBtn")
	public WebElement denyBtn;
}
