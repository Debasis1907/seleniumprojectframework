package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import resources.Base;

import java.io.IOException;

public class TC002_ValidationTest extends Base {

    public WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {
        driver = initializeDriver();
        driver.get(properties.getProperty("appURL"));
    }

    @Test
    public void verifyTitle(){
        HomePage homePage = new HomePage(driver);
        String expectedTitle = homePage.getTitle();
        Assert.assertEquals("Your Store",expectedTitle);
    }

    @Test
    public void verifyURL(){
        String expectedURL = driver.getCurrentUrl();
        Assert.assertEquals(properties.getProperty("appURL"),expectedURL);
    }

    @AfterMethod
    public void cleanUp(){
        driver.quit();
    }
}
