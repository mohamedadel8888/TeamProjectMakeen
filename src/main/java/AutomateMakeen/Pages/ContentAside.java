package AutomateMakeen.Pages;
import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import AutomateMakeen.Pages.UsersControl;
import AutomateMakeen.Pages.EditAccountPage;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;


public class ContentAside extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;

    public ContentAside(WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }

    @FindBy(xpath = "(//i[@class='fa fa-angle-double-down'])[1]")
    private WebElement empAffairArrowWebElement;

    @FindBy(id = "s_m_65")
    private WebElement adminStructureWebElement;

    @FindBy(id = "cph_main_btn_AddElement")
    private WebElement addNewDeptWebElement;

    @FindBy(id = "prog_cp")
    private WebElement controlPanel;  /*لوحه التحكم */

    @FindBy(id = "s_m_62")
    private WebElement userControl;  /*ادارة المستخدمين */

    @FindBy(xpath = "(//i[@class='fa fa-angle-double-down'])[3]")
    private WebElement mailArrowWebElement;  /*زر قائمةالبريد في القائمة الجانية*/

    @FindBy(id = "s_m_141")
    private WebElement createExternalMailWebELement;  /*اختيار انشاء بريد خارجي من قائمة البريد*/

    @FindBy(id = "cph_main_btn_edit_user")
    private WebElement editUserButtonWebElement;  /*اختيار زر تعديل الحساب من ادارة المستخدمين*/

    @FindBy(id = "cb_:1")
    private WebElement firstCheckboxWebElement;  /*اختيار اول مربع من النتائج*/

    @FindBy (id = "txt_user_id")  /* رقم المستخدم */
    private WebElement UserIDWebElement;

    @FindBy (id ="btn_srch")    /* بحث */
    private  WebElement singleSearch;

//    @FindBy(css=".alrt-bx")
//    WebElement alertMessage;
//    @FindBy (className = "close_box")
//    WebElement closeNotificationsIcon;

    @FindBy(id = "div_extGeha_notification")
    WebElement notificationWebElement;
    public UsersControl goToUsersControl() {
        exWait.until(ExpectedConditions.invisibilityOf(notificationWebElement));
        exWait.until(ExpectedConditions.elementToBeClickable(controlPanel));
        controlPanel.click();
        userControl.click();
        return new UsersControl(driver);
    }

    public CreateExternalMailPage goToCreateExternalMail() {
        exWait.until(ExpectedConditions.elementToBeClickable(mailArrowWebElement));
        mailArrowWebElement.click();
        exWait.until(ExpectedConditions.elementToBeClickable(createExternalMailWebELement));
        createExternalMailWebELement.click();
        return new CreateExternalMailPage(driver);}

    public EditAccountPage goToCreateExternalEditAccount() {
        exWait.until(ExpectedConditions.elementToBeClickable(controlPanel));
        controlPanel.click();
        userControl.click();
        exWait.until(ExpectedConditions.elementToBeClickable(firstCheckboxWebElement));
        UserIDWebElement.sendKeys("1654198");
        singleSearch.click();
        firstCheckboxWebElement.click();
        editUserButtonWebElement.click();
        return new EditAccountPage  (driver);
    }
    @FindBy(id = "s_m_69")
    WebElement exportedMailWebElement;

    @FindBy(id ="tbl_outbox")
    WebElement tableOutWebElement;
    @FindBy(id ="tbl_inbox")
    WebElement tableInWebElement;


    public OutboxMails goToExportedMail(){
        exWait.until(ExpectedConditions.invisibilityOf(notificationWebElement));
        try{
            exWait.until(ExpectedConditions.elementToBeClickable(mailArrowWebElement));
            mailArrowWebElement.click();
            exportedMailWebElement.click();
            exWait.until(ExpectedConditions.visibilityOf(tableOutWebElement));
            return new OutboxMails(driver);
        }catch(NoSuchElementException e){
            exWait.until(ExpectedConditions.elementToBeClickable(mailArrowWebElement));
            mailArrowWebElement.click();
            exportedMailWebElement.click();
            exWait.until(ExpectedConditions.visibilityOf(tableOutWebElement));
            return new OutboxMails(driver);
        }
    }
    @FindBy(id = "s_m_68")
    WebElement importedMailWebElement;

    public ImportedMails goToImportedMail(){
        exWait.until(ExpectedConditions.invisibilityOf(notificationWebElement));
        try{
            exWait.until(ExpectedConditions.elementToBeClickable(mailArrowWebElement));
            mailArrowWebElement.click();
            importedMailWebElement.click();
            exWait.until(ExpectedConditions.visibilityOf(tableInWebElement));
            return new ImportedMails(driver);
        }catch(NoSuchElementException e){
            mailArrowWebElement.click();
            importedMailWebElement.click();
            exWait.until(ExpectedConditions.visibilityOf(tableInWebElement));
            return new ImportedMails(driver);
        }
    }
}
