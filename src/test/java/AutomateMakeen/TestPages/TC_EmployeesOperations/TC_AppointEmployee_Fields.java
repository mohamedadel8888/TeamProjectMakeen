package AutomateMakeen.TestPages.TC_EmployeesOperations;

import AutomateMakeen.BaseTest.TestInit;
import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TC_AppointEmployee_Fields extends TestInit {
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
        homePage = loginPage.loginUserWithoutRemMe(userID,userPasswd);
        appointEmployee = contentAside.goToEmployeeOperations_AppointEmployee();
        exWait = new WebDriverWait(driver, Duration.ofSeconds(8));
        Faker faker = new Faker();
        nationNumberInMasar=faker.number().digits(10);
        IBAN="0380000000608010" + faker.number().digits(6);
        nationNumberNotInMasar=faker.number().digits(10);
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
        mandateJob = (getJsonData("EmployeeOperations","mandateJob"));
        noMandateJob = (getJsonData("EmployeeOperations","noMandateJob"));
        degree = (getJsonData("EmployeeOperations","degree"));
        organizationNumber = (getJsonData("EmployeeOperations","organizationNumber"));
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

    }

    @Test (priority = 1)
    public void verifyEmployeeOperations (){   /*التحقق من فتح صفحه عمليات على الموظفين*/
        Assert.assertTrue(appointEmployee.getEmpOperations().isDisplayed());
    }
    @Test (priority = 2)
    public void verifyAddEmployeeTab (){   /*التحقق من الدخول الى شاشة تعيين موظف*/
        appointEmployee.enterAppointEmployee();
        Assert.assertTrue(appointEmployee.getDivNewEmployee().isDisplayed());
    }
    @Test (priority = 3)
    public void verifyNotValidNationNumber (){ /*التحقق من فالديشن رقم الهوية يبدأ بصفر*/
        appointEmployee.enterAppointEmployee();
        appointEmployee.addNationNumber(notValidNationNumber);
        exWait.until(ExpectedConditions.elementToBeClickable(appointEmployee.masarBtn()));
        appointEmployee.masarBtn().click();
        Assert.assertEquals(notValidNumberValidation, appointEmployee.getValidationMessage());
    }
    @Test (priority = 4)
    public void verifyNationNumberBiggerThan10Num (){ /*التحقق من ادخال رقم الهوية  اكثر من 10 ارقام*/
        //appointEmployee.enterAppointEmployee();
        appointEmployee.addNationNumber(biggerThan10Numbers);
        Assert.assertNotEquals(biggerThan10Numbers, appointEmployee.getNationalNumber().getText());
    }
    @Test (priority = 5)
    public void verifyNationNumberNotInMasar (){  /*التحقق من فالديشن رقم الهوية غير موجود في مسار*/
        //appointEmployee.enterAppointEmployee();
        appointEmployee.addNationNumber(nationNumberNotInMasar);
        exWait.until(ExpectedConditions.elementToBeClickable(appointEmployee.masarBtn()));
        appointEmployee.masarBtn().click();
        Assert.assertEquals(notInMasarValidation, appointEmployee.getValidationMessage());
    }
    @Test (priority = 6)
    public void verifyFullNameNotValid (){ /*التحقق من ادخال الاسم غير صحيح*/
        //appointEmployee.enterAppointEmployee();
        appointEmployee.addFirstName(firstNameNotValid);
        Assert.assertEquals(appointEmployee.getFirstName(),"");
        appointEmployee.addSecondName(secondNameNotValid);
        Assert.assertEquals(appointEmployee.getSecondName(),"");
        appointEmployee.addThirdName(thirdNameNotValid);
        Assert.assertEquals(appointEmployee.getThirdName(),"");
        appointEmployee.addFourthName(fourthNameNotValid);
        Assert.assertEquals(appointEmployee.getFourthName(),"");
    }
    @Test (priority = 7)
    public void verifyFullNameValid (){ /*التحقق من ادخال الاسم صحيح*/
        //appointEmployee.enterAppointEmployee();
        appointEmployee.addFirstName(firstName);
        Assert.assertEquals(appointEmployee.getFirstName(),firstName);
        appointEmployee.addSecondName(secondName);
        Assert.assertEquals(appointEmployee.getSecondName(),secondName);
        appointEmployee.addThirdName(thirdName);
        Assert.assertEquals(appointEmployee.getThirdName(),thirdName);
        appointEmployee.addFourthName(fourthName);
        Assert.assertEquals(appointEmployee.getFourthName(),fourthName);
    }
    @Test (priority = 8)
    public void verifyDateOfBirthNotValid (){ /*التحقق من ادخال التاريخ غير صحيح*/
        //appointEmployee.enterAppointEmployee();
        appointEmployee.dateSelect(dayNotValid,monthNotValid,year);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");  // Adjust format as needed
        String expectedDate = today.format(formatter);
        Assert.assertEquals(appointEmployee.getDateText(),expectedDate);
    }
    @Test (priority = 9)
    public void verifyDateOfBirthValid (){ /*التحقق من ادخال التاريخ صحيح*/
        //appointEmployee.enterAppointEmployee();
        appointEmployee.dateSelectionFromIcon(day,month,year);
        Assert.assertEquals(appointEmployee.getDateText(),year+"/0"+month+"/0"+day);
    }
    @Test (priority = 10)
    public void verifyIBAN_NotValid(){ /*التحقق من ادخال رقم البطاقة البنكية اكبر من  30*/
        //appointEmployee.enterAppointEmployee();
        appointEmployee.addIBAN(IBANNotValid);
        Assert.assertNotEquals(appointEmployee.getIBAN(),IBANNotValid);
    }
    @Test (priority = 11)
    public void verifyIBAN_Valid (){ /*التحقق من ادخال رقم البطاقة البنكية صحيح*/
        //appointEmployee.enterAppointEmployee();
        appointEmployee.addIBAN(IBAN);
        Assert.assertEquals(appointEmployee.getIBAN(),IBAN);
    }

    @Test (priority = 12)
    public void verifySelectNationality ()  {
        //appointEmployee.enterAppointEmployee();
        appointEmployee.selectNationality(nationality);
        Assert.assertEquals(appointEmployee.getNationality(),noNationality);
    }
    @Test (priority = 13)
    public void verifyEmployeeType ()  {
        appointEmployee.selectEmployeeType(employeeType);
        Assert.assertEquals(appointEmployee.getEmployeeType(),employeeType);
    }
    @Test (priority =14)
    public void verifyMajorJob (){
        appointEmployee.selectMajorJob(majorJob);
        Assert.assertEquals(appointEmployee.getMajorJob(),majorJob);
    }
    @Test (priority = 15)
    public void verifyMandateJob (){
        appointEmployee.selectMandateJob(mandateJob);
        Assert.assertEquals(appointEmployee.getMandateJob(),mandateJob);
    }
    @Test (priority = 16)
    public void verifyDegree ()  {
        //appointEmployee.enterAppointEmployee();
        appointEmployee.selectDegree(degree);
        Assert.assertEquals(appointEmployee.getDegree(),degree);
    }
    @Test (priority = 17)
    public void verifyOrganizationNumber(){
        //appointEmployee.enterAppointEmployee();
        appointEmployee.addOrganizationNumber(organizationNumber);
        Assert.assertEquals(appointEmployee.getOrganizationNumber(),organizationNumber);
    }
    @Test (priority = 18)
    public void verifyInValidOrganizationNumber(){
        //appointEmployee.enterAppointEmployee();
        appointEmployee.addOrganizationNumber(inValidOrganizationNumber);
        Assert.assertEquals(appointEmployee.getOrganizationNumber(),organizationNumber);
    }
    @Test (priority = 19)
    public void verifyWorkDateNotValid (){ /*التحقق من ادخال تاريخ التعيين في الجهه غير صحيح*/
        //appointEmployee.enterAppointEmployee();
        appointEmployee.workDateSelect(dayNotValid,monthNotValid,year);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");  // Adjust format as needed
        String expectedDate = today.format(formatter);
        Assert.assertEquals(appointEmployee.getWorkDateText(),expectedDate);
    }
    @Test (priority = 20)
    public void verifyWorkDateValidation(){  /* التحقق من ظهور الفالديشن */
        //appointEmployee.enterAppointEmployee();
        LocalDate today = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String actualDay = today.format(formatter);
        appointEmployee.workDateSelect(actualDay);
        Assert.assertEquals(appointEmployee.getWorkdateValidationText(),workDateValidationTex);
    }
    @Test (priority = 21)
    public void verifyWorkDateValid (){ /*التحقق من ادخال تاريخ التعيين في الجهه صحيح*/
        //appointEmployee.enterAppointEmployee();
        appointEmployee.workDateSelect(day,month,year);
        Assert.assertEquals(appointEmployee.getWorkDateText(),year+"/0"+month+"/0"+day);
    }

    @Test (priority = 22)
    public void verifyLevelDateSelectNotValid (){ /*التحقق من ادخال تاريخ التعيين على مرتبة غير صحيح*/
        //appointEmployee.enterAppointEmployee();
        appointEmployee.levelDateSelect(dayNotValid,monthNotValid,year);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");  // Adjust format as needed
        String expectedDate = today.format(formatter);
        Assert.assertEquals(appointEmployee.getLevelDateText(),expectedDate);
    }
    @Test (priority = 23)
    public void verifyLevelDateValidation(){  /* التحقق من ظهور الفالديشن */
        //appointEmployee.enterAppointEmployee();
        LocalDate today = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String actualDay = today.format(formatter);
        appointEmployee.levelDateSelect(actualDay);
        Assert.assertEquals(appointEmployee.getLevelDateValidationText(),levelDateValidationTex);
    }
    @Test (priority = 24)
    public void verifyLevelDateSelectValid (){ /*التحقق من ادخال تاريخ التعيين على مرتبة*/
        //appointEmployee.enterAppointEmployee();
        appointEmployee.levelDateSelect(day,month,year);
        Assert.assertEquals(appointEmployee.getLevelDateText(),year+"/0"+month+"/0"+day);
    }

    @Test (priority = 25)
    public void verifyFirstJobDateSelectNotValid (){ /*التحقق من ادخال تاريخ اول تعيين في الدولة غير صحيح*/
        appointEmployee.enterAppointEmployee();
        appointEmployee.firstJobDateSelect(dayNotValid,monthNotValid,year);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");  // Adjust format as needed
        String expectedDate = today.format(formatter);
        Assert.assertEquals(appointEmployee.getFirstJobDateText(),expectedDate);
    }
    @Test (priority = 26)
    public void verifyFirstJobDateValidation(){  /* التحقق من ظهور الفالديشن */
        appointEmployee.enterAppointEmployee();
        LocalDate today = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String actualDay = today.format(formatter);
        appointEmployee.firstJobDateSelect(actualDay);
        Assert.assertEquals(appointEmployee.getFirstJobDateValidationText(),workDateValidationTex);
    }
    @Test (priority = 27)
    public void verifyFirstJobDateSelectValid (){ /*التحقق من ادخال تاريخ اول تعيين في الدولة*/
        appointEmployee.enterAppointEmployee();
        appointEmployee.firstJobDateSelect(day,month,year);
        Assert.assertEquals(appointEmployee.getFirstJobDateText(),year+"/0"+month+"/0"+day);
    }
    @Test (priority = 28)
    public void verifyDecisionDataToggle(){
        appointEmployee.enterAppointEmployee();
        appointEmployee.setToggle();
        Assert.assertTrue(appointEmployee.getRecNumberTextField().isDisplayed());
    }
}