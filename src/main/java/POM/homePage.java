package POM;

/*import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePage {
	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"nav-list-holder\"]/ul/li[1]/div")
	private WebElement Overview;
	@FindBy(xpath = "//*[@id=\"zl-nabvar-drawer\"]/div[2]/ul/li[1]")
	private WebElement Home;
	
	
	public homePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	public void ClickOnOverview() {
		Overview.click();
	}
	public void ClickOnHome() {
		Home.click();
	}
	public static Object isLogoDisplayed() {
		// TODO Auto-generated method stub
		return null;
	}
	
}  */
	
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePage {
    WebDriver driver;

    // Locate the logo element (Update with the actual locator)
    @FindBy(xpath = "//img[contains(@alt,'ZYGAL')]") 
    WebElement zygalLogo;

    // Constructor
    public homePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to check if the logo is displayed
    public boolean isLogoVisible() {
        return zygalLogo.isDisplayed();
    }

	public boolean isWelcomeTextDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEditButtonDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isDeviceManagementLinkDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}