package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.HomePage;
import AutomateMakeen.Pages.UsersControl;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_SearchInUsersControl extends TestInit {
    UsersControl usersControl;

    @BeforeClass(description = "Preconditions for each test in the class :" +
            "1- Login with authorized User." +
            "2- Navigate to User Control Page By Press 'لوحه التحكم ' in the content Aside" +
            "then press 'ادارة المستخدمين'")
    public void setupClass()  {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID,userPasswd);
        homePage.goToHomePage();
        usersControl = contentAside.goToUsersControl();
    }


    @Test
    public void tc_searchByFname (){
        usersControl.setFirstName("150");
        usersControl.singleSearch();
        Assert.assertTrue(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    @Test
    public void tc_searchBySname (){
        usersControl.setSecondName("150");
        usersControl.singleSearch();
        Assert.assertTrue(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();

    }
    @Test
    public void tc_searchByThirdName (){
        usersControl.setThirdName("150");
        usersControl.singleSearch();
        Assert.assertTrue(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    @Test
    public void tc_searchByLastName(){
        usersControl.setLastName("150");
        usersControl.singleSearch();
        Assert.assertTrue(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    @Test
    public void tc_searchByUserID (){
        usersControl.setUserID("150");
        usersControl.singleSearch();
        Assert.assertTrue(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    @Test
    public void tc_searchByDept (){
        usersControl.setChooseDept("''");
        usersControl.singleSearch();
        Assert.assertTrue(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    /********************************/
    @Test
    public void tc_searchByFnameExitElement (){
        usersControl.setFirstName("10");
        usersControl.singleSearch();
        Assert.assertFalse(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    @Test
    public void tc_searchBySnameExitElement (){
        usersControl.setSecondName("10");
        usersControl.singleSearch();
        Assert.assertFalse(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();

    }
    @Test
    public void tc_searchByThirdNameExitElement (){
        usersControl.setThirdName("10");
        usersControl.singleSearch();
        Assert.assertFalse(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    @Test
    public void tc_searchByLastNameExitElement(){
        usersControl.setLastName("10");
        usersControl.singleSearch();
        Assert.assertFalse(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    @Test
    public void tc_searchByUserIDExitElement (){
        usersControl.setUserID("10");
        usersControl.singleSearch();
        Assert.assertFalse(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    @Test
    public void tc_searchByDeptExitElement (){
        usersControl.setChooseDept("1");
        usersControl.singleSearch();
        Assert.assertFalse(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    /**********************************************/
    @Test
    public void tc_testShowAllFunctionality(){
        usersControl.setUserID("10");
        usersControl.setFirstName("10");
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
        Assert.assertEquals(usersControl.getChooseDept().getText(),"إخترالادارة");
        Assert.assertEquals(usersControl.getPageNum(),"صفحة 1 من 225");
    }
    @Test
    public void tc_clearTextPageSearchAndPressEnter (){  /*اختبار البحث عند الضغط على Enter يعود الى الصفحه الاولى */
        usersControl.getSearchText().sendKeys("20");
        usersControl.getSearchText().sendKeys(Keys.ENTER);
        usersControl.getSearchText().clear();
        usersControl.getSearchText().sendKeys(Keys.ENTER);
        Assert.assertTrue(usersControl.getPageNum().contains("صفحة 1"));
    }
    @Test
    public void tc_searchAboutEmployeeExitsByID(){
        usersControl.selectEmployeeByID("1020311");
        Assert.assertEquals(usersControl.getUserName(),"حمدي حمد حامد الحمدون");
    }
}

