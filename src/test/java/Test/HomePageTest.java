package Test;

import java.io.IOException;
import java.time.Duration;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Config.ConfigReader;
import POM.loginPage;
import Utility.parameterization;

public class HomePageTest extends baseTest {

    @BeforeMethod
    public void browserLaunch() {
        driver = Utility.browserLaunch.openBrowser();
    }

    @Test(description = "User Login with Valid Credentials", priority = 1)
    public void loginWithValidCredentialsTest() throws EncryptedDocumentException, IOException, InterruptedException {
        String captchaBypassToken = ConfigReader.getProperty("captcha_token");

        loginPage zygalLoginPage = new loginPage(driver);
        Thread.sleep(1000);

        String userName = parameterization.getData("loginData", 1, 0);
        String password = parameterization.getData("loginData", 1, 1);

        String[] userEmailSplit = userName.split("@");
        String userEmail = userEmailSplit[0] + captchaBypassToken + "@" + userEmailSplit[1];

        zygalLoginPage.enteruserId(userEmail);
        zygalLoginPage.enterpassword(password);
        zygalLoginPage.enterCaptcha();
        zygalLoginPage.ClickOnSubmit();

        // Wait for Zygal logo to be visible with Selenium 4 Duration
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement zygalLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Zygal Logo']"))); // Adjust XPath if needed

        // Assert that the Zygal logo is displayed
        Assert.assertTrue(zygalLogo.isDisplayed(), "Zygal logo is not visible after login");
    }
}



      /*  // Wait for Footer to be visible
        WebElement footer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//footer"))); //

        // Assert Footer visibility
        Assert.assertTrue(footer.isDisplayed(), "Footer is not visible on the home page");  */
   
