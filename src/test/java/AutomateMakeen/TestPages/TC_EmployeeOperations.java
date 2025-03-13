package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;

public class TC_EmployeeOperations extends TestInit {
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

    /** preconditions :
     السماحية للدخول الي النظام .
     الصلاحية للدخول الى الموارد البشرية .
     الصلاحية للدخول الي عمليات علي الموظفين .
     الصلاحية للدخول تعيين  .
     صلاحية امكانية استدعاء بيانات الموظفين من منصة مسار .
    * */
    @BeforeClass
    public void setupClass() throws FileNotFoundException {
        lunchDriver();
        loginPage.goToLoginPage();
        loginPage.loginUserWithoutRemMe(userID, userPasswd);
        employeesOperations = contentAside.goToEmployeeOperations();
        exWait = new WebDriverWait(driver, Duration.ofSeconds(8));
        nationNumberInMasar=(getJsonData("EmployeeOperations","nationNumberInMasar"));
        nationNumberNotInMasar=(getJsonData("EmployeeOperations","nationNumberNotInMasar"));
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
        IBAN = (getJsonData("EmployeeOperations","IBAN"));
        IBANNotValid = (getJsonData("EmployeeOperations","IBANNotValid"));
    }

    @Test (priority = 1)
    public void verifyEmployeeOperations (){   /*التحقق من فتح صفحه عمليات على الموظفين*/
        Assert.assertTrue(employeesOperations.getEmpOperations().isDisplayed());
    }
    @Test (priority = 2)
    public void verifyAddEmployeeTab (){   /*التحقق من الدخول الى شاشة تعيين موظف*/
        employeesOperations.enterAppointEmployee();
        Assert.assertTrue(employeesOperations.getDivNewEmployee().isDisplayed());
    }
    @Test (priority = 3)
    public void verifyNationNumberNotInMasar (){  /*التحقق من فالديشن رقم الهوية غير موجود في مسار*/
        //employeesOperations.enterAppointEmployee();
        employeesOperations.addNationNumber(nationNumberNotInMasar);
        exWait.until(ExpectedConditions.elementToBeClickable(employeesOperations.masarBtn()));
        employeesOperations.masarBtn().click();
        Assert.assertEquals(notInMasarValidation,employeesOperations.getValidationMessage());
    }
    @Test (priority = 4)
    public void verifyNotValidNationNumber (){ /*التحقق من فالديشن رقم الهوية يبدأ بصفر*/
       // employeesOperations.enterAppointEmployee();
        employeesOperations.addNationNumber(notValidNationNumber);
        exWait.until(ExpectedConditions.elementToBeClickable(employeesOperations.masarBtn()));
        employeesOperations.masarBtn().click();
        Assert.assertEquals(notValidNumberValidation,employeesOperations.getValidationMessage());
    }
    @Test (priority = 5)
    public void verifyNationNumberBiggerThan10Num (){ /*التحقق من ادخال رقم الهوية  اكثر من 10 ارقام*/
        //employeesOperations.enterAppointEmployee();
        employeesOperations.addNationNumber(biggerThan10Numbers);
        Assert.assertNotEquals(biggerThan10Numbers,employeesOperations.getNationalNumber().getText());
    }
    @Test (priority = 6)
    public void verifyFullNameNotValid (){ /*التحقق من ادخال الاسم غير صحيح*/
        employeesOperations.enterAppointEmployee();
        employeesOperations.addFirstName(firstNameNotValid);
        Assert.assertEquals(employeesOperations.getFirstName(),"");
        employeesOperations.addSecondName(secondNameNotValid);
        Assert.assertEquals(employeesOperations.getSecondName(),"");
        employeesOperations.addThirdName(thirdNameNotValid);
        Assert.assertEquals(employeesOperations.getThirdName(),"");
        employeesOperations.addFourthName(fourthNameNotValid);
        Assert.assertEquals(employeesOperations.getFourthName(),"");
    }
    @Test (priority = 7)
    public void verifyFullNameValid (){ /*التحقق من ادخال الاسم صحيح*/
        employeesOperations.enterAppointEmployee();
        employeesOperations.addFirstName(firstName);
        Assert.assertEquals(employeesOperations.getFirstName(),firstName);
        employeesOperations.addSecondName(secondName);
        Assert.assertEquals(employeesOperations.getSecondName(),secondName);
        employeesOperations.addThirdName(thirdName);
        Assert.assertEquals(employeesOperations.getThirdName(),thirdName);
        employeesOperations.addFourthName(fourthName);
        Assert.assertEquals(employeesOperations.getFourthName(),fourthName);
    }
    @Test (priority = 8)
    public void verifyDateOfBirthNotValid (){ /*التحقق من ادخال التاريخ غير صحيح*/
        employeesOperations.enterAppointEmployee();
        employeesOperations.dateSelect(dayNotValid,monthNotValid,year);
        HijrahDate today = HijrahDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");  // Adjust format as needed
        String expectedDate = today.format(formatter);
        Assert.assertEquals(employeesOperations.getDateText(),expectedDate);
    }
    @Test (priority = 9)
    public void verifyDateOfBirthValid (){ /*التحقق من ادخال التاريخ صحيح*/
        employeesOperations.enterAppointEmployee();
        employeesOperations.dateSelectionFromIcon(day,month,year);
        Assert.assertEquals(employeesOperations.getDateText(),year+"/0"+month+"/0"+day);
    }
    @Test (priority = 10)
    public void verifyIBAN_Valid (){ /*التحقق من ادخال رقم البطاقة البنكية صحيح*/
        employeesOperations.enterAppointEmployee();
        employeesOperations.addIBAN(IBAN);
        Assert.assertEquals(employeesOperations.getIBAN(),IBAN);
    }
    @Test (priority = 11)
    public void verifyIBAN_NotValid(){ /*التحقق من ادخال رقم البطاقة البنكية اكبر من  30*/
        employeesOperations.enterAppointEmployee();
        employeesOperations.addIBAN(IBANNotValid);        Assert.assertNotEquals(employeesOperations.getIBAN(),IBANNotValid);
    }
}
