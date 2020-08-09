package dev.alsabea.frontend.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerUpdateRequestPage {

	
	WebDriver driver;
	
	public ManagerUpdateRequestPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "bro")
	public WebElement secondRow;
	
	@FindBy(id = "theReason_7")
	public WebElement reasonTextAreaApprove;
	
	
	@FindBy (id = "dropDown_7")
	public WebElement optionsIdApprove;
	
	
	
	@FindBy(id = "updateRequestBtn_7")
	public WebElement updateBtnApprove;
	

	@FindBy(id = "theReason_8")
	public WebElement reasonTextAreaDeny;
	
	
	@FindBy (id = "dropDown_8")
	public WebElement optionsIdDeny;
	
	
	
	@FindBy(id = "updateRequestBtn_8")
	public WebElement updateBtnDeny;
	

	
	
	
	public int pendingRequestsTableSize() {
		
		
		List<WebElement> tableRows = driver.findElements(By.id("pendingRow"));
		return tableRows.size();
	}
	
	public int judgedRequestsTableSize() {
		List<WebElement> tableRows = driver.findElements(By.id("judgedRow"));
		return tableRows.size();
	}
	
	
	
}














