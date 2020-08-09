//package dev.alsabea.frontend.runners;
//
//import java.io.File;
//import java.util.concurrent.TimeUnit;
//
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.runner.RunWith;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;
//import dev.alsabea.frontend.pages.LoginPage;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(features = "src/test/resources/features", glue = "dev.alsabea.frontend.steps") // features is the location 
//public class LoginRunner {
//
//	public static WebDriver driver;
//	public static LoginPage loginPage;
//	
//	@BeforeClass
//	public static void setUp() {
//		File file = new File("src/test/resources/chromedriver.exe");
//		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());	
//		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//		loginPage = new LoginPage(driver);// Dependency Injection		
//	}
//
//	@AfterClass
//	public static void tearDown() {
//		driver.quit();
//	}
//	
//	
//}
