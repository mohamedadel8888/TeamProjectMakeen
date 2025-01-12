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

public class HR_Employee_View extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;
    public HR_Employee_View(WebDriver driver) {
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
    }

    private By fNameBy = By.id("spn_gov_emp_fname");
    private By sNameBy = By.id("spn_gov_emp_sname");
    private By tNameBy = By.id("spn_gov_emp_tname");
    private By lNameBy = By.id("spn_gov_emp_lname");

    public boolean validateEmpName(String fName ,String sName , String tName , String lName){
        return  driver.findElement(fNameBy).getText().equals(fName) &&
                driver.findElement(sNameBy).getText().equals(sName) &&
                driver.findElement(tNameBy).getText().equals(tName) &&
                driver.findElement(lNameBy).getText().equals(lName);
    }

    private By fEngNameBy = By.id("spn_gov_emp_fname_eng");
    private By sEngNameBy = By.id("spn_gov_emp_sname_eng");
    private By tEngNameBy = By.id("spn_gov_emp_tname_eng");
    private By lEngNameBy = By.id("spn_gov_emp_lname_eng");

    public boolean validateEmpEngName(String fName ,String sName , String tName , String lName){
        return  driver.findElement(fEngNameBy).getText().equals(fName) &&
                driver.findElement(sEngNameBy).getText().equals(sName) &&
                driver.findElement(tEngNameBy).getText().equals(tName) &&
                driver.findElement(lEngNameBy).getText().equals(lName);
    }

    private By signtureNameBy = By.id("spn_gov_emp_signature");
    private By signtureEngNameBy = By.id("spn_gov_emp_signature_eng");
    public boolean validateEmpSignature(String signArabic ,String signEng){
        return  driver.findElement(signtureNameBy).getText().equals(signArabic)&&
                driver.findElement(signtureEngNameBy).getText().equals(signEng);
    }

    private By idNumBy = By.id("spn_gov_emp_nat_num");
    private By mobileNumBy = By.id("spn_gov_emp_mobile_num");
    private By mailBy = By.id("spn_gov_emp_email");
    private By maleGenderBy = By.id("rd_gov_emp_male_show");
    private By femaleGenderBy = By.id("rd_gov_emp_female_show");

    public boolean validateEmpPersonalDetails(String empId, String mobile , String mail , String gender){
        return  driver.findElement(idNumBy).getText().equals(empId)&&
                driver.findElement(mobileNumBy).getText().equals(mobile)&&
                driver.findElement(mailBy).getText().equals(mail)&&
                driver.findElement(maleGenderBy).getAttribute("value").equals("0");
    }
    private By empTreatJobDDlBy = By.id("spn_gov_emp_mandate_job");
    private By empPhoneInLocalBy = By.id("spn_gov_emp_user_id");
    private By empPhoneInGovBy = By.id("spn_gov_emp_ministry_num");
    private By empPhoneAlterBy = By.id("spn_transferNum");

    public boolean validateEmpJobDetails(String treatJob, String empPhoneLocal , String empPhoneGov , String empPhoneAlter){
//        System.out.println(driver.findElement(empTreatJobDDlBy).getText());
//        System.out.println(driver.findElement(empPhoneInLocalBy).getText());
//        System.out.println(driver.findElement(empPhoneInGovBy).getText());
//        System.out.println(driver.findElement(empPhoneAlterBy).getText());
        return  driver.findElement(empTreatJobDDlBy).getText().equals(treatJob)&&
                driver.findElement(empPhoneInLocalBy).getText().equals(empPhoneLocal)&&
                driver.findElement(empPhoneInGovBy).getText().equals(empPhoneGov)&&
                driver.findElement(empPhoneAlterBy).getText().equals(empPhoneAlter);
    }

    private By attachmentContainerBy = By.cssSelector("div[attachcontainer='spn_divAttchesContainer']");
    // Method to validate if the file was added successfully
    public boolean validateFileAdded(){
        return driver.findElement(attachmentContainerBy).isDisplayed();
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
