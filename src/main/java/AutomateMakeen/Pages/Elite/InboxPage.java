package AutomateMakeen.Pages.Elite;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static org.openqa.selenium.By.cssSelector;
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
        private By orientationSelect = xpath("//a[contains(text(),'--')]"); /*اول عنصر في البحث */
    private By date = By.id("dropdown__date"); /*التاريخ */
    /*======================================================اجــراءات المــعامـلـة =======================================================*/

    private By explanation = By.id("btn_explanation"); /*الشروحات */
    private By explanationTab = By.cssSelector(".outbox_explaination"); /*تاب الشروحات */

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
        driver.findElement(xpath("//a[contains(text(),'"+text+"')]")).click();
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
        WebElement firstElement = driver.findElement(xpath("//a[normalize-space()='"+sefatLetter+"']"));
        firstElement.click();
    }

    public void selectForwardType (String name){     /*اختيار نوع التوجية*/
        WebElement forwardTypeDdl1 = driver.findElement(forwardTypeDdl);
        forwardTypeDdl1.click();
        WebElement forwardType = driver.findElement(xpath("(//a[contains(text(),'"+name+"')])"));
        forwardType.click();
    }

    /*========================================================================*/
                                  /*العرض*/
    /*========================================================================*/
    private By offers = By.id("btn_offerletter_display"); /*العروض*/
    private By receiverAlias = By.id("txt_RecieverAlias"); /*مسمى الموجه اليه*/
    private By redirectOfferBtn = By.id("btn_RedirectOffer"); /*توجيه العرض*/
    private By divRedirectoffer = By.xpath("(//div[@class='modal-content p-4'])[4]"); /*نافذه توجيه العرض*/
    private By directingOffer = By.cssSelector("#slcRedirectPurps"); /*التوجيه*/
    private By confirmationBtn = By.id("btnConfirmRedirectAction"); /*زر تأكيد*/
    private By textComment = By.id("txtPurposeComment"); /*نص التوجيه*/
    private By chkBoxRedirectWithSignLetter = By.cssSelector("label[for='chkRedirectWithSignLetter']");/*تشك بوكس مع توقيع الخطاب*/
    private By chkBoxRedirectWithSignMomo = By.cssSelector("label[for='chkRedirectWithSignMemo']"); /*تشك بوكس مع توقيع المذكرة*/
    private By returnOffer = By.id("btn_ReturnOffer"); /*اعادة العرض*/
    private By divReturnOffer = By.xpath("(//div[@class='modal-content p-4'])[3]"); /*نافذه اعادة العرض*/
    private By textViceComment = By.id("txtSignViceComment"); /*نص اعادة العرض*/
    private By confirmReturnBtn = By.id("btnConfirmViceAction"); /*زر تأكيد إعادة العرض*/



    public void offersTab (){  /*الدخول لتاب العروض */
        WebElement offers1 = driver.findElement(offers);
        offers1.click();
        WebElement offersTab1 = driver.findElement(letterTab);
        exWait.until(ExpectedConditions.visibilityOf(offersTab1));
    }
    public void receiverAlias (String alias){ /*مسمى الموجه اليه*/
        driver.findElement(receiverAlias).sendKeys(alias);
    }
    public void redirectOffer (String text){ /*توجيه العرض*/
        WebElement redirectOffer1 = driver.findElement(redirectOfferBtn);
        redirectOffer1.click();
        WebElement divRedirectOffer1 = driver.findElement(divRedirectoffer);
        exWait.until(ExpectedConditions.visibilityOf(divRedirectOffer1));
        WebElement directingOffer1 = driver.findElement(directingOffer);
        Select select = new Select(directingOffer1);
        select.selectByVisibleText(text);
        WebElement textComment1 = driver.findElement(textComment);
        textComment1.clear();
        textComment1.sendKeys("Mohamed Adel");
        WebElement confirmationBtn1 = driver.findElement(confirmationBtn);
        confirmationBtn1.click();
    }
    public void redirectOfferWithSignMomo (String text){ /*توجيه العرض مع توقيع المذكرة*/
        WebElement redirectOffer1 = driver.findElement(redirectOfferBtn);
        redirectOffer1.click();
        WebElement divRedirectOffer1 = driver.findElement(divRedirectoffer);
        exWait.until(ExpectedConditions.visibilityOf(divRedirectOffer1));
        WebElement chkBoxSignMomo = driver.findElement(chkBoxRedirectWithSignMomo);
        chkBoxSignMomo.click();
//        WebElement directingOffer1 = driver.findElement(directingOffer);
//        Select select = new Select(directingOffer1);
//        select.selectByVisibleText(text);
        WebElement textComment1 = driver.findElement(textComment);
        textComment1.clear();
        textComment1.sendKeys(text);
        WebElement confirmationBtn1 = driver.findElement(confirmationBtn);
        confirmationBtn1.click();
    }
    public void redirectOfferWithSignLetter (String text){ /*توجيه العرض مع توقيع الخطاب*/
        WebElement redirectOffer1 = driver.findElement(redirectOfferBtn);
        redirectOffer1.click();
        WebElement divRedirectOffer1 = driver.findElement(divRedirectoffer);
        exWait.until(ExpectedConditions.visibilityOf(divRedirectOffer1));
        WebElement chkBoxSignLetter = driver.findElement(chkBoxRedirectWithSignLetter);
        chkBoxSignLetter.click();
//        WebElement directingOffer1 = driver.findElement(directingOffer);
//        Select select = new Select(directingOffer1);
//        select.selectByVisibleText(text);
        WebElement textComment1 = driver.findElement(textComment);
        textComment1.clear();
        textComment1.sendKeys(text);
        WebElement confirmationBtn1 = driver.findElement(confirmationBtn);
        confirmationBtn1.click();
    }




    public void returnOffer (String text){  /*اعادة العرض */
        WebElement returnOffer1 = driver.findElement(returnOffer);
        returnOffer1.click();
        WebElement divReturnOffer1 = driver.findElement(divReturnOffer);
        exWait.until(ExpectedConditions.visibilityOf(divReturnOffer1));
        WebElement textViceComment1 = driver.findElement(textViceComment);
        textViceComment1.clear();
        textViceComment1.sendKeys(text);
        WebElement confirmReturnBtn1 = driver.findElement(confirmReturnBtn);
        confirmReturnBtn1.click();
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
    private By exportNotesField = By.id("btn_ExportNotes"); /*ملاحظات التصدير*/
    private By exportNotesTab = xpath("//div[@class='wrapper_head_title d-flex'][contains(text(),'ملاحظات التصدير')]"); /* تاب ملاحظات التصدير*/
    private By addExportNotesBtn = By.cssSelector(".btn_add_note.d-flex.align-items-center"); /*زر اضف ملاحظة*/
    private By divAddNotes = By.cssSelector("div[id='addNotesModal'] div[class='modal-content']"); /*نافذة اضف ملاحظة*/
    private By notesText = By.cssSelector("#txt_exportNote"); /*حقل النص*/
    private By saveNote = By.id("btn_saveExportNote"); /*زر حفظ*/
    private By confirmationDiv = By.id("div_confirmSavePopover"); /*نافذة هل تريد بالتأكيد الحفظ*/
    private By btnYes = By.id("btn_agreeExportNote"); /*زر موافق*/

    public void exportNotes(){
        WebElement exportNotes1 = driver.findElement(exportNotesField);
        exportNotes1.click();
        WebElement exportNotesTab1 = driver.findElement(exportNotesTab);
        exWait.until(ExpectedConditions.visibilityOf(exportNotesTab1));
    }
    public void addExportNotes ( String name ) {
        WebElement addExportNotes1 = driver.findElement(addExportNotesBtn);
        addExportNotes1.click();
        WebElement textField = driver.findElement(notesText);
        exWait.until(ExpectedConditions.visibilityOf(textField));
        textField.sendKeys(name);
        WebElement saveNote1 = driver.findElement(saveNote);
        exWait.until(ExpectedConditions.elementToBeClickable(saveNote1));
        saveNote1.click();
        WebElement confirmationDiv1 = driver.findElement(confirmationDiv);
        exWait.until(ExpectedConditions.visibilityOf(confirmationDiv1));
        WebElement btnYes1 = driver.findElement(btnYes);
        btnYes1.click();
    }
    public String getNotesContent(){
        WebElement content = driver.findElement(cssSelector("p[class='d-flex gap-2'] span:nth-child(2)"));
        return content.getText();
    }

    /*========================================================================*/
                                /* احاله*/
    /*========================================================================*/

    private By forward = By.id("btn_forward"); /*إحالة*/

    private final By forwardToDiv = By.cssSelector(".modal-content.modal-assignment.h-100"); /*تاب الاحالة  */

    private final By allBaladia = By.cssSelector("#rd_general"); /*عموم البلدية */
    private final By baladiaSearch = By.cssSelector("#txt_name_srch"); /*بحث في عموم البلدية */
    private final By selectOriginal = By.cssSelector("input[id='rd_original_8jCvnEMsbEs%3d']"); /*اصــــل*/
    private final By selectCopy = By.cssSelector("label[for='ch_copy_Wwu4ooBrnXU%3d']"); /*صـــورة */

    private final By favourite = By.cssSelector("#rd_fav"); /*المفضلة */
    private final By favouriteSearch = By.cssSelector("#txt_name_srch"); /* بحث في المفضلة */

    /*private final By selectOriginalFav = By.cssSelector("input[id='rd_original_saFigbSGQbc%3d']"); /*أصــل
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
    private final By confirmDiv = By.id("div_confirmForward"); /*تاب المواقفة*/
    private final By btnYesForward = By.id("btn_confirmForward"); /*زر موافق */



    public void goToForwardTab (){ /*فتح تاب احالـــة */
        WebElement forward1 = driver.findElement(forward);
        forward1.click();
        WebElement forwardToDiv1 = driver.findElement(forwardToDiv);
        exWait.until(ExpectedConditions.visibilityOf(forwardToDiv1));
    }


    public void forwardToAllBaladia (String name , String forwardType){ /* احالة الى عموم البلدية  ,0  اصل , 1 صورة */  /*this function is not dynamic*/
        goToForwardTab();
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
        WebElement divAgree = driver.findElement(confirmDiv);
        exWait.until(ExpectedConditions.visibilityOf(divAgree));
        WebElement btnYes = driver.findElement(btnYesForward);
        btnYes.click();
    }

    /*public void forwardToFavourite (String name , String forwardType){ /* احالة الى المفضلة  ,0  اصل , 1 صورة */ /*this function is not dynamic
        WebElement favRadio = driver.findElement(favourite);
        favRadio.click();
        WebElement search = driver.findElement(favouriteSearch);
        search.sendKeys(name);
        if (Objects.equals(forwardType, "0")){ /*اصــل
            WebElement original = driver.findElement(selectOriginalFav);
            original.click();
        }
        else if (Objects.equals(forwardType, "1")){ /*صــورة
            WebElement copy = driver.findElement(selectCopyFav);
            copy.click();
        }
        WebElement send = driver.findElement(sendButton);
        send.click();
        WebElement divAgree = driver.findElement(confirmDiv);
        exWait.until(ExpectedConditions.visibilityOf(divAgree));
        WebElement btnYes = driver.findElement(btnYesForward);
        btnYes.click();
    }*/
    public void forwardToCustomEmp (String Dept , String Name , String Directing ){ /* احالة الى موظف محدد  ,0  اصل , 1 صورة */
        goToForwardTab();
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
        WebElement divAgree = driver.findElement(confirmDiv);
        exWait.until(ExpectedConditions.visibilityOf(divAgree));
        WebElement btnYes = driver.findElement(btnYesForward);
        btnYes.click();
    }




    /*========================================================================*/
                                /* احالة للحفظ*/
    /*========================================================================*/

    private By forwardToSave = By.id("btn_forwardForSave"); /*إحالة للحفظ */
    private By forwardToSaveDiv = By.xpath("(//div[@class='modal-content p-4'])[1]"); /*نافذه احالة للحفظ*/
    private By comment = By.id("txt_comment"); /*الشرح */
    private By btnSave = By.cssSelector("button[onclick='inboxDetailsObj.forwardToSave();']");

    public void goToForwardToSave (){ /*فتح تاب احالة للحفظ*/
        WebElement forwardToSave1 = driver.findElement(forwardToSave);
        forwardToSave1.click();
        WebElement forwardToSaveDiv1 = driver.findElement(forwardToSaveDiv);
        exWait.until(ExpectedConditions.visibilityOf(forwardToSaveDiv1));
    }
    public void sendTransToSave (String comment1){
        WebElement commentField = driver.findElement(comment);
        commentField.sendKeys(comment1);
        WebElement btnSave1 = driver.findElement(btnSave);
        btnSave1.click();
    }





    private By forwardToSign = By.id("btn_ForwardToSign"); /* إحالة للتوقيع */

    private By btnMore = By.id("btn_more_options"); /*زر المزيد */




    /*========================================================================*/
                             /* المعلومة مكانية*/
    /*========================================================================*/
    private By addGeoInfo = By.id("btn_GeoInformation"); /*المعلومة الجيومكانية*/
    private By geoInfoDiv = By.cssSelector(".esri-view-surface.esri-view-surface--touch-none"); /*صفحه معلومة مكانية*/

    public void addGeoInfo (){ /* اضافه معلومة مكانية*/
        WebElement addGeoInfo1 = driver.findElement(addGeoInfo);
        addGeoInfo1.click();
    }
    public WebElement getGeoInfo (){
        WebElement geoDiv = driver.findElement(geoInfoDiv);
        exWait.until(ExpectedConditions.visibilityOf(geoDiv));
        return geoDiv;
    }







    /*========================================================================*/
                                   /* تأشير*/
    /*========================================================================*/
    private By marking = By.id("btn_Vice"); /*تأشير*/
    private By viceDiv = By.cssSelector("li[id='btn_Vice'] div[class='quick_actions-link_icon']");
    private By btnConfirmVice = By.cssSelector("#btnConfirmViceAction");  /*زر تأكيد */
    private By markingAndreferToPrince = By.xpath("//a[contains(text(),'تأشير و إحالة لسعادة أمير منطقة الحدود الشمالية')]"); /*تأشير و إحالة لسعادة أمير منطقة الحدود الشمالية*/
    private By markingAndreferToAgent = By.xpath("//a[contains(text(),'تأشير و إحالة للوكيل')]");  /*تأشير و إحاله الى الوكيل */
    private By markingAndreferToCustomEmp = By.xpath("//a[contains(text(),'تأشير إلي موظف محدد')]"); /*تأشير الى موظف محدد*/


    public void goToVice (){ /*فتح تاب تأشير */
        WebElement vice1 = driver.findElement(marking);
        vice1.click();
        WebElement viceDiv1 = driver.findElement(viceDiv);
        exWait.until(ExpectedConditions.visibilityOf(viceDiv1));
    }
    public void confirmVice (){
        WebElement viceDiv = driver.findElement(By.cssSelector("div[id='model_SignVicePopUp'] div[class='modal-content p-4']"));
        exWait.until(ExpectedConditions.visibilityOf(viceDiv));
        WebElement btnConfirmVice1 = driver.findElement(btnConfirmVice);
        btnConfirmVice1.click();
    }
    public void marking_ReferToPrince (){ /*تأشير و إحالة لسعادة أمير منطقة الحدود الشمالية*/
        WebElement viceDiv1 = driver.findElement(viceDiv);
        Actions actions = new Actions(driver);
        actions.moveToElement(viceDiv1).perform();
        WebElement markingAndreferToPrince1 = driver.findElement(markingAndreferToPrince);
        markingAndreferToPrince1.click();
        WebElement confirmDiv = driver.findElement(By.xpath("(//div[@class='modal-content p-4'])[3]"));
        exWait.until(ExpectedConditions.visibilityOf(confirmDiv));

        confirmVice();
    }
    public void marking_ReferToAgent (){ /*تأشير و إحالة للوكيل*/
        WebElement viceDiv1 = driver.findElement(viceDiv);
        Actions actions = new Actions(driver);
        actions.moveToElement(viceDiv1).perform();
        WebElement markingAndreferToAgent1 = driver.findElement(markingAndreferToAgent);
        markingAndreferToAgent1.click();
        WebElement confirmDiv = driver.findElement(By.xpath("(//div[@class='modal-content p-4'])[3]"));
        exWait.until(ExpectedConditions.visibilityOf(confirmDiv));
        confirmVice();
    }
    public void marking_ReferToCustomEmp (){ /*تأشير الى موظف محدد*/
        WebElement viceDiv1 = driver.findElement(viceDiv);
        Actions actions = new Actions(driver);
        actions.moveToElement(viceDiv1).perform();
        WebElement markingAndreferToCustomEmp1 = driver.findElement(markingAndreferToCustomEmp);
        markingAndreferToCustomEmp1.click();
        WebElement confirmDiv = driver.findElement(By.xpath("(//div[@class='modal-content p-4'])[3]"));
        exWait.until(ExpectedConditions.visibilityOf(confirmDiv));
        WebElement selectEmp = driver.findElement(By.id("slcSpeceficEmp_ddlSelectButton"));
        selectEmp.click();
        WebElement emp =driver.findElement(By.cssSelector("div[id='slcSpeceficEmp_Uu35rj0b1yY%3d_nav'] label[data-type='child']"));  /*اختيار الموظف "حسين حسن عبد القادر"*/
        emp.click();
        confirmVice();
    }

    public void viceLetterAndMomoTogether (){  /*تأشير الخطاب والمذكرة معا*/
        WebElement vice1 = driver.findElement(marking);
        vice1.click();
        WebElement signDiv1 = driver.findElement(signOfferAndLetterDiv);
        exWait.until(ExpectedConditions.visibilityOf(signDiv1));
        WebElement chkBoxWithSignMomo1 = driver.findElement(chkBoxWithViceMomo);
        chkBoxWithSignMomo1.click();
        WebElement btnConfirm1 = driver.findElement(btnConfirm);
        btnConfirm1.click();
    }
    public void viceOfferAndMomoTogether (){  /*تأشير العرض والمذكرة معا*/
        WebElement vice1 = driver.findElement(marking);
        vice1.click();
        WebElement signDiv1 = driver.findElement(signOfferAndLetterDiv);
        exWait.until(ExpectedConditions.visibilityOf(signDiv1));
        WebElement chkBoxWithSignMomo1 = driver.findElement(chkBoxWithViceMomo);
        chkBoxWithSignMomo1.click();
        WebElement btnConfirm1 = driver.findElement(btnConfirm);
        btnConfirm1.click();
    }
    public void viceOfferAndLetterTogether (){  /*تأشير العرض والخطاب معا*/
        WebElement vice1 = driver.findElement(marking);
        vice1.click();
        WebElement signDiv1 = driver.findElement(signOfferAndLetterDiv);
        exWait.until(ExpectedConditions.visibilityOf(signDiv1));
        WebElement chkBoxWithSignMomo1 = driver.findElement(chkBoxWithViceLetter);
        chkBoxWithSignMomo1.click();
        WebElement btnConfirm1 = driver.findElement(btnConfirm);
        btnConfirm1.click();
    }





    /**=======================================================================*/
                                  /* التوقيع*/
    /**======================================================================**/

    private By sign = By.id("btn_sign"); /*توقيع*/
    private By signDiv = By.xpath("(//div[@class='quick_actions-link_icon'])[9]");
    private By signOfferAndLetterDiv = By.xpath("(//div[@class='modal-content p-4'])[3]"); /*نافذة توقيع العرض وتأشير الخطاب معا*/
    private By btnConfirm = By.cssSelector("#btnConfirmViceAction");  /*زر تأكيد */
    private By signByOrderOfPrince = By.cssSelector("li[id='btn_sign'] li:nth-child(1) a:nth-child(1)"); /*توقيع بأمر سعاده الامير*/
    private By signByorderOfRepresentativePrince = By.xpath("//li[@id='btn_sign']//li[2]//a[1]"); /*توقيع بأمر من نائب الامير */
    private By signWithDelegate = By.xpath("//a[contains(text(),'توقيع بتفويض')]"); /*توقيع بتفويض*/
    private By delegateAbout = By.id("slcOnBehalf_ddlSelectButton"); /*ddl مفوض عنه*/
    private By chkBoxWithViceLetter = By.cssSelector("label[for='chkWithViceLetter']");/*تشك بوكس مع تأشير الخطاب*/
    private By chkBoxWithSignMomo = By.cssSelector("label[for='chkWithSignMemo']"); /*تشك بوكس مع توقيع المذكرة*/
    private By chkBoxWithViceMomo = By.cssSelector("label[for='chkWithViceMemo']"); /*تشك بوكس مع تأشير المذكرة*/
    private By signOfferAndDirctingAboutHim = By.xpath("//a[contains(text(),'توقيع و توجيه العرض عنه')]");  /*توقيع وتوجيه العرض عنه*/
    private By signOfferAndDirctingAboutHimDiv =By.xpath("(//div[@class='modal-content p-4'])[4]");  /*نافذة توقيع وتوجيه العرض عنه*/



    public void goToSign (){ /*فتح تاب توقيع*/
        WebElement sign1 = driver.findElement(sign);
        sign1.click();
        WebElement signDiv1 = driver.findElement(signDiv);
        exWait.until(ExpectedConditions.visibilityOf(signDiv1));
    }
    public void signConfirm (){   /*توقيع*/
        WebElement signDiv = driver.findElement(By.cssSelector("div[id='model_SignVicePopUp'] div[class='modal-content p-4']"));
        exWait.until(ExpectedConditions.visibilityOf(signDiv));
        WebElement btnConfirm1 = driver.findElement(btnConfirm);
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#model_SignVicePopUp"))));
        btnConfirm1.click();
    }

    public void signLetterAndSignMomoTogether (){ /*توقيع الخطاب وتوقيع المذكرة معا*/
        WebElement sign1 = driver.findElement(sign);
        sign1.click();
        WebElement signDiv1 = driver.findElement(signOfferAndLetterDiv);
        exWait.until(ExpectedConditions.visibilityOf(signDiv1));
        WebElement chkBoxWithSignMomo1 = driver.findElement(chkBoxWithSignMomo);
        chkBoxWithSignMomo1.click();
        WebElement btnConfirm1 = driver.findElement(btnConfirm);
        btnConfirm1.click();
    }
    public void signOfferAndViceLetterTogether (){ /*توقيع العرض وتأشير الخطاب معا*/
        WebElement sign1 = driver.findElement(sign);
        sign1.click();
        WebElement signDiv1 = driver.findElement(signOfferAndLetterDiv);
        exWait.until(ExpectedConditions.visibilityOf(signDiv1));
        WebElement chkBoxWithViceLetter1 = driver.findElement(chkBoxWithViceLetter);
        chkBoxWithViceLetter1.click();
        WebElement btnConfirm1 = driver.findElement(btnConfirm);
        btnConfirm1.click();
    }
    public void signOfferAndViceMomoTogether (){ /*توقيع العرض وتأشير المذكرة معا*/
        WebElement sign1 = driver.findElement(sign);
        sign1.click();
        WebElement signDiv1 = driver.findElement(signOfferAndLetterDiv);
        exWait.until(ExpectedConditions.visibilityOf(signDiv1));
        WebElement chkBoxWithViceLetter1 = driver.findElement(chkBoxWithViceMomo);
        chkBoxWithViceLetter1.click();
        WebElement btnConfirm1 = driver.findElement(btnConfirm);
        btnConfirm1.click();
    }



    public void signByOrderOfPrince (){ /*توقيع بأمر من الامير*/
        WebElement signDiv1 = driver.findElement(signDiv);
        Actions actions = new Actions(driver);
        actions.moveToElement(signDiv1).perform();
        WebElement signByOrderOfPrince1 = driver.findElement(signByOrderOfPrince);
        signByOrderOfPrince1.click();
        WebElement signDiv = driver.findElement(By.cssSelector("div[id='model_SignVicePopUp'] div[class='modal-content p-4']"));
        exWait.until(ExpectedConditions.visibilityOf(signDiv));
        WebElement btnConfirm1 = driver.findElement(btnConfirm);
        btnConfirm1.click();
    }
    public void signByOrderOfRepresentativePrince (){ /*توقيع بأمر من نائب الامير */
        WebElement signDiv1 = driver.findElement(signDiv);
        Actions actions = new Actions(driver);
        actions.moveToElement(signDiv1).perform();
        WebElement signByorderOfRepresentativePrince1 = driver.findElement(signByorderOfRepresentativePrince);
        signByorderOfRepresentativePrince1.click();
        WebElement signDiv = driver.findElement(By.cssSelector("div[id='model_SignVicePopUp'] div[class='modal-content p-4']"));
        exWait.until(ExpectedConditions.visibilityOf(signDiv));
        WebElement btnConfirm1 = driver.findElement(btnConfirm);
        btnConfirm1.click();
    }
    public void selectDelegateAbout (String name){     /*اختيار المفوض عنه*/
        WebElement delegateAbout1 = driver.findElement(delegateAbout);
        delegateAbout1.click();
        WebElement delegateName = driver.findElement(xpath("//label[contains(text(),'"+name+"')]"));
        delegateName.click();
    }
    public void signWithDelegate (String delegateName){  /*توقيع بتفويض*/
        WebElement signDiv1 = driver.findElement(signDiv);
        Actions actions = new Actions(driver);
        actions.moveToElement(signDiv1).perform();
        WebElement signWithDelegate1 = driver.findElement(signWithDelegate);
        signWithDelegate1.click();
        WebElement signDiv = driver.findElement(By.xpath("(//div[@class='modal-content p-4'])[3]"));
        exWait.until(ExpectedConditions.visibilityOf(signDiv));
        selectDelegateAbout(delegateName);
        WebElement btnConfirm1 = driver.findElement(btnConfirm);
        btnConfirm1.click();
    }
    public void signAndRedirectOfferAboutHim(String text){ /*توقيع وتوجيه العرض عنه*/
        WebElement signDiv1 = driver.findElement(signDiv);
        Actions actions = new Actions(driver);
        actions.moveToElement(signDiv1).perform();
        WebElement signOfferAndDirctingAboutHim1 = driver.findElement(signOfferAndDirctingAboutHim);
        signOfferAndDirctingAboutHim1.click();
        WebElement signDiv = driver.findElement(signOfferAndDirctingAboutHimDiv);
        exWait.until(ExpectedConditions.visibilityOf(signDiv));
        WebElement dirctingText = driver.findElement(By.id("txtPurposeComment"));
        dirctingText.sendKeys(text);
        WebElement btnConfirm = driver.findElement(By.id("btnConfirmRedirectAction"));
        btnConfirm.click();
    }


    public void waitDiv ()  { /*نافذة تم توقيع */
        WebElement afterSignDiv = driver.findElement(By.cssSelector("div[class='modal-dialog modal-dialog-centered modal-md'] div[class='modal-content']"));
        exWait.until(ExpectedConditions.visibilityOf(afterSignDiv));
        WebElement btnClose = driver.findElement(By.id("btnCloseClick"));
        btnClose.click();
    }

    /*========================================================================*/
                            /* اعادة للمصدر*/
    /*========================================================================*/
    private By returnToSource = By.id("btn_backToSource"); /*إعادة للمصدر*/
    private By divReturnToSource = By.xpath("(//div[@class='modal-content p-4'])[3]"); /*نافذه اعاده للمصدر*/
    private By textField = By.id("txtSignViceComment");

    public void returnToSource (String text){ /*فتح تاب اعاده للمصدر*/
        WebElement backToSource1 = driver.findElement(returnToSource);
        backToSource1.click();
        WebElement divReturnToSource1 = driver.findElement(divReturnToSource);
        exWait.until(ExpectedConditions.visibilityOf(divReturnToSource1));
        WebElement textField1 = driver.findElement(textField);
        textField1.sendKeys(text);
        WebElement btnConfirm = driver.findElement(btnConfirmVice);
        btnConfirm.click();
    }



    /*========================================================================*/
                           /* حفظ مؤقت*/
    /*========================================================================*/
    private By tempSave = By.id("btn_TempSaveForTreatment"); /*حفظ مؤقت*/
    private By tempSaveDiv = By.xpath("(//div[@class='modal-content'])[3]"); /*نافذه الحفظ المؤقت*/
    private By tempExplanation = By.id("txt_TempSaveExplanation"); /*نص الشرح*/
    private By tempSaveConfirm = By.cssSelector("button[onclick='inboxDetailsObj.tempSaveAction()']"); /*زر الحفظ*/
    private By popUpConfirm = By.id("div_confirmTempSavePopup"); /*نافذة تأكيد الحفظ*/
    private By btnSaveOnPopUp = By.id("btn_TempSaveFooterAgree"); /*الحفظ في نافذه تأكيد*/



    public void tempSave (String text){ /*حفظ مؤقت*/
        WebElement tempSave1 = driver.findElement(tempSave);
        tempSave1.click();
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(tempSaveDiv)));
        WebElement tempExplanation1 = driver.findElement(tempExplanation);
        tempExplanation1.sendKeys(text);
        WebElement tempSave2 = driver.findElement(tempSaveConfirm);
        tempSave2.click();
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(popUpConfirm)));
        WebElement btnSaveOnPopUp1 = driver.findElement(btnSaveOnPopUp);
        btnSaveOnPopUp1.click();
    }



    /*========================================================================*/
                                    /*طلب أصول*/
    /*========================================================================*/
    private By assetsRequest = By.id("btn_AssetsRequest"); /* زر طلب أصول*/
    private By divAssestsRequest = By.xpath("(//div[@class='modal-content p-4'])[2]"); /*نافذه طلب اصول*/
    private By chkBoxSaveAssests = By.cssSelector("label[for='chbx_keepAssets']"); /*تشك بوكس الاحتفاظ بالاصول */
    private By btnOkAssests = By.xpath("(//button[@class='qBtn m-0'][contains(text(),'موافق')])[2]");
    private By btnClose = By.id("btnCloseClick"); /*زر اغلاق النافذه*/

    public void assetsRequest (){ /*عمل طلب اصول*/
        WebElement assetsRequest1 = driver.findElement(assetsRequest);
        assetsRequest1.click();
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(divAssestsRequest)));
        WebElement chkBoxSaveAssests1 = driver.findElement(chkBoxSaveAssests);
        chkBoxSaveAssests1.click();
        WebElement btnOk1 = driver.findElement(btnOkAssests);
        btnOk1.click();
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//div[@id='errorModal'])[1]"))));
        WebElement btnClose1 = driver.findElement(btnClose);
        btnClose1.click();
        exWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("(//a[@class='quick_actions-link'])[19]"))));
    }
    /*========================================================================*/
                                  /*المرفقات*/
    /*========================================================================*/
    private By attachments = By.id("btn_attachments"); /*المرفقات */
    private By attachmentsTab = xpath("//div[@class='body_inbox_content']//div[@id='div_conatiner_attach']"); /*تاب المرفقات*/
    private By dropDownMenuBtn = By.xpath("//div[@id='btnAttachActions']//button[@id='dropdownMenuButton1']");
    private By btnAddAttach = By.id("btnAddAttach"); /*اضافه مرفق*/
    private By divAddAttach = By.xpath("(//div[@class='modal-content'])[14]");
    private By attachFileType = By.id("attachDiv_ddl_FileType");
    private By attachFileName = By.id("attachDiv_txt_FileName");
    private By attachFile = By.xpath("(//label[@for='attachDiv_btn_ChooseFile'])[1]");
    private By btnAdd = By.xpath("//button[contains(text(),'إضافة')]");
    private By attachmentDiv = By.cssSelector("div[class='cRound']"); /*نافذه المرفق*/

    public void goToAttachments(){ /*فتح المرفقات */
        WebElement attachments1 = driver.findElement(attachments);
        attachments1.click();
    }
    public WebElement getAttachmentsTab (){ /*ارجاع تاب المرفقات */
        WebElement attachments1 = driver.findElement(attachmentsTab);
        return attachments1;
    }
    public void addAttach(String filePath, String fileName){ /*اضافه مرفق */
        WebElement dropDown1 = driver.findElement(dropDownMenuBtn);
        dropDown1.click();
        WebElement btnAddAttach1 = driver.findElement(btnAddAttach);
        btnAddAttach1.click();
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(divAddAttach)));
        WebElement attachFileType1 = driver.findElement(attachFileType);
        Select select = new Select (attachFileType1);
        select.selectByVisibleText("ملفات صورية");
        WebElement attachFileName1 = driver.findElement(attachFileName);
        attachFileName1.sendKeys(fileName);
        File file = new File(filePath);
        String absolutePath = file.getAbsolutePath();
        WebElement attachFile1 = driver.findElement(attachFile);
        attachFile1.sendKeys(absolutePath);
        WebElement btnAdd1 = driver.findElement(btnAdd);
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("attachDiv_notify_0"))));
        btnAdd1.click();
    }

    public boolean attachAdded (){
        WebElement attachmentDiv1 = driver.findElement(attachmentDiv);
        return attachmentDiv1.isDisplayed();
    }

    /*========================================================================*/
                            /*ارسال كبرقية*/
    /*========================================================================*/
    private By asTelegramBtn = By.id("btn_telegram"); /*زر ارسال كبرقية*/
    private By asTelegramDiv = By.xpath("(//div[@class='modal-content p-4'])[5]"); /*نافذة ارسال كبرقية*/
    private By radSignPrince = By.id("RdPrincePurp"); /*اختيار بتوجيه سمو الامير */
    private By confirmTelegramBtn = By.id("btnConfirmTelegramAction"); /*زر تأكيد*/
    private By viceAndSendTelegram = By.id("(menu_telegram_actions"); /*  تأشير وارسال كبرقية  */

    public void sendAsTelegram (){   /*ارسال كبرقية*/
        WebElement asTelegramBtn1 = driver.findElement(asTelegramBtn);
        asTelegramBtn1.click();
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(asTelegramDiv)));
        WebElement radSignPrince1 = driver.findElement(radSignPrince);
        radSignPrince1.click();
        WebElement confirmTelegramBtn1 = driver.findElement(confirmTelegramBtn);
        confirmTelegramBtn1.click();
    }
    public void viceAndSendAsTelegram(){ /*تأشير وارسال كبرقية */
        Actions a = new Actions(driver);
        WebElement asTelegramBtn1 = driver.findElement(asTelegramBtn);
        a.moveToElement(asTelegramBtn1).perform();
        WebElement viceAndSendTelegram1 = driver.findElement(viceAndSendTelegram);
        viceAndSendTelegram1.click();
        WebElement radSignPrince1 = driver.findElement(radSignPrince);
        radSignPrince1.click();
        WebElement confirmTelegramBtn1 = driver.findElement(confirmTelegramBtn);
        confirmTelegramBtn1.click();


    }


    /*=========================================================================================================================*/

    private By showAll = xpath("//span[contains(text(),'عرض الجميع')]"); /*عرض الجميع */

    private By explanationDdlBtn = By.id("btnAttachActions");
        private By perview = xpath("//a[@class='dropdown-item'][contains(text(),'معاينة')]"); /*معاينة */
        private By generalExplanations = xpath("//a[contains(text(),'الشروحات الكلية')]"); /*الشروحات الكلية */
    private By sortExplanations = By.id("divExpSortCounterSpan"); /*الترتـيب*/

    private By treatDetails = xpath("(//div[@class='info-comp']/span[@class='i-icon'])[1]"); /*تفاصيل المعاملة */



    public WebElement getMailInboxPage() {   /*ارجاع صفحه الوارد */
        WebElement mailInboxPage = driver.findElement(inboxPage);
        return mailInboxPage;
    }

    public void mailInboxSearch (String text){  /*   البحث عن معامله */
        WebElement searchInput = driver.findElement(searchText);
        searchInput.clear();
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
        WebElement treatArchiveNum = driver.findElement(xpath("//table[@class='info-comp-table-list']/tr/th[text()='الأرشيف']/following-sibling::td[1]"));
        String archive = treatArchiveNum.getText();
        actions.moveByOffset(0, 0).perform();
        return archive;
    }
    /*========================================================================*/

    /*========================================================================*/


    /*========================================================================*/

    /*========================================================================*/

    /*========================================================================*/
    public void openReview (){ /*فتح معاينة*/
        WebElement preview1 = driver.findElement(preview);
        preview1.click();
    }
    /*========================================================================*/

    /*========================================================================*/

    /*========================================================================*/

    /*========================================================================*/

    /*========================================================================*/
    public void goForwardToSign (){ /*فتح تاب احالة للتوقيع*/
        WebElement forwardToSign1 = driver.findElement(forwardToSign);
        forwardToSign1.click();
    }
    /*========================================================================*/

    /*========================================================================*/
    public void btnMoreClick (){ /*زر المزيد*/
        WebElement btnMore1 = driver.findElement(btnMore);
        btnMore1.click();
    }
    /*========================================================================*/

    /*========================================================================*/

    /*========================================================================*/



}


