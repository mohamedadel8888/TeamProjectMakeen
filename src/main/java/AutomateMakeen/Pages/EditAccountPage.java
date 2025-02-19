package AutomateMakeen.Pages;
import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


// الفئة الرئيسية التي تحتوي على الطرق للاستخدام مع متصفح الويب
public class EditAccountPage extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;


    // الدرايفر الذي يقوم بتهيئة عناصر الصفحة
    public EditAccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver, Duration.ofSeconds(10));}


    // العثور على زر "left" والنقر عليه
    @FindBy(name = "left")
    private WebElement leftButtonWebElement;
    public void clickLeftButton() {
        leftButtonWebElement.click();}


    // العثور على زر "right" والنقر عليه
    @FindBy(name = "right")
    private WebElement rightButtonWebElement;
    public void clickRightButton() {
        rightButtonWebElement.click();}


    // العثور على زر "all_right" والنقر عليه
    @FindBy(name = "all_right")
    private WebElement allLeftButtonWebElement;
    public void clickAllLeftButton() {
        allLeftButtonWebElement.click();}


    // العثور على زر "all_left" والنقر عليه
    @FindBy(name = "all_left")
    private WebElement allRightButtonWebElement;
    public void clickAllRightButton() {
        allRightButtonWebElement.click();}


    // العثور على زر الحفظ والنقر عليه
    @FindBy(xpath = "//input[@value='حفظ']")
    private WebElement saveButtonWebElement;
    public void clickSaveButton() {
        saveButtonWebElement.click();}


    // العثور على زر العودة والنقر عليه
    @FindBy(xpath = "//input[@value='عودة']")
    private WebElement backButtonWebElement;
    public void  clickBackButton() {
        backButtonWebElement.click();}


    // العثور على زر التأكيد والنقر عليه
    @FindBy(xpath = "//p[@id='btnP0']//input[@value='موافق']")
    private WebElement confirmButtonWebElement;
    public void clickAgreeButton() {
        confirmButtonWebElement.click();}


    // العثور على زر عدم الموافقة والنقر عليه
    @FindBy(xpath = "//input[@value='غير موافق']")
    private WebElement notAgreeButtonWebElement;
    public void clickNotAgreeButton() {
        notAgreeButtonWebElement.click();}


    // العثور على عنصر رسالة الخطأ واسترجاع النص الخاص بها
    @FindBy(css = "div.popup_content")
    private WebElement ErrorMSWebElement;
    public String getErrorMS() {
        exWait.until(ExpectedConditions.visibilityOf(ErrorMSWebElement));
        return ErrorMSWebElement.getText();}

    // العثور على عنصر تحديد الأنشطة في حقل الارشيف والنظر إذا كان فارغاً
    @FindBy(id = "slc_all_activities")
    private WebElement selectAllRightEmptyWebElement;
    public boolean isSelectSystemEmpty() {
        Select select = new Select(selectAllRightEmptyWebElement);
        return select.getAllSelectedOptions().isEmpty();}


    // اختبار الاسكرول في حقل الارشيف
        public boolean isScrollBarWorkingForArchive() {
        Actions actions = new Actions(driver);
        actions.moveToElement(selectAllRightEmptyWebElement).click().sendKeys(Keys.END).perform();
        actions.moveToElement(selectAllRightEmptyWebElement).click().sendKeys(Keys.HOME).perform();
        WebElement lastOption = selectAllRightEmptyWebElement.findElement(By.xpath(".//option[last()]"));
        WebElement firstOption = selectAllRightEmptyWebElement.findElement(By.xpath(".//option[1]"));
        boolean isLastOptionVisible = lastOption.isDisplayed();
        boolean isFirstOptionVisible = firstOption.isDisplayed();
        return isLastOptionVisible && isFirstOptionVisible;}


    //  تحديد كل الأنشطة في حقل المستخدم والنظر إذا كان فارغاً
    @FindBy(id = "slc_user_activities")
    private WebElement selectAllLeftEmptyWebElement;
    public boolean isSelectUserEmpty() {
        Select select = new Select(selectAllLeftEmptyWebElement);
        return select.getAllSelectedOptions().isEmpty();}


    // اختبار الاسكرول في حقل المستخدم

    public boolean isScrollBarWorkingForUser() {
        Actions actions = new Actions(driver);
        actions.moveToElement(selectAllLeftEmptyWebElement).click().sendKeys(Keys.END).perform();
        actions.moveToElement(selectAllLeftEmptyWebElement).click().sendKeys(Keys.HOME).perform();
        WebElement lastOption = selectAllLeftEmptyWebElement.findElement(By.xpath("option[last()]"));
        WebElement firstOption = selectAllLeftEmptyWebElement.findElement(By.xpath("option[1]"));
        boolean isLastOptionVisible = lastOption.isDisplayed();
        boolean isFirstOptionVisible = firstOption.isDisplayed();
        return isLastOptionVisible && isFirstOptionVisible;}


    // اختيار مهمة محددة "42966" من حقل الارشيف
    @FindBy(xpath = "//*[@id='slc_all_activities']")
    private WebElement ArchiveSearchViewTransactionWebElement;
    public void clickArchiveTransaction() {
        Select select = new Select(ArchiveSearchViewTransactionWebElement);
        select.selectByValue("42966");}

    // العثور على عنوان صفحة تعديل الحساب
    @FindBy(xpath = "//*[@id='spn_title']")
    private WebElement editAccountTitleWebElement;
    public String getTitleText() {
        return editAccountTitleWebElement.getText();}


    // العثور على خانة اسم الادارة
    @FindBy(xpath = "//*[@id='span_deps']")
    private WebElement departmentNameWebElement;
    public String getDepartmentName() {
        return departmentNameWebElement.getText();}

    // اختبار امكانية الكتابة في حقل اسم الادارة
    public boolean isDepartmentFieldInteractive() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", departmentNameWebElement);
        WebElement activeElement = driver.switchTo().activeElement();
        return activeElement.equals(departmentNameWebElement);}


    // العثور على خانة اسم المستخدم
    @FindBy(xpath = "//*[@id='span_emp']")
    private WebElement userNameWebElement;
    public String getSUserName() {
        return userNameWebElement.getText();}

    // اختبار امكانية الكتابة في حقل اسم المستخدم
    public boolean isNameFieldInteractive() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", departmentNameWebElement);
        WebElement activeElement = driver.switchTo().activeElement();
        return activeElement.equals(departmentNameWebElement);}


    // الضغط على زر تسجيل الخروج
    @FindBy(css = ".fas.fa-power-off")
    private WebElement powerOffIconWebElement;
    public void clickPowerOffIcon() {
        powerOffIconWebElement.click();}

    // الضغط على خانة الارشيف
    @FindBy(xpath = "//a[@onclick='masterPage.addClassToTree(this)']")
    private WebElement archiveLinkWebElement;
    public void clickArchiveLink() {
        archiveLinkWebElement.click();}

    //  الضغط على البحث
    @FindBy(id = "s_m_73")
    private WebElement searchLinkWebElement;
    public void clickSearchLink() {
        searchLinkWebElement.click();}

    // العثور على عنوان الصفحة
    @FindBy(xpath = "//*[@id='div_maken_content']/div/div[1]/div/div/div/div[1]/h1")
    private WebElement headingWebElement;
    public String getSearchHeadingText() {
        return headingWebElement.getText();}

    // الضغط على صندق اختيار الموبايل لتسجيل الدخول
    @FindBy (id = "chk_mob_code")
    private WebElement MobileCheckBoxWebElement;
    public void clickMobileChickBox() {
        MobileCheckBoxWebElement.click();}


    // الضغط على صندوق اختيار الايميل لتسجيل الدخول
    @FindBy(id = "chk_email_code")
    private WebElement MailCheckBoxWebElement;
    public void clickEmailChickBox() {
        MailCheckBoxWebElement.click();}

    // اختبار ان صندوق اختيار الموبايل مفعل
    @FindBy(id = "chk_empMobile")
    private WebElement LoginMobileCheckboxWebElement;
    public boolean IsMobileCheckboxChecked() {
        return LoginMobileCheckboxWebElement.isSelected();}

    // اختبار ان صندوق احتيار الايميل مفعل
    @FindBy(id = "chk_empMail")
    private WebElement LoginMailCheckboxWebElement;
    public boolean IsMailCheckboxChecked() {
        return LoginMailCheckboxWebElement.isSelected();}

    // الضغط على الرجوع لصفحة تسجيل الدخول
    @FindBy(css = "a.lnk_bck[onclick='userLogin.backToLoginPage();']")
    private WebElement backLinkWebElement;
    public void clickBackToLoginPage() {
        backLinkWebElement.click();}

    // الضفط على صندوق اختيار موظف ارشفة شؤن الموظفين والتأكد انه مفعل
    @FindBy(id = "chk_prs_emp")
    private WebElement prsEmpCheckboxWebElement;
    public void clickPrsEmpCheckbox() {
        prsEmpCheckboxWebElement.click();}
    public boolean IsPersonCheckBoxIsClicked() {
        return prsEmpCheckboxWebElement.isSelected();}


    // الضفط على صندوق اختيار موظف موظف ارضفة المعاملات والتأكد انه مفعل
    @FindBy(id = "chk_arch_emp")
    private WebElement archEmpCheckboxWebElement;
    public void clickArchEmpCheckbox() {
        archEmpCheckboxWebElement.click();}
    public boolean IsArchPersonCheckBoxIsClicked() {
        return archEmpCheckboxWebElement.isSelected();}


    // الضفط على صندوق اختيار مستخدم عين مكين والتأكد انه مفعل
    @FindBy(id = "chk_ayeMakeenUser")
    private WebElement MakeenUserCheckboxWebElement;
    public void clickMakeenUserCheckbox() {
        MakeenUserCheckboxWebElement.click();}
    public boolean IsMakeenCheckBoxIsClicked() {
        return archEmpCheckboxWebElement.isSelected();}


    // التأكد من رسالة التحذير ظاهرة
    @FindBy(xpath = "//*[@id='span_A_slc_user_activities']")
    WebElement UserRedCircleWebElement;
    public boolean IsUserRedCircleElementPresent() {
    return this.UserRedCircleWebElement != null && this.UserRedCircleWebElement.isDisplayed();}


}
