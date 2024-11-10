package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;

public class TC_LoginPage extends TestInit {
    @BeforeClass
    public void setupClass()  {
        lunchDriver();
        loginPage.goToLoginPage();
    }
    //Test login user with valid user id and valid password then navigate to home page
//    @BeforeMethod
//    public void beforeMethod(){
//        loginPage.goToLoginPage();
//    }
    @Test
    public void tc_loginWithValidUser(){
        loginPage.clearAllFeild();
        System.out.println("التأكد من وظيفة تسجيل الدخول بحساب جديد بإستخدام رقم تعريفي صحيح و رقم سري صحيح");
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID ,userPasswd);
        Assert.assertTrue(homePage.getHomeUrl().contains("https://em.alqemam.com/qCMS_Test_v13.4.14/MainPage.aspx"));
    }
    //Test login user with invalid user id and validate error message
    @Test(dataProvider = "invalidIdData")
    public void tc_loginWithInvalidUser(String invalidUserId) {
        loginPage.clearAllFeild();
        loginPage.loginUserWithoutRemMe(invalidUserId ,userPasswd);
        switch (invalidUserId) {
            case "000000" : {
                //"عفواً ، محاولة غير صالحة لتسجيل الدخول"
                Assert.assertEquals(loginPage.getErrorMessage(),loginPage.getErrorMessages(0) );
                break;
            }
            case "00000000" : {
                //("عفوا، محاولة غير صالحة لتسجيل الدخول") -- return error message
                Assert.assertEquals(loginPage.getErrorMessage(),loginPage.getErrorMessages(0) );
                Assert.assertTrue(loginPage.getUserIdContent().equals("0000000"));
                break;
            }
            default : {
                Assert.assertEquals(loginPage.getErrorMessage(), loginPage.getErrorMessages(1));
                Assert.assertTrue(loginPage.getUserIdContent().equals(""));
            }
        }
    }
    @DataProvider(name = "invalidIdData")
    public Object[] invalidIdData() {
        return new Object[]{"","!@#$%^&", "Mohamed","       ","000000","00000000"};
    }

    //Test login user with valid user id, Invalid password and validate error message
    @Test(dataProvider = "invalidPasswordData")
    public void tc_loginWithInvalidPassword(String invalidPasswd) {
        loginPage.clearAllFeild();
        System.out.println("التأكد من وظيفة تسجيل الدخول بحساب جديد بإستخدام رقم تعريفي صحيح[" + userID +"] و رقم سري صحيح ["+invalidPasswd+"]");
        loginPage.loginUserWithoutRemMe(userID ,invalidPasswd);
        if (invalidPasswd.equals("")) {
                Assert.assertEquals(loginPage.getErrorMessage(), loginPage.getErrorMessages(2));
        }
        else {
                Assert.assertEquals(loginPage.getErrorMessage(), loginPage.getErrorMessages(0));
        }
    }
    @DataProvider(name = "invalidPasswordData")
    public Object[] invalidPasswordData() {
        return new Object[]{"","000000","      ","1234567"};
    }
    @Test
    public void atc_loginWithRememberMe(){
        loginPage.clearAllFeild();
        HomePage homePage = loginPage.loginUserWithRemMe(userID,userPasswd);
        loginPage = homePage.signOut();
        Assert.assertEquals(loginPage.getUserIdContent(),userID);
        Assert.assertEquals(loginPage.getUserPasswdContent(),userPasswd);
    }

    @AfterMethod
    public void refresh(){
        driver.navigate().refresh();
    }
}