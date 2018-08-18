import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sun.javafx.PlatformUtil;

@SuppressWarnings("restriction")
public class BaseClass {
	
	public WebElement getWebElement(WebDriver driver, String locatorType, String value){

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
		case "TAG" :
			element = driver.findElement(By.tagName(value));
			break;
		}
		return element;
	}
	
	public List<WebElement> getWebElements(WebDriver driver, String locatorType, String value){

		List<WebElement> elementList = null;

		switch(locatorType) {

		case "ID" : 
			elementList = driver.findElements(By.id(value));
			break;
		case "XPATH" : 	
			elementList = driver.findElements(By.xpath(value));
			break;
		case "LINKTEXT" :
			elementList = driver.findElements(By.linkText(value));
			break;
		case "PLINKTEXT" :
			elementList = driver.findElements(By.partialLinkText(value));
			break;
		case "CSS" :
			elementList = driver.findElements(By.cssSelector(value));
			break;
		case "CLASS" :
			elementList = driver.findElements(By.className(value));
			break;
		case "TAG" :
			elementList = driver.findElements(By.tagName(value));
			break;
		}

		return elementList;
	}
	
	public List<WebElement> getWebElements(WebElement element, String locatorType, String value){

		List<WebElement> elementList = null;

		switch(locatorType) {

		case "ID" : 
			elementList = element.findElements(By.id(value));
			break;
		case "XPATH" : 	
			elementList = element.findElements(By.xpath(value));
			break;
		case "LINKTEXT" :
			elementList = element.findElements(By.linkText(value));
			break;
		case "PLINKTEXT" :
			elementList = element.findElements(By.partialLinkText(value));
			break;
		case "CSS" :
			elementList = element.findElements(By.cssSelector(value));
			break;
		case "CLASS" :
			elementList = element.findElements(By.className(value));
			break;
		case "TAG" :
			elementList = element.findElements(By.tagName(value));
			break;
		}

		return elementList;
	}
	
	public boolean isElementPresent(WebDriver driver,String locatorType,String value) {
		try {
			getWebElement(driver,locatorType,value);

			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void setDriverPath() {
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
	

	public void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();  
		}
	}

}
