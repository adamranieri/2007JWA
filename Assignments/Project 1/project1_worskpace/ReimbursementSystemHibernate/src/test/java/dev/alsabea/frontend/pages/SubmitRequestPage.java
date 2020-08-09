package dev.alsabea.frontend.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class SubmitRequestPage {

	WebDriver driver;
	
	public SubmitRequestPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	
	@FindBy(id = "submitRequest")
	public WebElement createANewRequestBtn;
	
	
	@FindBy (id = "theRequest")
	public WebElement textArea;
	
	
	@FindBy (id= "submitBtn")
	public WebElement submitButton;
	
	
	public int countRequestRows() {
		List<WebElement> rows = driver.findElements(By.tagName("tr"));
		return rows.size();
	}

	

}








