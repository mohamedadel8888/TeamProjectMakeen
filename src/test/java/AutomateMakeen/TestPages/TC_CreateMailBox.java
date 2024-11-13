package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.CreateExternalMailPage;
import AutomateMakeen.Pages.ExportedMails;
import AutomateMakeen.Pages.HomePage;
import AutomateMakeen.Pages.UsersControl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_CreateMailBox extends TestInit {
    private String subject = "انشاء بريد خارجي";
    private String docTypeNum = "123";
    private String docTypeName = "اعادة المياة";
    private String receiverNum = "5432";
    private String receiverName = "مرسل جديد";
    private String senderNum = "14912";
    private String senderName = "جديده سلمى";
    private String treatClassification = "تصريح بناء جديد";
    private String recipient = "مروان خليل";
    private String activeDays;

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

    @Test
    public void tc_createValidExternalMail() {
        createExternalMailPage.enteringTheSubjectOfMail(subject);
        createExternalMailPage.setDocTypeUsingControl(docTypeName);
        createExternalMailPage.setReceiverUsingControl(receiverName);
        createExternalMailPage.setSenderUsingControl(senderName);
        createExternalMailPage.setTreatClassificationUsingControl("تصنيف رئيسي",treatClassification);
        createExternalMailPage.insertRecipient(recipient);
        createExternalMailPage.pressOnDeactivateReferralNumber();
        createExternalMailPage.clickSendConfirmBtn();
        Assert.assertTrue(createExternalMailPage.validateSuccessfulCreatingMail());
        driver.navigate().refresh();
        ExportedMails exportedMails = contentAside.goToExportedMail();
        exportedMails.getRecentlyAddedMail();

    }

//    @Test//(dataProvider = "docTypeDataProvider")
//    public void tc_createValidExternalMail() {
//        createExternalMailPage.enteringTheSubjectOfMail(subject);
//        createExternalMailPage.setDocTypeUsingControl(docTypeName);
//        createExternalMailPage.setReceiverUsingControl(receiverName);
//        createExternalMailPage.setSenderUsingControl(senderName);
//        createExternalMailPage.setTreatClassificationUsingControl("تصنيف رئيسي",treatClassification);
//        createExternalMailPage.insertRecipient(recipient);
//        createExternalMailPage.pressOnDeactivateReferralNumber();
//        createExternalMailPage.clickSendConfirmBtn();
//        Assert.assertTrue(createExternalMailPage.validateSuccessfulCreatingMail());
//    }

//
//    @DataProvider(name = "docTypeDataProvider")
//    public Object[] docTypeDataProvider() {
//        return new Object[]{"أبجد", "ABCD", "(@*%~^)", "١٣٠", ""};
//    }
}