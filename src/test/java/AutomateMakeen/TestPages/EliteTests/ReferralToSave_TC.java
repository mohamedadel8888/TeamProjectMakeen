package AutomateMakeen.TestPages.EliteTests;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.CreateExternalMailPage;
import AutomateMakeen.Pages.Elite.SentPage;
import AutomateMakeen.Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class ReferralToSave_TC extends TestInit {

    String subject;
    String exportedNotes;

    @BeforeClass
    public void setup() throws FileNotFoundException {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(getJsonData("CreateInternalMailDataElite", "ID"), getJsonData("DelegateData", "validPassword"));
        CreateExternalMailPage createExternalMailPage = contentAside.goToCreateExternalMail();
        createExternalMailPage.clearAllField();
        createExternalMailPage.pressOnNumberOfStorage();
        createExternalMailPage.enteringTheSubjectOfMail(getJsonData("ValidExternalMailData","subject"));
        subject = getJsonData("ValidExternalMailData","subject");
        createExternalMailPage.setDocTypeUsingControl(getJsonData("ValidExternalMailData","docTypeNum"));
        createExternalMailPage.setReceiverUsingControl(getJsonData("ValidExternalMailData", "receiverName"));
        createExternalMailPage.setSenderUsingControl(getJsonData("ValidExternalMailData","senderName"));
        createExternalMailPage.setTreatClassificationUsingControl(getJsonData("ValidExternalMailData","mainClass"),getJsonData("ValidExternalMailData","treatClassification"));
        createExternalMailPage.insertRecipient(getJsonData("ValidExternalMailData","recipient2"));
        exportedNotes = getJsonData("CreateInternalMailDataElite", "exportedNotes");
        createExternalMailPage.pressOnDeactivateReferralNumber();
        createExternalMailPage.clickSendConfirm();
        createExternalMailPage.validateSuccessfulCreatingMail();
        homePage.goToElite();
        eliteHomePage.goToInbox();
    }
    @Test (priority = 1)
    public void referralToSave(){
        inboxPage.mailInboxSearch(subject);
        inboxPage.goToForwardToSave();
        String archiveNum = inboxPage.getTreatArchiveNum();
        inboxPage.sendTransToSave(exportedNotes);
        SentPage sentPage = eliteHomePage.goToSent();
        sentPage.mailSentSearch(archiveNum);
        String directing = sentPage.getTreatDirecting();
        Assert.assertEquals(directing, "للحفظ");
    }

}
