package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.AddDelegatePage;
import AutomateMakeen.Pages.DelegatePage;
import AutomateMakeen.Pages.HomePage;
import AutomateMakeen.Pages.UsersControl;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TC_AddDelegatePage extends TestInit{



    UsersControl usersControl;
    DelegatePage delegatePage;
    AddDelegatePage addDelegatePage;

    @BeforeClass
   public void setupClass()  {
       lunchDriver();
       loginPage.goToLoginPage();
       HomePage homePage = loginPage.loginUserWithoutRemMe(userID,userPasswd);
       homePage.goToHomePage();
       usersControl = contentAside.goToUsersControl();
   }

    @Test
    public void scrollinDepartmentName(){
        usersControl.selectEmployeeByID("3569897");
        delegatePage = usersControl.delegationControl();
        addDelegatePage = delegatePage.clickAddButton();
        //addDelegatePage.selectDepartmentNameFromDropDown("إدارة عسير");
        addDelegatePage.scrollInDepartmentNameDDL("إدارة عسير");
        //addDelegatePage.selectDelegatedEmployeeFromDropDown("إدارة عسير");
        //addDelegatePage.chooseNewPeriodRadioButton();
    }


}


