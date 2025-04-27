package AutomateMakeen.TestPages.TC_EmployeesOperations;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.CreateExternalMailPage;
import AutomateMakeen.Pages.OutboxMails;
import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.time.Duration;

public class AppointAndReassignEmployee_TC_Cycle extends TestInit {
    WebDriverWait exWait;

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
    /** preconditions :
     السماحية للدخول الي النظام .
     الصلاحية للدخول الى الموارد البشرية .
     الصلاحية للدخول الي عمليات علي الموظفين .
     الصلاحية للدخول تعيين  .
     صلاحية امكانية استدعاء بيانات الموظفين من منصة مسار .
     . mail_greg_date = 1
     * */
    @BeforeClass
    public void setupClass() throws FileNotFoundException {
        lunchDriver();
        loginPage.goToLoginPage();
        exWait = new WebDriverWait(driver, Duration.ofSeconds(8));
        homePage = loginPage.loginUserWithoutRemMe(userID2,userPasswd);

        nationNumberInMasar="5"+faker.number().digits(9);
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

    }
    @Test (priority = 1)
    public void verifyAppointEmployee()throws FileNotFoundException{   /*التحقق من تعيين موظف عن طريق انشاء معامله في الوارد*/
        //-------------------------اضافه معامله والحصول على رقمها-------------------------
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
        OutboxMails outboxMails = contentAside.goToExportedMail();
        outboxMails.getRecentlyAddedMail(subject);
        archiveNum = outboxMails.getMailData().get(5);
         //------------------------اضافه وظيفه تعاملات الكترونية------------------------
        electronicTransactionsJobsPage = contentAside.goToElectronicTransactionsJobsPage();
        String mandateJob1 = electronicTransactionsJobsPage.addNewJob(employeeDepartment, mandateJob);
        //------------------------ اضافه وظيفه اساسية ------------------------
        addJob = contentAside.goToJobOperations();
        addJob.goToAddJob();
        addJob.selectClassCode("00102");
        jobName = addJob.selectJobName("001022001");
        addJob.selectJobGradient("21520404");
        addJob.selectJobType(employeeType);
        addJob.selectJobDegree(degree);
        addJob.addJobNumber(jobNumber);
        addJob.insertDepartment("ادارة المهندسين");
        addJob.insertJobStatus("غير مستثناة");
        addJob.startDateSelect("1","2","2025");
        addJob.setRecNumberTextField(archiveNum);
        addJob.decNumber(decNumber);
        addJob.decDateSelect("2023/2/2");
        addJob.decSelectDirection(decDirection);
        addJob.saveJob();
        addJob.confirmJob();
        //---------------------------------تعيين موظف----------------------------------*/
        appointEmployee = contentAside.goToEmployeeOperations_AppointEmployee_FromInside();
        appointEmployee.enterAppointEmployee();
        appointEmployee.addNationNumber(nationNumberInMasar);
        appointEmployee.addFirstName(firstName);
        appointEmployee.addSecondName(secondName);
        appointEmployee.addThirdName(thirdName);
        appointEmployee.addFourthName(fourthName);
        appointEmployee.dateSelectionFromIcon(day,month,year);
        appointEmployee.addIBAN(IBAN);
        appointEmployee.selectNationality(nationality);
        appointEmployee.selectEmployeeType(employeeType);
        appointEmployee.selectMajorJob("-"+degree+"-"+jobNumber);
        appointEmployee.selectMandateJob(mandateJob1);
        appointEmployee.selectDegree(degree);
        appointEmployee.addOrganizationNumber(organizationNumber);
        appointEmployee.workDateSelect(day,month,year);
        appointEmployee.levelDateSelect(day,month,year);
        appointEmployee.firstJobDateSelect(day,month,year);
        appointEmployee.setRecNumberTextField(archiveNum);
        appointEmployee.decNumber(faker.number().digits(3));
        appointEmployee.decDateSelect(year+"/"+month+"/2"+day);
        appointEmployee.decSelectDirection(decDirection);
        appointEmployee.saveTheEmployee();
        Assert.assertEquals(appointEmployee.validateSuccessfulSavingEmployee(nationNumberInMasar),nationNumberInMasar);
    }



    @Test (priority = 2)
    public void verifyReassignmentEmployee () throws InterruptedException {  /*يجب تحديث رقم الموظف قبل تشغيل حاله الاختبار التي تختبر اعاده تعيين موظف*/
        reassignEmployee =contentAside.goToEmployeeOperations_ReassignEmployee_FromInside();
        reassignEmployee.searchForEmployee(empToReassign);
        reassignEmployee.enterReassignEmployee();
        String nationNumber = reassignEmployee.getNationalNumber().getText();
        IBAN="0380000000608010" + faker.number().digits(6);
        reassignEmployee.addIBAN(IBAN);
        reassignEmployee.selectNationality(nationality);
        reassignEmployee.selectEmployeeType(employeeType);
        reassignEmployee.selectAppointType(appointType);
        reassignEmployee.selectMajorJob(majorJob);
        mandateJob = faker.job().title();
        reassignEmployee.selectMandateJob(mandateJob);
        reassignEmployee.selectDegree(degree);
        appointEmployee.workDateSelect(day,month,"2025");
        reassignEmployee.setRecNumberTextField("271866");
        reassignEmployee.saveTheEmployee();
        Assert.assertEquals(reassignEmployee.validateSuccessfulSavingEmployee(nationNumber), nationNumber);
    }

}
