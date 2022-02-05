package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import resources.Base;

import java.io.IOException;

public class Login extends Base {

    public WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;

    @Given("Open any Browser")
    public void open_any_browser() throws IOException {

        driver = initializeDriver();
        driver.get(properties.getProperty("appURL"));
    }
    @Given("Navigate to Login page")
    public void navigate_to_login_page() {
        homePage = new HomePage(driver);
        homePage.clickOnMyAccount().clickLogin();
    }
    @When("User performs login with username as {string} and password as {string} into the fields")
    public void user_enters_username_as_and_password_as_into_the_fields(String username, String password) {
        loginPage = new LoginPage(driver);
        loginPage.performLogin(username,password);
    }
    @Then("Verify user is able to successfully login")
    public void verify_user_is_able_to_successfully_login() {
        accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.verifyAccountInformation());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
