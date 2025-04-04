package POM;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddBusinessUnitPage {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/div[2]/section/main/section/div/aside/div/div/ul/li[3]/div")  // 
    private WebElement BusinessUnit;
    
    @FindBy(xpath = "//input[@placeholder='Business Unit']")  // Replace with correct locator if needed
    private WebElement businessUnitName;

    @FindBy(xpath = "//select[@placeholder='Select Business Type']") // Dropdown for Business Type
    private WebElement businessTypeDropdown;

    @FindBy(xpath = "//input[@type='file']") // File input for uploading image
    private WebElement uploadImage;

    @FindBy(xpath = "//button[contains(text(),'Upload')]") // Upload button
    private WebElement uploadButton;

    @FindBy(xpath = "//button[contains(text(),'Save')]") // Save button
    private WebElement saveButton;

    public AddBusinessUnitPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Enter Business Unit Name
    public void enterBusinessUnitName(String name) {
        businessUnitName.sendKeys(name);
    }

    // Select Business Type from dropdown
    public void selectBusinessType(String businessType) {
        Select dropdown = new Select(businessTypeDropdown);
        dropdown.selectByVisibleText(businessType);
    }

    // Upload Image
    public void uploadImage(String imagePath) {
        uploadImage.sendKeys(imagePath);
    }

    // Click on Upload button
    public void clickUploadButton() {
        uploadButton.click();
    }

    // Click on Save button
    public void clickSaveButton() {
        saveButton.click();
    }

    // Wait until Save button is enabled
    public boolean isSaveButtonEnabled() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.elementToBeClickable(saveButton)).isEnabled();
    }
}