package dev.nauman.steps;


import org.openqa.selenium.WebDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.deps.com.thoughtworks.xstream.io.xml.Dom4JDriver;
import dev.nauman.pages.ReimbursementHomePage;
import dev.nauman.runners.ReimbursementHomePageRunner;
import junit.framework.Assert;

public class ReimbursementHomePageSteps {

	public static ReimbursementHomePage homepage = ReimbursementHomePageRunner.homepage;
	public static WebDriver driver = ReimbursementHomePageRunner.driver;
	 
	@Given("^the manager is on the pawnee reimbursement manager page$")
	public void the_manager_is_on_the_pawnee_reimbursement_manager_page() throws Throwable {
		driver.get("http://localhost:7000/reimbursement_page.html");
	}
	@When("^the manager types into the search field \"([^\"]*)\"$")
	public void the_manager_types_into_the_search_field(String arg1) throws Throwable {
		System.out.print("input - " + arg1);
	    homepage.searchField.sendKeys(arg1);
	}

	@Then("^the first item should be \"([^\"]*)\"$")
	public void the_first_item_should_be(String arg1) throws Throwable {
		System.out.println(homepage.itemField.getAttribute("style"));
		Assert.assertEquals("",  homepage.itemField.getAttribute("style.display"));
	}
	@Then("^the first status should be \"([^\"]*)\"$")
	public void the_first_status_should_be(String arg1) throws Throwable {
		System.out.println(homepage.statusField.getAttribute("style"));
		Assert.assertEquals("",  homepage.statusField.getAttribute("style.display"));
	}

	@Then("^the first category should be \"([^\"]*)\"$")
	public void the_first_category_should_be(String arg1) throws Throwable {
		System.out.println(homepage.categoryField.getAttribute("style"));
		Assert.assertEquals("",  homepage.categoryField.getAttribute("style"));
	}

	@Then("^the first amount should be \"([^\"]*)\"$")
	public void the_first_amount_should_be(String arg1) throws Throwable {
		System.out.println(homepage.amountField.getAttribute("style"));
		Assert.assertEquals("",  homepage.amountField.getAttribute("style"));
	}

	@Then("^the first employee should be \"([^\"]*)\"$")
	public void the_first_employee_should_be(String arg1) throws Throwable {
		System.out.println(homepage.employeeField.getAttribute("style"));
		Assert.assertEquals("",  homepage.employeeField.getAttribute("style"));
	}

	@Then("^the first note should be \"([^\"]*)\"$")
	public void the_first_note_should_be(String arg1) throws Throwable {
		System.out.println(homepage.noteField.getAttribute("style"));
		Assert.assertEquals("",  homepage.noteField.getAttribute("style"));
	}

	@Then("^the first date should be \"([^\"]*)\"$")
	public void the_first_date_should_be(String arg1) throws Throwable {
		System.out.println(homepage.dateField.getAttribute("style"));
		Assert.assertEquals("",  homepage.dateField.getAttribute("style"));
	}
//
//	@When("^the manager clicks the sortItem button the first time$")
//	public void the_manager_clicks_the_sortItem_button_the_first_time() throws Throwable {
//	    homepage.item_btn.click();
//	}
//
//	@Then("^the reimbursements should sort alphabetically by item$")
//	public void the_reimbursements_should_sort_alphabetically_by_item() throws Throwable {
//		Assert.assertEquals("Brainstorming snackies",  homepage.itemField.getAttribute("innerText"));
//	}
//
//	@When("^the manager clicks the sortItem button the second time$")
//	public void the_manager_clicks_the_sortItem_button_the_second_time() throws Throwable {
//	    homepage.item_btn.click();
//	}
//
//	@Then("^the reimbursements should sort unalphabetically by item$")
//	public void the_reimbursements_should_sort_unalphabetically_by_item() throws Throwable {
//		Assert.assertEquals("wine",  homepage.itemField.getAttribute("innerText"));
//	}
//
//	@When("^the manager clicks the sortStatus button the first time$")
//	public void the_manager_clicks_the_sortStatus_button_the_first_time() throws Throwable {
//	    homepage.status_btn.click();
//	}
//
//	@Then("^the reimbursements should sort alphabetically by status$")
//	public void the_reimbursements_should_sort_alphabetically_by_status() throws Throwable {
//		Assert.assertEquals("approved",  homepage.statusField.getAttribute("innerText"));
//	}
//
//	@When("^the manager clicks the sortStatus button the second time$")
//	public void the_manager_clicks_the_sortStatus_button_the_second_time() throws Throwable {
//		homepage.status_btn.click();
//	}
//
//	@Then("^the reimbursements should sort unalphabetically by status$")
//	public void the_reimbursements_should_sort_unalphabetically_by_status() throws Throwable {
//		Assert.assertEquals("denied",  homepage.statusField.getAttribute("innerText"));
//	}
//
//	@When("^the manager clicks the sortCategory button the first time$")
//	public void the_manager_clicks_the_sortCategory_button_the_first_time() throws Throwable {
//	    homepage.category_btn.click();
//	}
//
//	@Then("^the reimbursements should sort alphabetically by category$")
//	public void the_reimbursements_should_sort_alphabetically_by_category() throws Throwable {
//		Assert.assertEquals("Event",  homepage.categoryField.getAttribute("innerText"));
//	}
//
//	@When("^the manager clicks the sortCategory button the second time$")
//	public void the_manager_clicks_the_sortCategory_button_the_second_time() throws Throwable {
//		homepage.category_btn.click();
//	}
//
//	@Then("^the reimbursements should sort unalphabetically  by category$")
//	public void the_reimbursements_should_sort_unalphabetically_by_category() throws Throwable {
//		Assert.assertEquals("Travel",  homepage.categoryField.getAttribute("innerText"));
//	}
//
//	@When("^the manager clicks the sortAmount button the first time$")
//	public void the_manager_clicks_the_sortAmount_button_the_first_time() throws Throwable {
//	    homepage.amount_btn.click();
//	}
//
//	@Then("^the reimbursements should sort in ascending order by amount$")
//	public void the_reimbursements_should_sort_in_ascending_order_by_amount() throws Throwable {
//		Assert.assertEquals(2000, Integer.parseInt(homepage.amountField.getAttribute("innerText")));
//	}
//
//	@When("^the manager clicks the sortAmount button the second time$")
//	public void the_manager_clicks_the_sortAmount_button_the_second_time() throws Throwable {
//		homepage.amount_btn.click();
//	}
//
//	@Then("^the reimbursements should sort in descending order by amount$")
//	public void the_reimbursements_should_sort_in_descending_order_by_amount() throws Throwable {
//		Assert.assertEquals(12,  Integer.parseInt(homepage.amountField.getAttribute("innerText")));
//	}
//
//	@When("^the manager clicks the sortEmployee button the first time$")
//	public void the_manager_clicks_the_sortEmployee_button_the_first_time() throws Throwable {
//	    homepage.employee_btn.click();
//	}
//
//	@Then("^the reimbursements should sort alphabetically by employee first name$")
//	public void the_reimbursements_should_sort_alphabetically_by_employee_first_name() throws Throwable {
//		Assert.assertEquals("April Ludgate",  homepage.employeeField.getAttribute("innerText"));
//	}
//
//	@When("^the manager clicks the sortEmployee button the second time$")
//	public void the_manager_clicks_the_sortEmployee_button_the_second_time() throws Throwable {
//		homepage.employee_btn.click();
//	}
//
//	@Then("^the reimbursements should sort unalphabetically by employee first name$")
//	public void the_reimbursements_should_sort_unalphabetically_by_employee_first_name() throws Throwable {
//		Assert.assertEquals("Tom Haverford",  homepage.employeeField.getAttribute("innerText"));
//	}
//
//	@When("^the manager clicks the sortNote button the first time$")
//	public void the_manager_clicks_the_sortNote_button_the_first_time() throws Throwable {
//	    homepage.note_btn.click();
//	}
//
//	@Then("^the reimbursements should sort alphabetically by note$")
//	public void the_reimbursements_should_sort_alphabetically_by_note() throws Throwable {
//		Assert.assertEquals("Food is good",  homepage.noteField.getAttribute("innerText"));
//	}
//
//	@When("^the manager clicks the sortNote button the second time$")
//	public void the_manager_clicks_the_sortNote_button_the_second_time() throws Throwable {
//		homepage.note_btn.click();
//	}
//
//	@Then("^the reimbursements should sort unalphabetically by note$")
//	public void the_reimbursements_should_sort_unalphabetically_by_note() throws Throwable {
//		Assert.assertEquals("We do not need paper clips",  homepage.noteField.getAttribute("innerText"));
//
//	}
//
//	@When("^the manager clicks the sortDate button the first time$")
//	public void the_manager_clicks_the_sortDate_button_the_first_time() throws Throwable {
//	    homepage.date_btn.click();
//	}
//
//	@Then("^the reimbursements should sort by earliest date$")
//	public void the_reimbursements_should_sort_by_earliest_date() throws Throwable {
//		Assert.assertEquals("Aug 6, 2020, 2:18:46 AM",  homepage.dateField.getAttribute("innerText"));
//
//	}
//
//	@When("^the manager clicks the sortDate button the second time$")
//	public void the_manager_clicks_the_sortDate_button_the_second_time() throws Throwable {
//		homepage.date_btn.click();
//	}
//
//	@Then("^the reimbursements should sort by latest date$")
//	public void the_reimbursements_should_sort_by_latest_date() throws Throwable {
//		Assert.assertEquals("Mar 13, 2020, 10:35:34 AM",  homepage.dateField.getAttribute("innerText"));
//	}
//

	
}
