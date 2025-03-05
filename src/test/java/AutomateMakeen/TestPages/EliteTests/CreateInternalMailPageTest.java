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
    WebDriverWait ex = new WebDriverWait(driver, Duration.ofSeconds(8));

    @BeforeClass
    public void setUp() throws FileNotFoundException {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(getJsonData("DelegateData","validEmployee"),getJsonData("DelegateData","validPassword") );
        homePage.goToElite();
        eliteHomePage.goToCreateInternalMail();

    }
    /*=============================================================*/

    @Test (priority = 1)
    public void verifyEnteringCreateInternalMailPage() {  /*التحقق من الدخول لصفحة انشاء بريد داخلي*/
        Assert.assertTrue(createInternalMailPage.getCreateInternalMailPage().isDisplayed());
    }

    @Test  (priority = 2)
    public void verifySubjectInput() {  /*التحقق من الحد الاقصى للاحرف للحقل*/
        createInternalMailPage.txtSubject("fdgfffffdfvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvbvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvergerrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        Assert.assertEquals(createInternalMailPage.getSubject(),"fdgfffffdfvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvbvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvergerrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
    }
    @Test (priority = 3)
    public void verifySubjectInputSpecialCharacters() {  /*التحقق من قبول رموز خاصه في حقل الموضوع */
        createInternalMailPage.txtSubject("*&^%$#@!%^&*()_+{}|:;<>,.?/~`");
        Assert.assertNotEquals(createInternalMailPage.getSubject(),"*&^%$#@!%^&*()_+{}|:;<>,.?/~`");
    }
    @Test (priority = 4)
    public void verifyCreateInternalMail() { /*ارسال بريد صحيح*/
        createInternalMailPage.clearFields();
        if (createInternalMailPage.isSecretToggleSelected())
        {
            createInternalMailPage.isSecretToggleSelected();
        }
        if (createInternalMailPage.isUrgentToggleSelected())
        {
            createInternalMailPage.isUrgentToggleSelected();
        }
        createInternalMailPage.txtSubject("hi iam mohamed");
        createInternalMailPage.explain("this is my internal mail");
        createInternalMailPage.secretExpToggle();
        createInternalMailPage.forwardToCustomEmp("ادارة المهندسين","احمد -- -- احمد","للإحاطة");
        createInternalMailPage.docTypeSelect("12190");
        createInternalMailPage.transClassification("1023");
        createInternalMailPage.SendMail();
        archiveNum = createInternalMailPage.getArchiveNum();
        Assert.assertTrue(createInternalMailPage.isMailSentSuccessfully());
        createInternalMailPage.closePopUp();
    }


}
