package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage{

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//a[@title='My Account']")
    private WebElement myAccountDropdown;

    @FindBy(xpath = "//a[text()='Login']")
    private WebElement loginOption;

    public HomePage clickOnMyAccount(){
        myAccountDropdown.click();
        return this;
    }

    public HomePage clickLogin(){
        loginOption.click();
        return this;
    }

    public String getTitle(){
        return driver.getTitle();
    }
}
