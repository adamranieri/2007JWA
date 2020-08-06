package dev.kusch.runners;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import dev.kusch.pages.HomePage;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue="dev.kusch.steps")
public class ReimRunner {
	
	public static WebDriver driver;
	public static HomePage homepage;

	@BeforeClass
	public static void setUp() {
		File file = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		homepage = new HomePage(driver);
	}
	
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

}
