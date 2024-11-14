package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.HomePage;
import AutomateMakeen.Pages.ImportedMails;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class TC_ImportedMails extends TestInit {
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

    ImportedMails importedMails;
    @BeforeClass(description = "Preconditions for each test in the class :" +
            "السماحية للدخول الي النظام : الأمانة الإلكترونية" +
            "الصلاحية للدخول الى البرنامج الرئيسي البريد ." +
            "الصلاحية للدخول الى البرنامج الفرعي انشاء بريد خارجي ." +
            "من خلال الضغط علي إنشاء بريد خارجي من ال Tree View  . ")
    public void setupClass() {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe("1561561", "112233");
        homePage.goToHomePage();
        importedMails = contentAside.goToImportedMail();
    }

    @Test(dependsOnMethods = "AutomateMakeen.TestPages.TC_CreateMailBox.tc_createValidExternalMail")
    public void tc_validateCreatedMailAddedToImportedMail(){
        importedMails.getRecentlyAddedMail(subject);
        List<String> mailData =importedMails.getMailData();
        Assert.assertEquals(mailData.get(0),subject);
        Assert.assertEquals(mailData.get(1),"محمد أحمد أحمد علي");
        Assert.assertEquals(mailData.get(2),senderName);
        Assert.assertEquals(mailData.get(3),docTypeName);
    }
}
