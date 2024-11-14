package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EditAccountPage extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;

    public EditAccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver, Duration.ofSeconds(10));}

    @FindBy(name = "left")
    private WebElement leftButtonWebElement;
    public void clickLeftButton() {
        leftButtonWebElement.click();}


    @FindBy(name = "right")
    private WebElement rightButtonWebElement;
    public void clickRightButton() {
        rightButtonWebElement.click();}


    @FindBy(name = "all_right")
    private WebElement allLeftButtonWebElement;
    public void clickAllLeftButton() {
        allLeftButtonWebElement.click();}


    @FindBy(name = "all_left")
    private WebElement allRightButtonWebElement;
    public void clickAllRightButton() {
        allRightButtonWebElement.click();}


    @FindBy(xpath = "//input[@value='حفظ']")
    private WebElement saveButtonWebElement;
    public void clickSaveButton() {
        saveButtonWebElement.click();}


    @FindBy(xpath = "//input[@value='عودة']")
    private WebElement backButtonWebElement;
    public void clickBackButton() {
        backButtonWebElement.click();}


    @FindBy(xpath = "//p[@id='btnP0']//input[@value='موافق']")
    private WebElement confirmButtonWebElement;
    public void clickAgreeButton() {
        confirmButtonWebElement.click();}


    @FindBy(xpath = "//input[@value='غير موافق']")
    private WebElement notAgreeButtonWebElement;
    public void clickNotAgreeButton() {
        notAgreeButtonWebElement.click();}


    @FindBy(css = "div.popup_content")
    private WebElement ErrorMSWebElement;
    public String getErrorMS() {
        exWait.until(ExpectedConditions.visibilityOf(ErrorMSWebElement));
        return ErrorMSWebElement.getText();}


    @FindBy(id = "slc_all_activities")
    private WebElement selectAllRightEmptyWebElement;
    public boolean isSelectSystemEmpty() {
        Select select = new Select(selectAllRightEmptyWebElement);
        return select.getAllSelectedOptions().isEmpty();}

    public boolean isScrollBarWorkingForArchive() {
        Actions actions = new Actions(driver);
        actions.moveToElement(selectAllRightEmptyWebElement).click().sendKeys(Keys.END).perform();
        actions.moveToElement(selectAllRightEmptyWebElement).click().sendKeys(Keys.HOME).perform();
        WebElement lastOption = selectAllRightEmptyWebElement.findElement(By.xpath(".//option[last()]"));
        WebElement firstOption = selectAllRightEmptyWebElement.findElement(By.xpath(".//option[1]"));
        boolean isLastOptionVisible = lastOption.isDisplayed();
        boolean isFirstOptionVisible = firstOption.isDisplayed();
        return isLastOptionVisible && isFirstOptionVisible;}


    @FindBy(id = "slc_user_activities")
    private WebElement selectAllLeftEmptyWebElement;
    public boolean isSelectUserEmpty() {
        Select select = new Select(selectAllLeftEmptyWebElement);
        return select.getAllSelectedOptions().isEmpty();}

    public boolean isScrollBarWorkingForUser() {
        Actions actions = new Actions(driver);
        actions.moveToElement(selectAllLeftEmptyWebElement).click().sendKeys(Keys.END).perform();
        actions.moveToElement(selectAllLeftEmptyWebElement).click().sendKeys(Keys.HOME).perform();
        WebElement lastOption = selectAllLeftEmptyWebElement.findElement(By.xpath("option[last()]"));
        WebElement firstOption = selectAllLeftEmptyWebElement.findElement(By.xpath("option[1]"));
        boolean isLastOptionVisible = lastOption.isDisplayed();
        boolean isFirstOptionVisible = firstOption.isDisplayed();
        return isLastOptionVisible && isFirstOptionVisible;}


    @FindBy(xpath = "//*[@id='slc_all_activities']")
    private WebElement ArchiveSearchViewTransactionWebElement;
    public void clickArchiveTransaction() {
        Select select = new Select(ArchiveSearchViewTransactionWebElement);
        select.selectByValue("42966");}


    @FindBy(xpath = "//*[@id='slc_all_activities']/option[2]")
    private WebElement DeleteEmployeeWebElement;
    public void clickDeleteEmployeeWeb() {
        Select select = new Select(DeleteEmployeeWebElement);
        select.selectByValue("42967");}


    @FindBy(xpath = "//*[@id='spn_title']")
    private WebElement editAccountTitleWebElement;
    public String getTitleText() {
        return editAccountTitleWebElement.getText();}

    public boolean isNameFieldInteractive() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", departmentNameWebElement);
        WebElement activeElement = driver.switchTo().activeElement();
        return activeElement.equals(departmentNameWebElement);}


    @FindBy(xpath = "//*[@id='span_deps']")
    private WebElement departmentNameWebElement;
    public String getDepartmentName() {
        return departmentNameWebElement.getText();}

    public boolean isDepartmentFieldInteractive() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", departmentNameWebElement);
        WebElement activeElement = driver.switchTo().activeElement();
        return activeElement.equals(departmentNameWebElement);}



    @FindBy(xpath = "//*[@id='span_emp']")
    private WebElement userNameWebElement;
    public String getSUserName() {
        return userNameWebElement.getText();}


    @FindBy(css = ".fas.fa-power-off")
    private WebElement powerOffIconWebElement;
    public void clickPowerOffIcon() {
        powerOffIconWebElement.click();}


    @FindBy(xpath = "//a[@onclick='masterPage.addClassToTree(this)']")
    private WebElement archiveLinkWebElement;
    public void clickArchiveLink() {
        archiveLinkWebElement.click();}


    @FindBy(id = "s_m_73")
    private WebElement searchLinkWebElement;
    public void clickSearchLink() {
        searchLinkWebElement.click();}


    @FindBy(xpath = "//*[@id='div_maken_content']/div/div[1]/div/div/div/div[1]/h1")
    private WebElement headingWebElement;
    public String getSearchHeadingText() {
        return headingWebElement.getText();}


    @FindBy (id = "chk_mob_code")
    private WebElement MobileCheckBoxWebElement;
    public void clickMobileChickBox() {
        MobileCheckBoxWebElement.click();}


    @FindBy(id = "chk_email_code")
    private WebElement MailCheckBoxWebElement;
    public void clickEmailChickBox() {
        MailCheckBoxWebElement.click();}


    @FindBy(id = "chk_empMobile")
    private WebElement LoginMobileCheckboxWebElement;
    public boolean IsMobileCheckboxChecked() {
        return LoginMobileCheckboxWebElement.isSelected();}


    @FindBy(id = "chk_empMail")
    private WebElement LoginMailCheckboxWebElement;
    public boolean IsMailCheckboxChecked() {
        return LoginMailCheckboxWebElement.isSelected();}


    @FindBy(css = "a.lnk_bck[onclick='userLogin.backToLoginPage();']")
    private WebElement backLinkWebElement;
    public void clickBackToLoginPage() {
        backLinkWebElement.click();}


    @FindBy(id = "chk_prs_emp")
    private WebElement prsEmpCheckboxWebElement;
    public void clickPrsEmpCheckbox() {
        prsEmpCheckboxWebElement.click();}
    public boolean IsPersonCheckBoxIsClicked() {
        return prsEmpCheckboxWebElement.isSelected();}


    @FindBy(id = "chk_arch_emp")
    private WebElement archEmpCheckboxWebElement;
    public void clickArchEmpCheckbox() {
        archEmpCheckboxWebElement.click();}
    public boolean IsArchPersonCheckBoxIsClicked() {
        return archEmpCheckboxWebElement.isSelected();}


    @FindBy(id = "chk_ayeMakeenUser")
    private WebElement MakeenUserCheckboxWebElement;
    public void clickMakeenUserCheckbox() {
        MakeenUserCheckboxWebElement.click();}
    public boolean IsMakeenCheckBoxIsClicked() {
        return archEmpCheckboxWebElement.isSelected();}


    @FindBy(xpath = "//*[@id='span_A_slc_user_activities']")
    WebElement UserRedCircleWebElement;
    public boolean IsUserRedCircleElementPresent() {
    return this.UserRedCircleWebElement != null && this.UserRedCircleWebElement.isDisplayed();}


}
