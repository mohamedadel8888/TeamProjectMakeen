package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.HR_Employee;
import AutomateMakeen.Pages.HR_Employee_Add;
import AutomateMakeen.Pages.HR_Employee_Edit;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_HR_Employee extends TestInit {
    private HR_Employee hrEmployee;
    private HR_Employee_Add hrEmployeeAdd;
    private HR_Employee_Edit hrEmployeeEdit;
    Faker faker = new Faker();
    private String empId = faker.number().digits(10);
    private String empMobile = "9665"+faker.number().digits(8);
    private String empEmail = faker.internet().emailAddress();
    private String empEmpTa7wala = faker.number().digits(7);
    private String empEmpTa7walaGov = faker.number().digits(6);
    @BeforeClass
    public void setupClass() {
        lunchDriver();
        loginPage.goToLoginPage();
        loginPage.loginUserWithoutRemMe("0342169", "24602460");
        hrEmployee = contentAside.goToEmployeePage();
    }
    @Test
    public void TC_searchByEmpName() {
        hrEmployee.searchByName("مروان",1);
        hrEmployee.searchByName("خليل",2);
        hrEmployee.searchByName("محمود",3);
        hrEmployee.searchByName("شمسو",4);
        hrEmployee.clickSearchBtn();
        hrEmployee.validateSearchByName("مروان خليل محمود شمسو");
    }
    @Test
    public void TC_searchByEmpNum(){
        hrEmployee.searchByEmpNum("6226400");
        hrEmployee.clickSearchBtn();
        hrEmployee.validateSearchByEmpNum("6226400");
    }
    @Test
    public void TC_searchByEmpId(){
        hrEmployee.searchByEmpId("2990130110");
        hrEmployee.clickSearchBtn();
        hrEmployee.validateSearchByEmpNum("6226400");
    }

    @Test
    public void TC_searchByMobile(){
        hrEmployee.searchByMobileNum("966501211313");
        hrEmployee.clickSearchBtn();
        hrEmployee.validateSearchByEmpNum("6226400");
    }

    @Test
    public void TC_addEmp() throws InterruptedException {
        hrEmployeeAdd = hrEmployee.goToAddPage();
        hrEmployeeAdd.empName("مروان","خليل","شاشة","الموظفين");
        hrEmployeeAdd.empEngName("A","B","C","D");
        hrEmployeeAdd.empSignature("Maro","Sign");
        hrEmployeeAdd.empPersonalDetails(empId,empMobile,empEmail,"male");
        hrEmployeeAdd.empJobDetails(empEmpTa7wala,empEmpTa7walaGov,"445545");
        Assert.assertTrue(hrEmployeeAdd.addFile("file1","resourse/qr.pdf"));
        hrEmployeeAdd.clickSaveBtn();
        hrEmployeeAdd.clickBackBtn();
        hrEmployee.searchByEmpId(empId);
        hrEmployee.clickSearchBtn();
        Assert.assertTrue(hrEmployee.validateSearchByEmpId(empId));
    }
    @Test
    public void TC_editEmp() throws InterruptedException {
        HR_Employee_Edit hrEmployeeEdit = hrEmployee.goToEditPage();
    }


}
