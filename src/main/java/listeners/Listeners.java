package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.Base;
import utilities.ExtentReporter;

import java.io.IOException;

public class Listeners extends Base implements ITestListener {

    WebDriver driver;
    ExtentReports extentReport = ExtentReporter.getExtentReport();
    ExtentTest extentTest;
    ThreadLocal<ExtentTest>extentTestThread = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReport.createTest(result.getName());
        extentTestThread.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //extentTest.log(Status.PASS, result.getName()+ " Test Passed");
        extentTestThread.get().log(Status.PASS, result.getName()+ " Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        //extentTest.fail(result.getThrowable());
        extentTestThread.get().fail(result.getThrowable());

        try {
            driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            String screenshotFilePath = takeScreenshot(result.getName(),driver);
            extentTestThread.get().addScreenCaptureFromPath(screenshotFilePath, result.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
    }
}
