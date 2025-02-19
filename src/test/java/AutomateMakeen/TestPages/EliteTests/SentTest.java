package AutomateMakeen.TestPages.EliteTests;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.Elite.SentPage;
import AutomateMakeen.Pages.HomePage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.time.Duration;

public class SentTest extends TestInit {
    WebDriverWait ex = new WebDriverWait(driver, Duration.ofSeconds(5));
    @BeforeClass
    public void setUp() throws FileNotFoundException {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(getJsonData("DelegateData","validEmployee"),getJsonData("DelegateData","validPassword") );
        homePage.goToElite();
    }
    @Test
    public void verifySentMails() {
        SentPage sentPage = eliteHomePage.goToSent();
        Assert.assertTrue(sentPage.getMailSentPage().isDisplayed());
    }
    @Test
    public void verifySentMailBySearch() {
        SentPage sentPage = eliteHomePage.goToSent();
        sentPage.mailSentSearch("204431");
        Assert.assertTrue(sentPage.getMailSentPage().isDisplayed());
        Assert.assertEquals(sentPage.getTreatArchiveNum(),"204431");
    }
}
