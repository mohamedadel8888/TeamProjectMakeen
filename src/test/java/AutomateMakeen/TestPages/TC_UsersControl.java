package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.HomePage;
import AutomateMakeen.Pages.UsersControl;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_UsersControl extends TestInit {
    UsersControl usersControl;

    @BeforeClass(description = "Preconditions for each test in the class :" +
            "1- Login with authorized User." +
            "2- Navigate to User Control Page By Press 'لوحه التحكم ' in the content Aside" +
            "then press 'ادارة المستخدمين'")
    public void setupClass() {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID,userPasswd);
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
        Assert.assertEquals(usersControl.getChooseDept().getText(),"إخترالادارة");
        Assert.assertEquals(usersControl.getSearchText().getAttribute("value"),"1");
    }
    @Test  (priority = 14)
    public void tc_clearTextPageSearchAndPressEnter (){  /*اختبار البحث عند الضغط على Enter يعود الى الصفحه الاولى */
        usersControl.getSearchText().sendKeys("20");
        usersControl.getSearchText().sendKeys(Keys.ENTER);
        usersControl.getSearchText().clear();
        usersControl.getSearchText().sendKeys(Keys.ENTER);
        Assert.assertTrue(usersControl.getPageNum().contains("صفحة 1"));
    }
    @Test  (priority = 14)
    public void tc_searchAboutEmployeeExitsByID(){
        usersControl.selectEmployeeByID("1020311");
        Assert.assertEquals(usersControl.getUserName(),"حمدي حمد حامد الحمدون");
    }
    @Test (priority = 15)
    public void tc_testUserNumberAcceptOnly_7Numbers (){    /* حقل رقم الموظف لايقبل اكتر من 7 ارقام */
        usersControl.setUserID("123456789");
        usersControl.singleSearch();
        Assert.assertEquals(usersControl.getUserID().getAttribute("value"),"1234567");
        usersControl.clearAllFeild();
    }
    @Test  (priority = 16)
    public void tc_testUserNumberWithOnly_4Numbers (){     /* حقل رقم الموظف لايظهر نتائج لرقم اقل من 7 */
        usersControl.setUserID("1234");
        usersControl.singleSearch();
        Assert.assertTrue(usersControl.getNoResultMessage().isDisplayed());
        usersControl.clearAllFeild();
    }
    @Test (priority = 17)
    public void tc_goToPageNotExitsAndPressEnter (){    /*كتابه رقم صفحه اكبر من الموجود و عند الضغط على Enter يعود الى الصفحه  15 */
        usersControl.setSearchText("15");
        String location1 = usersControl.getPageNum();
        usersControl.setSearchText("300");
        String location2 = usersControl.getPageNum();
        Assert.assertEquals(location2,location1);
    }
}

