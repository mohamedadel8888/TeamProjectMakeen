package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployeesOperations extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;

    public EmployeesOperations (WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
        contentAside = new ContentAside(driver);
    }
    @FindBy(id = "DivMainEmp") /*صفحه عمليات على الموظفين*/
    WebElement divEmpOperations;
    public WebElement getEmpOperations (){
        return divEmpOperations;
    }
    @FindBy (id ="btn_create")    /*زر تعيين موظف */
    WebElement appointEmployee;
    public void enterAppointEmployee () {
        appointEmployee.click();
    }
    private By divNewEmployee = By.id("DivNewEmp"); /*صفحه تعيين موظف*/
    public WebElement getDivNewEmployee() {
        return driver.findElement(divNewEmployee);
    }

    private By nationNumberText = By.id("txt_AddNation");    /*رقم الهوية*/
    public void addNationNumber(String id) {
        WebElement nationNumber = driver.findElement(nationNumberText);
        nationNumber.sendKeys(id);
    }
    private By masarButton = By.id("masarButtonT3een"); /*زر استدعاء البيانات من منصه مسار*/
    public WebElement masarBtn(){
        WebElement masarButton1 = driver.findElement(masarButton);
        return masarButton1;
    }
    private By validationAddNation = By.id("spnA_AddNation");
    private By notInMasarValidation = By.cssSelector("p[class='span_error'] span"); /*  رسالة "رقم الهوية غير موجود بمنصة مسار"  */

    public String getValidationMessage( ){
        WebElement validationDot = driver.findElement(validationAddNation);
        Actions actions = new Actions(driver);
        actions.moveToElement(validationDot).perform();
        WebElement validationMessage = driver.findElement(notInMasarValidation);
        return validationMessage.getText();
    }







}
