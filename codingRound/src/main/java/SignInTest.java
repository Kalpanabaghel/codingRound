import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends BaseClass{

	WebDriver driver;
	String Url = "https://www.cleartrip.com/";

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		try{
			setDriverPath();
			driver = new ChromeDriver();
			driver.get(Url);
			waitFor(2000);

			getWebElement(driver,"LINKTEXT","Your trips").click();
			getWebElement(driver,"ID","SignIn").click();

			WebDriverWait wait = new WebDriverWait(driver, 10);

			//Explicit wait condition for frame to be available
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getWebElement(driver,"ID","modal_window")));
			waitFor(2000);

			getWebElement(driver,"ID","signInButton").click();

			String errors1 = getWebElement(driver,"ID","errors1").getText();
			Assert.assertTrue(errors1.contains("There were errors in your submission"), "No error message displayed");
		}

		finally{	
			driver.quit(); 
		}
	}

}
