package AutomateMakeen.TestPages.EliteTests;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.Elite.EliteHomePage;
import AutomateMakeen.Pages.Elite.InboxPage;
import AutomateMakeen.Pages.HomePage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.time.Duration;

public class InboxTest extends TestInit {
    WebDriverWait ex = new WebDriverWait(driver, Duration.ofSeconds(5));
    @BeforeClass
    public void setUp() throws FileNotFoundException {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(getJsonData("DelegateData","validEmployee"),getJsonData("DelegateData","validPassword") );
        homePage.goToElite();
        EliteHomePage eliteHomePage = new EliteHomePage(driver);
        createInternalMailPage = eliteHomePage.goToCreateInternalMail();
        archiveNum = createInternalMailPage.createInternalMailForMe();
    }
    @Test
    public void verifyInboxPage() {  /*التحقق من فتح صفحه الوارد */
        Assert.assertTrue(inboxPage.getMailInboxPage().isDisplayed());
    }
    @Test
    public void verifyAttachementsPage (){
        inboxPage.mailInboxSearch(archiveNum);

    }
}
