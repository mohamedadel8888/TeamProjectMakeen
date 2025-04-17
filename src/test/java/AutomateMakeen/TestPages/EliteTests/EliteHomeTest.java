package AutomateMakeen.TestPages.EliteTests;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.HomePage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.time.Duration;

public class EliteHomeTest extends TestInit {

    @BeforeClass
    public void setUp() throws FileNotFoundException {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(getJsonData("DelegateData","validEmployee"),getJsonData("DelegateData","validPassword") );
        homePage.goToElite();
    }

    @Test
    public void verifyEliteHome() {
        Assert.assertTrue(eliteHomePage.getEliteHomePage().isDisplayed());
    }


}
