package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.CreateExternalMailPage;
import AutomateMakeen.Pages.HomePage;
import org.testng.annotations.BeforeClass;

public class TC_CreateMailBox extends TestInit {

    CreateExternalMailPage createExternalMailPage;
    @BeforeClass(description = "Preconditions for each test in the class :" +
            "1- Login with authorized User." +
            "2- Navigate to Create External Mail Page By Press 'البريد' in the content Aside" +
            "then press 'انشاء بريد خارجي'")
    public void setupClass()  {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID,userPasswd);
        homePage.goToHomePage();
        createExternalMailPage = contentAside.goToCreateExternalMail();
    }
}
