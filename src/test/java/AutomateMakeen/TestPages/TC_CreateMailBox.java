package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.CreateExternalMailPage;
import AutomateMakeen.Pages.HomePage;
import AutomateMakeen.Pages.UsersControl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_CreateMailBox extends TestInit {
    private String subject;
    private String docType;
    private String
    CreateExternalMailPage createExternalMailPage;

    @BeforeClass(description = "Preconditions for each test in the class :" +
            "السماحية للدخول الي النظام : الأمانة الإلكترونية" +
            "الصلاحية للدخول الى البرنامج الرئيسي البريد ." +
            "الصلاحية للدخول الى البرنامج الفرعي انشاء بريد خارجي ." +
            "من خلال الضغط علي إنشاء بريد خارجي من ال Tree View  . ")
    public void setupClass() {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID, userPasswd);
        homePage.goToHomePage();
        createExternalMailPage = contentAside.goToCreateExternalMail();
    }

    @Test(dataProvider = "docTypeDataProvider")
    public void tc_createValidExternalMail(String docTypeDP) {
        createExternalMailPage.enteringTheSubjectOfMail();
    }

    @DataProvider(name = "docTypeDataProvider")
    public Object[] docTypeDataProvider() {
        return new Object[]{"أبجد", "ABCD", "(@*%~^)", "١٣٠", ""};
    }
}