package AutomateMakeen.Pages.Elite;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateInternalMailPage extends BaseComp {
    protected WebDriver driver;
    private WebDriverWait exWait;

    public CreateInternalMailPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
    }

    private By createInternalMailPage = By.className("internal-mail__body"); /* صفحه انشاء بريد داخلي */
    private By txtSubject = By.id("txt_trSubject"); /*الموضـوع */
    private By explain = By.id("txt_Comment"); /*الشــــرح */
    private By docTypeText = By.id("txt_docTypeNum"); /*نوع المستند */
    private By transClassificationText = By.id("txt_trClassNum"); /*تصنيف المعاملة */
    private By secretExpToggle = By.cssSelector("label[for='ch_secretComm']"); /*شرح سري */
    private By selfRedirect = By.cssSelector("label[for='chk_forwardTreatForMe']"); /*احالة المعامله لحسابي */

    private  By forward = By.cssSelector("#icon_openForwardPo"); /*احالة */

    private By forwardToDiv = By.cssSelector(".modal-content.modal-assignment.h-100"); /*تاب الاحالة  */

        private By allBaladia = By.cssSelector("#rd_general"); /*عموم البلدية */
            private By baladiaSearch = By.cssSelector("#txt_name_srch"); /*بحث في عموم البلدية */
            private By selectOriginal = By.cssSelector("input[id='rd_original_8jCvnEMsbEs%3d']"); /*اصــــل*/
            private By selectCopy = By.cssSelector("label[for='ch_copy_Wwu4ooBrnXU%3d']"); /*صـــورة */

        private By favourite = By.cssSelector("#rd_fav"); /*المفضلة */
            private By favouriteSearch = By.cssSelector("#txt_name_srch"); /* بحث في المفضلة */
            private By selectOriginalFav = By.cssSelector("input[id='rd_original_saFigbSGQbc%3d']"); /*أصــل */
            private By selectCopyFav = By.cssSelector("label[for='ch_copy_saFigbSGQbc%3d']"); /*صــــورة */

        private By customEmp = By.cssSelector("#rd_select_emp"); /*موظف محدد */
            private By ddlDept = By.cssSelector("#depts_ddl_ddlSelectButton"); /*اختر الادارة */
                private By ddlDeptSearch = By.id("depts_ddl_txtSearch"); /*بحث الادارة */
            private By ddlEmp = By.cssSelector("#emps_ddl_ddlSelectButton"); /*اختر الموظف */
                private By ddlEmpSearch = By.id("emps_ddl_txtSearch"); /*بحث الموظف */
            private By ddlDirecting = By.cssSelector("#emp_purp_ddl_ddlSelectButtonTarget"); /*التوجيه */
                private By ddlDirectingSearch = By.id("emp_purp_ddl_txtSearch"); /*بحث التوجيه */

    private By saveForward = By.id("btn_SaveSelectedData"); /*حفظ */


    private By sendButton = By.cssSelector("button[onclick='internalMail.checkCanSend()']"); /*إرسـال  */

    private By DropDownType = By.cssSelector(".dropdown-toggle"); /*زر قائمة بريد سري , معامله عاجلة */
    private By DivDropDownType = By.cssSelector(".dropdown-menu.show"); /*قائمة بريد سري و معاملة عاجله  */
        private By secertMail = By.cssSelector("label[for='chk_secretMail']"); /*بريد سري */
        private By urgentTreat = By.cssSelector("label[for='chk_urgentTreat']"); /*معاملة عاجلة */
    private By addAttachment = By.id ("chk_secretMail"); /*اضافة المرفقات ============================================ */

//    private By popUpDocType = By.id("ContentdocTypesPopUpdocTypeModal"); /*بوب اب نوع المستند */
//        private By docTypeTextPop =By.id("txt_docTypesPopUpConainerDOCUMENTID"); /*رقم /نوع المستند */
//        private By searchBtnPop = By.xpath("(//button[@onclick='GridPopup.search()'])[1]"); /*بحث*/
//        private By searchAllBtnPop = By.cssSelector("button[id='btnSearchAll_docTypesPopUpConainer'] span[class='qBtn_text']"); /*عرض الكل*/
//        private By chkBoxFirstElementDocTypePopUp = By.cssSelector("label[for='tblGridDocTypes1']");
//        private By rightArrowDoc = By.xpath("(//li[@title='السابق'])[1]");
//        private By leftArrowDoc = By.xpath("(//li[contains(@title,'التالي')])[1]");
//
//    private By popUpTransClassification = By.id("ContentClassesPopUpModal"); /*بوب اب تصنيف المعاملة */
//        private By selectMainClass = By.id("classificationPopUpConainer_divClassifiation_ddlSelectButtonTarget"); /*التصنيف الرئيسي */
//            private By mainClassDiv = By.id("classificationPopUpConainer_divClassifiation_collapsibleDiv"); /*بحث التصنيف الرئيسي */
//        private By selectSubClass = By.id("txt_classificationPopUpConainerCLASSNAME"); /*التصنيف الفرعي */
//        private By searchBtn = By.cssSelector(".qBtn.d-flex.justify-content-center.align-content-center.gap-1.px-3"); /*بحث */
//        private By searchAllBtn = By.id("btnSearchAll_classificationPopUpConainer"); /* عرض الكل */
//        private By rightArrow = By.cssSelector("li[title='السابق']");
//        private By leftArrow = By.cssSelector("li[title='التالي']");
//        private By chekBoxFirstElement = By.cssSelector("label[for='tblGridClasses1']"); /*تشيك بوكس اختيار تصنيف*/


    public WebElement getCreateInternalMailPage() {
        WebElement createInternalMail = driver.findElement(createInternalMailPage);
        return createInternalMail;
    }

    public void  DropDownToggleSelect_SecretMail (){  /*اختيار بريد سري */
        exWait.until(ExpectedConditions.visibilityOf(getCreateInternalMailPage()));
        WebElement dropDownToggle = driver.findElement(DropDownType);
        dropDownToggle.click();
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(DivDropDownType)));
        WebElement secertMailToggle = driver.findElement(secertMail);
        secertMailToggle.click();
        dropDownToggle.click();
    }
    public void  DropDownToggleSelect_UrgentTreat (){  /*اختيار معاملة عاجلة */
        exWait.until(ExpectedConditions.visibilityOf(getCreateInternalMailPage()));
        WebElement dropDownToggle = driver.findElement(DropDownType);
        dropDownToggle.click();
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(DivDropDownType)));
        WebElement urgentTreateToggle = driver.findElement(urgentTreat);
        urgentTreateToggle.click();
        dropDownToggle.click();
    }

    public WebElement txtSubject (String text) {    /*ادخال نص في الموضوع */
        WebElement subject = driver.findElement(txtSubject);
        subject.sendKeys(text);
        return subject;
    }
    public WebElement explain (String text) { /*ادخال نص في الشرح */
        WebElement explainElement = driver.findElement(explain);
        explainElement.sendKeys(text);
        return explainElement;
    }
    public void docTypeSelect (String text) { /*ادخال نوع المستند  */
        WebElement docType = driver.findElement(docTypeText);
        docType.sendKeys(text);
    }
    public void transClassification (String text) { /*ادخال تصنيف المعاملة   */
        WebElement transClass = driver.findElement(transClassificationText);
        transClass.sendKeys(text);
    }
    public void secretExpToggle() { /*تحقق حالة شرح سري */
        WebElement secretExp = driver.findElement(secretExpToggle);
        secretExp.click();
    }
    public void selfRedirectToggle() { /*تحقق احالة المعاملة لحسابي */
        WebElement selfRedirectElement = driver.findElement(selfRedirect);
        selfRedirectElement.click();
    }
    public void forwardToAllBaladia (String name , char forwardType){ /* احالة الى عموم البلدية  ,0  اصل , 1 صورة */  /*this function is not dynamic*/
       // WebElement selfRedirectToggle = driver.findElement(selfRedirect);
       // if (!selfRedirectToggle.isEnabled()){
            WebElement forwardBtn = driver.findElement(forward);
            forwardBtn.click();
            WebElement forwardTo = driver.findElement(forwardToDiv);
            exWait.until(ExpectedConditions.visibilityOf(forwardTo));
            WebElement allBalad = driver.findElement(allBaladia);
            allBalad.click();
            WebElement search = driver.findElement(baladiaSearch);
            search.sendKeys(name);
            if (forwardType == '0') { /*اصــل */
                WebElement original = driver.findElement(selectOriginal);
                original.click();
            } else if (forwardType == '1') { /*صــورة */
                WebElement copy = driver.findElement(selectCopy);
                copy.click();
            }
            WebElement save = driver.findElement(saveForward);
            save.click();
       // }
    }

    public void forwardToFavourite (String name , char forwardType){ /* احالة الى المفضلة  ,0  اصل , 1 صورة */ /*this function is not dynamic*/
       // WebElement selfRedirectToggle = driver.findElement(selfRedirect);
        //if (!selfRedirectToggle.isEnabled()){
            WebElement forwardBtn = driver.findElement(forward);
            forwardBtn.click();
            WebElement forwardTo = driver.findElement(forwardToDiv);
            exWait.until(ExpectedConditions.visibilityOf(forwardTo));
            WebElement favRadio = driver.findElement(favourite);
            favRadio.click();
            WebElement search = driver.findElement(favouriteSearch);
            search.sendKeys(name);
            if (forwardType == '0'){ /*اصــل */
                WebElement original = driver.findElement(selectOriginalFav);
                original.click();
            }
            else if (forwardType == '1'){ /*صــورة */
                WebElement copy = driver.findElement(selectCopyFav);
                copy.click();
            }
        WebElement save = driver.findElement(saveForward);
        save.click();
       // }
    }
    public void forwardToCustomEmp (String Dept , String Name , String Directing ){ /* احالة الى موظف محدد  ,0  اصل , 1 صورة */
        // WebElement selfRedirectToggle = driver.findElement(selfRedirect);
        //if (!selfRedirectToggle.isEnabled()){
        WebElement forwardBtn = driver.findElement(forward);
        forwardBtn.click();
        WebElement forwardTo = driver.findElement(forwardToDiv);
        exWait.until(ExpectedConditions.visibilityOf(forwardTo));
        WebElement customEmpRadio = driver.findElement(customEmp);
        customEmpRadio.click();

        WebElement dept = driver.findElement(ddlDept);
        dept.click();
        WebElement searchDpt = driver.findElement(ddlDeptSearch);
        searchDpt.sendKeys(Dept);
        WebElement Dept1 = driver.findElement(By.xpath("//ul[@id='depts_ddl_collapsibleDiv']/li/div/label[text()='"+Dept+"']"));
        Dept1.click();

        WebElement emp = driver.findElement(ddlEmp);
        emp.click();
        WebElement searchEmp = driver.findElement(ddlEmpSearch);
        searchEmp.sendKeys(Name);
        WebElement Emp1 = driver.findElement(By.xpath("//ul[@id='emps_ddl_collapsibleDiv']/li/div/label[text()='"+Name+"']"));
        Emp1.click();

        WebElement dirct = driver.findElement(ddlDirecting);
        dirct.click();
        WebElement searchDirect = driver.findElement(ddlDirectingSearch);
        searchDirect.sendKeys(Directing);
        WebElement Direct1 = driver.findElement(By.xpath("//ul[@id='emp_purp_ddl_collapsibleDiv']/li/div/label[text()='"+Directing+"']"));
        Direct1.click();
        forwardToSave();
        // }
    }


    public void forwardToSave () /*حفظ الاحالة  */
    {
        WebElement saveForwardBtn = driver.findElement(saveForward);
        saveForwardBtn.click();
    }
    public void SendMail (){
        WebElement sendBtn = driver.findElement(sendButton);
        sendBtn.click();
    }
}
