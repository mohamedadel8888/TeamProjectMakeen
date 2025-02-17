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
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized"); // Ensure full screen
        WebDriver driver = new EdgeDriver(options);
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
