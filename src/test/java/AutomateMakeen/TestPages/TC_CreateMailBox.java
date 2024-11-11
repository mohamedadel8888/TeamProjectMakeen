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
    CreateExternalMailPage createExternalMailPage;
    @BeforeClass(description = "Preconditions for each test in the class :" +
            "السماحية للدخول الي النظام : الأمانة الإلكترونية" +
            "الصلاحية للدخول الى البرنامج الرئيسي البريد ." +
            "الصلاحية للدخول الى البرنامج الفرعي انشاء بريد خارجي ." +
            "من خلال الضغط علي إنشاء بريد خارجي من ال Tree View  . ")
    public void setupClass()  {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID,userPasswd);
        homePage.goToHomePage();
        createExternalMailPage = contentAside.goToCreateExternalMail();
    }

    @Test(dataProvider = "docTypeDataProvider")
    public void tc_insertIntoDocType(String docTypeDP) throws InterruptedException {
        createExternalMailPage.insertDocType(docTypeDP);
        createExternalMailPage.insertComment("");
        Thread.sleep(2000);
        System.out.println(createExternalMailPage.getDocType());
        Assert.assertEquals(createExternalMailPage.getDocTypeValidatorState(),"Red Circle");
        System.out.println(createExternalMailPage.getDocTypeErrorMsg());
        Thread.sleep(2000);
        createExternalMailPage.copyPasteToDocType(docTypeDP);
        createExternalMailPage.insertComment("");
        Thread.sleep(2000);
        System.out.println(createExternalMailPage.getDocType());
        Assert.assertEquals(createExternalMailPage.getDocTypeValidatorState(),"Red Circle");
        System.out.println(createExternalMailPage.getDocTypeErrorMsg());
        Thread.sleep(2000);
        createExternalMailPage.clearDocType();
        createExternalMailPage.docTypeControl("123");
    }
    @DataProvider(name = "docTypeDataProvider")
    public Object[] docTypeDataProvider(){
        return new Object[]{"أبجد","ABCD","(@*%~^)","١٣٠",""};
    }
}
