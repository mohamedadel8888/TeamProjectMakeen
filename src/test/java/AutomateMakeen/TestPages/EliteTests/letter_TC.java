package AutomateMakeen.TestPages.EliteTests;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.Elite.EliteHomePage;
import AutomateMakeen.Pages.Elite.SentPage;
import AutomateMakeen.Pages.HomePage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.time.Duration;

public class letter_TC extends TestInit {
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
    String forwardToName;
    String mainManager;

    @BeforeClass
    public void setUp () throws FileNotFoundException {
        /*pre condititons :
                                                                                                -ان يكون مسجل الدخول لدية صلاحية الدخول للنظام
                                                                                                - لدية صلاحية الشاشة الرئيسية للنخبة
                                                                                                - صلاحية بريد النخية
                                                                                                - صلاحية التأشير
                                                                                                - صلاحية التوقيع
        */
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(getJsonData("CreateInternalMailDataElite", "ID"), getJsonData("DelegateData", "validPassword"));
        homePage.goToElite();
        EliteHomePage eliteHomePage = new EliteHomePage(driver);
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
        forwardToName = getJsonData("CreateInternalMailDataElite", "forwardToName"); /*اسم الموجه اليه*/
        mainManager = getJsonData("CreateInternalMailDataElite", "mainManager");  /*الامين العام*/
    }
    @BeforeMethod
    public void setUp2 () throws FileNotFoundException {
        createInternalMailPage = eliteHomePage.goToCreateInternalMail();
        archiveNum = createInternalMailPage.createInternalMailForMe();
        inboxPage.lettersTab();
        inboxPage.selectDepartment(myDepartment);
        inboxPage.selectSefatLetter(sefatLetter);
        inboxPage.forwardTo(forwardToLetter);
        inboxPage.subject(mySubject);
        inboxPage.addModel(addModel);
        inboxPage.send();
    }
    @Test (priority = 1 )
    public void signLetter (){ /* توقيع الخطاب*/
        inboxPage.lettersTab();
        inboxPage.goToSign();
        inboxPage.signConfirm();
        SentPage sentPage = eliteHomePage.goToSent();
        sentPage.mailSentSearch(archiveNum);
        String recieverName = sentPage.getRecieverName();
        Assert.assertEquals(recieverName, forwardToName);
    }
    @Test (priority = 2)
    public void viceLetter (){   /*تأشير الخطاب*/
        inboxPage.lettersTab();
        inboxPage.goToVice();
        inboxPage.confirmVice();
        SentPage sentPage = eliteHomePage.goToSent();
        sentPage.mailSentSearch(archiveNum);
        String recieverName = sentPage.getRecieverName();
        Assert.assertEquals(recieverName,mainManager);
    }




}
