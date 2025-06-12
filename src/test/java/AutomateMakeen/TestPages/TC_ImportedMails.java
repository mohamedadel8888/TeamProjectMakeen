package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.CreateExternalMailPage;
import AutomateMakeen.Pages.HomePage;
import AutomateMakeen.Pages.ImportedMails;
import AutomateMakeen.Pages.OutboxMails;
import org.testng.Assert;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static AutomateMakeen.TestPages.TC_OutboxMails.importNumber;


public class TC_ImportedMails extends TestInit {


    String biggerThan10Numbers;
    String subject;
    String exportedNotes;
    String myDepartment;
    String sefatLetter;
    String forwardToOffer;
    String forwardToLetter;
    String forwardType;
    String forwardName;
    String addModel;
    String receiverAlias;
    String employeeName;
    String employeeDepartment;
    String empToReassign;
    String appointType;
    String decDirection;
    Faker faker = new Faker();

    ImportedMails importedMails;
    @BeforeMethod(description = "Preconditions for each test in the class :" +
            "السماحية للدخول الي النظام : الأمانة الإلكترونية" +
            "الصلاحية للدخول الى البرنامج الرئيسي البريد ." +
            "الصلاحية للدخول الى البرنامج الفرعي انشاء بريد خارجي ." +
            "من خلال الضغط علي إنشاء بريد خارجي من ال Tree View  . ")
    public void setupClass() throws FileNotFoundException {
        lunchDriver();
        loginPage.goToLoginPage();
        loginPage.loginUserWithoutRemMe("1002669", "24602460");

        subject = faker.lorem().sentence(2);

        biggerThan10Numbers = (getJsonData("EmployeeOperations","biggerThan10Numbers"));
        myDepartment = getJsonData("CreateInternalMailDataElite", "deptName"); /*من الادارة*/
        sefatLetter = getJsonData("CreateInternalMailDataElite", "sefatLetter"); /* صفة الخطاب*/
        // mySubject = getJsonData("CreateInternalMailDataElite", "mailSubject"); /*الموضوع*/
        forwardToOffer = getJsonData("CreateInternalMailDataElite", "forwardNumOffer");
        forwardToLetter = getJsonData("CreateInternalMailDataElite", "forwardNumLetter");
        forwardType = getJsonData("CreateInternalMailDataElite", "forwardType");
        forwardName = getJsonData("CreateInternalMailDataElite", "forwardName"); /*اسم ادارة الموجه اليه*/
        addModel = getJsonData("CreateInternalMailDataElite", "addModel");
        receiverAlias = getJsonData("CreateInternalMailDataElite", "receiverAlias"); /*مسمى الموجه اليه*/
        empToReassign = getJsonData("EmployeeOperations", "empToReassign"); /*رقم موظف ليس على رأس العمل*/
        appointType = getJsonData("EmployeeOperations","appointType");
        decDirection = getJsonData("EmployeeOperations","decDirection"); /*جهه القرار*/



        CreateExternalMailPage createExternalMailPage = contentAside.goToCreateExternalMail();
        createExternalMailPage.clearAllField();
        createExternalMailPage.pressOnNumberOfStorage();
        createExternalMailPage.enteringTheSubjectOfMail(subject);
        createExternalMailPage.setDocTypeUsingControl(getJsonData("ValidExternalMailData","docTypeNum"));
        createExternalMailPage.setReceiverUsingControl(getJsonData("ValidExternalMailData", "receiverName"));
        createExternalMailPage.setSenderUsingControl(getJsonData("ValidExternalMailData","senderName"));
        createExternalMailPage.setTreatClassificationUsingControl(getJsonData("ValidExternalMailData","mainClass"),getJsonData("ValidExternalMailData","treatClassification"));
        createExternalMailPage.insertRecipient(getJsonData("ValidExternalMailData","recipient2"));
        exportedNotes = getJsonData("CreateInternalMailDataElite", "exportedNotes");
        employeeName = getJsonData("CreateInternalMailDataElite", "employeeName");
        employeeDepartment = getJsonData("CreateInternalMailDataElite", "employeeDepartment");
        createExternalMailPage.pressOnDeactivateReferralNumber();
        createExternalMailPage.clickSendConfirm();
        createExternalMailPage.validateSuccessfulCreatingMail();
        importedMails = contentAside.goToImportedMail();
        importedMails.getRecentlyAddedMail(subject);


        //OutboxMails outboxMails = contentAside.goToExportedMail();
        //outboxMails.getRecentlyAddedMail(subject);
        //archiveNum = outboxMails.getMailData().get(5);
    }
    @Test (priority = 1)
    public void tc_letterCheck(){
        importedMails.goToLetterTab();
        importedMails.selectCoverLetterType("بدون");
        importedMails.enterSubject("hello my team");
        importedMails.selectSecretDegree("عام");
        importedMails.selectDirectTo("1");
        importedMails.enterBodyLetter("dear mohamed you can make this order by the information mention in attachments");
        importedMails.signLetter();
    }


    @Test(priority = 2)
    public void tc_validateCreatedMailAddedToImportedMail() throws Exception{
        importedMails.getRecentlyAddedMail(getJsonData("ValidExternalMailData","subject"));
        List<String> mailData =importedMails.getMailData();
        Assert.assertEquals(mailData.get(0),getJsonData("ValidExternalMailData","subject"));
        Assert.assertEquals(mailData.get(1),"محمد أحمد أحمد علي");
        Assert.assertEquals(mailData.get(2),getJsonData("ValidExternalMailData","senderName"));
        Assert.assertEquals(mailData.get(3),getJsonData("ValidExternalMailData","docTypeName"));
        Assert.assertEquals(mailData.get(4),importNumber);
    }
}
