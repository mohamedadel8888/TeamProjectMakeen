package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.CreateExternalMailPage;
import AutomateMakeen.Pages.HomePage;
import AutomateMakeen.Pages.LoginPage;
import AutomateMakeen.Pages.UsersControl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC_SearchInUsersControl extends TestInit {
    UsersControl usersControl;

    @BeforeClass(description = "Preconditions for each test in the class :" +
            "1- Login with authorized User." +
            "2- Navigate to Create External Mail Page By Press 'البريد' in the content Aside" +
            "then press 'انشاء بريد خارجي'")
    public void setupClass()  {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID,userPasswd);
        homePage.goToHomePage();
        usersControl = contentAside.goToUsersControl();
    }


    @Test
    public void tc_searchByFname (){
        usersControl.setFirstName("150");
        usersControl.setSecondName("150");
        usersControl.setThirdName("150");
        usersControl.setLastName("150");
        usersControl.setUserID("150");
        usersControl.singleSearch();
        usersControl.clearAllFeild();
    }

}
