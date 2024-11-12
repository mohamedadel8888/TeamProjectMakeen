package AutomateMakeen.TestPages;
import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.CreateExternalEditAccountPage;
import AutomateMakeen.Pages.HomePage;
import AutomateMakeen.Pages.UsersControl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import AutomateMakeen.Pages.UsersControl;


public class TC_EditAccountPage extends TestInit {
    private WebDriver driver;
    CreateExternalEditAccountPage createExternalEditAccountPage;
    UsersControl usersControl;
    String AssertText;
    private WebDriverWait exWait;



    @Test (priority = 1)
    public void TestNavigateToEditAccountPage()  {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID,userPasswd);
        homePage.goToHomePage();
        createExternalEditAccountPage = contentAside.goToCreateExternalEditAccount();
        AssertText = editAccountPage.getSUserName();
        Assert.assertEquals(AssertText, "عبدالرحمن السيد محمد حموده", "should be 'عبدالرحمن السيد محمد حموده'");
        AssertText = editAccountPage.getTitleText();
        Assert.assertEquals(AssertText, "تعديل حساب مستخدم", "should be 'تعديل حساب مستخدم'");
        AssertText = editAccountPage.getDepartmentName();
        Assert.assertEquals(AssertText, "ادارة الكهرباء", "should be 'ادارة الكهرباء'");
    }


    @Test (priority = 2)
    public void Test()  {
        editAccountPage.clickAllRightButton();
        boolean isEmpty = editAccountPage.isSelectUserEmpty();
        Assert.assertTrue(isEmpty, "empty");
        editAccountPage.clickAllLeftButton();
         isEmpty = editAccountPage.isSelectSystemEmpty();
        Assert.assertTrue(isEmpty, "empty");
    }

    @Test (priority = 3)
    public void TestAddingMission()  {
        editAccountPage.clickAllRightButton();
        editAccountPage.clickArchiveTransaction();
        editAccountPage.clickLeftButton();
        try {TimeUnit.SECONDS.sleep(1);}
        catch (InterruptedException e)
        {throw new RuntimeException(e);}
        editAccountPage.clickSaveButton();
        AssertText = editAccountPage.getErrorMS();
        Assert.assertEquals(AssertText, "هل تريد اتمام عملية الحفظ؟", "should be 'هل تريد اتمام عملية الحفظ؟'");
        editAccountPage.clickNotAgreeButton();
        editAccountPage.clickSaveButton();
        editAccountPage.clickAgreeButton();
    }








}
