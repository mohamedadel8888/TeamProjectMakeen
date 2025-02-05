package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.qCMS_HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        qCMS_HomePage qCMSHomePage = loginPage.loginUserWithoutRemMe(userID ,userPasswd);
        Assert.assertTrue(qCMSHomePage.getHomeUrl().contains("MainPage.aspx"));
    }

    @Test
    public void tc_loginWithRememberMe(){
        loginPage.clearAllFeild();
        qCMS_HomePage qCMSHomePage = loginPage.loginUserWithRemMe(userID,userPasswd);
        loginPage = qCMSHomePage.signOut();
        Assert.assertEquals(loginPage.getUserIdContent(),userID);
        Assert.assertEquals(loginPage.getUserPasswdContent(),userPasswd);
    }

    @AfterMethod
    public void refresh(){
        driver.navigate().refresh();
    }
}