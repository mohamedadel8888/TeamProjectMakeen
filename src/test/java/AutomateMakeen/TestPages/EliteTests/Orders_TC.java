package AutomateMakeen.TestPages.EliteTests;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.Elite.EliteHomePage;
import AutomateMakeen.Pages.Elite.SentPage;
import AutomateMakeen.Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.time.Duration;

public class Orders_TC extends TestInit {
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
    String delegateName;

    @BeforeClass
    public void setUp () throws FileNotFoundException {
        /*pre condititons :
                                                                                                -ان يكون مسجل الدخول لدية صلاحية الدخول للنظام
                                                                                                - لدية صلاحية الشاشة الرئيسية للنخبة
                                                                                                - صلاحية بريد النخية
                                                                                                - صلاحية التأشير
                                                                                                - صلاحية التوقيع بأمر من سعادة الامير
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
        delegateName = getJsonData("CreateInternalMailDataElite", "delegateName"); /*اسم المفوض عنه*/
    }
    @BeforeMethod
    public void setUp2 () {
        createInternalMailPage = eliteHomePage.goToCreateInternalMail();
        archiveNum = createInternalMailPage.createInternalMailForMe();
        inboxPage.offersTab();
        inboxPage.selectDepartment(myDepartment);
        inboxPage.forwardTo(forwardToOffer);
        inboxPage.receiverAlias(receiverAlias);
        inboxPage.subject(mySubject);
        inboxPage.addModel(addModel);
        inboxPage.send();
    }
    @Test (priority = 1)
    public void checkCreateOffer () { /*توقيع عرض*/
        inboxPage.offersTab();
        inboxPage.goToSign();
        inboxPage.signConfirm();
        SentPage sentPage = eliteHomePage.goToSent();
        sentPage.mailSentSearch(archiveNum);
        String recieverName = sentPage.getRecieverName();
        Assert.assertEquals(recieverName, delegateName);
    }
    @Test (priority = 2)  /*توقيع العرض بتفويض */
    public void verifySignOfferWith_Delegate (){
        inboxPage.offersTab();
        inboxPage.signWithDelegate(delegateName);
        SentPage sentPage = eliteHomePage.goToSent();
        sentPage.mailSentSearch(archiveNum);
        WebElement signText = driver.findElement(By.cssSelector("body > main:nth-child(2) > div:nth-child(3) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > p:nth-child(1)"));
        Assert.assertTrue(signText.getText().contains("تم توقيع العرض"));
    }
    @Test (priority = 3)
    public void viceOffer (){   /*تأشير العرض*/
        inboxPage.offersTab();
        inboxPage.goToVice();
        inboxPage.confirmVice();
        SentPage sentPage = eliteHomePage.goToSent();
        sentPage.mailSentSearch(archiveNum);
        String recieverName = sentPage.getRecieverName();
        Assert.assertEquals(recieverName,mainManager);
    }
    @Test (priority = 4)
    public void verifyViceOffer_ReferToAgent(){   /*تأشير العرض واحالتة الى الوكيل*/
        inboxPage.offersTab();
        inboxPage.marking_ReferToAgent();
        SentPage sentPage = eliteHomePage.goToSent();
        sentPage.mailSentSearch(archiveNum);
        Assert.assertTrue(sentPage.getDirecting().contains("لتوقيع العرض"));
    }
    @Test (priority = 5)
    public void verifyViceLetter_ReferToCustomEmp(){   /*تأشير العرض واحالتة الى موظف محدد*/
        inboxPage.offersTab();
        inboxPage.marking_ReferToCustomEmp();
        SentPage sentPage = eliteHomePage.goToSent();
        sentPage.mailSentSearch(archiveNum);
        Assert.assertTrue(sentPage.getRecieverName().contains("حسين حسن كمال عبدالقادر"));
    }
}
