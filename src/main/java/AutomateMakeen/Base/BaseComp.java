package AutomateMakeen.Base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.chrono.HijrahDate;

import java.io.File;
import java.time.Duration;

public class BaseComp {
    private WebDriver driver;
    private  WebDriverWait exWait;
    private Actions actions ;
    public ContentAside contentAside ;
    ;
    public BaseComp(WebDriver driver)
    {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
        exWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        this.driver = driver;
    }
    public void cutPastAction(WebElement copyFrom, WebElement copyTo, String txt ){
        actions = new Actions(driver);
        copyFrom.sendKeys(txt);
        actions.moveToElement(copyFrom).keyDown(Keys.CONTROL).sendKeys("a").sendKeys("x").keyUp(Keys.CONTROL).build().perform();
        copyTo.sendKeys("");
        actions.moveToElement(copyTo).keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
    }
    public void tapAction(){
        actions = new Actions(driver);
        actions.keyDown(Keys.TAB).keyUp(Keys.TAB).build().perform();
    }

    public void hoverOnAction(WebElement webElement){
        actions = new Actions(driver);
        actions.moveToElement(webElement).build().perform();
    }

    public String getValidatorState(WebElement validator){
        switch(validator.getAttribute("class")){
            case "fa fa-question-circle redText": return "Red Circle";
            case "fa arow-none fa-asterisk":
            case "fa fa-asterisk redText": return "Asterisk";
            default: return "Error !!";
        }
    }
    @FindBy(id = "txt_pickPopUp_srchParam")
    WebElement docTypeControlTxtSearchWebElement;

    @FindBy(id = "btn_pickPopUp_srch")
    WebElement docTypeControlSearchBtnWebElement;

    @FindBy(id = "btn_session_time_out")
    WebElement signOutBtn;

    public void control(WebElement openControlWebElement,String searchTxt){
        openControlWebElement.click();
        try{
            docTypeControlTxtSearchWebElement.sendKeys(searchTxt);
            docTypeControlSearchBtnWebElement.click();
            driver.findElement(By.xpath("//div[@title='"+searchTxt+"']/../../td/input")).click();
        }catch(Exception e){
            try{
                driver.findElement(By.cssSelector("div[class='content_bx'] input[type='text']")).sendKeys(searchTxt);
                driver.findElement(By.cssSelector("div[class='btn-bx row-bx'] input[value='بحث']")).click();
                driver.findElement(By.xpath("//div[@full_title='"+searchTxt+"']/../../td/input")).click();
            }catch (Exception s){
                driver.findElement(By.xpath("//div[text()='"+searchTxt+"']/../../td/input")).click();
                driver.findElement(By.cssSelector("li[id='saveBtn_div_master_controls'] p")).click();

            }


        }
    }

    public LoginPage signOut(){
        signOutBtn.click();
        return new LoginPage(driver);
    }

    private By addAttachmentBy = By.id("btn_add_file");
    private By fileNameWebElementBy = By.id("attachDiv_txt_FileName");
    private By filePathBy = By.id("attachDiv_btn_ChooseFile");
    private By confirmAddBtnBy = By.cssSelector("li p");
    public boolean addFile(String fileName, String path) throws InterruptedException {
        driver.findElement(addAttachmentBy).click();
        driver.findElement(fileNameWebElementBy).sendKeys(fileName);
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        driver.findElement(filePathBy).sendKeys(absolutePath);
        driver.findElement(filePathBy).clear();
        boolean flag = validateFileAdded();
        driver.findElement(confirmAddBtnBy).click();
        return flag;
    }

    private By treatmentJobBy = By.id("drp_gov_emp_mandate_job_ddlSelectButtonTarget");
    public String getTreatJob(){
        return driver.findElement(treatmentJobBy).getText();
    }

    private By attachmentContainerBy = By.id("attachDiv_file_name_0");
    // Method to validate if the file was added successfully
    public boolean validateFileAdded(){
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(attachmentContainerBy)));
        return driver.findElement(attachmentContainerBy).isDisplayed();
    }
    private By logoutBy = By.cssSelector(".fas.fa-power-off");
    public LoginPage logOut(){
        driver.findElement(logoutBy).click();
        return new LoginPage(driver);
    }
    private By ddlSearchDeptBy = By.id("drp_dept_txtSearch");

    public void selectFromSearchableDDL(WebElement openDDL , String data) {
        openDDL.click();
        driver.findElement(ddlSearchDeptBy).sendKeys(data);
        driver.findElement(By.xpath("//ul[@id='drp_dept_collapsibleDiv']/li/div/label[text()='" + data + "']")).click();
    }
    private By defaultChooseInList = By.cssSelector("li[value='-1'][class='unSelectedElement']");
    public void clearSearchableDDL(WebElement openDDL) {
        openDDL.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTop = 0;", driver.findElement(By.id("drp_dept_collapsibleDiv")));
        driver.findElement(defaultChooseInList).click();
    }
    @FindBy(css = "li[onclick='goto_mainpage();'] a")
    WebElement homePageIconWebElement;
    public void goToHomePage(){
        homePageIconWebElement.click();
    }

    public static String getHijriDate() {
        // Get the current date in the Hijri calendar
        HijrahDate hijriDate = HijrahDate.from(LocalDate.now());

        // Format the Hijri date in "yyyy/m/d" format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        return formatter.format(hijriDate);
    }
}
