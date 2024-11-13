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

    @FindBy (xpath = "//b[@id='span_A_txt_pass_word']/p/span")
    WebElement errorMessage1;

    @FindBy (xpath = "//b[@id='span_A_txt_pass_word_conf']/p/span")
    WebElement errorMessage2;



    @FindBy(id ="span_A_txt_pass_word")
    WebElement validation1;
    @FindBy (id="span_A_txt_pass_word_conf")
    WebElement validation2;


    @FindBy (xpath = "(//span[contains(text(),'رسالة تأكيدية')])[1]")
    WebElement confirmationMessage;
    @FindBy (id = "btnP0")
    WebElement acceptIcon;
    @FindBy (id = "btnP1")
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

    public WebElement getNotAcceptIcon() {
        exWait.until(ExpectedConditions.visibilityOf(getConfirmationMessage()));
        return notAcceptIcon;
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


}
