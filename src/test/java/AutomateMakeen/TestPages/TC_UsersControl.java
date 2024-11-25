package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.HomePage;
import AutomateMakeen.Pages.UsersControl;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_UsersControl extends TestInit {
    UsersControl usersControl;
    WebDriverWait ex = new WebDriverWait(driver,Duration.ofSeconds(5));

    @BeforeClass(description = "Preconditions for each test in the class :" +
            "1- Login with authorized User." +
            "2- Navigate to User Control Page By Press 'لوحه التحكم ' in the content Aside" +
            "then press 'ادارة المستخدمين'")
    public void setupClass(){
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID, userPasswd);
        usersControl = contentAside.goToUsersControl();
    }

    @Test (priority = 1)
    public void tc_searchByFnameNotExits () throws Exception{
        UsersControl.setFirstName(getJsonData("UserControl","NotExitsName"));
        usersControl.singleSearch();
        Assert.assertTrue(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    @Test (priority = 2)
    public void tc_searchBySnameNotExits ()throws Exception{
        usersControl.setSecondName(getJsonData("UserControl","NotExitsName"));
        usersControl.singleSearch();
        Assert.assertTrue(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();

    }
    @Test (priority = 3)
    public void tc_searchByThirdNameNotExits ()throws Exception{
        usersControl.setThirdName(getJsonData("UserControl","NotExitsName"));
        usersControl.singleSearch();
        Assert.assertTrue(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    @Test   (priority = 4)
    public void tc_searchByLastNameNotExits()throws Exception{
        usersControl.setLastName(getJsonData("UserControl","NotExitsName"));
        usersControl.singleSearch();
        Assert.assertTrue(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    @Test   (priority = 5)
    public void tc_searchByUserIDNotExits ()throws Exception{
        usersControl.setUserID(getJsonData("UserControl","NotExitsName"));
        usersControl.singleSearch();
        Assert.assertTrue(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    @Test  (priority = 6)
    public void tc_searchByDeptNotExits ()throws Exception{
        usersControl.setChooseDept(getJsonData("UserControl","DeptHasNoUsers"));
        usersControl.singleSearch();
        Assert.assertTrue(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    /********************************/
    @Test   (priority = 7)
    public void tc_searchByFnameExitElement ()throws Exception{
        UsersControl.setFirstName(getJsonData("UserControl","ExitsName"));
        usersControl.singleSearch();
        Assert.assertFalse(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    @Test (priority = 8)
    public void tc_searchBySnameExitElement ()throws Exception{
        usersControl.setSecondName(getJsonData("UserControl","ExitsName"));
        usersControl.singleSearch();
        Assert.assertFalse(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();

    }
    @Test (priority = 9)
    public void tc_searchByThirdNameExitElement ()throws Exception{
        usersControl.setThirdName(getJsonData("UserControl","ExitsName"));
        usersControl.singleSearch();
        Assert.assertFalse(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    @Test  (priority = 10)
    public void tc_searchByLastNameExitElement()throws Exception{
        usersControl.setLastName(getJsonData("UserControl","ExitsName"));
        usersControl.singleSearch();
        Assert.assertFalse(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    @Test  (priority = 11)
    public void tc_searchByUserIDExitElement ()throws Exception{
        usersControl.setUserID(getJsonData("UserControl","ExitsName"));
        usersControl.singleSearch();
        Assert.assertFalse(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    @Test  (priority = 12)
    public void tc_searchByDeptExitElement ()throws Exception{
        usersControl.setChooseDept(getJsonData("UserControl","DeptHasUsers"));
        usersControl.singleSearch();
        Assert.assertFalse(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    /**********************************************/
    @Test   (priority = 13)
    public void tc_testShowAllFunctionality()throws Exception{  /* التحقق من عرض الكل */
        usersControl.setUserID(getJsonData("UserControl","ExitsName"));
        UsersControl.setFirstName(getJsonData("UserControl","ExitsName"));
        usersControl.singleSearch();
        usersControl.showall();
        String fName = usersControl.getFirstName().getAttribute("value");
        String sName = usersControl.getSecondName().getAttribute("value");
        String thirdName = usersControl.getThirdName().getAttribute("value");
        String lName = usersControl.getLastName().getAttribute("value");
        String userID = usersControl.getUserID().getAttribute("value");
        Assert.assertEquals(fName,"");
        Assert.assertEquals(sName,"");
        Assert.assertEquals(thirdName,"");
        Assert.assertEquals(lName,"");
        Assert.assertEquals(userID,"");
        Assert.assertEquals(usersControl.getChooseDept().getText(),getJsonData("UserControl","DeptDefaultChoose"));
    }
    @Test  (priority = 14)
    public void tc_clearTextPageSearchAndPressEnter ()throws Exception{  /*اختبار البحث عند الضغط على Enter يعود الى الصفحه الاولى */
        usersControl.getSearchText().sendKeys(getJsonData("UserControl","ExitsPageNumber"));
        usersControl.getSearchText().sendKeys(Keys.ENTER);
        usersControl.getSearchText().clear();
        usersControl.getSearchText().sendKeys(Keys.ENTER);
        Assert.assertTrue(usersControl.getPageNum().contains("صفحة 1"));
    }
    @Test  (priority = 14)
    public void tc_searchAboutEmployeeExitsByID()throws Exception{
        usersControl.selectEmployeeByID(getJsonData("UserControl","SpecificUser"));
        Assert.assertEquals(usersControl.getUserName(),getJsonData("UserControl","NameOfSpecificUser"));
    }
    @Test (priority = 15)
    public void tc_testUserNumberAcceptOnly_7Numbers ()throws Exception{    /* حقل رقم الموظف لايقبل اكتر من 7 ارقام */
        usersControl.setUserID(getJsonData("UserControl","UserIdGreatThan7"));
        usersControl.singleSearch();
        Assert.assertEquals(usersControl.getUserID().getAttribute("value"),getJsonData("UserControl","ValidValueGreatThan7"));
        usersControl.clearAllFeild();
    }
    @Test  (priority = 16)
    public void tc_testUserNumberWithOnly_4Numbers ()throws Exception{     /* حقل رقم الموظف لايظهر نتائج لرقم اقل من 7 */
        usersControl.setUserID(getJsonData("UserControl","UserIdLessThan7"));
        usersControl.singleSearch();
        Assert.assertTrue(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    @Test (priority = 17)
    public void tc_goToPageNotExitsAndPressEnter ()throws Exception{    /*كتابه رقم صفحه اكبر من الموجود و عند الضغط على Enter يعود الى الصفحه  15 */
        usersControl.setSearchText(getJsonData("UserControl","ExitsPageNumber"));
        String location1 = usersControl.getPageNum();
        usersControl.setSearchText(getJsonData("UserControl","NotExitsPageNumber"));
        String location2 = usersControl.getPageNum();
        Assert.assertEquals(location2,location1);
    }
}

