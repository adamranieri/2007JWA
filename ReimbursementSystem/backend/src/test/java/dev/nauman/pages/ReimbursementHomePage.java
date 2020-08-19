package dev.nauman.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReimbursementHomePage {

	WebDriver driver;
	
	public ReimbursementHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "my_input")
	public WebElement searchField;
	@FindBy(id = "itemField")
	public WebElement itemField;
	@FindBy(id = "statusField")
	public WebElement statusField;
	@FindBy(id = "categoryField")
	public WebElement categoryField;
	@FindBy(id = "amountField")
	public WebElement amountField;
	@FindBy(id = "employeeField")
	public WebElement employeeField;
	@FindBy(id = "noteField")
	public WebElement noteField;
	@FindBy(id = "dateField")
	public WebElement dateField;
	@FindBy(id = "item_btn")
	public WebElement item_btn;
	@FindBy(id = "status_btn")
	public WebElement status_btn;
	@FindBy(id = "category_btn")
	public WebElement category_btn;
	@FindBy(id = "amount_btn")
	public WebElement amount_btn;
	@FindBy(id = "employee_btn")
	public WebElement employee_btn;
	@FindBy(id = "note_btn")
	public WebElement note_btn;
	@FindBy(id = "date_btn")
	public WebElement date_btn;
}
