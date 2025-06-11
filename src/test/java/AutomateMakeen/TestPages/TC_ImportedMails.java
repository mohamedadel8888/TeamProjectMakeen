package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.CreateExternalMailPage;
import AutomateMakeen.Pages.HomePage;
import AutomateMakeen.Pages.ImportedMails;
import AutomateMakeen.Pages.OutboxMails;
import org.testng.Assert;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static AutomateMakeen.TestPages.TC_OutboxMails.importNumber;


public class TC_ImportedMails extends TestInit {

    String nationNumberInMasar ;
    String nationNumberNotInMasar;
    String notInMasarValidation;
    String notValidNationNumber;
    String notValidNumberValidation;
    String biggerThan10Numbers;

    String firstName;
    String secondName;
    String thirdName;
    String fourthName;

    String firstNameNotValid;
    String secondNameNotValid;
    String thirdNameNotValid;
    String fourthNameNotValid;

    String day;
    String month;
    String year;
    String dayNotValid;
    String monthNotValid;

    String IBAN;
    String IBANNotValid;

    String nationality;
    String noNationality;

    String employeeType;
    String majorJob;
    String noMinorJob;
    String noMandateJob;
    String mandateJob;
    String degree;
    String organizationNumber;
    String inValidOrganizationNumber;

    String levelDateValidationTex;
    String workDateValidationTex;

    String sucessSavingEmpMsg;
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
    String jobNumber;
    String decNumber;
    String jobName;
    Faker faker = new Faker();



    ImportedMails importedMails;
    @BeforeClass(description = "Preconditions for each test in the class :" +
            "السماحية للدخول الي النظام : الأمانة الإلكترونية" +
            "الصلاحية للدخول الى البرنامج الرئيسي البريد ." +
            "الصلاحية للدخول الى البرنامج الفرعي انشاء بريد خارجي ." +
            "من خلال الضغط علي إنشاء بريد خارجي من ال Tree View  . ")
    public void setupClass() throws FileNotFoundException {





        lunchDriver();
        loginPage.goToLoginPage();
        loginPage.loginUserWithoutRemMe("1002669", "24602460");



        nationNumberInMasar ="5"+faker.number().digits(9);
        IBAN="0380000000608010" + faker.number().digits(6);
        nationNumberNotInMasar=faker.number().digits(10);
        subject = faker.lorem().sentence(2);
        notInMasarValidation=(getJsonData("EmployeeOperations","notInMasarValidation"));
        notValidNationNumber=(getJsonData("EmployeeOperations","notValidNationNumber"));
        notValidNumberValidation = (getJsonData("EmployeeOperations","notValidNumberValidation"));
        biggerThan10Numbers = (getJsonData("EmployeeOperations","biggerThan10Numbers"));
        firstName = (getJsonData("EmployeeOperations","firstName"));
        secondName = (getJsonData("EmployeeOperations","secondName"));
        thirdName = (getJsonData("EmployeeOperations","thirdName"));
        fourthName = (getJsonData("EmployeeOperations","fourthName"));
        firstNameNotValid = (getJsonData("EmployeeOperations","firstNameNotValid"));
        secondNameNotValid = (getJsonData("EmployeeOperations","secondNameNotValid"));
        thirdNameNotValid = (getJsonData("EmployeeOperations","thirdNameNotValid"));
        fourthNameNotValid = (getJsonData("EmployeeOperations","fourthNameNotValid"));
        day = (getJsonData("EmployeeOperations","day"));
        month = (getJsonData("EmployeeOperations","month"));
        dayNotValid = (getJsonData("EmployeeOperations","dayNotValid"));
        monthNotValid = (getJsonData("EmployeeOperations","monthNotValid"));
        year = (getJsonData("EmployeeOperations","year"));
        IBANNotValid = (getJsonData("EmployeeOperations","IBANNotValid"));
        nationality = (getJsonData("EmployeeOperations","nationality"));
        noNationality = (getJsonData("EmployeeOperations","noNationality"));
        employeeType = (getJsonData("EmployeeOperations","employeeType"));
        majorJob = (getJsonData("EmployeeOperations","majorJob"));
        noMinorJob = (getJsonData("EmployeeOperations","noMajorJob"));
        mandateJob = faker.job().title();
        noMandateJob = (getJsonData("EmployeeOperations","noMandateJob"));
        degree = (getJsonData("EmployeeOperations","degree"));
        organizationNumber = faker.number().digits(7);
        inValidOrganizationNumber = (getJsonData("EmployeeOperations","inValidOrganizationNumber"));
        workDateValidationTex = (getJsonData("EmployeeOperations","workDateValidationTex"));
        levelDateValidationTex = (getJsonData("EmployeeOperations","levelDateValidationTex"));
        sucessSavingEmpMsg = (getJsonData("EmployeeOperations","sucessSavingEmpMsg"));
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
        jobNumber = faker.number().digits(4);
        decNumber = faker.number().digits(3);


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
