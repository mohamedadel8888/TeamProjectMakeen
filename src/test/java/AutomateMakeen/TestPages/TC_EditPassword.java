package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.EditPassword;
import AutomateMakeen.Pages.HomePage;
import AutomateMakeen.Pages.UsersControl;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class TC_EditPassword extends TestInit {
    HomePage homePage ;
    UsersControl usersControl;
    EditPassword editPassword;
    private WebDriverWait exWait = new WebDriverWait(driver, Duration.ofSeconds(5)) ;
    @BeforeClass(description = "Preconditions for each test in the class :" +
            "1- Login with authorized User." +
            "2- Navigate to Edit Password Page By Press 'ادارة المستخدمين' in the content Aside" +
            "then press 'ادارة المستخدمين' then 'تعديل كلمه المرور'")
    public void setupClass()  {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID,userPasswd);
        usersControl = contentAside.goToUsersControl();
    }
    @Test (priority = 1)
    public void tc_ChangePasswordValidScenario () throws Exception {    /* تغيير كلمه المرور بطريقه صحيحه */
        usersControl.selectEmployeeByID(getJsonData("EditPassword","UserID"));
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword(getJsonData("EditPassword","ValidPassword_1"));
        editPassword.setConfirmNewPassword(getJsonData("EditPassword","ValidPassword_1"));
        editPassword.save();
        editPassword.acceptIcon();
        homePage = new HomePage(driver);
        loginPage =homePage.signOut();
        loginPage.loginUserWithoutRemMe(getJsonData("EditPassword","UserID"),getJsonData("EditPassword","ValidPassword_1"));
        Assert.assertTrue(loginPage.getMobileCode().isDisplayed());
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID,userPasswd);
    }
    @Test (priority = 2)
    public void tc_TestErrorMessages () throws Exception {   /* التحقق من رسائل الخطأ */
        usersControl = contentAside.goToUsersControl();
        usersControl.selectEmployeeByID(getJsonData("EditPassword","UserID"));
        editPassword = usersControl.editPassword();
        editPassword.save();
        exWait.until(ExpectedConditions.visibilityOf(editPassword.ErrorMessage1()));
        Assert.assertEquals(editPassword.validation1(),"Red Circle");
        Assert.assertEquals(editPassword.validation2(),"Red Circle");
        editPassword.back();
    }
    @Test (priority = 3)
    public void tc_lessThan6Numbers() throws Exception {  /* التحقق من ادخال كلمه مرورو اقل من 6 ارقام */
        usersControl.selectEmployeeByID(getJsonData("EditPassword","UserID"));
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword(getJsonData("EditPassword","PasswordLessSixNum"));
        String m1 = editPassword.validation1();
        Assert.assertEquals(editPassword.getErrorMessage1(),getJsonData("EditPassword","ErrorMessageLessThanSixNum"));
        editPassword.back();
        editPassword.acceptIcon();
    }
    @Test (priority = 4)
    public void tc_passwordNotMatching  () throws Exception{     /*التحقق من عدم مطابقه كلمه المرورو */
        usersControl.selectEmployeeByID(getJsonData("EditPassword","UserID"));
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword(getJsonData("EditPassword","ValidPassword_2"));
        editPassword.setConfirmNewPassword(getJsonData("EditPassword","PasswordNotMatching"));
        String m2 = editPassword.validation2();
        Assert.assertEquals(editPassword.getErrorMessage2(),getJsonData("EditPassword","ErrorMessageNotMatching"));
        editPassword.back();
        editPassword.acceptIcon();
    }
    @Test (priority = 5)
    public void tc_enterSameNumberInPassword() throws Exception{       /* ادخال نفس الرقم فقط  */
        usersControl.selectEmployeeByID(getJsonData("EditPassword","UserID"));
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword(getJsonData("EditPassword","PasswordOnlyOneNumber"));
        editPassword.save();
        String m1 = editPassword.validation1();
        String e1 = editPassword.getErrorMessage1();
        Assert.assertEquals(e1,getJsonData("EditPassword","ErrorMessageFormula"));
        editPassword.back();
        editPassword.acceptIcon();
    }
    @Test (priority = 6)
    public void tc_enterSameLetterInPassword()throws Exception{/* ادخال نفس الحرف فقط  */
        usersControl.selectEmployeeByID(getJsonData("EditPassword","UserID"));
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword(getJsonData("EditPassword","PasswordOnlyOneChar"));
        editPassword.save();
        String m1 = editPassword.validation1();
        String e1 = editPassword.getErrorMessage1();
        Assert.assertEquals(e1,getJsonData("EditPassword","ErrorMessageFormula"));
        editPassword.back();
        editPassword.acceptIcon();
    }
    @Test (priority = 7)
    public void tc_enterDataAndReturnWithoutSaving()throws Exception {     /* ادخال بيانات والعوده بدون حفظ */
        usersControl.selectEmployeeByID(getJsonData("EditPassword","UserID"));
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword(getJsonData("EditPassword","PasswordOnlyOneNumber"));
        editPassword.setConfirmNewPassword(getJsonData("EditPassword","PasswordOnlyOneNumber"));
        editPassword.back();
        editPassword.acceptIcon();
        Assert.assertTrue(usersControl.getUserControlPage().isDisplayed());
    }


    @Test  (priority =8)
    public void tc_enterPasswordAsSameAsOldPassword ()throws Exception{   /* ادخال كلمه مرور بنفس كلمه المرور القديمه */
        usersControl.selectEmployeeByID(getJsonData("EditPassword","UserID"));
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword(getJsonData("EditPassword","ValidPassword_1"));
        editPassword.setConfirmNewPassword(getJsonData("EditPassword","ValidPassword_1"));
        editPassword.save();
        String m2 = editPassword.validation2();
        String e2 = editPassword.getErrorMessage2();
        Assert.assertEquals(e2,getJsonData("EditPassword","ErrorMessageOldPassword"));
        editPassword.back();
        editPassword.acceptIcon();
    }
    @Test  (priority =9)
    public void tc_enterPasswordAsSameAsUserNumber()throws Exception{   /* ادخال كلمه مرور بنفس ارقام رقم الموظف */
        usersControl.selectEmployeeByID( getJsonData("EditPassword","UserID"));
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword(getJsonData("EditPassword","PasswordAsUserID"));
        editPassword.setConfirmNewPassword(getJsonData("EditPassword","PasswordAsUserID"));
        editPassword.save();
        Assert.assertFalse(editPassword.getConfirmationMessage().isDisplayed());
    }
}
