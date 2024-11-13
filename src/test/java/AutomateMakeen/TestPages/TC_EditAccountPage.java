package AutomateMakeen.TestPages;
import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.CreateExternalEditAccountPage;
import AutomateMakeen.Pages.HomePage;
import AutomateMakeen.Pages.UsersControl;
import org.openqa.selenium.JavascriptExecutor;
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
    private WebDriverWait exWait;
    CreateExternalEditAccountPage createExternalEditAccountPage;
    UsersControl usersControl;
    String AssertText;




    @Test (priority = 1)
    public void TestNavigateToEditAccountPage()  {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID,userPasswd);
        homePage.goToHomePage();
        createExternalEditAccountPage = contentAside.goToCreateExternalEditAccount();
        AssertText = editAccountPage.getTitleText();
        Assert.assertEquals(AssertText, "تعديل حساب مستخدم", "should be 'تعديل حساب مستخدم'");
        AssertText = editAccountPage.getSUserName();
        Assert.assertEquals(AssertText, "عبدالرحمن السيد محمد حموده", "should be 'عبدالرحمن السيد محمد حموده'");
        AssertText = editAccountPage.getDepartmentName();
        Assert.assertEquals(AssertText, "ادارة الكهرباء", "should be 'ادارة الكهرباء'");}

    @Test (priority = 2)
    public void TestNotAllowingEnteringTextInUserAndDepartment  ()  {
        boolean isInteractive = editAccountPage.isDepartmentFieldInteractive();
        Assert.assertFalse(isInteractive, "The input field should not be interactive.");
         isInteractive = editAccountPage.isNameFieldInteractive();
        Assert.assertFalse(isInteractive, "The input field should not be interactive.");}



    @Test (priority = 3)
    public void TestMakingFieldsEmpty ()  {
        editAccountPage.clickAllRightButton();
        boolean isEmpty = editAccountPage.isSelectUserEmpty();
        Assert.assertTrue(isEmpty, "empty");
        editAccountPage.clickAllLeftButton();
         isEmpty = editAccountPage.isSelectSystemEmpty();
        Assert.assertTrue(isEmpty, "empty");}


    @Test (priority = 4)
    public void TestFieldsScrolling()  {
        editAccountPage.clickAllRightButton();
        boolean isScrollBarWorking = editAccountPage.isScrollBarWorkingForArchive();
        Assert.assertTrue(isScrollBarWorking, "scroll should be working");
        editAccountPage.clickAllLeftButton();
        isScrollBarWorking = editAccountPage.isScrollBarWorkingForUser();
        Assert.assertTrue(isScrollBarWorking, "scroll should be working");}


    @Test (priority = 5)
    public void TestCheckBoxesAreWorking()  {
        editAccountPage.clickPrsEmpCheckbox();
        editAccountPage.clickArchEmpCheckbox();
        editAccountPage.clickMakeenUserCheckbox();
        boolean isChecked = editAccountPage.IsPersonCheckBoxIsClicked();
        Assert.assertTrue(isChecked, "should be checked.");
        isChecked = editAccountPage.IsArchPersonCheckBoxIsClicked();
        Assert.assertTrue(isChecked, "should be checked.");
        isChecked = editAccountPage.IsMakeenCheckBoxIsClicked();
        Assert.assertTrue(isChecked, "should be checked.");
        editAccountPage.clickPrsEmpCheckbox();
        editAccountPage.clickArchEmpCheckbox();
        editAccountPage.clickMakeenUserCheckbox();
    }



    @Test (priority = 6)
    public void TestMobileAndMailCheckBoxes()  {
        editAccountPage.clickEmailChickBox();
        editAccountPage.clickEmailChickBox();
        editAccountPage.clickEmailChickBox();
        editAccountPage.clickMobileChickBox();
        editAccountPage.clickMobileChickBox();
        editAccountPage.clickMobileChickBox();
        editAccountPage.clickSaveButton();
        editAccountPage.clickAgreeButton();
        editAccountPage.clickPowerOffIcon();
        HomePage homePage = loginPage.loginUserWithoutRemMe("1654198","123321");
        boolean isChecked = editAccountPage.IsMobileCheckboxChecked();
        Assert.assertTrue(isChecked, "should be checked.");
        isChecked = editAccountPage.IsMailCheckboxChecked();
        Assert.assertTrue(isChecked, "should be checked.");
        editAccountPage.clickBackToLoginPage();
        loginPage.loginUserWithoutRemMe(userID,userPasswd);
        homePage.goToHomePage();
        createExternalEditAccountPage = contentAside.goToCreateExternalEditAccount();
        editAccountPage.clickEmailChickBox();
        editAccountPage.clickMobileChickBox();}


    @Test (priority = 7)
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
        editAccountPage.clickPowerOffIcon();
        HomePage homePage = loginPage.loginUserWithoutRemMe("1654198","123321");
        homePage.goToHomePage();
        editAccountPage.clickArchiveLink();
        editAccountPage.clickSearchLink();
        try {TimeUnit.SECONDS.sleep(3);}
        catch (InterruptedException e) {
            throw new RuntimeException(e);}
        AssertText = editAccountPage.getSearchHeadingText();
        Assert.assertEquals(AssertText, "بحث", "should be 'بحث'");
        editAccountPage.clickPowerOffIcon();
        loginPage.loginUserWithoutRemMe(userID,userPasswd);
        homePage.goToHomePage();
        createExternalEditAccountPage = contentAside.goToCreateExternalEditAccount();}


}
