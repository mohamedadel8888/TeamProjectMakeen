package AutomateMakeen.TestPages.EliteTests;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.Elite.EliteHomePage;
import AutomateMakeen.Pages.Elite.SentPage;
import AutomateMakeen.Pages.HomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.time.Duration;

public class InboxTest extends TestInit  {
    WebDriverWait ex;
    String myDepartment;
    String sefatLetter;
    String mySubject;
    String forwardToOffer;
    String forwardToLetter;
    String forwardType;
    String forwardName;
    String addModel;
    String receiverAlias;
    String exportedNotes;
    String employeeName;
    String employeeDepartment;

    @BeforeClass
        public void setUp () throws FileNotFoundException {  /*pre condititons :
                                                                                                -ان يكون مسجل الدخول لدية صلاحية الدخول للنظام
                                                                                                - لدية صلاحية الشاشة الرئيسية للنخبة
                                                                                                - صلاحية بريد النخية
                                                                                                - صلاحية التأشير
                                                                                                - صلاحية التوقيع
            */
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
            forwardName = getJsonData("CreateInternalMailDataElite", "forwardName"); /*اسم ادارة الموجه اليه*/
            addModel = getJsonData("CreateInternalMailDataElite", "addModel");
            receiverAlias = getJsonData("CreateInternalMailDataElite", "receiverAlias"); /*مسمى الموجه اليه*/
            exportedNotes = getJsonData("CreateInternalMailDataElite", "exportedNotes");
            employeeName = getJsonData("CreateInternalMailDataElite", "employeeName"); /*اسم الموظف*/
            employeeDepartment = getJsonData("CreateInternalMailDataElite", "employeeDepartment"); /*اسم ادارة الموظف*/
        }
        @Test  (priority = 1)
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
        @Test (priority = 9)
        public void forwardToCustomEmployer (){  /*احاله الى موظف محدد */
            inboxPage.goToForwardTab();
            inboxPage.forwardToCustomEmp(employeeDepartment,employeeName,forwardType);
            SentPage sentPage = eliteHomePage.goToSent();
            sentPage.mailSentSearch(archiveNum);
            String department = sentPage.getDepartmentName();
            Assert.assertEquals(department, employeeDepartment);
        }
        @Test(priority = 11)
        public void checkAddGeoInfo (){
            inboxPage.addGeoInfo();
            Assert.assertTrue(inboxPage.getGeoInfo().isDisplayed());
        }
        @Test (priority = 12)
        public void addExportNotes (){
            inboxPage.exportNotes();
            inboxPage.addExportNotes(exportedNotes);
            Assert.assertEquals(inboxPage.getNotesContent(),exportedNotes);
        }
    }

