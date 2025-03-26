package AutomateMakeen.TestPages.EliteTests;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.Elite.EliteHomePage;
import AutomateMakeen.Pages.Elite.SentPage;
import AutomateMakeen.Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
        public void verifyAttachmentPage () {  /***************************************************/
            inboxPage.goToAttachments();
            ex.until(ExpectedConditions.visibilityOf(inboxPage.getAttachmentsTab()));
            inboxPage.addAttach("src\\test\\resources\\UplodedImage.png","file name");
            Assert.assertTrue(inboxPage.attachAdded());
        }
        @Test (priority = 4)
        public void forwardToCustomEmployer (){  /*احاله الى موظف محدد */
            inboxPage.goToForwardTab();
            inboxPage.forwardToCustomEmp(employeeDepartment,employeeName,forwardType);
            SentPage sentPage = eliteHomePage.goToSent();
            sentPage.mailSentSearch(archiveNum);
            String department = sentPage.getDepartmentName();
            Assert.assertEquals(department, employeeDepartment);
        }
        @Test(priority = 5)
        public void checkAddGeoInfo (){  /*التحقق من فتح الخريطة*/
            inboxPage.addGeoInfo();
            Assert.assertTrue(inboxPage.getGeoInfo().isDisplayed());
        }
        @Test (priority = 6)  /*  ملاحظات للتصدير */
        public void addExportNotes (){
            inboxPage.exportNotes();
            inboxPage.addExportNotes(exportedNotes);
            Assert.assertEquals(inboxPage.getNotesContent(),exportedNotes);
        }
        @Test (priority =7)
        public void verifyTempSave (){  /* التحقق من الحفظ المؤقت*/
            inboxPage.mailInboxSearch(archiveNum);
            inboxPage.tempSave(exportedNotes);
            SentPage sentPage = eliteHomePage.goToSent();
            sentPage.mailSentSearch(archiveNum);
            Assert.assertTrue(sentPage.getDirecting().contains("حفظ مؤقت"));
        }
        @Test (priority = 8)
        public void signOfferWithLetter(){ /* توقيع العرض وتأشير الخطاب معا */
            inboxPage.mailInboxSearch(archiveNum);
            inboxPage.lettersTab();
            inboxPage.selectDepartment(myDepartment);
            inboxPage.selectSefatLetter(sefatLetter);
            inboxPage.forwardTo(forwardToLetter);
            inboxPage.subject(mySubject);
            inboxPage.addModel(addModel);
            inboxPage.send();
            inboxPage.offersTab();
            inboxPage.selectDepartment(myDepartment);
            inboxPage.forwardTo(forwardToOffer);
            inboxPage.receiverAlias(receiverAlias);
            inboxPage.subject(mySubject);
            inboxPage.addModel(addModel);
            inboxPage.send();
            inboxPage.signOfferAndViceLetterTogether();
            SentPage sentPage = eliteHomePage.goToSent();
            sentPage.mailSentSearch(archiveNum);
            WebElement signText = driver.findElement(By.cssSelector("body > main:nth-child(2) > div:nth-child(3) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > p:nth-child(1)"));
            Assert.assertTrue(signText.getText().contains("تم توقيع العرض وتأشير الخطاب"));
        }
        @Test (priority = 9)
        public void signMozakeraWithLetter (){ /*توقيع المذكرة و تأشير الخطاب معا */
            inboxPage.mailInboxSearch(archiveNum);
            inboxPage.lettersTab();
            inboxPage.selectDepartment(myDepartment);
            inboxPage.selectSefatLetter(sefatLetter);
            inboxPage.forwardTo(forwardToLetter);
            inboxPage.subject(mySubject);
            inboxPage.addModel(addModel);
            inboxPage.send();
            inboxPage.internalMemoTab();
            inboxPage.selectDepartment(myDepartment);
            inboxPage.setChkBoxDesc();
            inboxPage.forwardTo(forwardToOffer);
            inboxPage.subject(mySubject);
            inboxPage.addModel(addModel);
            inboxPage.send();
            inboxPage.internalMemoTab();
            inboxPage.signOfferAndViceLetterTogether();
            SentPage sentPage = eliteHomePage.goToSent();
            sentPage.mailSentSearch(archiveNum);
            WebElement signText = driver.findElement(By.cssSelector("body > main:nth-child(2) > div:nth-child(3) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > p:nth-child(1)"));
            Assert.assertTrue(signText.getText().contains("تم تأشير الخطاب و توقيع المذكرة الداخلية"));
        }
        @Test (priority = 10)
        public void signLetterAndSignMomoTogether (){  /*التحقق من توقيع الخطاب والمذكرة معا  */
            inboxPage.mailInboxSearch(archiveNum);
            inboxPage.internalMemoTab();
            inboxPage.selectDepartment(myDepartment);
            inboxPage.setChkBoxDesc();
            inboxPage.forwardTo(forwardToOffer);
            inboxPage.subject(mySubject);
            inboxPage.addModel(addModel);
            inboxPage.send();
            inboxPage.lettersTab();
            inboxPage.selectDepartment(myDepartment);
            inboxPage.selectSefatLetter(sefatLetter);
            inboxPage.forwardTo(forwardToLetter);
            inboxPage.subject(mySubject);
            inboxPage.addModel(addModel);
            inboxPage.send();
            inboxPage.lettersTab();
            inboxPage.signLetterAndSignMomoTogether();
            SentPage sentPage = eliteHomePage.goToSent();
            sentPage.mailSentSearch(archiveNum);
            WebElement signText = driver.findElement(By.cssSelector("body > main:nth-child(2) > div:nth-child(3) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > p:nth-child(1)"));
            Assert.assertTrue(signText.getText().contains("تم توقيع الخطاب و توقيع المذكرة الداخلية"));
        }
        @Test (priority = 11)
        public void viceMomoAndViceLetterTogether (){  /*التحقق من تأشير الخطاب والمذكرة معا*/
            inboxPage.mailInboxSearch(archiveNum);
            inboxPage.internalMemoTab();
            inboxPage.selectDepartment(myDepartment);
            inboxPage.setChkBoxDesc();
            inboxPage.forwardTo(forwardToOffer);
            inboxPage.subject(mySubject);
            inboxPage.addModel(addModel);
            inboxPage.send();
            inboxPage.lettersTab();
            inboxPage.selectDepartment(myDepartment);
            inboxPage.selectSefatLetter(sefatLetter);
            inboxPage.forwardTo(forwardToLetter);
            inboxPage.subject(mySubject);
            inboxPage.addModel(addModel);
            inboxPage.send();
            inboxPage.lettersTab();
            inboxPage.viceLetterAndMomoTogether();
            SentPage sentPage = eliteHomePage.goToSent();
            sentPage.mailSentSearch(archiveNum);
            WebElement signText = driver.findElement(By.cssSelector("body > main:nth-child(2) > div:nth-child(3) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > p:nth-child(1)"));
            Assert.assertTrue(signText.getText().contains("تم تأشير الخطاب و تأشير المذكرة الداخلية"));
        }
        @Test (priority = 12)
        public void viceOfferAndViceMomoTogether(){ /*التحقق من تأشير العرض وتأشير المذكرة معا */
            inboxPage.mailInboxSearch(archiveNum);
            inboxPage.internalMemoTab();
            inboxPage.selectDepartment(myDepartment);
            inboxPage.setChkBoxDesc();
            inboxPage.forwardTo(forwardToOffer);
            inboxPage.subject(mySubject);
            inboxPage.addModel(addModel);
            inboxPage.send();
            inboxPage.offersTab();
            inboxPage.selectDepartment(myDepartment);
            inboxPage.forwardTo(forwardToOffer);
            inboxPage.receiverAlias(receiverAlias);
            inboxPage.subject(mySubject);
            inboxPage.addModel(addModel);
            inboxPage.send();
            inboxPage.viceOfferAndMomoTogether();
            SentPage sentPage = eliteHomePage.goToSent();
            sentPage.mailSentSearch(archiveNum);
            WebElement signText = driver.findElement(By.cssSelector("body > main:nth-child(2) > div:nth-child(3) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > p:nth-child(1)"));
            Assert.assertTrue(signText.getText().contains("تم تأشير العرض وتأشير المذكرة"));
        }
    @Test (priority = 13)
    public void viceOfferAndViceLetterTogether(){ /*التحقق من تأشير العرض وتأشير الخطاب معا */
        inboxPage.mailInboxSearch(archiveNum);
        inboxPage.lettersTab();
        inboxPage.selectDepartment(myDepartment);
        inboxPage.selectSefatLetter(sefatLetter);
        inboxPage.forwardTo(forwardToLetter);
        inboxPage.subject(mySubject);
        inboxPage.addModel(addModel);
        inboxPage.send();
        inboxPage.offersTab();
        inboxPage.selectDepartment(myDepartment);
        inboxPage.forwardTo(forwardToOffer);
        inboxPage.receiverAlias(receiverAlias);
        inboxPage.subject(mySubject);
        inboxPage.addModel(addModel);
        inboxPage.send();
        inboxPage.offersTab();
        inboxPage.viceOfferAndLetterTogether();
        SentPage sentPage = eliteHomePage.goToSent();
        sentPage.mailSentSearch(archiveNum);
        WebElement signText = driver.findElement(By.cssSelector("body > main:nth-child(2) > div:nth-child(3) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > p:nth-child(1)"));
        Assert.assertTrue(signText.getText().contains("تم تأشير العرض وتأشير الخطاب"));
    }
    @Test (priority = 14)
    public void verifyRedirectOfferWithSignLetter(){  /*التحقق من توجيه العرض مع توقيع الخطاب*/
        inboxPage.mailInboxSearch(archiveNum);
        inboxPage.lettersTab();
        inboxPage.selectDepartment(myDepartment);
        inboxPage.selectSefatLetter(sefatLetter);
        inboxPage.forwardTo(forwardToLetter);
        inboxPage.subject(mySubject);
        inboxPage.addModel(addModel);
        inboxPage.send();
        inboxPage.offersTab();
        inboxPage.selectDepartment(myDepartment);
        inboxPage.forwardTo(forwardToOffer);
        inboxPage.receiverAlias(receiverAlias);
        inboxPage.subject(mySubject);
        inboxPage.addModel(addModel);
        inboxPage.send();
        inboxPage.signOfferAndViceLetterTogether();
        inboxPage.mailInboxSearch(archiveNum);
        inboxPage.offersTab();
        inboxPage.redirectOfferWithSignLetter(exportedNotes);
        SentPage sentPage = eliteHomePage.goToSent();
        sentPage.mailSentSearch(archiveNum);
        WebElement signText = driver.findElement(By.cssSelector("body > main:nth-child(2) > div:nth-child(3) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > p:nth-child(1)"));
        Assert.assertTrue(signText.getText().contains("تم توجيه العرض و توقيع الخطاب"));
    }
    @Test (priority = 15)
    public void verifyRedirectOfferWithSignMomo(){  /*التحقق من توجيه العرض مع توقيع المذكرة*/
        inboxPage.mailInboxSearch(archiveNum);
        inboxPage.internalMemoTab();
        inboxPage.selectDepartment(myDepartment);
        inboxPage.setChkBoxDesc();
        inboxPage.forwardTo(forwardToOffer);
        inboxPage.subject(mySubject);
        inboxPage.addModel(addModel);
        inboxPage.send();
        inboxPage.offersTab();
        inboxPage.selectDepartment(myDepartment);
        inboxPage.forwardTo(forwardToOffer);
        inboxPage.receiverAlias(receiverAlias);
        inboxPage.subject(mySubject);
        inboxPage.addModel(addModel);
        inboxPage.send();
        inboxPage.signOfferAndViceMomoTogether();
        inboxPage.mailInboxSearch(archiveNum);
        inboxPage.offersTab();
        inboxPage.redirectOfferWithSignMomo(exportedNotes);
        SentPage sentPage = eliteHomePage.goToSent();
        sentPage.mailSentSearch(archiveNum);
        WebElement signText = driver.findElement(By.cssSelector("body > main:nth-child(2) > div:nth-child(3) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > p:nth-child(1)"));
        Assert.assertTrue(signText.getText().contains("تم توجيه العرض و توقيع المذكرة"));
    }



}

