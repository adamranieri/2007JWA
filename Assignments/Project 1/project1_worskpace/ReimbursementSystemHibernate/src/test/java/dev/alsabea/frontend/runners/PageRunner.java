package dev.alsabea.frontend.runners;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import dev.alsabea.frontend.pages.LoginPage;
import dev.alsabea.frontend.pages.ManagerUpdateRequestPage;
import dev.alsabea.frontend.pages.SubmitRequestPage;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "dev.alsabea.frontend.steps") 
public class PageRunner {

	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public static SubmitRequestPage srPage;
	public static LoginPage loginPage;
	public static ManagerUpdateRequestPage updateRequestPage;
	
	@BeforeClass
	public static void setUp() {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());	
		
		driver = new ChromeDriver();
		wait= new WebDriverWait(driver, 2);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		srPage = new SubmitRequestPage(driver);	
		loginPage = new LoginPage(driver);
		updateRequestPage= new ManagerUpdateRequestPage(driver);
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
	
}
