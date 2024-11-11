package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EditAccountPage extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;
    private static int flag = 0;

    public EditAccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver, Duration.ofSeconds(10));}

    @FindBy(id = "spn_title")
    private WebElement editAccountTitleWebElement;

    @FindBy(id = "span_deps")
    private WebElement departmentNameWebElement;

    @FindBy(id = "span_emp")
    private WebElement userNameWebElement;

    @FindBy(name = "left")
    private WebElement leftButtonWebElement;

    @FindBy(name = "right")
    private WebElement rightButtonWebElement;

    @FindBy(name = "all_right")
    private WebElement allRightButtonWebElement;

    @FindBy(name = "all_left")
    private WebElement allLeftButtonWebElement;

    @FindBy(xpath = "//input[@value='حفظ']")
    private WebElement saveButtonWebElement;

    @FindBy(xpath = "//input[@value='عودة']")
    private WebElement backButtonWebElement;

    @FindBy(xpath = "//p[@id='btnP0']//input[@value='موافق']")
    private WebElement confirmButtonWebElement;

    @FindBy(xpath = "//input[@value='غير موافق']")
    private WebElement notAgreeButtonWebElement;

    @FindBy(css = "div.popup_content")
    private WebElement ErrorMSWebElement;


    @FindBy(css = "select[name='66 الإعدادات']") // Replace 'yourSelectName'
    private WebElement selectSettingsWebElement;

    @FindBy(css = "select[name='66 الإعدادات -البريد']") // Replace 'yourSelectName'
    private WebElement selectPostSettingsElement;


    public void clickSettingsOption() {
        Select select = new Select(selectPostSettingsElement);
        select.selectByValue("29390");}

    public void clickPostSettingsOption() {
        Select select = new Select(selectSettingsWebElement);
        select.selectByValue("27549");}

    public String getTitleText() {
        return editAccountTitleWebElement.getText();}

    public String getDepartmentName() {
        return departmentNameWebElement.getText();}

    public String getSpanText() {
        return userNameWebElement.getText();}

    public void clickLeftButton() {
        leftButtonWebElement.click();}

    public void clickRightButton() {
        rightButtonWebElement.click();}

    public void clickAllRightButton() {
        allRightButtonWebElement.click();}

    public void clickAllLeftButton() {
        allLeftButtonWebElement.click();}

    public void clickSaveButton() {
        saveButtonWebElement.click();}

    public void clickBackButton() {
        backButtonWebElement.click();}

    public void clickConfirmButton() {
        confirmButtonWebElement.click();}

    public void clickNotAgreeButton() {
        notAgreeButtonWebElement.click();}

    public String getErrorMS() {
        exWait.until(ExpectedConditions.visibilityOf(ErrorMSWebElement));
        return ErrorMSWebElement.getText();}




}
