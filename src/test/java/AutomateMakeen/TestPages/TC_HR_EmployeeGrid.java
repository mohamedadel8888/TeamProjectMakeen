package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.*;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static AutomateMakeen.TestPages.TC_TreatmentJob.treatmentName;

public class TC_HR_EmployeeGrid extends TestInit {
    private HR_Employee_Grid hrEmployee;
    private HR_Employee_Add hrEmployeeAdd;
    private HR_Employee_Edit hrEmployeeEdit;
    private HR_Employee_View hrEmployeeView;
    static Faker faker = new Faker();
    private HR_TreatmentJob_grid treatmentJobGrid;
    private String treatmentManagement = "مروان خليل هيكل اداري";
    static String treatmentName = faker.name().title();



    private String empId = faker.number().digits(10);
    private String empMobile = "9665"+faker.number().digits(8);
    private String empEmail = faker.internet().emailAddress();
    private String empEmpLocal = faker.number().digits(7);
    private String empEmpTa7walaGov = faker.number().digits(6);
    private  String treatJob = treatmentName;
    @BeforeClass
    public void setupClass() throws InterruptedException {
        lunchDriver();
        loginPage.goToLoginPage();
        loginPage.clearAllFeild();
        qCMSHomePage = loginPage.loginUserWithoutRemMe(userID, userPasswd);
        treatmentJobGrid = contentAside.goToTreatmentJob();
        treatmentJobGrid.addNewTreatmentJob(treatmentName,treatmentManagement);
        treatmentJobGrid.goToHomePage();
        hrEmployee = contentAside.goToEmployeePage();
    }//(dependsOnMethods = "AutomateMakeen.TestPages.TC_HR_Employee.TC_addEmp")

    @Test
    public void TC_searchByEmpNameAdvanced() throws InterruptedException {
        hrEmployee.searchByName("مروان",1);
        hrEmployee.clickSearchBtn();
        Assert.assertTrue(hrEmployee.validateSearchResults("مروان",1));
        Thread.sleep(2000);
        hrEmployee.clearEmpName();
        hrEmployee.searchByName("خليل",2);
        hrEmployee.clickSearchBtn();
        Assert.assertTrue(hrEmployee.validateSearchResults("خليل",2));
        Thread.sleep(2000);
        hrEmployee.clearEmpName();
        hrEmployee.searchByName("محمود",3);
        hrEmployee.clickSearchBtn();
        Assert.assertTrue(hrEmployee.validateSearchResults("محمود",3));
        Thread.sleep(2000);
        hrEmployee.clearEmpName();
        hrEmployee.searchByName("السيد",4);
        hrEmployee.clickSearchBtn();
        Assert.assertTrue(hrEmployee.validateSearchResults("السيد",4));
    }
    @Test(description = "test case that test search functionality by employee name")
    public void TC_searchByEmpNum(){
        hrEmployee.searchByEmpNum(empEmpLocal);
        hrEmployee.clickSearchBtn();
        Assert.assertTrue(hrEmployee.validateSearchByEmpNum(empEmpLocal));

    }
    @Test(description = "test case that test search functionality by national Id")
    public void TC_searchByEmpId(){
        hrEmployee.searchByEmpId(empId);
        hrEmployee.clickSearchBtn();
        Assert.assertTrue(hrEmployee.validateSearchByEmpId(empId));
        Assert.assertTrue(hrEmployee.validateSearchByEmpNum(empEmpLocal));
    }

    @Test(description = "test case that test search functionality by Mobile Number")
    public void TC_searchByMobile(){
        hrEmployee.searchByMobileNum(empMobile);
        hrEmployee.clickSearchBtn();
        Assert.assertTrue(hrEmployee.validateSearchByEmpNum(empEmpLocal));

    }
    @Test(description = "test case that test search functionality treatment job")
    public void TC_searchByTreatmentJob(){
        hrEmployee.searchByTreatmentJob(treatJob);
        hrEmployee.clickSearchBtn();
        Assert.assertTrue(hrEmployee.validateSearchByEmpNum(empEmpLocal));
    }

    @Test(description = "test case that test search functionality by treatment job management")
    public void TC_searchByTreatmentManagement(){
        hrEmployee.searchByTreatmentManagement("مروان خليل هيكل اداري");
        hrEmployee.clickSearchBtn();
        Assert.assertTrue(hrEmployee.searchByDeptTreatJop("مروان خليل هيكل اداري"));
    }
    @Test(description = "test case that test add new employee functionality")//(priority = 1)
    public void TC_addEmp() throws InterruptedException {
        hrEmployeeAdd = hrEmployee.goToAddPage();
        hrEmployeeAdd.empName("مروان","خليل","شاشة","الموظفين");
        hrEmployeeAdd.empEngName("A","B","C","D");
        hrEmployeeAdd.empSignature("مروان","marwan");
        //if the faker generate id number start with 0 then th econdition
        // become true and it will add 1 to the id
        if(empId.charAt(0) == '0'){empId = (char) ('1' + faker.number().numberBetween(0, 9)) + empId.substring(1);}
        hrEmployeeAdd.empPersonalDetails(empId,empMobile,empEmail,"male");
        hrEmployeeAdd.empJobDetails(treatJob,empEmpLocal,empEmpTa7walaGov,"2025","يناير" ,"2","445545");
//        treatJob = hrEmployeeAdd.getTreatJob();
        Assert.assertTrue(hrEmployeeAdd.addFile("file1","resourse/qr.pdf"));
        hrEmployeeAdd.clickSaveBtn();
        hrEmployeeAdd.clickBackBtn();
        hrEmployee.searchByEmpId(empId);
        hrEmployee.clickSearchBtn();
        Assert.assertTrue(hrEmployee.validateSearchByEmpNum(empEmpLocal));
        Assert.assertTrue(hrEmployee.validateSearchByName("مروان خليل شاشة الموظفين"));
//        treatJob = hrEmployee.getTreatmentManagement(empEmpLocal);

    }
//    @Test(dependsOnMethods = "AutomateMakeen.TestPages.TC_HR_Employee.TC_addEmp")
    @Test(description = "test case that test edit employee data functionality")
    //(priority = 3)
    public void TC_editEmp() throws InterruptedException {
        hrEmployee.searchByEmpId(empId);
        hrEmployee.clickSearchBtn();
        hrEmployee.selectSearchByEmpId();
        hrEmployeeEdit = hrEmployee.goToEditPage();
        hrEmployeeEdit.clearEmpName();
        hrEmployeeEdit.empName("مروان","خليل","تعديل","الاسم");
        hrEmployeeEdit.clickSaveBtn();
        hrEmployee.searchByEmpId(empId);
        hrEmployee.clickSearchBtn();
        Assert.assertTrue(hrEmployee.validateSearchByEmpId(empId));
        Assert.assertTrue(hrEmployee.validateSearchByName("مروان خليل تعديل الاسم"));
    }

//    @Test(dependsOnMethods = "AutomateMakeen.TestPages.TC_HR_Employee.TC_addEmp")
    @Test(description = "test case that test view employee functionality")
    //(priority = 2)
    public void TC_ViewEmp() throws InterruptedException {
        hrEmployee.searchByEmpId(empId);
        hrEmployee.clickSearchBtn();
        hrEmployee.selectSearchByEmpId();
        hrEmployeeView = hrEmployee.goToViewPage();
        softAssert.assertTrue(hrEmployeeView.validateEmpName("مروان","خليل","شاشة","الموظفين"),"Employee arabic name ");
        softAssert.assertTrue(hrEmployeeView.validateEmpEngName("A","B","C","D"),"Employee english name ");
        softAssert.assertTrue(hrEmployeeView.validateEmpSignature("مروان","marwan"),"Employee signature ");
        softAssert.assertTrue(hrEmployeeView.validateEmpPersonalDetails(empId,empMobile,empEmail,"male"),"Employee personal details ");
        softAssert.assertTrue(hrEmployeeView.validateEmpJobDetails(treatJob, empEmpLocal,empEmpTa7walaGov,"445545"),"Employee job details ");
        softAssert.assertTrue(hrEmployeeView.validateFileAdded(),"File added ");
        softAssert.assertAll();
    }

//    @Test(dependsOnMethods = "AutomateMakeen.TestPages.TC_HR_Employee.TC_addEmp")
    @Test(description = "test case that test delete employee functionality")
    //(priority = 4)
    public void TC_deleteEmp()  {
        hrEmployee.searchByEmpId(empId);
        hrEmployee.clickSearchBtn();
        hrEmployee.selectSearchByEmpId();
        hrEmployee.deleteEmp();
        hrEmployee.searchByEmpId(empId);
        hrEmployee.clickSearchBtn();
        Assert.assertTrue(hrEmployee.validateSearchEmpty(),"Employee deleted successfully");
    }
    @AfterMethod
    public void tearDown() {
        driver.navigate().refresh();
    }
}
