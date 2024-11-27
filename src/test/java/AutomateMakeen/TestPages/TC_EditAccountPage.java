package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.HomePage;
import AutomateMakeen.Pages.UsersControl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.time.Duration;

public class TC_EditAccountPage extends TestInit {
    private WebDriver driver;
    private UsersControl usersControl;
    private String AssertText;
    private String AssertText2;
    private WebDriverWait exWait;

    @BeforeClass
    public void setupClass() {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID, userPasswd);
        homePage.goToHomePage();
        usersControl = contentAside.goToUsersControl();}


    // اختبار تسجيل الدخول والوصول لصفحة تعديل الحساب
    // وان اسم المستحدم والادارة صحيحين
    @Test(priority = 1)
    public void TestNavigateToEditAccountPage() throws Exception{
        contentAside.goToCreateExternalEditAccount();
        AssertText = editAccountPage.getTitleText();
        AssertText2 = getJsonData("EditAccount", "titleText");
        Assert.assertEquals(AssertText, "45", "should be AssertText2");
        AssertText = editAccountPage.getSUserName();
        AssertText2 = getJsonData("EditAccount", "UserName");
        Assert.assertEquals(AssertText, AssertText2, "should be AssertText2");
        AssertText = editAccountPage.getDepartmentName();
        AssertText2 = getJsonData("EditAccount", "DepartmentName");
        Assert.assertEquals(AssertText, AssertText2, "should be AssertText2");}


    // اختبار عدم السماحية بالكتابة في حقول اسم المستخدم والادارة في حالة انا المتغير لا يسمح بذلك
    @Test(priority = 2)
    public void TestNotAllowingEnteringTextInUserAndDepartment() {
        boolean isInteractive = editAccountPage.isDepartmentFieldInteractive();
        Assert.assertFalse(isInteractive, "input field should not be interactive.");
        isInteractive = editAccountPage.isNameFieldInteractive();
        Assert.assertFalse(isInteractive, "input field should not be interactive.");}


    // اختبار امكانية نقل المهمات بين الحقول وتفريغها وظهور رسالة التحذير
    @Test(priority = 3)
    public void TestMakingFieldsEmpty() {
        editAccountPage.clickAllRightButton();
        boolean isEmpty = editAccountPage.isSelectUserEmpty();
        Assert.assertTrue(isEmpty, "empty");
        editAccountPage.clickSaveButton();
        boolean isPresent = editAccountPage.IsUserRedCircleElementPresent();
        Assert.assertTrue(isPresent, " element should be present.");
        editAccountPage.clickSaveButton();
        editAccountPage.clickAllLeftButton();
        isEmpty = editAccountPage.isSelectSystemEmpty();
        Assert.assertTrue(isEmpty, "empty");}


    // اختبار الاسكرول في حقول المهمات
    @Test(priority = 4)
    public void TestFieldsScrolling() {
        editAccountPage.clickAllRightButton();
        boolean isScrollBarWorking = editAccountPage.isScrollBarWorkingForArchive();
        Assert.assertTrue(isScrollBarWorking, "scroll should be working");
        editAccountPage.clickAllLeftButton();
        isScrollBarWorking = editAccountPage.isScrollBarWorkingForUser();
        Assert.assertTrue(isScrollBarWorking, "scroll should be working");}


    // اختبار ان صناديق الاختيار لا تعمل
    @Test(priority = 5)
    public void TestCheckBoxesAreWorking() {
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
        editAccountPage.clickMakeenUserCheckbox();}


    // اختبار من ان صناديق الاختيار الخاصة بالموبايل والايميل تعمل
    @Test(priority = 6)
    public void TestMobileAndMailCheckBoxes() throws Exception {
        editAccountPage.clickEmailChickBox();
        editAccountPage.clickEmailChickBox();
        editAccountPage.clickEmailChickBox();
        editAccountPage.clickMobileChickBox();
        editAccountPage.clickMobileChickBox();
        editAccountPage.clickMobileChickBox();
        editAccountPage.clickSaveButton();
        editAccountPage.clickAgreeButton();
        editAccountPage.clickPowerOffIcon();
        HomePage homePage = loginPage.loginUserWithoutRemMe(getJsonData("EditAccount", "UserAbdelrahmanID")
                ,getJsonData("EditAccount", "UserAbdelrahmanPW")  );
        boolean isChecked = editAccountPage.IsMobileCheckboxChecked();
        Assert.assertTrue(isChecked, "should be checked.");
        isChecked = editAccountPage.IsMailCheckboxChecked();
        Assert.assertTrue(isChecked, "should be checked.");
        editAccountPage.clickBackToLoginPage();
        loginPage.loginUserWithoutRemMe(userID, userPasswd);
        homePage.goToHomePage();
        contentAside.goToCreateExternalEditAccount();
        editAccountPage.clickEmailChickBox();
        editAccountPage.clickMobileChickBox();}


    // اختبار اضافة مهمة وظهورها عند المستخدم
    @Test(priority = 7)
    public void TestAddingMission()throws Exception {
        editAccountPage.clickAllRightButton();
        editAccountPage.clickArchiveTransaction();
        editAccountPage.clickLeftButton();
        editAccountPage.clickSaveButton();
        AssertText = editAccountPage.getErrorMS();
        AssertText2 = getJsonData("EditAccount", "errorMS");
        Assert.assertEquals(AssertText, AssertText2, "should be AssertText2");
        editAccountPage.clickNotAgreeButton();
        editAccountPage.clickSaveButton();
        editAccountPage.clickAgreeButton();
        editAccountPage.clickPowerOffIcon();
        HomePage homePage = loginPage.loginUserWithoutRemMe(getJsonData("EditAccount", "UserAbdelrahmanID")
                ,getJsonData("EditAccount", "UserAbdelrahmanPW")  );
        homePage.goToHomePage();
        editAccountPage.clickArchiveLink();
        editAccountPage.clickSearchLink();
        AssertText = editAccountPage.getSearchHeadingText();
        AssertText2 = getJsonData("EditAccount", "searchHeadingText");
        Assert.assertEquals(AssertText, AssertText2, "should be AssertText2");
        editAccountPage.clickPowerOffIcon();
        loginPage.loginUserWithoutRemMe(userID, userPasswd);
        homePage.goToHomePage();
        contentAside.goToCreateExternalEditAccount();}
}
