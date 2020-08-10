package dev.kusch.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="createBtn")
	public WebElement createBtn;
	
//	@FindBy(tagName="tr")
//	public List<WebElement> reimTable;
	
	@FindBy(className="reimbursementMessage")
	public List<WebElement> reimMessage;
	
	@FindBy(className="reimbursementAmount")
	public List<WebElement> reimAmount;
	
	@FindBy(id="approvedReim1")
	public WebElement approvedReimbursement;
	
	@FindBy(id="decisionBtn1")
	public WebElement approveBtn;
}
