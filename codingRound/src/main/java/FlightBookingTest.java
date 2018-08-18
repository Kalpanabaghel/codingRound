import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest extends BaseClass { 

	WebDriver driver;
	String Url = "https://www.cleartrip.com/";

	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		setDriverPath();
		driver = new ChromeDriver();
		driver.get(Url);
		waitFor(2000);
		
		getWebElement(driver,"ID","OneWay").click();
		getWebElement(driver,"ID","FromTag").clear();
		getWebElement(driver,"ID","FromTag").sendKeys("Bangalore");
		
		//wait for the auto complete options to appear for the origin
		waitFor(2000);
		List<WebElement> originOptions = getWebElements(getWebElement(driver,"ID","ui-id-1"),"TAG","li");
		originOptions.get(0).click();

		driver.findElement(By.id("ToTag")).clear();
		driver.findElement(By.id("ToTag")).sendKeys("Delhi");

		//wait for the auto complete options to appear for the destination

		waitFor(2000);
		//select the first item from the destination auto complete list
		List<WebElement> destinationOptions = getWebElements(getWebElement(driver,"ID","ui-id-2"),"TAG","li");
		destinationOptions.get(0).click();
		
		getWebElement(driver,"XPATH","//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a").click();

		//all fields filled in. Now click on search
		getWebElement(driver,"ID","SearchBtn").click();
		
		waitFor(5000);
		//verify that result appears for the provided journey search
		Assert.assertTrue(isElementPresent(driver,"CLASS","searchSummary"));

		//close the browser
		driver.quit();

	}

}
