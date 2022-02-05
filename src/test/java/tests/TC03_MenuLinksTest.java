package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.Base;

import java.io.IOException;
import java.util.List;

public class TC03_MenuLinksTest extends Base {

    public WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {
        driver = initializeDriver();
        driver.get(properties.getProperty("appURL"));
    }

    @Test
    public void getTotalMenuLinks(){
        List<WebElement> menuLinks = driver.findElements(By.xpath("//nav[@id='menu']/div/ul/li/a"));
        System.out.println("Total menu links are : " + menuLinks.size());
        menuLinks.forEach(ele -> System.out.println(ele.getText()));
    }

    @AfterMethod
    public void cleanUp(){
        driver.quit();
    }
}
