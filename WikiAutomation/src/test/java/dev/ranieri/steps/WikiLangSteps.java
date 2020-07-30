package dev.ranieri.steps;

import org.openqa.selenium.WebDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dev.ranieri.pages.WikiHomePage;
import dev.ranieri.runners.WikiRunner;
import junit.framework.Assert;

public class WikiLangSteps {
	
	public static WikiHomePage homepage = WikiRunner.homepage;
	public static WebDriver driver = WikiRunner.driver;
	
	@Given("^The Guest is on the Wikipedia home page$")
	public void the_Guest_is_on_the_Wikipedia_home_page() throws Throwable {
	    driver.get("https://www.wikipedia.org/");
	}

	@When("^The Guest clicks English$")
	public void the_Guest_clicks_English() throws Throwable {
		homepage.english.click();
	}

	// thens should include an assertion
	@Then("^The Guest should be on the English home page$")
	public void the_Guest_should_be_on_the_English_home_page() throws Throwable {
	  Assert.assertEquals("Wikipedia, the free encyclopedia", driver.getTitle());
	}

	@When("^The Guest clicks Espanol$")
	public void the_Guest_clicks_Espanol() throws Throwable {
	   homepage.espanol.click();
	}

	@Then("^The Guest should be on the Spanish home page$")
	public void the_Guest_should_be_on_the_Spanish_home_page() throws Throwable {
		 Assert.assertEquals("Wikipedia, la enciclopedia libre", driver.getTitle());
	}

	@When("^The Guest clicks on Francais$")
	public void the_Guest_clicks_on_Francais() throws Throwable {
	    homepage.francais.click();
	}

	@Then("^The Guest should be on the French home page$")
	public void the_Guest_should_be_on_the_French_home_page() throws Throwable {
		 Assert.assertEquals("Wikipédia, l'encyclopédie libre", driver.getTitle());
	}


}
