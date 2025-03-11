package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditPassword extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;

    public EditPassword(WebDriver driver){
        super(driver);
        this.driver = driver;
        //specific wait for every page
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
        contentAside = new ContentAside(driver);
    }



    @FindBy (id = "txt_pass_word")  /*حقل كلمه سر جديدة */
    WebElement newPassword;
    @FindBy (id = "txt_pass_word_conf")   /*حقل اعاده كتابه كلمه السر */
    WebElement confirmNewPassword;
    @FindBy (css = "div[id='fs_edit_pw']") /*عنوان صفحه تعديل كلمه المرور*/
    WebElement editPasswordTitle;
    @FindBy (css = "input[onclick='cpUsers.saveEditedPw()']") /* ايقونة الحفظ */
    WebElement saveIcon;
    @FindBy (css = "input[onclick='cpUsers.backPWConfirm()']") /* ايقونة العودة */
    WebElement backIcon;



    @FindBy (id = "txt_user_name")  /*حقل اسم المستخدم */
    WebElement userName;

    @FindBy (css = "#span_A_txt_pass_word") /*رساله الخطأ الاولى*/
    WebElement errorMessage1;

    @FindBy (css = "#span_A_txt_pass_word_conf") /*رساله الخطأ الثانية */
    WebElement errorMessage2;



    @FindBy(id ="span_A_txt_pass_word") /* الاستريك الاول */
    WebElement validation1;
    @FindBy (id="span_A_txt_pass_word_conf")   /*الاستريك الثاني */
    WebElement validation2;

    @FindBy (css = "div[class='popup_title']")  /* عنوان شاشه الرساله التأكيدية */
    WebElement confirmationMessage;
    @FindBy (id = "btnP0")   /* زر موافق*/
    WebElement acceptIcon;
    @FindBy (id = "btnP1")    /*زر عير موافق */
    WebElement notAcceptIcon;

    public String  getNewPassword() {
        return newPassword.getText();
    }

    public void setNewPassword(String newPassword1) {
        newPassword.sendKeys(newPassword1);
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword.getText();
    }

    public void setConfirmNewPassword(String confirmNewPassword1) {
        confirmNewPassword.sendKeys(confirmNewPassword1);
    }

    public String getErrorMessage1() {
        return errorMessage1.getText();
    }

    public String getErrorMessage2() {
        return errorMessage2.getText();
    }
    public WebElement ErrorMessage1() {
        return validation1;
    }

    public WebElement ErrorMessage2() {
        return validation2;
    }

    public WebElement getEditPasswordTitle() {
        return editPasswordTitle;
    }

    public void save() {
       saveIcon.click();
    }

    public void back() {
        backIcon.click();
    }


    public WebElement getConfirmationMessage() {
        return confirmationMessage;
    }
    public void acceptIcon() {
        exWait.until(ExpectedConditions.visibilityOf(getConfirmationMessage()));
         acceptIcon.click();
    }

    public void notAcceptIcon() {
        exWait.until(ExpectedConditions.visibilityOf(getConfirmationMessage()));
        notAcceptIcon.click();
    }

    public String validation1() {
        String message;
        message=getValidatorState(validation1);
        validation1.click();
        return message;
    }

    public String validation2() {
        String message;
        message=getValidatorState(validation2);
        validation2.click();
        return message;
    }

    public WebElement getUserName() {
        return userName;
    }


}
