package dev.nauman.runners;


import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import dev.nauman.pages.ReimbursementHomePage;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",glue = "dev.nauman.steps")
public class ReimbursementHomePageRunner {

	public static WebDriver driver;
	public static ReimbursementHomePage homepage;
	
	@BeforeClass
	public static void setUp() {
		File file = new File("src\\main\\resources\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		homepage = new ReimbursementHomePage(driver);
//		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

}
