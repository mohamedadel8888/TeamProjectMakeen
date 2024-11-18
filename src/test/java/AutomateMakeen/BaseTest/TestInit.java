package AutomateMakeen.BaseTest;

import AutomateMakeen.Pages.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalTime;
import java.time.chrono.HijrahDate;
import java.time.temporal.ChronoUnit;

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
        WebDriver driver = new EdgeDriver();
        return driver;
    }


    public void lunchDriver(){
        driver = initDriver();
        loginPage = new LoginPage(driver);
        contentAside = new ContentAside(driver);
        editAccountPage = new EditAccountPage(driver);
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

    public void quitDriver(){
        driver.quit();
    }


    @AfterClass
    public void afterClass(){
        quitDriver();
    }


}
