package AutomateMakeen.BaseTest;

import AutomateMakeen.Pages.*;
import AutomateMakeen.Pages.Elite.CreateInternalMailPage;
import AutomateMakeen.Pages.Elite.EliteHomePage;
import AutomateMakeen.Pages.Elite.InboxPage;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.chrono.HijrahDate;
import java.time.temporal.ChronoUnit;

public class TestInit  {
    protected String userID = "0342169";
    protected String userPasswd = "24602460";
    protected ContentAside contentAside;
    public EditAccountPage editAccountPage;
    public String archiveNum;
    public WebDriver driver ;
    public static LoginPage loginPage;
    public static HomePage homePage;

    public static UsersControl usersControl;
    public static DelegatePage delegatePage;
    public static AddDelegatePage addDelegatePage;
    public static PersonalAccountsPage personalAccountsPage;
    public static EditDelegatePage editDelegatePage;
    public static EliteHomePage eliteHomePage;
    public static CreateInternalMailPage createInternalMailPage;
    public static InboxPage inboxPage;

    //HijriDates
    protected HijrahDate dateHijriMinus = HijrahDate.now().minus(1, ChronoUnit.DAYS);
    protected HijrahDate dateHijri = HijrahDate.now();
    protected HijrahDate dateHijriPlus10Days = dateHijri.plus(10, ChronoUnit.DAYS);

    //Time
    protected LocalTime currentTime = LocalTime.now();
    protected LocalTime updatedTime = currentTime.plusMinutes(2);


    protected SoftAssert softAssert = new SoftAssert();

    private String Test_Data_Path = "src/test/resources/TestData/";


    public WebDriver initDriver(){
        driver = new EdgeDriver();
        return driver;
    }


    public void lunchDriver() {
        driver = initDriver();
        loginPage = new LoginPage(driver);
        contentAside = new ContentAside(driver);
        editAccountPage = new EditAccountPage(driver);
        eliteHomePage = new EliteHomePage(driver);
        createInternalMailPage = new CreateInternalMailPage(driver);
        inboxPage = new InboxPage(driver);
//        Listeners listeners = new Listeners(driver);
    }

    public String getJsonData(String fileName,String field) throws FileNotFoundException {
        FileReader reader = new FileReader(Test_Data_Path+fileName+".json");
        JsonElement jsonElement = JsonParser.parseReader(reader);
        return jsonElement.getAsJsonObject().get(field).getAsString();
    }

    public Object[] getJsonArrayAsObjectArray(String fileName, String field) throws FileNotFoundException {
        FileReader reader = new FileReader(Test_Data_Path + fileName + ".json");
        JsonElement jsonElement = JsonParser.parseReader(reader);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray(field);
        Object[] dataArray = new Object[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            dataArray[i] = jsonArray.get(i).getAsString();
        }
        return dataArray;
    }

    @AfterMethod

    public void ScreenshotOnFail(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {

            System.out.println("Failed");
            System.out.println("Taking Schreenshot....");
            Helper.captureScreenshot(driver, result.getName());


        }
    }


    public void quitDriver(){
        driver.quit();
    }


//    @AfterClass
//    public void afterClass(){
//        quitDriver();
//    }


}
