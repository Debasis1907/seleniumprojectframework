package resources;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.BrowserType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {

    public WebDriver Driver;
    public Properties properties;

    public WebDriver initializeDriver() throws IOException {

        properties = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resources/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        properties.load(fis);

        if(properties.getProperty("browser").equalsIgnoreCase(String.valueOf(BrowserType.Chrome))){
            WebDriverManager.chromedriver().setup();
            Driver = new ChromeDriver();
        }
        else if(properties.getProperty("browser").equalsIgnoreCase("Firefox")){
            WebDriverManager.firefoxdriver().setup();
            Driver = new FirefoxDriver();
        }
        Driver.manage().window().maximize();
        Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return Driver;
    }

    public String takeScreenshot(String testName,WebDriver Driver) throws IOException {

        File srcFile = ((TakesScreenshot)Driver).getScreenshotAs(OutputType.FILE);
        String destFilePath = System.getProperty("user.dir")+ "/screenshots/"+testName+".png";
        FileUtils.copyFile(srcFile,new File(destFilePath));

        return destFilePath;
    }
}
