package Test;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Config.ConfigReader;
import POM.loginPage;
import POM.AddBusinessUnitPage;
import Utility.parameterization;

public class AddBusinessUnitTest extends baseTest {

    AddBusinessUnitPage addBU;

    @BeforeMethod
    public void browserLaunch() {
        driver = Utility.browserLaunch.openBrowser();
    }

    @Test(description = "User Login with Valid Credentials", priority = 1)
    public void loginWithValidCredentialsTest() throws EncryptedDocumentException, IOException, InterruptedException {
        // Fetch Captcha bypass key from config file
        String captchaBypassToken = ConfigReader.getProperty("captcha_token");

        loginPage zygalLoginPage = new loginPage(driver);
        Thread.sleep(1000);

        // Get the user email and password from Excel sheet
        String userName = parameterization.getData("loginData", 1, 0);
        String password = parameterization.getData("loginData", 1, 1);

        // Split email to insert bypass key in the email
        String[] userEmailSplit = userName.split("@");
        String userEmail = userEmailSplit[0] + captchaBypassToken + "@" + userEmailSplit[1];

        // Send email and password to browser
        zygalLoginPage.enteruserId(userEmail);
        zygalLoginPage.enterpassword(password);
        zygalLoginPage.enterCaptcha();
        zygalLoginPage.ClickOnSubmit();

        // Initialize AddBusinessUnitPage
        addBU = new AddBusinessUnitPage(driver);

        // Wait after login if needed
        Thread.sleep(2000);

        // Call the method to add business unit
        addBusinessUnit();
    }

    @Test(description = "User Login with Invalid Credentials", priority = 2)
    public void loginWithInvalidCredentialsTest() throws EncryptedDocumentException, IOException, InterruptedException {
        // Fetch Captcha bypass key from config file
        String captchaBypassToken = ConfigReader.getProperty("captcha_token");

        loginPage zygalLoginPage = new loginPage(driver);
        Thread.sleep(1000);

        // Get the invalid user email and password from Excel sheet
        String invalidUserName = parameterization.getData("loginData", 2, 0);
        String invalidPassword = parameterization.getData("loginData", 2, 1);

        // Split email to insert bypass key in the email
        String[] userEmailSplit = invalidUserName.split("@");
        String userEmail = userEmailSplit[0] + captchaBypassToken + "@" + userEmailSplit[1];

        // Send invalid email and password to browser
        zygalLoginPage.enteruserId(userEmail);
        zygalLoginPage.enterpassword(invalidPassword);
        zygalLoginPage.enterCaptcha();
        zygalLoginPage.ClickOnSubmit();
    }

    // Method to add business unit
    public void addBusinessUnit() throws InterruptedException {
        System.out.println("Adding Business Unit...");

        // Enter Business Unit Details
        addBU.enterBusinessUnitName("Automation BU");
        Thread.sleep(1000);

        // Select Business Type (e.g., Hotel)
        addBU.selectBusinessType("Hotel");
        Thread.sleep(1000);

        // Upload Image (Replace with your image path)
        String imagePath = "C:\\Users\\Admin\\Pictures\\Saved Pictures"; 
        addBU.uploadImage(imagePath);
        Thread.sleep(1000);

        // Click Upload Button
        addBU.clickUploadButton();
        Thread.sleep(1000);

        // Click Save Button
        addBU.clickSaveButton();

        System.out.println("Business Unit added successfully!");
    }
}