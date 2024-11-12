package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import AutomateMakeen.Pages.CreateExternalEditAccountPage;

public class EditAccountPage extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;

    public EditAccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver, Duration.ofSeconds(10));}


    @FindBy(name = "left")
    private WebElement leftButtonWebElement;

    @FindBy(name = "right")
    private WebElement rightButtonWebElement;

    @FindBy(name = "all_right")
    private WebElement allLeftButtonWebElement;

    @FindBy(name = "all_left")
    private WebElement allRightButtonWebElement;

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

    @FindBy(id = "slc_all_activities")
    private WebElement selectAllRightEmptyWebElement;

    @FindBy(id = "slc_user_activities")
    private WebElement selectAllLeftEmptyWebElement;

    @FindBy(xpath = "//*[@id='slc_all_activities']")
    private WebElement ArchiveSearchViewTransactionWebElement;

    @FindBy(xpath = "//*[@id='slc_all_activities']/option[2]")
    private WebElement DeleteEmployeeWebElement;

    @FindBy(xpath = "//*[@id='spn_title']")
    private WebElement editAccountTitleWebElement;

    @FindBy(xpath = "//*[@id='span_deps']")
    private WebElement departmentNameWebElement;

    @FindBy(xpath = "//*[@id='span_emp']")
    private WebElement userNameWebElement;


    public String getTitleText() {
        return editAccountTitleWebElement.getText();}

    public String getDepartmentName() {
        return departmentNameWebElement.getText();}

    public String getSUserName() {
        return userNameWebElement.getText();}

    public void clickArchiveTransaction() {
        Select select = new Select(ArchiveSearchViewTransactionWebElement);
        select.selectByValue("42966");}

    public boolean isSelectSystemEmpty() {
        Select select = new Select(selectAllRightEmptyWebElement);
        return select.getAllSelectedOptions().isEmpty();}

    public boolean isSelectUserEmpty() {
        Select select = new Select(selectAllLeftEmptyWebElement);
        return select.getAllSelectedOptions().isEmpty();}

    public void clickDeleteEmployeeWeb() {
        Select select = new Select(DeleteEmployeeWebElement);
        select.selectByValue("42967");}

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

    public void clickAgreeButton() {
        confirmButtonWebElement.click();}

    public void clickNotAgreeButton() {
        notAgreeButtonWebElement.click();}

    public String getErrorMS() {
        exWait.until(ExpectedConditions.visibilityOf(ErrorMSWebElement));
        return ErrorMSWebElement.getText();}




}
