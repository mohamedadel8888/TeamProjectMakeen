package AutomateMakeen.BaseTest;

import AutomateMakeen.Pages.ContentAside;
import AutomateMakeen.Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import java.time.Duration;

public class TestInit {
    protected String userID = "0342169";
    protected String userPasswd = "24602460";
    protected String itemName = "ادارة المواصلاات";
    protected String itemEnglishName = "management Of traffic";
    protected String type = "إدارة";
    protected String higherItem = "وكالة الوكالة";
    protected String day = "20";
    protected String month = "ديسمبر";
    protected String year = "2023";
    protected ContentAside contentAside;
    public WebDriver driver;
    public static LoginPage loginPage;


    public WebDriver initDriver(){
        driver =new ChromeDriver();
        return driver;
    }


    public void lunchDriver(){
        driver = initDriver();
        loginPage = new LoginPage(driver);
        contentAside = new ContentAside(driver);
    }


    public void quitDriver(){
        //driver.quit();
    }

    @AfterClass
    public void afterClass(){
        quitDriver();
    }
}
