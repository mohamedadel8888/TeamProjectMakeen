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



    @FindBy (id = "txt_pass_word")
    WebElement newPassword;
    @FindBy (id = "txt_pass_word_conf")
    WebElement confirmNewPassword;
    @FindBy (xpath = "(//h1[contains(text(),'تعديل كلمة المرور')])[1]")
    WebElement editPasswordTitle;
    @FindBy (xpath = "(//input[@onclick='cpUsers.saveEditedPw()'])[1]")
    WebElement saveIcon;
    @FindBy (xpath = "(//input[@onclick='cpUsers.backPWConfirm()'])[1]")
    WebElement backIcon;

    @FindBy (id = "span_A_txt_pass_word")
    WebElement errorMessage1;
    @FindBy (id = "span_A_txt_pass_word_conf")
    WebElement errorMessage2;

    @FindBy (xpath = "(//span[contains(text(),'رسالة تأكيدية')])[1]")
    WebElement confirmationMessage;
    @FindBy (id = "btnP0")
    WebElement acceptIcon;
    @FindBy (id = "btnP1")
    WebElement notAcceptIcon;


    public WebElement getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword1) {
        newPassword.sendKeys(newPassword1);
    }

    public WebElement getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword1) {
        confirmNewPassword.sendKeys(confirmNewPassword1);
    }

    public WebElement getErrorMessage1() {
        return errorMessage1;
    }

    public WebElement getErrorMessage2() {
        return errorMessage2;
    }

    public WebElement getEditPasswordTitle() {
        return editPasswordTitle;
    }

    public WebElement save() {
        return saveIcon;
    }

    public WebElement back() {
        return backIcon;
    }


    public WebElement getConfirmationMessage() {
        return confirmationMessage;
    }
    public WebElement getAcceptIcon() {
        exWait.until(ExpectedConditions.visibilityOf(getConfirmationMessage()));
        return acceptIcon;
    }

    public WebElement getNotAcceptIcon() {
        exWait.until(ExpectedConditions.visibilityOf(getConfirmationMessage()));
        return notAcceptIcon;
    }

}
