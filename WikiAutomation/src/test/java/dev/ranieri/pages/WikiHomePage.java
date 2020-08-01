package dev.ranieri.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WikiHomePage {
	
	WebDriver driver;
	
	// Example of Dependency injection where this POM needs a driver
	// The actual driver implementation will change based on the browser
	public WikiHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	// Languages
	@FindBy(id = "js-link-box-en")
	public WebElement english;
	
	@FindBy(css = "div[lang='es']") // finding by CSS can be really helpful if there is no name or id
	public WebElement espanol;
	
	//xpath is the very direct directions to your element
	//if your page adds or remvoes elements the xpath changes
	@FindBy(xpath = "/html/body/div[2]/div[6]") // xpath leads to the dark side. Becuase it is so easy but prone failure
	public WebElement francais;

}
