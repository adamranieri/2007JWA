package dev.cosentino.runners;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import dev.cosentino.pages.EmployeePage;
import dev.cosentino.pages.LoginPage;
import dev.cosentino.pages.ManagerPage;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = "dev.cosentino.steps")
public class EmployeeRunner {

	public static WebDriver driver;
	public static LoginPage loginpage;
	public static EmployeePage employeepage;
	public static ManagerPage managerpage;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File file = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		
		driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
		driver.manage().timeouts().setScriptTimeout(1, TimeUnit.MINUTES);
		
		loginpage = new LoginPage(driver); 
		employeepage = new EmployeePage(driver);
		managerpage = new ManagerPage(driver);
	}
 
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

}
