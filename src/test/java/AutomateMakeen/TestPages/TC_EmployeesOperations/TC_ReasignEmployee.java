package AutomateMakeen.TestPages.TC_EmployeesOperations;

import AutomateMakeen.BaseTest.TestInit;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TC_ReasignEmployee extends TestInit {
    WebDriverWait exWait;

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
    String mandateJob;
    String degree;

    String levelDateValidationTex;
    String workDateValidationTex;

    String sucessSavingEmpMsg;

    @BeforeClass
    public void setupClass() throws FileNotFoundException {
        lunchDriver();
        loginPage.goToLoginPage();
        loginPage.loginUserWithoutRemMe(userID, userPasswd);
        reassignEmployee = contentAside.goToEmployeeOperations_ReassignEmployee();
        reassignEmployee.searchForEmployee(getJsonData("EmployeeOperations","empToReassign"));
        reassignEmployee.enterReassignEmployee();
        exWait = new WebDriverWait(driver, Duration.ofSeconds(8));
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
        IBAN = (getJsonData("EmployeeOperations","IBAN"));
        IBANNotValid = (getJsonData("EmployeeOperations","IBANNotValid"));
        nationality = (getJsonData("EmployeeOperations","nationality"));
        noNationality = (getJsonData("EmployeeOperations","noNationality"));
        employeeType = (getJsonData("EmployeeOperations","employeeType"));
        majorJob = (getJsonData("EmployeeOperations","majorJob"));

        noMinorJob = (getJsonData("EmployeeOperations","noMajorJob"));
        mandateJob = (getJsonData("EmployeeOperations","mandateJob"));
        degree = (getJsonData("EmployeeOperations","degree"));
        workDateValidationTex = (getJsonData("EmployeeOperations","workDateValidationTex"));
        levelDateValidationTex = (getJsonData("EmployeeOperations","levelDateValidationTex"));
        sucessSavingEmpMsg = (getJsonData("EmployeeOperations","sucessSavingEmpMsg"));

    }
    @Test(priority = 1)
    public void verifyEmployeeOperations (){
        Assert.assertTrue(reassignEmployee.getDivNewEmployee().isDisplayed());
    }
    @Test (priority = 2)
    public void verifyFullNameNotValid (){ /*التحقق من ادخال الاسم غير صحيح*/
        reassignEmployee.addFirstName(firstNameNotValid);
        Assert.assertEquals(reassignEmployee.getFirstName(),"");
        reassignEmployee.addSecondName(secondNameNotValid);
        Assert.assertEquals(reassignEmployee.getSecondName(),"");
        reassignEmployee.addThirdName(thirdNameNotValid);
        Assert.assertEquals(reassignEmployee.getThirdName(),"");
        reassignEmployee.addFourthName(fourthNameNotValid);
        Assert.assertEquals(reassignEmployee.getFourthName(),"");
    }
    @Test (priority = 3)
    public void verifyFullNameValid (){ /*التحقق من ادخال الاسم صحيح*/
        reassignEmployee.addFirstName(firstName);
        Assert.assertEquals(reassignEmployee.getFirstName(),firstName);
        reassignEmployee.addSecondName(secondName);
        Assert.assertEquals(reassignEmployee.getSecondName(),secondName);
        reassignEmployee.addThirdName(thirdName);
        Assert.assertEquals(reassignEmployee.getThirdName(),thirdName);
        reassignEmployee.addFourthName(fourthName);
        Assert.assertEquals(reassignEmployee.getFourthName(),fourthName);
    }

    @Test (priority = 4)
    public void verifyDateOfBirthNotValid (){ /*التحقق من ادخال التاريخ غير صحيح*/
        reassignEmployee.dateSelect(dayNotValid,monthNotValid,year);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");  // Adjust format as needed
        String expectedDate = today.format(formatter);
        Assert.assertEquals(reassignEmployee.getDateText(),expectedDate);
    }
    @Test (priority = 5)
    public void verifyDateOfBirthValid (){ /*التحقق من ادخال التاريخ صحيح*/

        reassignEmployee.dateSelectionFromIcon(day,month,year);
        Assert.assertEquals(reassignEmployee.getDateText(),year+"/0"+month+"/0"+day);
    }
    @Test (priority = 6)
    public void verifyIBAN_NotValid(){ /*التحقق من ادخال رقم البطاقة البنكية اكبر من  30*/
        reassignEmployee.addIBAN(IBANNotValid);
        Assert.assertNotEquals(reassignEmployee.getIBAN(),IBANNotValid);
    }
    @Test (priority = 7)
    public void verifyIBAN_Valid (){ /*التحقق من ادخال رقم البطاقة البنكية صحيح*/
        reassignEmployee.addIBAN(IBAN);
        Assert.assertEquals(reassignEmployee.getIBAN(),IBAN);
    }
    @Test (priority = 8)
    public void verifySelectNationality ()  {
        reassignEmployee.selectNationality(nationality);
        Assert.assertEquals(reassignEmployee.getNationality(),nationality);
    }
    @Test (priority = 9)
    public void verifyEmployeeType ()  {
        reassignEmployee.selectEmployeeType(employeeType);
        Assert.assertEquals(reassignEmployee.getEmployeeType(),employeeType);
    }
    @Test (priority =10)
    public void verifyMajorJob (){
        reassignEmployee.selectMajorJob(majorJob);
        Assert.assertEquals(reassignEmployee.getMajorJob(),majorJob);
    }
    @Test (priority = 11)
    public void verifyMandateJob (){
        reassignEmployee.selectMandateJob(mandateJob);
        Assert.assertEquals(reassignEmployee.getMandateJob(),mandateJob);
    }
    @Test (priority = 12)
    public void verifyDegree ()  {
        reassignEmployee.selectDegree(degree);
        Assert.assertEquals(reassignEmployee.getDegree(),degree);
    }

    @Test (priority = 13)
    public void verifyOrganizationNumber(){
        Assert.assertFalse(reassignEmployee.getOrganizationNumber().isEnabled());
    }
    @Test (priority = 14)
    public void verifyWorkDateNotValid (){ /*التحقق من ادخال تاريخ التعيين في الجهه غير صحيح*/
        reassignEmployee.workDateSelect(dayNotValid,monthNotValid,year);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");  // Adjust format as needed
        String expectedDate = today.format(formatter);
        Assert.assertEquals(reassignEmployee.getWorkDateText(),expectedDate);
    }
    @Test (priority = 15)
    public void verifyWorkDateValidation(){  /* التحقق من ظهور الفالديشن */
        LocalDate today = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String actualDay = today.format(formatter);
        reassignEmployee.workDateSelect(actualDay);
        Assert.assertEquals(reassignEmployee.getWorkdateValidationText(),workDateValidationTex);
    }
    @Test (priority = 16)
    public void verifyWorkDateValid (){ /*التحقق من ادخال تاريخ التعيين في الجهه صحيح*/
        reassignEmployee.workDateSelect(day,month,year);
        Assert.assertEquals(reassignEmployee.getWorkDateText(),year+"/0"+month+"/0"+day);
    }

    @Test (priority = 17)
    public void verifyLevelDateSelectNotValid (){ /*التحقق من ادخال تاريخ التعيين على مرتبة غير صحيح*/
        reassignEmployee.levelDateSelect(dayNotValid,monthNotValid,year);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");  // Adjust format as needed
        String expectedDate = today.format(formatter);
        Assert.assertEquals(reassignEmployee.getLevelDateText(),expectedDate);
    }
    @Test (priority = 18)
    public void verifyLevelDateValidation(){  /* التحقق من ظهور الفالديشن */
        LocalDate today = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String actualDay = today.format(formatter);
        reassignEmployee.levelDateSelect(actualDay);
        Assert.assertEquals(reassignEmployee.getLevelDateValidationText(),levelDateValidationTex);
    }
    @Test (priority = 19)
    public void verifyLevelDateSelectValid (){ /*التحقق من ادخال تاريخ التعيين على مرتبة*/
        reassignEmployee.levelDateSelect(day,month,year);
        Assert.assertEquals(reassignEmployee.getLevelDateText(),year+"/0"+month+"/0"+day);
    }

    @Test (priority = 20)
    public void verifyFirstJobDateSelectNotValid (){ /*التحقق من ادخال تاريخ اول تعيين في الدولة غير صحيح*/

        reassignEmployee.firstJobDateSelect(dayNotValid,monthNotValid,year);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");  // Adjust format as needed
        String expectedDate = today.format(formatter);
        Assert.assertEquals(reassignEmployee.getFirstJobDateText(),expectedDate);
    }
    @Test (priority = 21)
    public void verifyFirstJobDateValidation(){  /* التحقق من ظهور الفالديشن */
        LocalDate today = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String actualDay = today.format(formatter);
        reassignEmployee.firstJobDateSelect(actualDay);
        Assert.assertEquals(reassignEmployee.getFirstJobDateValidationText(),workDateValidationTex);
    }
    @Test (priority = 22)
    public void verifyFirstJobDateSelectValid (){ /*التحقق من ادخال تاريخ اول تعيين في الدولة*/

        reassignEmployee.firstJobDateSelect(day,month,year);
        Assert.assertEquals(reassignEmployee.getFirstJobDateText(),year+"/0"+month+"/0"+day);
    }
    @Test (priority = 23)
    public void verifyDecisionDataToggle(){
        reassignEmployee.setToggle();
        Assert.assertTrue(reassignEmployee.getRecNumberTextField().isDisplayed());
    }


}
