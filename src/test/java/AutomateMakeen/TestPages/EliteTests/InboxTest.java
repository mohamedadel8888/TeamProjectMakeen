package AutomateMakeen.TestPages.EliteTests;

import AutomateMakeen.BaseTest.TestInit;
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
    }
    @Test
    public void verifyInboxPage() {
        InboxPage inboxPage = eliteHomePage.goToInbox();
        inboxPage.mailInboxSearch("204118");
        String arcNum = inboxPage.getTreatArchiveNum();
        Assert.assertEquals(arcNum,"204118");
        Assert.assertTrue(inboxPage.getMailInboxPage().isDisplayed());
    }
    @Test
    public void verifyAttachementsPage (){
        InboxPage inboxPage = eliteHomePage.goToInbox();
        inboxPage.mailInboxSearch("204118");
        inboxPage.goToAttachments();
        inboxPage.createLettersTab();
        inboxPage.selectDepartment("رئاسة امانة عسير");
        inboxPage.selectSefatLetter("بدون");
        inboxPage.forwardTo("32");
        inboxPage.selectForwardType("للافاده");
        Assert.assertTrue(inboxPage.getAttachmentsTab().isDisplayed());
    }
}
