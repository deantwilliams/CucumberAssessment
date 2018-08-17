package MavenTest.Cucumber;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TeaSteps {

	private WebDriver driver = null;
	private PassionTeaHome home = null;
	private PassionTeaMenu menu = null;
	private ExtentReports report = null;
	private ExtentTest test = null;
	private String currentURL = "";
	private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
	private Date date = null;
	
	@Before
	public void setup(Scenario scenario)
	{
		System.setProperty("webdriver.chrome.driver", CONST.CHROME_DRIVER);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		report = new ExtentReports(CONST.REPORT_PATH,false);
		home = PageFactory.initElements(driver, PassionTeaHome.class);
		menu = PageFactory.initElements(driver, PassionTeaMenu.class);
		date = new Date();
		test = report.startTest(scenario.getName() + " \n(" + dateFormat.format(date)+")");
	}
	
	@After
	public void tearDown()
	{
		driver.close();
		driver.quit();
		report.endTest(test);
		report.flush();
	}
	
	@Given("^the correct web address$")
	public void the_correct_web_address() {
		driver.get(CONST.URL_HOME);
		test.log(LogStatus.INFO,"Navigating to URL - " + CONST.URL_HOME);
	}

	@When("^I navigate to the 'Menu' page$")
	public void i_navigate_to_the_Menu_page() {
	    home.clickMenuLink();
	    test.log(LogStatus.INFO, "Clicking Menu link");
	}

	@Then("^I can browse a list of the available products\\.$")
	public void i_can_browse_a_list_of_the_available_products() {
	    currentURL = driver.getCurrentUrl();
	    try
	    {
	    	assertEquals(currentURL,CONST.URL_MENU);
	    	test.log(LogStatus.PASS, "Menu page has been correctly loaded");
	    }catch(AssertionError e)
	    {
	    	test.log(LogStatus.FAIL,"Error loading menu page");
	    }
	}

	@When("^I click the checkout button$")
	public void i_click_the_checkout_button() {
		home.clickMenuLink();
	    test.log(LogStatus.INFO, "Clicking Menu link");
	    menu.clickGreenTeaCheckout();
	    test.log(LogStatus.INFO, "Clicking Green Tea checkout button");
	}

	@Then("^I am taken to the checkout page$")
	public void i_am_taken_to_the_checkout_page() {
	    currentURL = driver.getCurrentUrl();
	    try
	    {
	    	assertEquals(currentURL,CONST.URL_CHECKOUT);
	    	test.log(LogStatus.PASS, "Checkout page has been correctly loaded");
	    }catch(AssertionError e)
	    {
	    	test.log(LogStatus.FAIL,"Error loading checkout page");
	    }
	}
}
