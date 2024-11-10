package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.CreateExternalMailPage;
import AutomateMakeen.Pages.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_CreateMailBox extends TestInit {
    CreateExternalMailPage createExternalMailPage;
    @BeforeClass
    public void setupClass()  {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID,userPasswd);
        homePage.goToHomePage();
        createExternalMailPage = new CreateExternalMailPage(driver);
        createExternalMailPage.goToCreateExternalMail();
    }
}
