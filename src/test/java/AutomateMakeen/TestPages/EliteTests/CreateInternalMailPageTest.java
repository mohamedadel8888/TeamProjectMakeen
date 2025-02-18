package AutomateMakeen.TestPages.EliteTests;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.Elite.CreateInternalMailPage;
import AutomateMakeen.Pages.HomePage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.time.Duration;

public class CreateInternalMailPageTest extends TestInit {
    WebDriverWait ex = new WebDriverWait(driver, Duration.ofSeconds(5));
    @BeforeClass
    public void setUp() throws FileNotFoundException {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(getJsonData("DelegateData","validEmployee"),getJsonData("DelegateData","validPassword") );
        homePage.goToElite();
    }
    @Test
    public void verifyCreateInternalMailPage() {
        CreateInternalMailPage createInternalMailPage = eliteHomePage.goToCreateInternalMail();
        createInternalMailPage.DropDownToggleSelect_SecretMail();
        createInternalMailPage.DropDownToggleSelect_UrgentTreat();
        createInternalMailPage.txtSubject("hi iam mohamed");
        createInternalMailPage.explain("this is my internal mail");
        createInternalMailPage.secretExpToggle();
       // createInternalMailPage.selfRedirectToggle();
        createInternalMailPage.forwardToCustomEmp("ادارة المهندسين","احمد -- -- احمد","للإحاطة");
        createInternalMailPage.docTypeSelect("12190");
        createInternalMailPage.transClassification("2");
        createInternalMailPage.SendMail();
        Assert.assertTrue(createInternalMailPage.getCreateInternalMailPage().isDisplayed());
    }


}
