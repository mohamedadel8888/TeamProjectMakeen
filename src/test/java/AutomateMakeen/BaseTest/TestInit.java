package AutomateMakeen.BaseTest;

import AutomateMakeen.Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;

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
    public EditAccountPage editAccountPage;
    public WebDriver driver;
    public static LoginPage loginPage;
    public static HomePage homePage;

    public static UsersControl usersControl;
    public static DelegatePage delegatePage;
    public static AddDelegatePage addDelegatePage;
    public static PersonalAccountsPage personalAccountsPage;
    public static EditDelegatePage editDelegatePage;

    protected SoftAssert softAssert = new SoftAssert();

    public WebDriver initDriver(){
        WebDriver driver = new EdgeDriver();
        return driver;
    }


    public void lunchDriver(){
        driver = initDriver();
        loginPage = new LoginPage(driver);
        contentAside = new ContentAside(driver);
        editAccountPage = new EditAccountPage(driver);
    }

    public void quitDriver(){
        driver.quit();
    }

    /*
    @AfterClass
    public void afterClass(){
        quitDriver();
    }

     */
}
