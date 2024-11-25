package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.OutboxMails;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class TC_OutboxMails extends TestInit {
    OutboxMails outboxMails;
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
        outboxMails = contentAside.goToExportedMail();
    }
    @Test
    public void tc_validateCreatedMailAddedToExportedMail() throws Exception{
        outboxMails.getRecentlyAddedMail(getJsonData("ValidExternalMailData","subject"));
        List<String> mailData = outboxMails.getMailData();
        Assert.assertEquals(mailData.get(0),getJsonData("ValidExternalMailData","subject"));
        Assert.assertEquals(mailData.get(1),getJsonData("ValidExternalMailData","recipient"));
        Assert.assertEquals(mailData.get(2),getJsonData("ValidExternalMailData","senderName"));
        Assert.assertEquals(mailData.get(3),getJsonData("ValidExternalMailData","docTypeNum"));
        importNumber = mailData.get(4);
    }
}
