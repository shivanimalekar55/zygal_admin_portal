package Test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Config.ConfigReader;
import POM.loginPage;
import Utility.parameterization;

public class loginPageTest extends baseTest {

    @BeforeMethod
    public void browserLaunch() {
        driver = Utility.browserLaunch.openBrowser();
    }

    @Test(description = "User Login with Valid Credentials", priority = 1)    //Test Case-1
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
    }

    @Test(description = "User Login with Invalid Credentials", priority = 2)    //Test Case-2
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
    
    
    @Test(description = "User Login with Empty Credentials", priority = 3)   //Test Case-3
    public void loginWithEmptyCredentialsTest() throws EncryptedDocumentException, IOException, InterruptedException {
        // Fetch Captcha bypass key from config file
        String captchaBypassToken = ConfigReader.getProperty("captcha_token");

        loginPage zygalLoginPage = new loginPage(driver);
        Thread.sleep(1000);

        // Get the Blank user email and password from Excel sheet
        String blankUserName = parameterization.getData("loginData", 3, 0);
        String blankPassword = parameterization.getData("loginData", 3, 1);

        // Split email to insert bypass key in the email
        String[] userEmailSplit = blankUserName.split("@");
        String userEmail = userEmailSplit[0] + captchaBypassToken + "@" + userEmailSplit[1];

        // Sending empty email and password
        zygalLoginPage.enteruserId("");
        zygalLoginPage.enterpassword("");
        zygalLoginPage.enterCaptcha();
        zygalLoginPage.ClickOnSubmit();
    } 
    
     
    @Test(description = "User Login with Valid User Email ID & Invalid Password", priority = 4)    //Test Case-4
    public void loginWithValidUserEmailAndInvalidPasswordTest() throws EncryptedDocumentException, IOException, InterruptedException {
        // Fetch Captcha bypass key from config file
        String captchaBypassToken = ConfigReader.getProperty("captcha_token");

        loginPage zygalLoginPage = new loginPage(driver);
        Thread.sleep(1000);

        // Get the user email and Invalid password from Excel sheet
        String ValidUserName = parameterization.getData("loginData", 4, 0);
        String InvalidPassword = parameterization.getData("loginData", 4, 1);

        // Split email to insert bypass key in the email
        String[] userEmailSplit = ValidUserName.split("@");
        String userEmail = userEmailSplit[0] + captchaBypassToken + "@" + userEmailSplit[1];

        // Sending valid email and Invalid password
        zygalLoginPage.enteruserId("userEmail");
        zygalLoginPage.enterpassword("Invalidpassword");
        zygalLoginPage.enterCaptcha();
        zygalLoginPage.ClickOnSubmit();
    } 
    
    
    @Test(description = "User Login with InValid User Email ID & valid Password", priority = 4)    //Test Case-5
    public void loginWithInvalidUserEmailAndvalidPasswordTest() throws EncryptedDocumentException, IOException, InterruptedException {
        // Fetch Captcha bypass key from config file
        String captchaBypassToken = ConfigReader.getProperty("captcha_token");

        loginPage zygalLoginPage = new loginPage(driver);
        Thread.sleep(1000);

        // Get the user email and Invalid password from Excel sheet
        String InValidUserName = parameterization.getData("loginData", 5, 0);
        String validPassword = parameterization.getData("loginData", 5, 1);

        // Split email to insert bypass key in the email
        String[] userEmailSplit = InValidUserName.split("@");
        String userEmail = userEmailSplit[0] + captchaBypassToken + "@" + userEmailSplit[1];

        // Sending Invalid user email and valid password
        zygalLoginPage.enteruserId("InvaliduserEmail");
        zygalLoginPage.enterpassword("validpassword");
        zygalLoginPage.enterCaptcha();
        zygalLoginPage.ClickOnSubmit();
    } 
    
/*  @Test
    public void testInvalidLogin() {
        loginPage login = null;
		login.enteruserId("invalidUser");
        login.enterpassword("invalidPass");
        login.enterCaptcha();
        login.ClickOnSubmit();   */

        String errorMsg = login.getErrorText();
        Assert.assertTrue(errorMsg.contains("Invalid credentials"), "Expected error message not found!");
    }
    /* @Test(description = "User Login with Empty Credentials")
    public void loginWithEmptyCredentialsTest() throws InterruptedException {
        loginPage zygalLoginPage = new loginPage(driver);
        Thread.sleep(1000);

       

        // Verify if login fails due to empty fields
        String expectedErrorMessage = "Username and Password cannot be empty"; // Adjust as per actual error message
        String actualErrorMessage = zygalLoginPage.getErrorMessage();
        
        assert actualErrorMessage.contains(expectedErrorMessage) : "Error message mismatch: " + actualErrorMessage;
    }
}*/
}
    
    
    /* @Test(description = "User Login with Empty Credentials")
    public void loginWithEmptyCredentialsTest() throws InterruptedException {
        loginPage zygalLoginPage = new loginPage(driver);
        Thread.sleep(1000);

       

        // Verify if login fails due to empty fields
        String expectedErrorMessage = "Username and Password cannot be empty"; // Adjust as per actual error message
        String actualErrorMessage = zygalLoginPage.getErrorMessage();
        
        assert actualErrorMessage.contains(expectedErrorMessage) : "Error message mismatch: " + actualErrorMessage;
    }
}*/
