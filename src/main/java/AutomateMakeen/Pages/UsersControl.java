package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class UsersControl extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;

    public UsersControl(WebDriver driver){
        super(driver);
        this.driver = driver;
        //specific wait for every page
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
        contentAside = new ContentAside(driver);
    }

    @FindBy (id = "cph_main_btn_add_user")    /* اضافه حساب */
    WebElement addAccount;
    @FindBy (id ="cph_main_btn_edit_user")    /* تعديل حساب */
    WebElement editAccount;
    @FindBy (id = "cph_main_btn_disabe_user_perm")    /* تعطيل دائم */
    WebElement permanentDisable;
    @FindBy (id = "cph_main_btn_disable_user_mang")     /* اداره حالات التعديل */
    WebElement manageDisableUsers ;
    @FindBy (id = "cph_main_btn_activate_user")   /* تفعيل */
    WebElement activateUser;
    @FindBy (id = "cph_main_btn_show_tasks")  /* عرض المهام  */
    WebElement viewTasks ;
    @FindBy (id = "cph_main_btn_edit_password")   /* تعديل كلمه المرور */
    WebElement editPassword ;
    @FindBy (id = "cph_main_btn_delegate_mang")  /* ادارة التفويض */
    WebElement delegationControl;



    /****************************       Search      *******************************/
    @FindBy (id = "txt_user_fname")  /* اسم المستخدم الاول */
    WebElement firstName;
    @FindBy (id = "txt_user_sname")  /* اسم المستخدم الثاني */
    WebElement secondName;
    @FindBy (id = "txt_user_thname") /* اسم المستخدم الثالت */
    WebElement thirdName ;
    @FindBy (id = "txt_user_lname") /* اسم المستخدم الاخير */
    WebElement lastName;
    @FindBy (id ="txt_user_id")  /*رقم المستخدم */
    WebElement userID;
    @FindBy (id ="ddl_deps_ddlSelectButton")  /* الادارة */
    WebElement departmentChoose;
    @FindBy (id ="divDdlSubGovDept_ddlSelectButton")  /* الجهات */
    WebElement partChoose;
    @FindBy (id ="btn_srch")    /* بحث */
    WebElement singleSearch;
    @FindBy (id ="btn_show_all")  /* عرض الكل */
    WebElement showAll;


    public void setChooseDept (String name){
        departmentChoose.click();
        WebElement search = driver.findElement(By.id("ddl_deps_txtSearch"));
        search.sendKeys(name);
        List<WebElement> listItems = driver.findElements(By.xpath("(//label[normalize-space()="+name+"])[1]"));
        for (WebElement item : listItems) {
            if (item.getText().equals(name)) {
                item.click();
            }
            break;
        }
    }
    public void clearChooseDept (){
        departmentChoose.click();
        WebElement noSelection = driver.findElement(By.xpath("(//i[@id='ddl_deps_ddlSelectButtonTarget'])[1]"));
        noSelection.click();
    }


    public WebElement getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName1) {
        firstName.sendKeys(firstName1);
    }

    public WebElement getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName1) {
        secondName.sendKeys(secondName1);
    }

    public WebElement getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName1) {
       thirdName.sendKeys(thirdName1);
    }

    public WebElement getLastName() {
        return lastName;
    }

    public void setLastName(String lastName1) {
        lastName.sendKeys(lastName1);
    }

    public WebElement getUserID() {
        return userID;
    }

    public void setUserID(String userID1) {
        userID.sendKeys(userID1);
    }
    public void clearAllFeild(){
        firstName.clear();
        secondName.clear();
        thirdName.clear();
        lastName.clear();
        userID.clear();
        clearChooseDept();
    }

    public void singleSearch (){
        singleSearch.click();
    }
    public void showall (){
        showAll.click();
    }



}
