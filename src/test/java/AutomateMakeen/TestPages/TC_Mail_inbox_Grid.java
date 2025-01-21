package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.Mail_Inbox_Grid;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static AutomateMakeen.TestPages.TC_Mail_CreateExMail.etSubject;


public class TC_Mail_inbox_Grid extends TestInit {
//    private String subject = "انشاء بريد خارجي";
//    private String docTypeNum = "123";
//    private String docTypeName = "اعادة المياة";
//    private String receiverNum = "5432";
//    private String receiverName = "مرسل جديد";
//    private String senderNum = "14912";
//    private String senderName = "جديده سلمى";
//    private String treatClassification = "تصريح بناء جديد";
//    private String recipient = "مروان خليل";
//    private String activeDays;
     static String etArchiveNum ;

     static String etIncomeNumber ;

    Mail_Inbox_Grid importedMails;
    @BeforeClass(description = "Preconditions for each test in the class :" +
            "السماحية للدخول الي النظام : الأمانة الإلكترونية" +
            "الصلاحية للدخول الى البرنامج الرئيسي البريد ." +
            "الصلاحية للدخول الى البرنامج الفرعي انشاء بريد خارجي ." +
            "من خلال الضغط علي إنشاء بريد خارجي من ال Tree View  . ")
    public void setupClass() {
        lunchDriver();
        loginPage.goToLoginPage();
        loginPage.loginUserWithoutRemMe(userID, userPasswd);
        importedMails = contentAside.goToImportedMail();
    }

    @Test(dependsOnMethods = "AutomateMakeen.TestPages.TC_Mail_CreateExMail.tc_createValidExternalMail")
    public void tc_validateCreatedMailAddedToImportedMail() throws Exception{
        importedMails.getRecentlyAddedMail(etSubject);
        List<String> mailData = importedMails.getMailData();
        etIncomeNumber = mailData.get(4);
        etArchiveNum = mailData.get(5);
        System.out.println("Import : "+etIncomeNumber+ " Archive : "+etArchiveNum);
    }
}
