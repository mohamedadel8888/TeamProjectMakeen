package AutomateMakeen.Pages;
import java.util.concurrent.TimeUnit;
import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ContentAside extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;

    public ContentAside(WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "(//i[@class='fa fa-angle-double-down'])[1]")
    WebElement empAffairArrowWebElement;

    @FindBy (id = "s_m_65")
    WebElement adminStructureWebElement;

    @FindBy(id = "cph_main_btn_AddElement")
    WebElement addNewDeptWebElement;

    @FindBy (id = "prog_cp")
    WebElement ControlPanel;  /*لوحه التحكم */
    @FindBy ( id = "s_m_62")
    WebElement userControl ; /*ادارة المستخدمين */

    @FindBy (xpath = "(//i[@class='fa fa-angle-double-down'])[3]")
    WebElement mailArrowWebElement; /*زر قائمةالبريد في القائمة الجانية*/

    @FindBy (id = "s_m_141")/*اختيار انشاء بريد خارجي من قائمة البريد*/
    WebElement createExternalMailWebELement;

    @FindBy(id = "cph_main_btn_edit_user")     /*اختيار زر تعديل الحساب من ادارة المستخدمين*/
    private WebElement editUserButtonWebElement;

    @FindBy(id = "cb_:1")       /*اختيار اول مربع من النتائج*/
    private WebElement FirstCheckboxWebElement;



    public UsersControl goToUsersControl (){
        exWait.until(ExpectedConditions.elementToBeClickable(ControlPanel));
        ControlPanel.click();
        userControl.click();
        return new UsersControl(driver);
    }

    public CreateExternalMailPage goToCreateExternalMail(){
        exWait.until(ExpectedConditions.elementToBeClickable(mailArrowWebElement));
        mailArrowWebElement.click();
        createExternalMailWebELement.click();
        return new CreateExternalMailPage(driver);
    }

    public CreateExternalEditAccountPage goToCreateExternalEditAccount(){
        exWait.until(ExpectedConditions.elementToBeClickable(ControlPanel));
        ControlPanel.click();
        userControl.click();
        exWait.until(ExpectedConditions.elementToBeClickable(FirstCheckboxWebElement));
        FirstCheckboxWebElement.click();
        editUserButtonWebElement.click();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new CreateExternalEditAccountPage(driver);
    }



}
