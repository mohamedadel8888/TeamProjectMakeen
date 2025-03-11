package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.time.Duration;

public class TC_EmployeeOperations extends TestInit {
    WebDriverWait exWait;
    String nationNumberInMasar ;
    String nationNumberNotInMasar;
    String notInMasarValidation;
    String notValidNationNumber;
    String notValidNumberValidation;



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
    }
    @Test (priority = 1)
    public void verifyEmployeeOperations (){
        Assert.assertTrue(employeesOperations.getEmpOperations().isDisplayed());
    }
    @Test (priority = 2)
    public void verifyAddEmployeeTab (){
        employeesOperations.enterAppointEmployee();
        Assert.assertTrue(employeesOperations.getDivNewEmployee().isDisplayed());
    }
    @Test (priority = 3)
    public void verifyNationNumberNotInMasar (){
        //employeesOperations.enterAppointEmployee();
        employeesOperations.addNationNumber(nationNumberNotInMasar);
        exWait.until(ExpectedConditions.elementToBeClickable(employeesOperations.masarBtn()));
        employeesOperations.masarBtn().click();
        Assert.assertEquals(notInMasarValidation,employeesOperations.getValidationMessage());
    }
    @Test (priority = 4)
    public void verifyNotValidNationNumber (){
       // employeesOperations.enterAppointEmployee();
        employeesOperations.addNationNumber(notValidNationNumber);
        exWait.until(ExpectedConditions.elementToBeClickable(employeesOperations.masarBtn()));
        employeesOperations.masarBtn().click();
        Assert.assertEquals(notValidNumberValidation,employeesOperations.getValidationMessage());
    }

}
