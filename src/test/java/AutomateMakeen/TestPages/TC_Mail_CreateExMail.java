package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.Mail_CreateExMail;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_Mail_CreateExMail extends TestInit {
    private Mail_CreateExMail mail_CreateExMail;

    static Faker faker = new Faker();
    static String etSubject = faker.name().title();
    //اسم معاملة التعميم
    static String etSubjectG = faker.name().title();

    static String etDocType = "اعادة المياة";
    static String etReceivcer = "مروان خليل";
    static String etSender = "مروان";
    static String etMainClass = "تصنيف رئيسي";
    static String etSubClass = "مولد";
    static String etPeriod = "6";
    static String etRecipient = "مروان خليل وظيفة تعاملات ادارة عامة وظيفة علي السطر الثاني";
    static String letterNum = faker.number().digits(8);
    static String letterDate = "2025/01/10";
    static String referalEtNum = "251576";
    static String civilId = "1122002211";
    // Setup method to initialize preconditions for tests in this class
    @BeforeClass(description = "Preconditions for each test in the class :" +
            "السماحية للدخول الي النظام : الأمانة الإلكترونية" +
            "الصلاحية للدخول الى البرنامج الرئيسي البريد ." +
            "الصلاحية للدخول الى البرنامج الفرعي انشاء بريد خارجي ." +
            "من خلال الضغط علي إنشاء بريد خارجي من ال Tree View  . ")
    public void setupClass() {
        lunchDriver();
        loginPage.goToLoginPage();
        qCMSHomePage = loginPage.loginUserWithoutRemMe(userID, userPasswd);
        mail_CreateExMail = contentAside.goToCreateExMail();
    }

    // Test method to create a valid external mail
    @Test
    public void tc_createValidExternalMail() throws InterruptedException {
        mail_CreateExMail.enteringTheSubjectOfMail(etSubject);
        mail_CreateExMail.setEtNum(letterNum);
        mail_CreateExMail.setEtDate(letterDate);
        mail_CreateExMail.setReferralMailNumber(referalEtNum);
        mail_CreateExMail.setDocTypeUsingControl(etDocType);
        mail_CreateExMail.setReceiverUsingControl(etReceivcer);
        mail_CreateExMail.setSenderUsingControl(etSender);
        mail_CreateExMail.setTreatClassificationUsingControl(etMainClass,etSubClass);
        mail_CreateExMail.clickLinkEt();
        mail_CreateExMail.setCivilId(civilId);
        Assert.assertTrue(mail_CreateExMail.addFile("file1","resourse/qr.pdf"));
        mail_CreateExMail.insertRecipient(etRecipient);
        mail_CreateExMail.clickSendConfirm();
        Assert.assertTrue(mail_CreateExMail.validateSuccessfulCreatingMail());
    }
    @Test
    public void tc_createValidExternalMailWithGeneralization() {
        mail_CreateExMail.enteringTheSubjectOfMail(etSubjectG);
        mail_CreateExMail.pressOnDeactivateReferralNumber();
        mail_CreateExMail.setTheEtGeneralized();
        mail_CreateExMail.setDocTypeUsingControl(etDocType);
        mail_CreateExMail.setReceiverUsingControl(etReceivcer);
        mail_CreateExMail.setSenderUsingControl(etSender);
        mail_CreateExMail.setTreatClassificationUsingControl(etMainClass, etSubClass);
        mail_CreateExMail.clickSendConfirm();
        Assert.assertTrue(mail_CreateExMail.validateSuccessfulCreatingMail());
        mail_CreateExMail.logOut();
    }
    
}