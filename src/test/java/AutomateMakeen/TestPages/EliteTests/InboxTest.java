package AutomateMakeen.TestPages.EliteTests;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.Elite.EliteHomePage;
import AutomateMakeen.Pages.Elite.InboxPage;
import AutomateMakeen.Pages.Elite.SentPage;
import AutomateMakeen.Pages.HomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.time.Duration;

public class InboxTest extends TestInit  {
    WebDriverWait ex;
    private String currentTest;
    String myDepartment;
    String sefatLetter;
    String mySubject;
    String forwardToOffer;
    String forwardToLetter;
    String forwardType;
    String addModel;
    String receiverAlias;
    String exportedNotes;

    @BeforeClass
        public void setUp () throws FileNotFoundException {
            lunchDriver();
            loginPage.goToLoginPage();
            HomePage homePage = loginPage.loginUserWithoutRemMe(getJsonData("DelegateData", "validEmployee"), getJsonData("DelegateData", "validPassword"));
            homePage.goToElite();
            EliteHomePage eliteHomePage = new EliteHomePage(driver);
            createInternalMailPage = eliteHomePage.goToCreateInternalMail();
            archiveNum = createInternalMailPage.createInternalMailForMe();
            ex = new WebDriverWait(driver, Duration.ofSeconds(8));
            myDepartment = getJsonData("CreateInternalMailDataElite", "deptName"); /*من الادارة*/
            sefatLetter = getJsonData("CreateInternalMailDataElite", "sefatLetter"); /* صفة الخطاب*/
            mySubject = getJsonData("CreateInternalMailDataElite", "mailSubject"); /*الموضوع*/
            forwardToOffer = getJsonData("CreateInternalMailDataElite", "forwardNumOffer");
            forwardToLetter = getJsonData("CreateInternalMailDataElite", "forwardNumLetter");
            forwardType = getJsonData("CreateInternalMailDataElite", "forwardType");
            addModel = getJsonData("CreateInternalMailDataElite", "addModel");
            receiverAlias = getJsonData("CreateInternalMailDataElite", "receiverAlias"); /*مسمى الموجه اليه*/
            exportedNotes = getJsonData("CreateInternalMailDataElite", "exportedNotes");
        }


        @Test  (priority = 1) /*pre condititons :
                -ان يكون مسجل الدخول لدية صلاحية الدخول للنظام
                - لدية صلاحية الشاشة الرئيسية للنخبة
                - صلاحية بريد النخية
            */
        public void verifyInboxPage () {  /*التحقق من فتح صفحه الوارد */
            Assert.assertTrue(inboxPage.getMailInboxPage().isDisplayed());
        }
        @Test (priority = 2)
        public void verifyExplanationPage () { /*فتح الشروحات */
            Assert.assertTrue(inboxPage.goToExplanations());
        }
        @Test  (priority = 3)/*قتح المرفقات */
        public void verifyAttachmentPage () {
            inboxPage.goToAttachments();
            ex.until(ExpectedConditions.visibilityOf(inboxPage.getAttachmentsTab()));
            Assert.assertTrue(inboxPage.getAttachmentsTab().isDisplayed());
        }
        @Test (priority = 4)
        public void checkCreateLetter () {  /*انشاء خطاب */
            currentTest = "checkCreateLetter";
            inboxPage.lettersTab();
            inboxPage.selectDepartment(myDepartment);
            inboxPage.selectSefatLetter(sefatLetter);
            inboxPage.forwardTo(forwardToLetter);
            inboxPage.selectForwardType(forwardType);
            inboxPage.subject(mySubject);
            inboxPage.addModel(addModel);
            inboxPage.send();
            inboxPage.lettersTab();
            Assert.assertTrue(inboxPage.statusDone());
        }
        @Test (priority = 5)
        public void checkCreateOffer () { /*انشاء عرض*/
            currentTest = "checkCreateOffer";
            inboxPage.offersTab();
            inboxPage.selectDepartment(myDepartment);
            inboxPage.forwardTo(forwardToOffer);
            inboxPage.receiverAlias(receiverAlias);
            inboxPage.subject(mySubject);
            inboxPage.addModel(addModel);
            inboxPage.send();
            inboxPage.offersTab();
            Assert.assertTrue(inboxPage.statusDone());
        }
        @Test (priority = 6)
        public void checkCreateInternalMemo () { /*انشاء مذكرة داخلية*/
            currentTest = "checkCreateInternalMemo";
            inboxPage.internalMemoTab();
            inboxPage.selectDepartment(myDepartment);
            inboxPage.setChkBoxDesc();
            inboxPage.forwardTo(forwardToOffer);
            inboxPage.subject(mySubject);
            inboxPage.addModel(addModel);
            inboxPage.send();
            inboxPage.internalMemoTab();
            Assert.assertTrue(inboxPage.statusDone());
        }
        @Test (priority = 7)
        public void signLetter (){
            inboxPage.lettersTab();
            inboxPage.goToSign();
            inboxPage.signConfirm();
            SentPage sentPage = eliteHomePage.goToSent();
            sentPage.mailSentSearch(archiveNum);
            String archive = sentPage.getTreatArchiveNum();
            Assert.assertEquals(archive, archiveNum);
        }
        @Test (priority = 8)
        public void addExportNotes (){
            inboxPage.exportNotes();
            inboxPage.addExportNotes(exportedNotes);
            Assert.assertEquals(inboxPage.getNotesContent(),exportedNotes);
        }


    }

