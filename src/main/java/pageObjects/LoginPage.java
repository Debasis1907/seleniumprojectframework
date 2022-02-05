package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    //testautomation999@gmail.com
    //test@1234
    @FindBy(id = "input-email")
    private WebElement txtEmail;

    @FindBy(id="input-password")
    private WebElement txtPassword;

    @FindBy(css = "input[value='Login']")
    private WebElement btnLogin;

    public LoginPage performLogin(String userName,String password){
        txtEmail.sendKeys(userName);
        txtPassword.sendKeys(password);
        btnLogin.click();
        return this;
    }
}
