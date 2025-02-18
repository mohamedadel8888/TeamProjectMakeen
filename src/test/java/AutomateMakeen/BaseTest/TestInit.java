package AutomateMakeen.BaseTest;

import AutomateMakeen.Base.ContentAside;
import AutomateMakeen.Pages.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Duration;
import java.time.LocalTime;
import java.time.chrono.HijrahDate;
import java.time.temporal.ChronoUnit;

public class TestInit  {
    protected String userID = "2562562";
    protected String userPasswd = "24602460";
    protected String userName ;//= "مروان خليل موظف اول";
    protected String userDept ;//= "ادارة عامة آيه";
    protected String userTreatJob;

    protected ContentAside contentAside;
    public WebDriver driver;
    public static LoginPage loginPage;
    public static qCMS_HomePage qCMSHomePage;

    protected SoftAssert softAssert = new SoftAssert();

    private String Test_Data_Path = "src/test/resources/TestData/";

    public WebDriver initDriver(){
        EdgeOptions options = new EdgeOptions();
//        options.addArguments("--headless");  // Run Edge in headless mode
//        options.addArguments("--disable-gpu"); // Disable GPU acceleration (for Windows)
        options.addArguments("--window-size=1920,1160"); // Set viewport size
        options.addArguments("--no-sandbox"); // Required for CI/CD environments
//        options.addArguments("--disable-dev-shm-usage"); // Avoid memory issues

        WebDriver driver = new EdgeDriver(options);
//        WebDriver driver = new EdgeDriver();
//        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return driver;
    }


    public void lunchDriver(){
        driver = initDriver();
        loginPage = new LoginPage(driver);
        contentAside = new ContentAside(driver);
    }

    @AfterClass
    public void quitDriver(){
        driver.quit();
    }

}
