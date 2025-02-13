package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class HR_Employee_Edit extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;
    public HR_Employee_Edit(WebDriver driver) {
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
    }

    private By fNameBy = By.id("txt_gov_emp_fname");
    private By sNameBy = By.id("txt_gov_emp_sname");
    private By tNameBy = By.id("txt_gov_emp_tname");
    private By lNameBy = By.id("txt_gov_emp_lname");

    public void empName(String fName ,String sName , String tName , String lName){
        driver.findElement(fNameBy).sendKeys(fName);
        driver.findElement(sNameBy).sendKeys(sName);
        driver.findElement(tNameBy).sendKeys(tName);
        driver.findElement(lNameBy).sendKeys(lName);
    }

    public void clearEmpName(){
        driver.findElement(fNameBy).clear();
        driver.findElement(sNameBy).clear();
        driver.findElement(tNameBy).clear();
        driver.findElement(lNameBy).clear();
    }

    private By fEngNameBy = By.id("txt_gov_emp_fname_eng");
    private By sEngNameBy = By.id("txt_gov_emp_sname_eng");
    private By tEngNameBy = By.id("txt_gov_emp_tname_eng");
    private By lEngNameBy = By.id("txt_gov_emp_lname_eng");

    public void empEngName(String fName ,String sName , String tName , String lName){
        driver.findElement(fEngNameBy).sendKeys(fName);
        driver.findElement(sEngNameBy).sendKeys(sName);
        driver.findElement(tEngNameBy).sendKeys(tName);
        driver.findElement(lEngNameBy).sendKeys(lName);
    }

    private By signtureNameBy = By.id("txt_gov_emp_signature");
    private By signtureEngNameBy = By.id("txt_gov_emp_signature_eng");
    public void empSignature(String signatureName, String signatureEngName){
        driver.findElement(signtureNameBy).sendKeys(signatureName);
        driver.findElement(signtureEngNameBy).sendKeys(signatureEngName);
    }

    private By idNumBy = By.id("txt_gov_emp_nat_num");
    private By mobileNumBy = By.id("txt_gov_emp_mobile_num");
    private By mailBy = By.id("txt_gov_emp_email");
    private By maleGenderBy = By.id("rd_gov_emp_male");
    private By femaleGenderBy = By.id("rd_gov_emp_female");

    public void empPersonalDetails(String empId, String mobile , String mail , String gender){
        driver.findElement(idNumBy).sendKeys(empId);
        driver.findElement(mobileNumBy).sendKeys(mobile);
        driver.findElement(mailBy).sendKeys(mail);
        switch(gender){
            case "male":driver.findElement(maleGenderBy).click();break;
            case "female":driver.findElement(femaleGenderBy).click();break;
        }
    }
    private By empTreatJobFirstSelectionBy = By.cssSelector("#drp_gov_emp_mandate_job_collapsibleDiv .list_child div");
    private By empTreatJobDDlBy = By.id("drp_gov_emp_mandate_job");
    private By empPhoneInLocalBy = By.id("txt_gov_emp_user_id");
    private By empPhoneInGovBy = By.id("txt_gov_emp_ministry_num");
    private By empPhoneAlterBy = By.id("txt_TransferNo");
    private By hireDateBy = By.id("txt_gov_emp_hire_date");

    public void empJobDetails(String empPhoneLocal , String empPhoneGov , String empPhoneAlter){
        driver.findElement(empTreatJobDDlBy).click();
        driver.findElement(empTreatJobFirstSelectionBy).click();
        driver.findElement(empPhoneInLocalBy).sendKeys(empPhoneLocal);
        driver.findElement(empPhoneInGovBy).sendKeys(empPhoneGov);
        driver.findElement(empPhoneAlterBy).sendKeys(empPhoneAlter);
        driver.findElement(hireDateBy).sendKeys(Keys.SPACE);
        driver.findElement(hireDateBy).sendKeys(Keys.SPACE);
        driver.findElement(hireDateBy).sendKeys(Keys.SPACE);

    }

    private By attachmentContainerBy = By.id("attachDiv_file_name_0");
    // Method to validate if the file was added successfully
    public boolean validateFileAdded(){
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(attachmentContainerBy)));
        return driver.findElement(attachmentContainerBy).isDisplayed();
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

    private By saveBtnBy = By.cssSelector("input[onclick='employees.empForm.govEmployee.saveData();']");
    private By confirmSaveBtnBy = By.cssSelector("input[value='موافق']");
    public void clickSaveBtn(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");
        driver.findElement(saveBtnBy).click();
        driver.findElement(confirmSaveBtnBy).click();
    }

    private By backBtnBy = By.cssSelector("div[id='cph_main_div_complete_emp_data'] input[value='عودة']");
    public void clickBackBtn(){
        driver.findElement(backBtnBy).click();
        driver.findElement(confirmSaveBtnBy).click();

    }
}
