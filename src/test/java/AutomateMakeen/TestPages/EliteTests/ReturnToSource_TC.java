package AutomateMakeen.TestPages.EliteTests;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.CreateExternalMailPage;
import AutomateMakeen.Pages.Elite.EliteHomePage;
import AutomateMakeen.Pages.Elite.SentPage;
import AutomateMakeen.Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class ReturnToSource_TC extends TestInit {
    String subject;
    String exportedNotes;
    String myDepartment;
    String sefatLetter;
    String mySubject;
    String forwardToOffer;
    String forwardToLetter;
    String forwardType;
    String forwardName;
    String addModel;
    String receiverAlias;
    @BeforeClass
    public void setupClass() throws FileNotFoundException {
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
        createExternalMailPage.insertRecipient(getJsonData("ValidExternalMailData","recipient3"));
        exportedNotes = getJsonData("CreateInternalMailDataElite", "exportedNotes");
        createExternalMailPage.pressOnDeactivateReferralNumber();
        createExternalMailPage.clickSendConfirm();
        createExternalMailPage.validateSuccessfulCreatingMail();
        loginPage = homePage.signOut();
        HomePage homePage2 = loginPage.loginUserWithoutRemMe(getJsonData("DelegateData", "validEmployee"), getJsonData("DelegateData", "validPassword"));
            myDepartment = getJsonData("CreateInternalMailDataElite", "deptName"); /*من الادارة*/
            sefatLetter = getJsonData("CreateInternalMailDataElite", "sefatLetter"); /* صفة الخطاب*/
           // mySubject = getJsonData("CreateInternalMailDataElite", "mailSubject"); /*الموضوع*/
            forwardToOffer = getJsonData("CreateInternalMailDataElite", "forwardNumOffer");
            forwardToLetter = getJsonData("CreateInternalMailDataElite", "forwardNumLetter");
            forwardType = getJsonData("CreateInternalMailDataElite", "forwardType");
            forwardName = getJsonData("CreateInternalMailDataElite", "forwardName"); /*اسم ادارة الموجه اليه*/
            addModel = getJsonData("CreateInternalMailDataElite", "addModel");
            receiverAlias = getJsonData("CreateInternalMailDataElite", "receiverAlias"); /*مسمى الموجه اليه*/
        homePage2.goToElite();
        eliteHomePage.goToInbox();
        inboxPage.mailInboxSearch(subject);
        inboxPage.offersTab();
        inboxPage.selectDepartment(myDepartment);
        inboxPage.forwardTo(forwardToOffer);
        inboxPage.receiverAlias(receiverAlias);
        //inboxPage.subject(mySubject);
        inboxPage.addModel(addModel);
        inboxPage.send();
        inboxPage.offersTab();
        inboxPage.goToSign();
        inboxPage.signConfirm();
        loginPage = eliteHomePage.signOutFromElite();
        HomePage homePage3 = loginPage.loginUserWithoutRemMe(getJsonData("CreateInternalMailDataElite", "ID"), getJsonData("DelegateData", "validPassword"));
        homePage3.goToElite();
        eliteHomePage.goToInbox();


    }
    @Test
    public void verifyReturnToSourceFeature(){
        inboxPage.mailInboxSearch(subject);
        inboxPage.returnToSource(exportedNotes);
        SentPage sentPage = eliteHomePage.goToSent();
        sentPage.mailSentSearch(subject);
        Assert.assertTrue(sentPage.getTreatDirecting().contains("للتحرير"));

    }
}
