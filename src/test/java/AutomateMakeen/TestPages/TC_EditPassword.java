package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.EditPassword;
import AutomateMakeen.Pages.HomePage;
import AutomateMakeen.Pages.UsersControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_EditPassword extends TestInit {
    HomePage homePage ;
    UsersControl usersControl;
    EditPassword editPassword;
    @BeforeClass(description = "Preconditions for each test in the class :" +
            "1- Login with authorized User." +
            "2- Navigate to Edit Password Page By Press 'ادارة المستخدمين' in the content Aside" +
            "then press 'ادارة المستخدمين' then 'تعديل كلمه المرور'")
    public void setupClass()  {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID,userPasswd);
        homePage.goToHomePage();
        usersControl = contentAside.goToUsersControl();
    }
    @AfterMethod
    public void MethodDown(){
        driver.navigate().refresh();
    }

    /*@Test
    public void tc_ChangePasswordValidScenario (){
        usersControl.selectEmployeeByID("0123456");
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword("7000123mmm");
        editPassword.setConfirmNewPassword("7000123mmm");
        editPassword.save();
        editPassword.acceptIcon();
        homePage = new HomePage(driver);
        loginPage =homePage.signOut();
        loginPage.loginUserWithoutRemMe("0123456","7000123mmm");
        Assert.assertTrue(loginPage.getMobileCode().isDisplayed());
    }*/
    @Test
    public void tc_TestErrorMessages (){
        usersControl.selectEmployeeByID("0123456");
        editPassword = usersControl.editPassword();
        editPassword.save();
        Assert.assertEquals(editPassword.validation1(),"Red Circle");
        Assert.assertEquals(editPassword.validation2(),"Red Circle");
    }
    @Test
    public void tc_lessThan6Numbers(){
        usersControl.selectEmployeeByID("0123456");
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword("12345");
        String m1 = editPassword.validation1();
        Assert.assertEquals(editPassword.getErrorMessage1(),"كلمة المرور تقبل أكبر من 5 خانات");
    }
    @Test
    public void tc_passwordNotMatching (){
        usersControl.selectEmployeeByID("0123456");
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword("123456");
        editPassword.setConfirmNewPassword("234567");
        String m2 = editPassword.validation2();
        Assert.assertEquals(editPassword.getErrorMessage2(),"عفواً،كلمة المرور غير متطابقة");
    }
    @Test
    public void tc_enterPasswordAsSameAsUserNumber(){
        usersControl.selectEmployeeByID("0123456");
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword("0123456");
        editPassword.setConfirmNewPassword("0123456");
        String m1 = editPassword.validation1();
        editPassword.save();
        Assert.assertEquals(editPassword.getErrorMessage1(),"عفواً،كلمة المرور غير مناسبة يرجي تغيرها بصيغة أخرى");
        String m2 = editPassword.validation2();
        Assert.assertEquals(editPassword.getErrorMessage2(),"عفواً،كلمة المرور غير مناسبة يرجي تغيرها بصيغة أخرى");
    }

    @Test
    public void tc_enterPasswordAsSameAsUserName(){
        usersControl.selectEmployeeByID("6956529");
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword("121212");
        editPassword.setConfirmNewPassword("121212");
        editPassword.save();
        String m1 = editPassword.validation1();
        Assert.assertEquals(editPassword.getErrorMessage1(),"عفواً،كلمة المرور غير مناسبة يرجي تغيرها بصيغة أخرى");
        String m2 = editPassword.validation2();
        Assert.assertEquals(editPassword.getErrorMessage2(),"عفواً،كلمة المرور غير مناسبة يرجي تغيرها بصيغة أخرى");
    }
    @Test
    public void tc_enterEasyPassword(){
        usersControl.selectEmployeeByID("0123456");
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword("111111");
        editPassword.setConfirmNewPassword("111111");
        editPassword.save();
        String m1 = editPassword.validation1();
        String e1 = editPassword.getErrorMessage1();
        String m2 = editPassword.validation2();
        String e2 = editPassword.getErrorMessage2();
        Assert.assertEquals(e1,"عفواً،كلمة المرور غير مناسبة يرجي تغيرها بصيغة أخرى");
        Assert.assertEquals(e2,"عفواً،كلمة المرور غير مناسبة يرجي تغيرها بصيغة أخرى");
    }
    @Test
    public void tc_enterDataAndReturnWithoutSaving(){
        usersControl.selectEmployeeByID("0123456");
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword("111111");
        editPassword.setConfirmNewPassword("111111");
        editPassword.back();
        editPassword.acceptIcon();
        Assert.assertTrue(usersControl.getUserControlPage().isDisplayed());
    }
}
