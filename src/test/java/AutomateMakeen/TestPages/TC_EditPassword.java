package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.EditPassword;
import AutomateMakeen.Pages.HomePage;
import AutomateMakeen.Pages.UsersControl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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
        usersControl = contentAside.goToUsersControl();
    }
    @Test (priority = 1)
    public void tc_ChangePasswordValidScenario (){    /* تغيير كلمه المرور بطريقه صحيحه */
        usersControl.selectEmployeeByID("0123456");
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword("24602460r");
        editPassword.setConfirmNewPassword("24602460r");
        editPassword.save();
        editPassword.acceptIcon();
        homePage = new HomePage(driver);
        loginPage =homePage.signOut();
        loginPage.loginUserWithoutRemMe("0123456","24602460r");
        Assert.assertTrue(loginPage.getMobileCode().isDisplayed());
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID,userPasswd);
    }
    @Test (priority = 2)
    public void tc_TestErrorMessages () throws InterruptedException {   /* التحقق من رسائل الخطأ */
        usersControl = contentAside.goToUsersControl();
        usersControl.selectEmployeeByID("0123456");
        editPassword = usersControl.editPassword();
        editPassword.save();
        Thread.sleep(2000);
        Assert.assertEquals(editPassword.validation1(),"Red Circle");
        Assert.assertEquals(editPassword.validation2(),"Red Circle");
        editPassword.back();
    }
    @Test (priority = 3)
    public void tc_lessThan6Numbers(){  /* التحقق من ادخال كلمه مرورو اقل من 6 ارقام */
        usersControl.selectEmployeeByID("0123456");
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword("12345");
        String m1 = editPassword.validation1();
        Assert.assertEquals(editPassword.getErrorMessage1(),"كلمة المرور تقبل أكبر من 5 خانات");
        editPassword.back();
        editPassword.acceptIcon();
    }
    @Test (priority = 4)
    public void tc_passwordNotMatching (){     /*التحقق من عدم مطابقه كلمه المرورو */
        usersControl.selectEmployeeByID("0123456");
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword("123456");
        editPassword.setConfirmNewPassword("234567");
        String m2 = editPassword.validation2();
        Assert.assertEquals(editPassword.getErrorMessage2(),"عفواً،كلمة المرور غير متطابقة");
        editPassword.back();
        editPassword.acceptIcon();
    }
    @Test (priority = 5)
    public void tc_enterSameNumberInPassword(){       /* ادخال نفس الرقم فقط  */
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
        editPassword.back();
        editPassword.acceptIcon();
    }
    @Test (priority = 6)
    public void tc_enterSameLetterInPassword(){/* ادخال نفس الحرف فقط  */
        usersControl.selectEmployeeByID("0123456");
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword("aaaaaa");
        editPassword.setConfirmNewPassword("aaaaaa");
        editPassword.save();
        String m1 = editPassword.validation1();
        String e1 = editPassword.getErrorMessage1();
        String m2 = editPassword.validation2();
        String e2 = editPassword.getErrorMessage2();
        Assert.assertEquals(e1,"عفواً،كلمة المرور غير مناسبة يرجي تغيرها بصيغة أخرى");
        Assert.assertEquals(e2,"عفواً،كلمة المرور غير مناسبة يرجي تغيرها بصيغة أخرى");
        editPassword.back();
        editPassword.acceptIcon();
    }
    @Test (priority = 7)
    public void tc_enterDataAndReturnWithoutSaving(){     /* ادخال بيانات والعوده بدون حفظ */
        usersControl.selectEmployeeByID("0123456");
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword("111111");
        editPassword.setConfirmNewPassword("111111");
        editPassword.back();
        editPassword.acceptIcon();
        Assert.assertTrue(usersControl.getUserControlPage().isDisplayed());
    }
    @Test  (priority =8)
    public void tc_enterPasswordAsSameAsUserNumber(){   /* ادخال كلمه مرور بنفس ارقام رقم الموظف */
        usersControl.selectEmployeeByID("0123456");
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword("0123456");
        editPassword.setConfirmNewPassword("0123456");
        editPassword.save();
        Assert.assertFalse(editPassword.getConfirmationMessage().isDisplayed());
    }
    @Test  (priority = 9)
    public void tc_enterPasswordAsSameAsUserName(){/* ادخال كلمه مرور بنفس ارقام اسم الموظف */
        editPassword.notAcceptIcon();
        editPassword.back();
        editPassword.acceptIcon();
        usersControl.selectEmployeeByID("6956529");
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword("121212");
        editPassword.setConfirmNewPassword("121212");
        editPassword.save();
        Assert.assertFalse(editPassword.getConfirmationMessage().isDisplayed());
    }
}
