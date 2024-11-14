package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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


    @FindBy (xpath = "(//div[@class='col-bx-12'])[1]")
    WebElement userControlPage;

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
    @FindBy (xpath = "(//h1[contains(text(),'تعديل كلمة المرور')])[1]")
    WebElement editPasswordTitle;
    @FindBy (id = "cph_main_btn_delegate_mang")  /* ادارة التفويض */
    WebElement delegationControl;



    /****************************       Search      *******************************/
    @FindBy (id = "txt_user_fname")  /* اسم المستخدم الاول */
    static
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
    @FindBy (id = "dv_Empty")  /* رساله لايوجد نتائج */
    WebElement noResultMessage;
    @FindBy (id = "txt_Page")  /* حقل الصفحه */
    WebElement searchText;
    @FindBy (id = "ddl_deps_collapsibleDiv")
    WebElement divDeptChoose;

    @FindBy(css = "li[value='-1']")
    WebElement defaultChoose;

    @FindBy (id = "ddl_deps_ddlSelectButtonTarget")
    WebElement deptTargetChosen;


    @FindBy (id = "spn_grid_paging")   /* نص : صفحه x من y  */
    WebElement pageNum;

    @FindBy (xpath = "(//div[@full_title='000'])[1]")
    WebElement firstElementInSearchTable;





    public WebElement getUserControlPage() {
        return userControlPage;
    }

    public void setChooseDept (String name){
        departmentChoose.click();
        WebElement search = driver.findElement(By.id("ddl_deps_txtSearch"));
        search.sendKeys(name);
        List<WebElement> listItems = driver.findElements(By.xpath("(//div[@id='ddl_deps_YidWw4iKHVc%3d_nav'])[1]"));
        for (WebElement item : listItems) {
            if (item.getText().equals(name)) {
                item.click();
            }
            break;
        }
    }


    public WebElement getChooseDept(){
        return deptTargetChosen;
    }
    public void clearChooseDept(){
        deptTargetChosen.click();
        WebElement ulElement = divDeptChoose;   // Find all list items within the unordered list
        List<WebElement> listItems = ulElement.findElements(By.tagName("li"));   // Check if there are any list items
        if (!listItems.isEmpty()) {               // Select the first item (default)
            WebElement defaultItem = listItems.get(0);
            defaultItem.click();
        }
    }


    public WebElement getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName1) {
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
    public WebElement getNoResultMessage() {
        return noResultMessage;
    }
    public String getPageNum() {
        return pageNum.getAttribute("value");
    }
    public WebElement getSearchText() {
        return searchText;
    }
    public void setSearchText(String num) {
        searchText.clear();
        searchText.sendKeys(num);
        searchText.sendKeys(Keys.ENTER);
    }

    public void selectEmployeeByID(String number){
        userID.sendKeys(number);
        singleSearch();
        WebElement select = driver .findElement(By.xpath("(//input[@id='cb_:1'])[1]"));
        select.click();
    }
    public void addAccount (){
        addAccount.click();
    }
    public void editAccount (){
        editAccount.click();
    }
    public void permanentDisable(){
        permanentDisable.click();
    }
    public void manageDisableUsers(){
        manageDisableUsers.click();
    }
    public void activateUser(){
        activateUser.click();
    }
    public void viewTasks (){
        viewTasks.click();
    }
    public EditPassword editPassword(){
        editPassword.click();
        exWait.until(ExpectedConditions.visibilityOf(editPasswordTitle));
        EditPassword editPassword1 = new EditPassword(driver);
        editPassword1.newPassword.clear();
        editPassword1.confirmNewPassword.clear();
        return new EditPassword(driver);
    }
    public DelegatePage delegationControl (){
        delegationControl.click();
        return new DelegatePage(driver);
    }
    public String getUserName (){
        WebElement firstElement = driver.findElement(By.xpath("(//td)[3]"));
        return firstElement.getText();
    }


}
