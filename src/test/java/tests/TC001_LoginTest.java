package tests;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import resources.Base;

import java.io.IOException;


public class TC001_LoginTest extends Base {
    public WebDriver driver;
    Logger log;

    @BeforeMethod
    public void setup() throws IOException {
        log = LogManager.getLogger(TC001_LoginTest.class.getName());
        driver = initializeDriver();
        log.debug("Browser launched");
        driver.get(properties.getProperty("appURL"));
        log.debug("Navigated to Application URL");
    }

    @Test(dataProvider = "getLoginData")
    public void Login(String username,String password,String expectedStatus){

        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccount().clickLogin();
        //Assert.assertTrue(false);
        log.debug("Clicked on my account dropdown and login option");
        LoginPage loginPage = new LoginPage(driver);
        //loginPage.performLogin(properties.getProperty("username"), properties.getProperty("password"));
        loginPage.performLogin(username,password);
        log.debug("Login successful");
        AccountPage accountPage = new AccountPage(driver);

        String actualStatus = null;

        try {
            if (accountPage.verifyAccountInformation()) {
                actualStatus = "Success";
            }
        }catch (Exception e){
            actualStatus = "failure";
            e.getMessage();
        }

        Assert.assertEquals(actualStatus,expectedStatus);
        log.debug("Assertion passed");
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp(){
        driver.quit();
        log.debug("Browser closed");
    }

    @DataProvider
    public Object[][] getLoginData(){
        Object[][] data = {{"testautomation999@gmail.com","test@1234","Success"}};
        return data ;

    }
}
