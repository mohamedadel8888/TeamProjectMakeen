package AutomateMakeen.Pages.Elite;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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
    private By attachments = By.id("btn_attachments"); /*المرفقات */
    private By attachmentsTab = By.xpath("//div[@class='body_inbox_content']//div[@id='div_conatiner_attach']"); /*تاب المرفقات*/
    /*========================================================================*/
    private By letters = By.id("btn_letter_display"); /*الخطاب*/
        private By ddlDepartments = By.id("drp_letter_header_depts"); /* من الادارة */
        private By getDdlDepartmentsDiv = By.id("drp_letter_header_depts_collapsibleDiv"); /* ديف الادارة */
        private By sefatLetterDdl = By.cssSelector("div[onclick='externalLetter.clearLetterTypesDDLText(event)']"); /*صفه الخطاب */
        private By getDdlSefatLetterTextSearch = By.id("txt_letterTypesSrch"); /* حقل البحث في صفه الخطاب */
        private By forwardToBtn =By.id("btnOriginalReciever");/*زر اختيار موجه الي */
        private By forwardTypeDdl = By.id("div_drpPurpsContainer");  /* اختيار نوع التوجيه */






    private By offers = By.id("btn_offerletter_display"); /*العروض*/
    private By internalMemo = By.id("btn_letter_display"); /*مذكرة داخلية*/
    private By preview = By.id("btn_PreviewLetter"); /*المعاينة*/
    private By exportNotes = By.id("btn_ExportNotes"); /*ملاحظات التصدير*/
    private By forward = By.id("btn_forward"); /*إحالة*/
    private By forwardToSave = By.id("btn_forwardForSave"); /*إحالة للحفظ */
    private By forwardToSign = By.id("btn_ForwardToSign"); /* إحالة للتوقيع */
    private By backToSource = By.id("btn_backToSource"); /*إعادة للمصدر*/
    private By btnMore = By.id("btn_more_options"); /*زر المزيد */
    private By marking = By.id("btn_Vice"); /*تأشير*/
        private By markingAndreferToAgent = By.xpath("//a[contains(text(),'تأشير و إحالة للوكيل')]"); /*تأشير و إحاله الى الوكيل */
        private By markingAndreferToCustomEmp = By.xpath("//a[contains(text(),'تأشير إلي موظف محدد')]"); /*تأشير الى موظف محدد*/
    private By sign = By.id("btn_sign"); /*توقيع*/
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
    public void sortingBtn (){   /*زر ترتيب*/
        WebElement sortBtn = driver.findElement(sorting);
        sortBtn.click();
    }
    public String getTreatArchiveNum (){ /*ارجاع الأرشيف */
        WebElement details = driver.findElement(treatDetails);
        Actions actions = new Actions(driver);
        actions.moveToElement(details).perform();
        WebElement treatArchiveNum = driver.findElement(By.xpath("//table[@class='info-comp-table-list']/tr/th[text()='الأرشيف']/following-sibling::td[1]"));
        String archive = treatArchiveNum.getText();
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
    public void createLettersTab (){
        WebElement letters1 = driver.findElement(letters);
        letters1.click();
    }
    public void selectDepartment(String department){ /*اختيار من الادارة */
        WebElement ddlDepartments1 = driver.findElement(ddlDepartments);
        ddlDepartments1.click();
        List<WebElement> l1 = driver.findElements(By.cssSelector("#drp_letter_header_depts_collapsibleDiv"));
        l1.get(0).click();
    }
    public void selectSefatLetter(String sefatLetter){ /*اختيار صفه الخطاب */
    WebElement sefatLetterDdl1 = driver.findElement(sefatLetterDdl);
    sefatLetterDdl1.click();
    WebElement sefatLetterSearch = driver.findElement(getDdlSefatLetterTextSearch);
    sefatLetterSearch.sendKeys(sefatLetter);
    WebElement firstElement = driver.findElement(By.xpath("//a[normalize-space()='"+sefatLetter+"']"));
    firstElement.click();
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
    public void selectForwardType (String name){     /*اختيار نوع التوجية*/
        WebElement forwardTypeDdl1 = driver.findElement(forwardTypeDdl);
        forwardTypeDdl1.click();
        WebElement forwardType = driver.findElement(By.xpath("(//a[contains(text(),'"+name+"')])"));
        forwardType.click();
    }


    /*========================================================================*/
    public void createOfferTab (){ /*فتح تاب انشاء عرض*/
        WebElement offers1 = driver.findElement(offers);
        offers1.click();
    }
    /*========================================================================*/
    public void createMozakeraTab (){ /*فتح تاب انشاء مذكرة*/
        WebElement internalMomo1 = driver.findElement(internalMemo);
        internalMomo1.click();
    }
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
    public void goToForwardTab (){ /*فتح تاب احالـــة */
        WebElement forward1 = driver.findElement(forward);
        forward1.click();
    }
    /*========================================================================*/
    public void goToForwardToSave (){ /*فتح تاب احالة للحفظ*/
        WebElement forwardToSave1 = driver.findElement(forwardToSave);
        forwardToSave1.click();
    }
    /*========================================================================*/
    public void goToSign (){ /*فتح تاب توقيع*/
        WebElement sign1 = driver.findElement(sign);
        sign1.click();
    }
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


