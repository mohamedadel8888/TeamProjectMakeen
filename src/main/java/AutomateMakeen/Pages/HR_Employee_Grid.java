package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HR_Employee_Grid extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;
    private JavascriptExecutor js ;
    public HR_Employee_Grid(WebDriver driver) {
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
        if(!name.isEmpty()) {
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
    }
    public void clearEmpName(){
        driver.findElement(fNameFieldBy).clear();
        driver.findElement(sNameFieldBy).clear();
        driver.findElement(tNameFieldBy).clear();
        driver.findElement(lNameFieldBy).clear();
    }
    private By empNameColBy = By.xpath("//tr/td[3]/div");

    public boolean validateSearchResults(String name,int split){
        boolean flag;
        switch(split){
            case 1:
                List<WebElement> empName = driver.findElements(empNameColBy);
                flag = empName.stream().map(s-> s.getText().split(" ",2)[0])
                        .allMatch(s->s.equals(name));
                break;
            case 2:
                List<WebElement> empName2 = driver.findElements(empNameColBy);
                flag = empName2.stream().map(s-> s.getText().split(" ",3)[1])
                        .allMatch(s->s.equals(name));
                break;
            case 3:
                List<WebElement> empName3 = driver.findElements(empNameColBy);
                flag = empName3.stream().map(s-> s.getText().split(" ",4)[2])
                        .allMatch(s->s.equals(name));
                break;
            case 4:
                List<WebElement> empName4 = driver.findElements(empNameColBy);
                flag = empName4.stream().map(s-> s.getText().split(" ",5)[3])
                        .allMatch(s->s.equals(name));
                break;
            default:
                System.out.println("Invalid search number");
                return false;
        }
        return flag;
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

    private By tabJobInfoBy = By.id("tb_ctrldiv_Tab_Hrf_1");
    private By treatmentJobBy = By.id("txt_srch_mandate_job_name");
    public void searchByTreatmentJob(String treatmentJob){
        driver.findElement(tabJobInfoBy).click();
        driver.findElement(treatmentJobBy).sendKeys(treatmentJob);
    }
    private By empIdBy = By.id("txt_srch_Nation_num");
    public void searchByEmpId(String empId) {
        driver.findElement(empIdBy).sendKeys(empId);
    }
    public boolean validateSearchByEmpId(String empNum){
        return driver.findElement(By.xpath("//table[@id='tbl_employees']/tbody/tr/td[2]/div")).isDisplayed();
    }

    public String getTreatmentManagement(String empNum){
        return driver.findElement(By.xpath("//table[@id='tbl_employees']/tbody/tr/td[2]/div[text()='"+empNum+"']/../../td[7]/div")).getText();
    }

    private By ddlTreatmentManagementBy = By.id("drp_srch_emp_dep_ddlSelectButtonTarget");
    private By searchBarTreatmentManagementBy = By.id("drp_srch_emp_dep_txtSearch");

    public void searchByTreatmentManagement(String treatmentManagement){
        driver.findElement(tabJobInfoBy).click();
        driver.findElement(ddlTreatmentManagementBy).click();
        driver.findElement(searchBarTreatmentManagementBy).sendKeys(treatmentManagement);
        try{
            driver.findElement(By.xpath("//ul[@id='drp_srch_emp_dep_collapsibleDiv']/li/div/label[text()='"+treatmentManagement+"']")).click();
        } catch (Exception e) {
            js.executeScript("arguments[0].scrollBy(0,30);", ddlTreatmentManagementBy);
            driver.findElement(By.xpath("//ul[@id='drp_srch_emp_dep_collapsibleDiv']/li/div/label[text()='"+treatmentManagement+"']")).click();
        }

    }

    private By gridEmptyBy = By.id("div_employees_Empty");
    public boolean validateSearchEmpty(){
        try{
            return driver.findElement(gridEmptyBy).isDisplayed();
        }catch(Exception e){
            return !(driver.findElement(By.xpath("//table[@id='tbl_employees']/tbody/tr/td[2]/div")).isDisplayed());

        }
    }

    public void selectSearchByEmpId(){
         driver.findElement(By.xpath("//table[@id='tbl_employees']/tbody/tr/td[1]/input")).click();
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
    public HR_Employee_Add goToAddPage() throws InterruptedException {
        driver.findElement(addPageBy).click();
//        exWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-icon")));
        return new HR_Employee_Add(driver);
    }

    private By editPageBy = By.id("cph_main_btn_edit");
    public HR_Employee_Edit goToEditPage() throws InterruptedException {
        driver.findElement(editPageBy).click();
//        exWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-icon")));
        return new HR_Employee_Edit(driver);
    }

    private By viewPageBy = By.id("cph_main_btn_view");
    public HR_Employee_View goToViewPage() throws InterruptedException {
        driver.findElement(viewPageBy).click();
//        exWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-icon")));
        return new HR_Employee_View(driver);
    }

    private By deletePageBy = By.id("cph_main_btn_disable");
    private By confirmDeleteBy = By.cssSelector("input[value='موافق']");
    public void deleteEmp(){
        driver.findElement(deletePageBy).click();
        driver.findElement(confirmDeleteBy).click();
    }



}
