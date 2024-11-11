package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.CreateExternalEditAccountPage;
import AutomateMakeen.Pages.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TC_EditAccountPage extends TestInit {

    CreateExternalEditAccountPage createExternalEditAccountPage;
    @Test(description = "Preconditions for each test in the class :" +
            "1- Login with authorized User." +
            "2- Navigate to Create External EditAccountPage Page By Press 'لوحة التحكم' in the content Aside" +
            "then press 'إدارة المستخدمين'")

    public void setupClass()  {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID,userPasswd);
        homePage.goToHomePage();
        createExternalEditAccountPage = contentAside.goToCreateExternalEditAccount();
    }
}
