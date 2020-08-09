package dev.alsabea.frontend.pages;

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
	WebElement secondRow;
	
	
	
	
	
}
