package AutomateMakeen.TestPages;

import AutomateMakeen.Base.BaseComp;
import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.ExportedMails;
import AutomateMakeen.Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class TC_ExportedMails extends TestInit {
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
    ExportedMails exportedMails;
    protected static String importNumber;
    @BeforeClass(description = "Preconditions for each test in the class :" +
            "السماحية للدخول الي النظام : الأمانة الإلكترونية" +
            "الصلاحية للدخول الى البرنامج الرئيسي البريد ." +
            "الصلاحية للدخول الى البرنامج الفرعي انشاء بريد خارجي ." +
            "من خلال الضغط علي إنشاء بريد خارجي من ال Tree View  . ")
    public void setupClass() {
        lunchDriver();
        loginPage.goToLoginPage();
        loginPage.loginUserWithoutRemMe(userID, userPasswd);
        exportedMails = contentAside.goToExportedMail();
    }
    @Test(priority = 1)
    public void tc_validateCreatedMailAddedToExportedMail(){
        exportedMails.getRecentlyAddedMail(subject);
        List<String> mailData = exportedMails.getMailData();
        Assert.assertEquals(mailData.get(0),subject);
        Assert.assertEquals(mailData.get(1),recipient);
        Assert.assertEquals(mailData.get(2),senderName);
        Assert.assertEquals(mailData.get(3),docTypeName);
        importNumber = mailData.get(4);
    }
}
