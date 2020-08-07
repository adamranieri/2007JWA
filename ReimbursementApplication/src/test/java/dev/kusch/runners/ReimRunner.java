package dev.kusch.runners;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import dev.kusch.pages.FormPage;
import dev.kusch.pages.HomePage;
import dev.kusch.pages.LoginPage;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue="dev.kusch.steps")
public class ReimRunner {
	
	public static WebDriver driver;
	public static HomePage homepage;
	public static LoginPage loginpage;
	public static FormPage formpage;

	@BeforeClass
	public static void setUp() {
		File file = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		formpage = new FormPage(driver);
	}
	
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

}
