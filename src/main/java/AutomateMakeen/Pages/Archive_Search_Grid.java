package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Archive_Search_Grid extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;
    public Archive_Search_Grid(WebDriver driver) {
        super(driver);
        this.driver = driver;
        //specific wait for every page
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));

    }

    private By toggleDateType = By.cssSelector(".slider.round");

    public void toggleDateType(){
        driver.findElement(toggleDateType).click();
    }

    private By firstRowHijriDateBy = By.xpath("//tr[1]/td[5]/div");
    private By firstRowMiladiDateBy = By.xpath("//tr[1]/td[4]/div");

    private By todayDateInCal = By.id("div_TodayDate");
    public boolean validateToggleDate(String dateType){
        switch(dateType){
            case "هجري":
            {
                driver.findElement(incomeMailDateBy).click();
                return driver.findElement(todayDateInCal).getText().contains(getHijriDate()) &&
                        driver.findElement(firstRowHijriDateBy).getText().contains(getHijriDate());
            } case "ميلادي":{
                driver.findElement(incomeMailDateBy).click();
                return driver.findElement(todayDateInCal).getText().contains(getMiladiDate()) &&
                        driver.findElement(firstRowMiladiDateBy).getText().contains(getMiladiDate());
            } default:{
                System.out.println("Wrong Input");
                return false;
            }
        }
    }
//    private By calenderCreateDateBy = By.cssSelector(".fa.fa-calendar[onclick=\"CalenderObj.showCalender('txt_gov_emp_hire_date',true,prs_greg_date!=1?'Hij':'Georg')\"]");

    private By incomeTabBy = By.id("tb_ctrldiv_Tab_Hrf_0");
    private By archiveTabBy = By.id("tb_ctrldiv_Tab_Hrf_3");
    private By senderTabBy = By.id("tb_ctrldiv_Tab_Hrf_4");
    private By subjectTabBy = By.id("tb_ctrldiv_Tab_Hrf_1");
    private By attachmentsTabBy = By.id("tb_ctrldiv_Tab_Hrf_2");
    private By receiverTabBy = By.id("tb_ctrldiv_Tab_Hrf_5");
    private By citizenTabBy = By.id("tb_ctrldiv_Tab_Hrf_6");
    private By outcomeTabBy = By.id("tb_ctrldiv_Tab_Hrf_7");
    private By docTypeTabBy = By.id("tb_ctrldiv_Tab_Hrf_8");
    private By treatmentCreatorTabBy = By.id("tb_ctrldiv_Tab_Hrf_9");
    private By internalLecTabBy = By.id("tb_ctrldiv_Tab_Hrf_10");
    private By generalizationTabBy = By.id("tb_ctrldiv_Tab_Hrf_11");
    WebElement currentTab;
    public void searchTabNavigator(String tabName){
        switch(tabName){
            case "الوارد":
                driver.findElement(incomeTabBy).click();
            break;
            case "الموضوع":
                driver.findElement(subjectTabBy).click();
                break;
            case "المرفقات":
                driver.findElement(attachmentsTabBy).click();
                break;
            case "الارشيف":
                driver.findElement(archiveTabBy).click();
                break;
            case "المرسل":
                driver.findElement(senderTabBy).click();
                break;
            case "المستقبل":
                driver.findElement(receiverTabBy).click();
                break;
            case "المواطن":
                driver.findElement(citizenTabBy).click();
                break;
            case "الصادر":
                driver.findElement(outcomeTabBy).click();
                break;
            case "نوع المستند":
                driver.findElement(docTypeTabBy).click();
                break;
            case "منشئ المعاملة":
                driver.findElement(treatmentCreatorTabBy).click();
                break;
            case "المذكرة الداخلية":
                driver.findElement(internalLecTabBy).click();
                break;
            case "التعميم":
                driver.findElement(generalizationTabBy).click();
                break;
            default:
                 System.out.println("Wrong input parameters");                 return;
        }
    }


    private By incomeMailNumberBy = By.id("cph_main_txt_inbox_no");
    private By incomeMailDateBy = By.id("span_calender_inbox_date");



    private By incomeMailDateInputBy = By.id("txt_inbox_date");
    public void searchByIncomeMail(String fieldName , String data) {
        System.out.println("Method"+data);
        switch(fieldName) {
            case "رقم الوارد": {
                driver.findElement(incomeMailNumberBy).clear();
                driver.findElement(incomeMailDateInputBy).clear();
                driver.findElement(incomeMailNumberBy).sendKeys(data);
                break;
            }
            case "تاريخ الوارد": {
                driver.findElement(incomeMailNumberBy).clear();
                driver.findElement(incomeMailDateBy).click();
                setDateWithSplit(data);
                break;
            }
        }
    }

    public boolean etIncomeNumIsEmpty() {
        return driver.findElement(incomeMailNumberBy).getAttribute("value").isEmpty();
    }
    private By archiveSearchBtnBy = By.id("btn_search");
    public void clickSearch() {
        driver.findElement(archiveSearchBtnBy).click();
    }

    private By archiveSearchAllBy = By.id("btn_search_all");
    public void clickSearchAll() {
        driver.findElement(archiveSearchAllBy).click();
    }

    private By monthDDL = By.id("drp_Month");

    Select calenderMonthSelect ;

    private By calenderYearBy = By.id("tb_Year");
//    public void setDate(String year, String month, String day){
//        calenderMonthSelect = new Select(driver.findElement(monthDDL));
//        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(monthDDL)));
//        calenderMonthSelect.selectByVisibleText(month);
//        driver.findElement(calenderYearBy).clear();
//        driver.findElement(calenderYearBy).sendKeys(year);
//        driver.findElement(By.cssSelector("input[value='"+day+"']")).click();
//    }
    private By subjectFieldBy = By.id("cph_main_txt_subject");
    public void searchByEtSubject(String treatmentSubject){
        driver.findElement(subjectFieldBy).sendKeys( treatmentSubject);
    }

    private By attachmentCommentFieldBy = By.id("txt_att_comment");
    private By attachmentTypeBy = By.id("drp_att_type_ddlSelectButtonTarget");
    private By attachmentFilterBy = By.id("drp_att_type_0_nav");
    public void searchByAttachment(String attachmentComment, String attachmentType){
        if(attachmentType.isEmpty()){
            driver.findElement(attachmentCommentFieldBy).sendKeys(attachmentComment);
        }else if(attachmentComment.isEmpty()) {
            driver.findElement(attachmentCommentFieldBy).clear();
            driver.findElement(attachmentTypeBy).click();
            driver.findElement(attachmentFilterBy).click();
        }

        //لسه جزء اتواع المرفقات
    }

    private By archiveMailNumberBy = By.id("cph_main_txt_archive_no");
    private By archiveMailDateBy = By.id("span_calender_archive_date");



    private By archiveMailDateInputBy = By.id("txt_archive_date");
    public void searchByArchive(String fieldName , String data) {
        switch(fieldName) {
            case "رقم الارشيف": {
                driver.findElement(archiveMailNumberBy).clear();
                driver.findElement(archiveMailDateInputBy).clear();
                driver.findElement(archiveMailNumberBy).sendKeys(data);
                break;
            }
            case "تاريخ الانشاء": {
                driver.findElement(archiveMailNumberBy).clear();
                driver.findElement(archiveMailDateBy).click();
                setDateWithSplit(data);
                break;
            }
        }
    }

    public void openTreatmentDetails(String treatment){
        driver.findElement(By.xpath("//table[@id='tbl_arc']/tbody/tr/td[1]/div[contains(.,'"+treatment+"')]")).click();
    }
    public String getTreatmentSpecificDetail(String detail) {
        return driver.findElement(By.xpath("//div[@class='content-bx']/div/div/span[contains(.,'"+detail+"')]/following::span[1]")).getText();
    }

    public boolean checkIfTreatmentExists(String treatment) {
        try {
            return driver.findElement(By.xpath("//table[@id='tbl_arc']/tbody/tr/td/div[contains(.,'" + treatment + "')]")).isDisplayed();
        }
        catch(Exception e) {
            return false;
        }

    }

    private By senderPopupBy = By.cssSelector("input[onclick=\"pickPopUp.initialize('senders', 'txt_sender_id');\"]");
    public void searchByEtSenderName(String sender){
        control(driver.findElement(senderPopupBy),sender);
    }
    private By senderNumBy = By.id("txt_sender_id");
    public void clearEtSenderName(){
        driver.findElement(senderNumBy).clear();
    }
    private By letterNumberBy = By.id("cph_main_txt_letter_num");
    public void searchByLetterNumber(String letterNumber){
        driver.findElement(letterNumberBy).sendKeys(letterNumber);
    }
    public void clearLetterNumber(){
        driver.findElement(letterNumberBy).clear();
    }
    private By dateFromLabelBy = By.id("txt_letter_date_from");
    private By dateFromCalBy = By.cssSelector("span[onclick=\"arcSearch.changCalenderCulture('txt_letter_date_from',true);\"]");

    private By dateToLabelBy = By.id("txt_letter_date_to");
    private By dateToCalBy = By.cssSelector("span[onclick=\"arcSearch.changCalenderCulture('txt_letter_date_to',true);\"]");

    public void setLetterDateFrom(String date){
        driver.findElement(dateFromCalBy).click();
        setDateWithSplit(date);
    }
    public void clearLetterDateFrom(){
        driver.findElement(dateFromLabelBy).clear();
    }

    public void setLetterDateTo(String date){
        driver.findElement(dateToCalBy).click();
        setDateWithSplit(date);
    }
    public void clearLetterDateTo(){
        driver.findElement(dateToLabelBy).clear();
    }
    private By etSourceBy = By.id("cph_main_txt_source");
    public void setEtSource(String source){
        driver.findElement(etSourceBy).sendKeys(source);
    }

    private By etReceiverBy = By.id("txt_receiver");
    public void searchByEtReceiver(String receiver){
        driver.findElement(etReceiverBy).sendKeys(receiver);
    }

    private By civilIdBy = By.id("txt_nat_num");
    private By civilFirstNameBy = By.id("txt_first_name");
    private By civilLastNameBy = By.id("txt_last_name");
    private By civilSecondNameBy = By.id("txt_second_name");
    private By civilThirdNameBy = By.id("txt_third_name");
    
    public void searchByLinkEt(String linkField, String linkData){
        switch(linkField){
            case "الرقم التعريفي":{
                driver.findElement(civilFirstNameBy).clear();
                driver.findElement(civilSecondNameBy).clear();
                driver.findElement(civilThirdNameBy).clear();
                driver.findElement(civilLastNameBy).clear();
                driver.findElement(civilIdBy).sendKeys(linkData);    break;}
            case "الاسم الاول" :{
                driver.findElement(civilIdBy).clear();
                driver.findElement(civilSecondNameBy).clear();
                driver.findElement(civilThirdNameBy).clear();
                driver.findElement(civilLastNameBy).clear();
                driver.findElement(civilFirstNameBy).sendKeys(linkData); break;}
            case "الاسم الثاني":{
                driver.findElement(civilIdBy).clear();
                driver.findElement(civilFirstNameBy).clear();
                driver.findElement(civilThirdNameBy).clear();
                driver.findElement(civilLastNameBy).clear();
                driver.findElement(civilSecondNameBy).sendKeys(linkData); break;}
            case "الاسم الثالث":{
                driver.findElement(civilIdBy).clear();
                driver.findElement(civilFirstNameBy).clear();
                driver.findElement(civilSecondNameBy).clear();
                driver.findElement(civilLastNameBy).clear();
                driver.findElement(civilThirdNameBy).sendKeys(linkData); break;}
            case "الاسم الاخير": {
                driver.findElement(civilIdBy).clear();
                driver.findElement(civilFirstNameBy).clear();
                driver.findElement(civilSecondNameBy).clear();
                driver.findElement(civilThirdNameBy).clear();
                driver.findElement(civilLastNameBy).sendKeys(linkData); break;
            }
        }
    }

    private By etExportNumBy = By.id("txtSaderSearch");
    private By etEportDateFromBy = By.id("txt_outBox_dateFrom");
    private By etEportDateToBy = By.id("txt_outBox_dateTo");
    private By exCalDateFromBy = By.id("span_outBox_dateFrom");
    private By exCalDateToBy = By.id("span_outBox_dateTo");
    private By forwardPopUpBy = By.cssSelector("input[onclick=\"pickPopUp.initialize('covletrecivers', 'txt_let_rcvrId');\"]") ;

    public void searchByEtExport(String fieldName, String exportData){
        switch(fieldName){
            case "رقم الصادر": {
                driver.findElement(etEportDateFromBy).clear();
                driver.findElement(etEportDateToBy).clear();
                driver.findElement(etExportNumBy).sendKeys(exportData);
                break;
            } case "تاريخ من": {
                driver.findElement(etExportNumBy).clear();
                driver.findElement(etEportDateToBy).clear();
                driver.findElement(exCalDateFromBy).click();
                setDateWithSplit(exportData);
                break;
            }case "تاريخ الي": {
                driver.findElement(etEportDateFromBy).clear();
                driver.findElement(exCalDateToBy).click();
                setDateWithSplit(exportData);
                break;
            }case "الموجه اليه": {
                driver.findElement(etEportDateFromBy).clear();
                driver.findElement(etEportDateToBy).clear();
                driver.findElement(etExportNumBy).clear();
                control(driver.findElement(forwardPopUpBy),exportData);
                break;
            }
        }
    }
    public void setDateWithSplit(String date){
        String[] parts = date.split("/");
        String day = parts[2];
        String month = parts[1];
        String year = parts[0];
        System.out.println("day="+day);
        System.out.println("month="+month);
        System.out.println("year="+year);
        int number = Integer.parseInt(month);
        calenderMonthSelect = new Select(driver.findElement(monthDDL));
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(monthDDL)));
        calenderMonthSelect.selectByIndex(number);
        driver.findElement(calenderYearBy).clear();
        driver.findElement(calenderYearBy).sendKeys(year);
        driver.findElement(By.cssSelector("input[value='"+day+"']")).click();

        }

        private By docTypePopupBy = By.cssSelector("input[onclick=\"pickPopUp.initialize('doctypes', 'txt_docType_id');\"]");

        public void searchByDocType(String docType){
            control(driver.findElement(docTypePopupBy),docType);
        }

        private By ddlSelectDeptBy = By.id("drp_dept_ddlSelectButton");
        private By ddlSelectEmpBy = By.id("drp_emp_ddlSelectButton");
        private By ddlSearchDeptBy = By.id("drp_dept_txtSearch");
        private By ddlSearchEmpBy = By.id("drp_emp_txtSearch");
        public void selectEtCreator(String fieldName , String deptName , String empName){
            switch(fieldName){
                case"الادارة":{
                    driver.findElement(ddlSelectDeptBy).click();
                    driver.findElement(ddlSearchDeptBy).sendKeys(deptName);
                    driver.findElement(By.xpath("//ul[@id='drp_dept_collapsibleDiv']/li/div/label[text()='" + deptName + "']")).click();
                    break;
                }case"الموظف":{
                    clearSearchableDDL(driver.findElement(ddlSelectDeptBy));
                    driver.findElement(ddlSelectDeptBy).click();
                    driver.findElement(ddlSearchDeptBy).sendKeys(deptName);
                    driver.findElement(By.xpath("//ul[@id='drp_dept_collapsibleDiv']/li/div/label[text()='" + deptName + "']")).click();
                    //
                    driver.findElement(ddlSelectEmpBy).click();
                    driver.findElement(ddlSearchEmpBy).sendKeys(empName);
                    driver.findElement(By.xpath("//ul[@id='drp_emp_collapsibleDiv']/li/div/label[text()='" + empName + "']")).click();
                    break;
                }
            }
        }
        private By decisonNumBy = By.id("txt_intrLetDesc");

        public void searchByDecisionNum(String decisionNum){
            driver.findElement(decisonNumBy).sendKeys(decisionNum);
        }
    private By GenDateFromBy = By.cssSelector("span[onclick=\"arcSearch.changCalenderCulture('gen_From_Date', true)\"]");
    private By GenDateToBy = By.cssSelector("span[onclick=\"arcSearch.changCalenderCulture('gen_To_Date', true);\"]");
    private By genCalDateFromBy = By.id("gen_From_Date");
    private By genCalDateToBy = By.id("gen_To_Date");
    private By genEtSubjectBy = By.id("gen_title");
    private By genDocTypeBy = By.cssSelector("span[onclick=\"pickPopUp.initialize('doctypes', 'txt_gen_doctype_id');\"]");
    private By docTypeFieldBy = By.id("txt_gen_doctype_id");
    private By genSubClassBy = By.cssSelector("div[id='cph_main_div_sub_classification'] span[class='srch_btn']");
    private By subClassFieldBy = By.id("txt_sub_class_id");
    private By genTypeBy = By.id("sel_gen_type_ddlSelectButton");
    private By genSubClassFieldBy = By.id("txt_sub_class_id");
    private By genTextBy  = By.id("txt_gen_text");
    private By genToPopupBy = By.cssSelector(".srch_btn[onclick='arcSearch.showGeneralizeGroupsPopup();']");
    public void searchByGeneralization(String fieldName , String testData){
            switch (fieldName){
                case "تاريخ من":
                    driver.findElement(genCalDateToBy).clear();
                    driver.findElement(genEtSubjectBy).clear();
                    driver.findElement(docTypeFieldBy).clear();
                    driver.findElement(subClassFieldBy).clear();
                    driver.findElement(GenDateFromBy).click();
                    setDateWithSplit(testData);
                    break;
                case "تاريخ الي":
                    driver.findElement(genCalDateFromBy).clear();
                    driver.findElement(genEtSubjectBy).clear();
                    driver.findElement(docTypeFieldBy).clear();
                    driver.findElement(subClassFieldBy).clear();
                    driver.findElement(GenDateToBy).click();
                    setDateWithSplit(testData);
                    break;
                case "الموضوع":
                    driver.findElement(genCalDateFromBy).clear();
                    driver.findElement(genCalDateToBy).clear();
                    driver.findElement(docTypeFieldBy).clear();
                    driver.findElement(subClassFieldBy).clear();
                    driver.findElement(genEtSubjectBy).sendKeys(testData);
                    break;
                case "نوع المستند":
                    driver.findElement(genCalDateFromBy).clear();
                    driver.findElement(genCalDateToBy).clear();
                    driver.findElement(genEtSubjectBy).clear();
                    driver.findElement(subClassFieldBy).clear();
                    control(driver.findElement(genDocTypeBy),testData);
                    break;
                case "التصنيف الفرعي":
                    driver.findElement(genCalDateFromBy).clear();
                    driver.findElement(genCalDateToBy).clear();
                    driver.findElement(docTypeFieldBy).clear();
                    driver.findElement(genEtSubjectBy).clear();
                    control(driver.findElement(genSubClassBy),testData);
                    break;
                case "نوع التعميم":
                    driver.findElement(genCalDateFromBy).clear();
                    driver.findElement(genCalDateToBy).clear();
                    driver.findElement(docTypeFieldBy).clear();
                    driver.findElement(genEtSubjectBy).clear();
                    driver.findElement(genSubClassFieldBy).clear();
                    driver.findElement(genTypeBy).click();
                    driver.findElement(By.xpath("//ul[@id='sel_gen_type_collapsibleDiv']/li/div/label[contains(.,'"+testData+"')]/..")).click();
                    driver.findElement(genTypeBy).click();
                    break;
                case "نص التعميم":
                    driver.findElement(genCalDateFromBy).clear();
                    driver.findElement(genCalDateToBy).clear();
                    driver.findElement(docTypeFieldBy).clear();
                    driver.findElement(genEtSubjectBy).clear();
                    driver.findElement(genSubClassFieldBy).clear();
                    driver.findElement(genTypeBy).click();
                    driver.findElement(By.cssSelector("ul[id='sel_gen_type_collapsibleDiv'] li[value='-1']")).click();
                    driver.findElement(genTextBy).sendKeys(testData);
                    break;
                case "تعميم ل":
                    driver.findElement(genCalDateFromBy).clear();
                    driver.findElement(genCalDateToBy).clear();
                    driver.findElement(docTypeFieldBy).clear();
                    driver.findElement(genEtSubjectBy).clear();
                    driver.findElement(genSubClassFieldBy).clear();
                    driver.findElement(genTextBy).clear();
                    control(driver.findElement(genToPopupBy),testData);

            }
        }
        private By showAllBtnBy = By.id("btn_search_all");
    public void clickShowAllBtn() { driver.findElement(showAllBtnBy).click(); }
    private By closeDetailsBy = By.cssSelector("i[class='fa fa-close']");
    public void closeEtDetails(){
        driver.findElement(closeDetailsBy).click();
    }

}


