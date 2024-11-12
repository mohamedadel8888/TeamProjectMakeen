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
        homePage.goToHomePage();
        usersControl = contentAside.goToUsersControl();
    }
    @Test
    public void tc_ChangePasswordValidScenario (){
        usersControl.selectEmployeeByID("0123456");
        editPassword = usersControl.editPassword();
        editPassword.setNewPassword("7000123mmm");
        editPassword.setConfirmNewPassword("7000123mmm");
        editPassword.save().click();
        editPassword.getAcceptIcon().click();
        homePage = new HomePage(driver);
        loginPage =homePage.signOut();
        loginPage.loginUserWithoutRemMe("0123456","7000123mmm");
        Assert.assertTrue(loginPage.getMobileCode().isDisplayed());
    }
    @Test
    public void tc_TestErrorMessages (){/********************* مشكله رساله الخطأ *****************************/
        usersControl.selectEmployeeByID("0123456");
        editPassword = usersControl.editPassword();
        editPassword.save().click();
        editPassword.getErrorMessage1().click();

        WebElement errorMessage1 = driver.findElement(By.xpath("(//p[@class='span_error'])[1]"));
        editPassword.getErrorMessage2().click();
        WebElement errorMessage2 = driver.findElement(By.className("span_error"));
        Assert.assertEquals(errorMessage1.getText(),"أدخل  كلمة المرور");
        //Assert.assertEquals(errorMessage2.getText(),"برجاء ادخال تأكيد لكلمة المرور");

    }
}
