package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Archive_Search_Grid extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;
    public Archive_Search_Grid(WebDriver driver) {
        super(driver);
        this.driver = driver;
        //specific wait for every page
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));

    }

    private By toggleDateType = By.id("ch_Culture");

    public void toggleDateType(){
        driver.findElement(toggleDateType).click();
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

//    public void searchTabNavigator(String tabName){
//        switch(tabName){
//            case "الوارد":
//                currentTab = driver.findElement(incomeTabBy);
//                currentTab.click();
//                break;
//            case "الموضوع":
//                currentTab = driver.findElement(subjectTabBy);
//                currentTab.click();
//                break;
//            case "المرفقات":
//                currentTab = driver.findElement(attachmentsTabBy);
//                currentTab.click();
//                break;
//            case "الارشيف":
//                currentTab = driver.findElement(archiveTabBy);
//                currentTab.click();
//                break;
//            case "المرسل":
//                currentTab = driver.findElement(senderTabBy);
//                currentTab.click();
//                break;
//            case "المستقبل":
//                currentTab = driver.findElement(receiverTabBy);
//                currentTab.click();
//                break;
//            case "المواطن":
//                currentTab = driver.findElement(citizenTabBy);
//                currentTab.click();
//                break;
//            case "الصادر":
//                currentTab = driver.findElement(outcomeTabBy);
//                currentTab.click();
//                break;
//            case "نوع المستند":
//                currentTab = driver.findElement(docTypeTabBy);
//                currentTab.click();
//                break;
//            case "منشئ المعاملة":
//                currentTab = driver.findElement(treatmentCreatorTabBy);
//                currentTab.click();
//                break;
//            case "المذكرة الداخلية":
//                currentTab = driver.findElement(internalLecTabBy);
//                currentTab.click();
//                break;
//            case "التعميم":
//                currentTab = driver.findElement(generalizationTabBy);
//                currentTab.click();
//                break;
//            default:
//                System.out.println("Wrong input parameters");                 return;
//        }
//        driver.findElement(calenderDaySelectionBy).click();
//    }
    private By incomeMailNumberBy = By.id("cph_main_txt_inbox_no");
    private By incomeMailDateBy = By.id("span_calender_inbox_date");

    private By calenderDaySelectionBy = By.cssSelector("input[class='btn']");

    private By monthDDL = By.id("drp_Month");

    Select calenderMonthSelect ;

    private By calenderYearBy = By.id("tb_Year");

    private By incomeMailDateInputBy = By.id("txt_inbox_date");
    public void searchByIncomeMail(String incomeMailNumber , String year , String month , String day) {
        if (incomeMailNumber.isEmpty() && !(year.isEmpty() && month.isEmpty() && day.isEmpty())) {
            driver.findElement(incomeMailNumberBy).clear();
            driver.findElement(incomeMailDateBy).click();
            this.setDate(year, month, day);
        } else if (!incomeMailNumber.isEmpty() && (year.isEmpty() && month.isEmpty() && day.isEmpty())) {
            driver.findElement(incomeMailDateInputBy).clear();
            driver.findElement(incomeMailNumberBy).sendKeys(incomeMailNumber);
        }
        return;
    }
    private By archiveSearchBtnBy = By.id("btn_search");
    public void clickSearch() {
        driver.findElement(archiveSearchBtnBy).click();
    }

    public void setDate(String year, String month, String day){
        calenderMonthSelect = new Select(driver.findElement(monthDDL));
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(monthDDL)));
        calenderMonthSelect.selectByVisibleText(month);
        driver.findElement(calenderYearBy).clear();
        driver.findElement(calenderYearBy).sendKeys(year);
        driver.findElement(By.cssSelector("input[value='"+day+"']")).click();
    }


}
