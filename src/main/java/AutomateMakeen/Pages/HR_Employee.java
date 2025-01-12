package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HR_Employee extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;
    public HR_Employee(WebDriver driver) {
        super(driver);
        this.driver = driver;
        //specific wait for every page
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
    }

    private By fNameFieldBy = By.id("txt_srch_fname");
    private By sNameFieldBy = By.id("txt_srch_sname");
    private By tNameFieldBy = By.id("txt_srch_tname");
    private By lNameFieldBy = By.id("txt_srch_lname");

    public void searchByName(String name , int number){
        switch (number){
            case 1:
                driver.findElement(fNameFieldBy).sendKeys(name);
                break;
            case 2:
                driver.findElement(sNameFieldBy).sendKeys(name);
                break;
            case 3:
                driver.findElement(tNameFieldBy).sendKeys(name);
                break;
            case 4:
                driver.findElement(lNameFieldBy).sendKeys(name);
                break;
            default:
                System.out.println("Invalid search number");
        }
    }
    private By searchBtnBy = By.id("btn_Search");
    public void clickSearchBtn(){
        driver.findElement(searchBtnBy).click();
    }
    public boolean validateSearchByName(String name){
        try{
            driver.findElement(By.xpath("//table[@id='tbl_employees']/tbody/tr/td[3]/div[text()='"+name+"']"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private By empNumBy = By.id("txt_srch_user_id");
    public void searchByEmpNum(String empNum) {
        driver.findElement(empNumBy).sendKeys(empNum);
    }
    public boolean validateSearchByEmpNum(String empNum){
        try{
            driver.findElement(By.xpath("//table[@id='tbl_employees']/tbody/tr/td[2]/div[text()='"+empNum+"']"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private By empIdBy = By.id("txt_srch_Nation_num");
    public void searchByEmpId(String empId) {
        driver.findElement(empIdBy).sendKeys(empId);
    }
    public boolean validateSearchByEmpId(String empNum){
        return driver.findElement(By.xpath("//table[@id='tbl_employees']/tbody/tr/td[2]/div")).isDisplayed();
    }

    private By empMobileNumBy = By.id("txt_srch_Mob_num");
    public void searchByMobileNum(String MobileNum) {
        driver.findElement(empMobileNumBy).sendKeys(MobileNum);
    }
    public boolean validateSearchByMobileNum(String mobileNum) {
        try{
            driver.findElement(By.xpath("//table[@id='tbl_employees']/tbody/tr/td[2]/div[text()='"+mobileNum+"']"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private By addPageBy = By.id("cph_main_btn_add");
    public HR_Employee_Add goToAddPage(){
        driver.findElement(addPageBy).click();
        return new HR_Employee_Add(driver);
    }

    private By editPageBy = By.id("cph_main_btn_edit");
    public HR_Employee_Edit goToEditPage(){
        driver.findElement(editPageBy).click();
        return new HR_Employee_Edit(driver);
    }
}
