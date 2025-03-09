package AutomateMakeen.Pages.Elite;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static org.openqa.selenium.By.xpath;

public class InboxPage extends BaseComp {

    protected WebDriver driver;
    private WebDriverWait exWait;

    public InboxPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
    }
    /*========================================================الــبــحــث =====================================================*/
    private By inboxPage = By.cssSelector(".row.g-3.inbox-details-container");  /* شريط الصفحة الواردة */
    private By searchText = By.id("txt_recNameSrch"); /* شريط البحث */
    private By searchButton = By.id("search-bar-btn"); /* زر البحث */
    private By proceedings = By.id("div_ddlActions"); /*الاجراءات */
        private By quickReferral = By.id("action_GDiNoio7hcs%3d"); /*احاله سريعة*/
    private By sorting = By.id("div_gridSort"); /*الترتيب */
    private By classification = By.id("div_ddlClassification"); /*التصنيف*/
    private By orientation = By.id("div_ddlOrient"); /*التوجية*/
        private By orientationSearch = By.id("txt_purpsSearch"); /*بحث التوجيه*/
        private By orientationSelect = By.xpath("//a[contains(text(),'--')]"); /*اول عنصر في البحث */
    private By date = By.id("dropdown__date"); /*التاريخ */
    /*======================================================اجــراءات المــعامـلـة =======================================================*/

    private By explanation = By.id("btn_explanation"); /*الشروحات */
    private By explanationTab = By.cssSelector(".outbox_explaination"); /*تاب الشروحات */
    private By attachments = By.id("btn_attachments"); /*المرفقات */
    private By attachmentsTab = By.xpath("//div[@class='body_inbox_content']//div[@id='div_conatiner_attach']"); /*تاب المرفقات*/

    public boolean goToExplanations (){  /*فتح تاب الشروحات والتأكد من فتحها*/
        driver.findElement(explanation).click();
        WebElement explanationTab1  = driver.findElement(explanationTab);
        exWait.until(ExpectedConditions.visibilityOf(explanationTab1));
        return explanationTab1.isDisplayed();
    }


    private By letterTab = By.cssSelector("#letterContainer"); /*تاب الخطاب و تاب العروض و تاب المذكرة*/
    private By ddlDepartments = By.id("drp_letter_header_depts"); /* من الادارة */
    private By editingTab = By.cssSelector("#div_letter_editing_container");






    public void selectDepartment(String department){ /*اختيار من الادارة */
        WebElement ddlDepartments1 = driver.findElement(ddlDepartments);
        ddlDepartments1.click();
        List<WebElement> l1 = driver.findElements(By.cssSelector("#drp_letter_header_depts_collapsibleDiv"));
        l1.get(0).click();
        ddlDepartments1.click();
    }

    public void forwardTo (String forwardToName) {  /*اختيار موجه الى*/
        WebElement forwarding = driver.findElement(forwardToBtn);
        forwarding.click();
        By forwardingDiv = By.id("ContentLetterReciversPopUpModal");
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(forwardingDiv)));
        By searchText = By.id("txt_letterReciversPopUpContainerID");
        WebElement searchTextElement = driver.findElement(searchText);
        searchTextElement.sendKeys(forwardToName);
        WebElement SearchBtn = driver.findElement(By.cssSelector(".qBtn.d-flex.justify-content-center.align-content-center.gap-1.px-3"));
        SearchBtn.click();
        WebElement checkbox1 = driver.findElement(By.cssSelector("label[for='tblGridLetterReciversPopUp1']"));
        checkbox1.click();
    }


    public void subject(String subject1) { /*موضوع الخطاب والعرض والمذكرة*/
        driver.findElement(subject).sendKeys(subject1);
    }

    public void addModel (String text){   /*اختيار نموذج*/
        driver.findElement(addModel).click();
        WebElement textSearch1 = driver.findElement(textSearch);
        exWait.until(ExpectedConditions.visibilityOf(textSearch1));
        textSearch1.sendKeys(text);
        driver.findElement(By.xpath("//a[contains(text(),'"+text+"')]")).click();
    }

    public void send (){  /*حفظ وارسال */
        driver.findElement(savebutton).click();
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(div)));
        WebElement btnOk1 = driver.findElement(btnOk);
        btnOk1.click();
    }

    public boolean statusDone (){    /*حاله صفحه الانشاء */
        boolean docStatus = driver.findElement(editingTab).getAttribute("style").equals("display: none;");
        return docStatus;
    }



    /*========================================================================*/
                                    /*الخطاب*/
    /*========================================================================*/
    private By letters = By.id("btn_letter_display"); /*الخطاب*/


        private By getDdlDepartmentsDiv = By.id("drp_letter_header_depts_collapsibleDiv"); /* ديف الادارة */
        private By sefatLetterDdl = By.cssSelector("div[onclick='externalLetter.clearLetterTypesDDLText(event)']"); /*صفه الخطاب */
        private By getDdlSefatLetterTextSearch = By.id("txt_letterTypesSrch"); /* حقل البحث في صفه الخطاب */
        private By forwardToBtn =By.id("btnOriginalReciever");/*زر اختيار موجه الي */
        private By forwardTypeDdl = By.id("div_drpPurpsContainer");  /* اختيار نوع التوجيه */
        private By subject = By.id("txt_subject"); /*موضوع الخطاب */
        private By addModel = By.cssSelector("div[onclick='htmlEditor.clearLetterTemplatesDDLText()']"); /* ddl add model */
            private By textSearch = By.id("txt_letterTemplatesSrch"); /*البحث*/
        private By savebutton = By.cssSelector("button[onclick='inboxDetailsObj.saveLetter()']"); /*زر حفظ*/
        private By div = By.cssSelector("div[id='dynamicModalDialog'] div[class='modal-content']"); /*ديف موافق او الغاء*/
        private By btnOk = By.cssSelector("#btnOk"); /*زر موافق*/



    public void lettersTab(){  /*تاب انشاء خطاب*/
        WebElement letters1 = driver.findElement(letters);
        letters1.click();
        WebElement lettersTab1 = driver.findElement(letterTab);
        exWait.until(ExpectedConditions.visibilityOf(lettersTab1));
    }
    public void selectSefatLetter(String sefatLetter){ /*اختيار صفه الخطاب */
        WebElement sefatLetterDdl1 = driver.findElement(sefatLetterDdl);
        sefatLetterDdl1.click();
        WebElement sefatLetterSearch = driver.findElement(getDdlSefatLetterTextSearch);
        sefatLetterSearch.sendKeys(sefatLetter);
        WebElement firstElement = driver.findElement(By.xpath("//a[normalize-space()='"+sefatLetter+"']"));
        firstElement.click();
    }

    public void selectForwardType (String name){     /*اختيار نوع التوجية*/
        WebElement forwardTypeDdl1 = driver.findElement(forwardTypeDdl);
        forwardTypeDdl1.click();
        WebElement forwardType = driver.findElement(By.xpath("(//a[contains(text(),'"+name+"')])"));
        forwardType.click();
    }

    /*========================================================================*/
                                  /*العرض*/
    /*========================================================================*/
    private By offers = By.id("btn_offerletter_display"); /*العروض*/
    private By receiverAlias = By.id("txt_RecieverAlias"); /*مسمى الموجه اليه*/


    public void offersTab (){  /*الدخول لتاب العروض */
        WebElement offers1 = driver.findElement(offers);
        offers1.click();
        WebElement offersTab1 = driver.findElement(letterTab);
        exWait.until(ExpectedConditions.visibilityOf(offersTab1));
    }
    public void receiverAlias (String alias){ /*مسمى الموجه اليه*/
        driver.findElement(receiverAlias).sendKeys(alias);
    }

    /*========================================================================*/
                                  /*المذكرة*/
    /*========================================================================*/
    private By internalMemo = By.id("div_internalMemoIcon"); /*مذكرة داخلية*/
    private By chkBoxDesc = By.id("chk_isDecision"); /*تشك بوكس قرار*/

    public void internalMemoTab (){ /*فتح تاب انشاء مذكرة*/
        WebElement internalMemo1 = driver.findElement(internalMemo);
        internalMemo1.click();
        WebElement internalMemoTab1 = driver.findElement(letterTab);
        exWait.until(ExpectedConditions.visibilityOf(internalMemoTab1));
    }
    public void setChkBoxDesc (){  /*تحديد تشك بوكس قرار*/
        WebElement chkBoxDesc1 = driver.findElement(chkBoxDesc);
        chkBoxDesc1.click();
    }

    private By preview = By.id("btn_PreviewLetter"); /*المعاينة*/

    /*========================================================================*/
                                /*ملاحظات التصدير*/
    /*========================================================================*/
    private By exportNotes = By.id("btn_ExportNotes"); /*ملاحظات التصدير*/
    private By exportNotesTab = By.xpath("//div[@class='wrapper_head_title d-flex'][contains(text(),'ملاحظات التصدير')]"); /* تاب ملاحظات التصدير*/
    private By addExportNotes = By.cssSelector(".btn_add_note.d-flex.align-items-center"); /*زر اضف ملاحظة*/
    private By divAddNotes = By.cssSelector("div[id='addNotesModal'] div[class='modal-content']"); /*نافذة اضف ملاحظة*/
    private By notesText = By.id("txt_exportNote"); /*حقل النص*/
    private By saveNote = By.id("btn_saveExportNote"); /*زر حفظ*/
    private By confirmationDiv = By.id("div_confirmSavePopover"); /*نافذة هل تريد بالتأكيد الحفظ*/
    private By btnYes = By.id("btn_agreeExportNote"); /*زر موافق*/

    public void exportNotes(){
        WebElement exportNotes1 = driver.findElement(exportNotes);
        exportNotes1.click();
        WebElement exportNotesTab1 = driver.findElement(exportNotesTab);
        exWait.until(ExpectedConditions.visibilityOf(exportNotesTab1));
    }
    public void addExportNotes(String text) {
        WebElement addExportNotes1 = driver.findElement(addExportNotes);
        addExportNotes1.click();
        WebElement divAddNotes1 = driver.findElement(divAddNotes);
        exWait.until(ExpectedConditions.visibilityOf(divAddNotes1));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(notesText));
        WebElement textArea = driver.findElement(notesText);
        textArea.sendKeys(text);

        /*WebElement textField = driver.findElement(notesText);
        textField.sendKeys(text);*/
        WebElement saveNote1 = driver.findElement(saveNote);
        saveNote1.click();
        WebElement confirmationDiv1 = driver.findElement(confirmationDiv);
        exWait.until(ExpectedConditions.visibilityOf(confirmationDiv1));
        WebElement btnYes1 = driver.findElement(btnYes);
        btnYes1.click();
    }
    public String getNotesContent(){
        WebElement content = driver.findElement(By.xpath("(//p[@class='d-flex gap-2'])[1]"));
        return content.getText();
    }


    /*========================================================================*/
                                  /*احاله */
    /*========================================================================*/

    private By forward = By.id("btn_forward"); /*إحالة*/

    private final By forwardToDiv = By.cssSelector(".modal-content.modal-assignment.h-100"); /*تاب الاحالة  */

    private final By allBaladia = By.cssSelector("#rd_general"); /*عموم البلدية */
    private final By baladiaSearch = By.cssSelector("#txt_name_srch"); /*بحث في عموم البلدية */
    private final By selectOriginal = By.cssSelector("input[id='rd_original_8jCvnEMsbEs%3d']"); /*اصــــل*/
    private final By selectCopy = By.cssSelector("label[for='ch_copy_Wwu4ooBrnXU%3d']"); /*صـــورة */

    private final By favourite = By.cssSelector("#rd_fav"); /*المفضلة */
    private final By favouriteSearch = By.cssSelector("#txt_name_srch"); /* بحث في المفضلة */
    private final By selectOriginalFav = By.cssSelector("input[id='rd_original_saFigbSGQbc%3d']"); /*أصــل */
    private final By selectCopyFav = By.cssSelector("label[for='ch_copy_saFigbSGQbc%3d']"); /*صــــورة */

    private final By customEmp = By.cssSelector("#rd_select_emp"); /*موظف محدد */
    private final By ddlDept = By.cssSelector("#depts_ddl_ddlSelectButton"); /*اختر الادارة */
    private final By ddlDeptSearch = By.id("depts_ddl_txtSearch"); /*بحث الادارة */
    private final By ddlEmp = By.cssSelector("#emps_ddl_ddlSelectButton"); /*اختر الموظف */
    private final By ddlEmpSearch = By.id("emps_ddl_txtSearch"); /*بحث الموظف */
    private final By ddlDirecting = By.cssSelector("#emp_purp_ddl_ddlSelectButtonTarget"); /*التوجيه */
    private final By ddlDirectingSearch = By.id("emp_purp_ddl_txtSearch"); /*بحث التوجيه */

    private final By saveForward = By.id("btn_SaveSelectedData"); /*حفظ */


    private final By sendButton = By.id("btnAssignmentSendClick"); /*إرسـال  */

    private final By divConfirmForward = By.id("div_confirmForward");
    private final By btnYesForward = By.id("btn_confirmForward"); /*موافق */




    public void goToForwardTab (){ /*فتح تاب احالـــة */
        WebElement forward1 = driver.findElement(forward);
        forward1.click();
        WebElement forwardDiv = driver.findElement(forwardToDiv);
        exWait.until(ExpectedConditions.visibilityOf(forwardDiv));
    }
    public void forwardToAllBaladia (String name , String forwardType){ /* احالة الى عموم البلدية  ,0  اصل , 1 صورة */  /*this function is not dynamic*/

        WebElement allBalad = driver.findElement(allBaladia);
        allBalad.click();
        WebElement search = driver.findElement(baladiaSearch);
        search.sendKeys(name);
        if (Objects.equals(forwardType, "0")) { /*اصــل */
            WebElement original = driver.findElement(selectOriginal);
            original.click();
        } else if (Objects.equals(forwardType, "1")) { /*صــورة */
            WebElement copy = driver.findElement(selectCopy);
            copy.click();
        }
        WebElement send = driver.findElement(sendButton);
        send.click();
        WebElement divConfirmed = driver.findElement(divConfirmForward);
        exWait.until(ExpectedConditions.visibilityOf(divConfirmed));
        WebElement btnYes = driver.findElement(btnYesForward);
        btnYes.click();
    }

    public void forwardToFavourite (String name , String forwardType){ /* احالة الى المفضلة  ,0  اصل , 1 صورة */ /*this function is not dynamic*/

        WebElement favRadio = driver.findElement(favourite);
        favRadio.click();
        WebElement search = driver.findElement(favouriteSearch);
        search.sendKeys(name);
        if (Objects.equals(forwardType, "0")){ /*اصــل */
            WebElement original = driver.findElement(selectOriginalFav);
            original.click();
        }
        else if (Objects.equals(forwardType, "1")){ /*صــورة */
            WebElement copy = driver.findElement(selectCopyFav);
            copy.click();
        }
        WebElement send = driver.findElement(sendButton);
        send.click();
        WebElement divConfirmed = driver.findElement(divConfirmForward);
        exWait.until(ExpectedConditions.visibilityOf(divConfirmed));
        WebElement btnYes = driver.findElement(btnYesForward);
        btnYes.click();

    }
    public void forwardToCustomEmp (String Dept , String Name , String Directing ){ /* احالة الى موظف محدد  ,0  اصل , 1 صورة */

        WebElement customEmpRadio = driver.findElement(customEmp);
        customEmpRadio.click();

        WebElement dept = driver.findElement(ddlDept);
        dept.click();
        WebElement searchDpt = driver.findElement(ddlDeptSearch);
        searchDpt.sendKeys(Dept);
        WebElement Dept1 = driver.findElement(xpath("//ul[@id='depts_ddl_collapsibleDiv']/li/div/label[text()='"+Dept+"']"));
        Dept1.click();

        WebElement emp = driver.findElement(ddlEmp);
        emp.click();
        WebElement searchEmp = driver.findElement(ddlEmpSearch);
        searchEmp.sendKeys(Name);
        WebElement Emp1 = driver.findElement(xpath("//ul[@id='emps_ddl_collapsibleDiv']/li/div/label[text()='"+Name+"']"));
        Emp1.click();

        WebElement dirct = driver.findElement(ddlDirecting);
        dirct.click();
        WebElement searchDirect = driver.findElement(ddlDirectingSearch);
        searchDirect.sendKeys(Directing);
        WebElement Direct1 = driver.findElement(xpath("//ul[@id='emp_purp_ddl_collapsibleDiv']/li/div/label[text()='"+Directing+"']"));
        Direct1.click();
        WebElement send = driver.findElement(sendButton);
        send.click();
        WebElement divConfirmed = driver.findElement(divConfirmForward);
        exWait.until(ExpectedConditions.visibilityOf(divConfirmed));
        WebElement btnYes = driver.findElement(btnYesForward);
        btnYes.click();

    }




    private By forwardToSave = By.id("btn_forwardForSave"); /*إحالة للحفظ */
    private By forwardToSign = By.id("btn_ForwardToSign"); /* إحالة للتوقيع */
    private By backToSource = By.id("btn_backToSource"); /*إعادة للمصدر*/
    private By btnMore = By.id("btn_more_options"); /*زر المزيد */



    /*========================================================================*/
                                /* تأشير*/
    /*========================================================================*/
    private By marking = By.id("btn_Vice"); /*تأشير*/
    private By viceDiv = By.id("spViceLetterPopUpTitle");
    private By btnConfirmVice = By.cssSelector("#btnConfirmViceAction");  /*زر تأكيد */

    public void goToVice (){ /*فتح تاب تأشير */
        WebElement vice1 = driver.findElement(marking);
        vice1.click();
        WebElement viceDiv1 = driver.findElement(viceDiv);
        exWait.until(ExpectedConditions.visibilityOf(viceDiv1));
    }
    public void confirmVice (){
        WebElement btnConfirmVice1 = driver.findElement(btnConfirmVice);
        btnConfirmVice1.click();
    }






        private By markingAndreferToAgent = By.xpath("//a[contains(text(),'تأشير و إحالة للوكيل')]");  /*تأشير و إحاله الى الوكيل */
        private By markingAndreferToCustomEmp = By.xpath("//a[contains(text(),'تأشير إلي موظف محدد')]"); /*تأشير الى موظف محدد*/


    /*========================================================================*/
                                  /* التوقيع*/
    /*========================================================================*/

    private By sign = By.id("btn_sign"); /*توقيع*/
    private By signDiv = By.cssSelector("#spViceLetterPopUpTitle");
    private By btnConfirm = By.cssSelector("#btnConfirmViceAction");  /*زر تأكيد */

    public void goToSign (){ /*فتح تاب توقيع*/
        WebElement sign1 = driver.findElement(sign);
        sign1.click();
        WebElement signDiv1 = driver.findElement(signDiv);
        exWait.until(ExpectedConditions.visibilityOf(signDiv1));
    }
    public void signConfirm (){
        WebElement btnConfirm1 = driver.findElement(btnConfirm);
        btnConfirm1.click();
    }





        private By signWithAuthority = By.xpath("//a[contains(text(),'توقيع بتفويض')]"); /* توقيع بتفويض */
    private By assetsRequest = By.id("btn_AssetsRequest"); /*طلب أصول*/
    private By tempSave = By.id("btn_TempSaveForTreatment"); /*حفظ مؤقت*/
    private By addGeoInfo = By.id("btn_GeoInformation"); /*المعلومة الجيومكانية*/
    /*=========================================================================================================================*/

    private By showAll = By.xpath("//span[contains(text(),'عرض الجميع')]"); /*عرض الجميع */

    private By explanationDdlBtn = By.id("btnAttachActions");
        private By perview = By.xpath("//a[@class='dropdown-item'][contains(text(),'معاينة')]"); /*معاينة */
        private By generalExplanations = By.xpath("//a[contains(text(),'الشروحات الكلية')]"); /*الشروحات الكلية */
    private By sortExplanations = By.id("divExpSortCounterSpan"); /*الترتـيب*/

    private By treatDetails = By.xpath("(//div[@class='info-comp']/span[@class='i-icon'])[1]"); /*تفاصيل المعاملة */



    public WebElement getMailInboxPage() {   /*ارجاع صفحه الوارد */
        WebElement mailInboxPage = driver.findElement(inboxPage);
        return mailInboxPage;
    }

    public void mailInboxSearch (String text){  /*   البحث عن معامله */
        WebElement searchInput = driver.findElement(searchText);
        searchInput.sendKeys(text);
        driver.findElement(searchButton).click();
    }
    public void veiwExplanations (){ /* معاينة الشروحات */
        WebElement threeDotsBtn = driver.findElement(explanationDdlBtn);
        threeDotsBtn.click();
        WebElement perview1 = driver.findElement(perview);
        perview1.click();
    }
    public void sortingBtn (){   /*زر ترتيب*//*========*/
        WebElement sortBtn = driver.findElement(sorting);
        sortBtn.click();
    }
    public String getTreatArchiveNum (){ /*ارجاع الأرشيف */
        WebElement details = driver.findElement(treatDetails);
        Actions actions = new Actions(driver);
        actions.moveToElement(details).perform();
        WebElement treatArchiveNum = driver.findElement(By.xpath("//table[@class='info-comp-table-list']/tr/th[text()='الأرشيف']/following-sibling::td[1]"));
        String archive = treatArchiveNum.getText();
        actions.moveToElement(details).click().perform();
        return archive;
    }
    /*========================================================================*/
    public void goToAttachments(){ /*فتح المرفقات */
        WebElement attachments1 = driver.findElement(attachments);
        attachments1.click();

    }

    public WebElement getAttachmentsTab (){ /*ارجاع تاب المرفقات */
        WebElement attachments1 = driver.findElement(attachmentsTab);
        return attachments1;
    }
    /*========================================================================*/


    /*========================================================================*/

    /*========================================================================*/

    /*========================================================================*/
    public void openReview (){ /*فتح معاينة*/
        WebElement preview1 = driver.findElement(preview);
        preview1.click();
    }
    /*========================================================================*/
    public void goToExportNotes (){ /*فتح تاب ملاحظات التصدير*/
        WebElement exportNotes1 = driver.findElement(exportNotes);
        exportNotes1.click();
    }
    /*========================================================================*/




    /*========================================================================*/
    public void goToForwardToSave (){ /*فتح تاب احالة للحفظ*/
        WebElement forwardToSave1 = driver.findElement(forwardToSave);
        forwardToSave1.click();
    }
    /*========================================================================*/

    /*========================================================================*/
    public void goForwardToSign (){ /*فتح تاب احالة للتوقيع*/
        WebElement forwardToSign1 = driver.findElement(forwardToSign);
        forwardToSign1.click();
    }
    /*========================================================================*/
    public void goToBackToSource (){ /*فتح تاب اعاده للمصدر*/
        WebElement backToSource1 = driver.findElement(backToSource);
        backToSource1.click();
    }
    /*========================================================================*/
    public void btnMoreClick (){ /*زر المزيد*/
        WebElement btnMore1 = driver.findElement(btnMore);
        btnMore1.click();
    }
    /*========================================================================*/
    public void goToAssetsRequest (){ /*طلب اصول*/
        WebElement assetsRequest1 = driver.findElement(assetsRequest);
        assetsRequest1.click();
    }
    /*========================================================================*/
    public void tempSave (){ /*حفظ مؤقت*/
        WebElement tempSave1 = driver.findElement(tempSave);
        tempSave1.click();
    }
    /*========================================================================*/
    public void addGeoInfo (){ /* اضافه معلومة مكانية*/
        WebElement addGeoInfo1 = driver.findElement(addGeoInfo);
        addGeoInfo1.click();
    }

}


