package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;
    private By accountsPageTitle = By.cssSelector(".PopUpHead span");

    private static int flag = 0;
    @FindBy(id = "txtUserName")
    WebElement idWebElement;

    @FindBy(id = "txtUserPass")
    WebElement passwordWebElement;

    @FindBy(id = "lblShowPass")
    WebElement showPassWebElement;

    @FindBy(className = "toggle")
    WebElement remeberMeWebElement;

    @FindBy(id = "btnLogin")
    WebElement loginBtnWebElement;

    @FindBy(id = "btnActivationCode")
    WebElement activationCodeWebElement;

    @FindBy(css = "a[href='http://www.alqemam.com/alqemam/']")
    WebElement alqemamLogoWebElement;

    @FindBy(id = "error_div")
    WebElement errorMsgWebElement;

    @FindBy (id = "dvMobileCode")
    WebElement mobileCode;

    public LoginPage (WebDriver driver){
        super(driver);
        this.driver = driver;
        //specific wait for every page
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
    }

    public void goToLoginPage(){
        driver.get("https://g-line-iis.alqemam.com/qCMS_Test_v14.0.13/");
    }
    public HomePage loginUserWithoutRemMe(String id , String password)
    {
        idWebElement.sendKeys(id);
        passwordWebElement.sendKeys(password);
        loginBtnWebElement.click();
        return new HomePage(driver);
    }
    public HomePage loginUserWithRemMe(String id , String password)
    {
        idWebElement.sendKeys(id);
        passwordWebElement.sendKeys(password);
        remeberMeWebElement.click();
        loginBtnWebElement.click();
        return new HomePage(driver);
    }
    public String getErrorMessage(){
        exWait.until(ExpectedConditions.visibilityOf(errorMsgWebElement));
        return errorMsgWebElement.getText();
    }
    public String getUserIdContent(){
        return idWebElement.getAttribute("value");
    }
    public String getUserPasswdContent(){
        return passwordWebElement.getAttribute("value");
    }
    public void clearAllField ()
    {
        idWebElement.clear();
        passwordWebElement.clear();
    }
    public String getErrorMessages(int errorMsgId){
        switch (errorMsgId) {
            case 0: {
                return "عفواً ، محاولة غير صالحة لتسجيل الدخول";
            }
            case 1: {
                return "برجاء إدخال رقم المستخدم !";
            }
            case 2: {
                return "برجاء إدخال كلمة المرور";
            }
            default:return "Invalid Error Message ID";
        }
    }
    public void clearAllFeild(){
        idWebElement.clear();
        passwordWebElement.clear();
    }
    public WebElement getMobileCode() {
        return mobileCode;
    }


    //Navigate To Personal Accounts Page

    public PersonalAccountsPage loginUserWithDelegateAccounts(String id , String password)
    {
        idWebElement.sendKeys(id);
        passwordWebElement.sendKeys(password);
        remeberMeWebElement.click();
        loginBtnWebElement.click();
        exWait.until(ExpectedConditions.visibilityOfElementLocated(accountsPageTitle));
        return new PersonalAccountsPage(driver);
    }
}
