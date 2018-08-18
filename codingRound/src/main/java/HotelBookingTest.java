import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest extends BaseClass{

	@FindBy(xpath = "(.//a[@href='/hotels'])[2]")
	private WebElement hotelLink;

	@FindBy(how = How.ID, using = "Tags")
	private WebElement localityTextBox;

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	private WebElement travellerSelection;

	@FindBy(xpath = ".//*[@id='ui-id-1']/li/a")
	private List<WebElement> listElem;

	@FindBy(xpath = ".//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[6]/a")
	private WebElement checkIn;

	@FindBy(xpath = ".//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")
	private WebElement checkOut;
	
	WebDriver driver;
	String Url = "https://www.cleartrip.com/";

	@Test
	public void shouldBeAbleToSearchForHotels() {

		try{

			setDriverPath();

			driver = new ChromeDriver();

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get(Url);

			HotelBookingTest hotelBooking = PageFactory.initElements(driver, HotelBookingTest.class);

			hotelBooking.hotelLink.click();

			hotelBooking.localityTextBox.sendKeys("Indiranagar, Bangalore");

			hotelBooking.listElem.get(0).click(); hotelBooking.checkIn.click(); 

			waitFor(2000);
			hotelBooking.checkOut.click();

			new Select(hotelBooking.travellerSelection).selectByVisibleText("1 room, 2 adults");
			hotelBooking.searchButton.click();
		}
		
		finally{
			//close browser
			driver.quit();
		}
	}

}
