package dev.ranieri.app;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
	
	public static void main(String[] args) {
		
		File file = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());		
		WebDriver driver = new ChromeDriver();
		
		
		driver.get("https://www.google.com/");
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// Implicit wait. Tells selenium to wait 2 seconds before throwing an exception
		// If I try to get the button but can't, keep trying for 2 seconds before throwing an exception
		
		WebElement searchBar = driver.findElement(By.name("q"));
		WebElement searchBtn = driver.findElement(By.name("btnK"));
		
		searchBar.sendKeys("German Shepards");
		searchBtn.click();
		
		driver.quit(); // this closes the browser and shuts down the chromedriver.exe
		
		
		
	}

}
