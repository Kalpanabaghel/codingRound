import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

@SuppressWarnings("restriction")
public class SignInTest {

	WebDriver driver;
	String Url = "https://www.cleartrip.com/";

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		try{
			setDriverPath();
			driver = new ChromeDriver();
			driver.get(Url);
			waitFor(2000);

			getWebElement("LINKTEXT","Your trips").click();
			getWebElement("ID","SignIn").click();

			WebDriverWait wait = new WebDriverWait(driver, 10);

			//Explicit wait condition for frame to be available
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getWebElement("ID","modal_window")));
			waitFor(2000);

			getWebElement("ID","signInButton").click();
		
			String errors1 = getWebElement("ID","errors1").getText();
			Assert.assertTrue(errors1.contains("There were errors in your submission"), "No error message displayed");
		}

		finally{	
			driver.quit(); 
		}
	}

	public WebElement getWebElement(String locatorType, String value){

		WebElement element = null;

		switch(locatorType) {

		case "ID" : 
			element = driver.findElement(By.id(value));
			break;
		case "XPATH" : 	
			element = driver.findElement(By.xpath(value));
			break;
		case "LINKTEXT" :
			element = driver.findElement(By.linkText(value));
			break;
		case "PLINKTEXT" :
			element = driver.findElement(By.partialLinkText(value));
			break;
		case "CSS" :
			element = driver.findElement(By.cssSelector(value));
			break;
		case "CLASS" :
			element = driver.findElement(By.className(value));
			break;
		}

		return element;
	}

	private void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();  
		}
	}

	private void setDriverPath() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}

		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}
	}


}
