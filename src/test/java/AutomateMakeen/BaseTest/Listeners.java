package AutomateMakeen.BaseTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listeners implements ITestListener {
    private WebDriver driver;
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        System.out.println("Test Passed : " + result.getTestName());
    }

    @Override
    public void onTestFailure(ITestResult result){
        try {
            String screenshotName = result.getName() ;
            // Create a timestamped screenshot name
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File("AutomateMakeen/BaseTest/screenshots/" + screenshotName + "_" + timestamp + ".png");
            FileHandler.copy(screenshot, destination);
            System.out.println("Screenshot saved at: " + destination.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }

    @Override
    public boolean isEnabled() {
        return ITestListener.super.isEnabled();
    }
}
