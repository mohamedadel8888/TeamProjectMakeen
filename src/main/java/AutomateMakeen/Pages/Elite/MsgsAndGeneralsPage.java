package AutomateMakeen.Pages.Elite;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MsgsAndGeneralsPage extends BaseComp {
    protected WebDriver driver;
    private WebDriverWait exWait;
    public MsgsAndGeneralsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
    }

    private By msgsAndGeneralsPage = By.cssSelector(".row.g-3.inbox-details-container.msgs-and-generalization"); /* صفحه رســائل و تعميمات */
    private By searchText = By.id("txt_recNameSrch"); /*شريط البحث */
    private By searchButton = By.cssSelector(".search_bar__actions"); /*زر البحث */



    private By treatDetails = By.xpath("(//div[@class='info-comp']/span[@class='i-icon'])[1]"); /*تفاصيل المعاملة */
    private By treatSubject = By.cssSelector("#p_treatment_subject"); /*موضوع المعاملة */
    private By dayFilter = By.cssSelector("#a_allFilter"); /*اليوم*/
    private By weekFilter =By.xpath("//a[contains(text(),'أسبوع')]"); /*اسبوع */
    private By allFilter = By.cssSelector(".list_filter-link.active"); /*الجميع */

    private By messageText = By.id("btn_MessageContent"); /*نص الرسالة */
    private By attachmentsBtn = By.cssSelector("#btn_attachments"); /*المرفقات  */
    private By lettersCopy = By.cssSelector("#btn_letterCCPreview"); /*صور الخطاب*/
    private By letterPereview = By.cssSelector("#btn_letterPreview"); /*معاينة الخطاب */


    public WebElement getMailSentPage() {   /*ارجاع صفحه رسائل وتعميمات */
        WebElement msgsAndGeneral = driver.findElement(msgsAndGeneralsPage);
        return msgsAndGeneral;
    }
    public void msgAndGeneralAllSearch (String text){  /*   البحث عن معامله في الجميع */
        WebElement all = driver.findElement(allFilter);
        all.click();
        WebElement searchInput = driver.findElement(searchText);
        searchInput.sendKeys(text);
        driver.findElement(searchButton).click();
    }
    public void msgAndGeneralWeekSearch (String text){  /*   البحث عن معامله في الاسبوع */
        WebElement week = driver.findElement(weekFilter);
        week.click();
        WebElement searchInput = driver.findElement(searchText);
        searchInput.sendKeys(text);
        driver.findElement(searchButton).click();
    }
    public void msgAndGeneralDaySearch (String text){  /*   البحث عن معامله في اليوم */
        WebElement day = driver.findElement(dayFilter);
        day.click();
        WebElement searchInput = driver.findElement(searchText);
        searchInput.sendKeys(text);
        driver.findElement(searchButton).click();
    }

    public String getTreatIncomingNum (){ /*ارجاع رقم الوارد */
        WebElement details = driver.findElement(treatDetails);
        Actions actions = new Actions(driver);
        actions.moveToElement(details).build().perform();
        WebElement treatIncomingNum = driver.findElement(By.xpath("//table[@class='info-comp-table-list']/tr/th[text()='رقم الوارد']/following-sibling::td[1]"));
        String IncomingNum = treatIncomingNum.getText();
        return IncomingNum;
    }

    public String getTreatArchiveNum (){ /*ارجاع الأرشيف */
        WebElement details = driver.findElement(treatDetails);
        Actions actions = new Actions(driver);
        actions.moveToElement(details).perform();
        WebElement treatArchiveNum = driver.findElement(By.xpath("//table[@class='info-comp-table-list']/tr/th[text()='الأرشيف']/following-sibling::td[1]"));
        String archive = treatArchiveNum.getText();
        return archive;
    }
    public String getTreatSource (){ /*ارجاع مصدر المعاملة  */
        WebElement details = driver.findElement(treatDetails);
        Actions actions = new Actions(driver);
        actions.moveToElement(details).perform();
        WebElement treatSource = driver.findElement(By.xpath("//table[@class='info-comp-table-list']/tr/th[text()='مصدر المعاملة']/following-sibling::td[1]"));
        String source = treatSource.getText();
        return source;
    }
    public String getDirecting (){ /*ارجاع التوجيه */
        WebElement details = driver.findElement(treatDetails);
        Actions actions = new Actions(driver);
        actions.moveToElement(details).perform();
        WebElement directing = driver.findElement(By.xpath("//table[@class='info-comp-table-list']/tr/th[text()='التوجيه']/following-sibling::td[1]"));
        String dir = directing.getText();
        return dir;
    }
    public void previewLetter (){   /*معاينة خطــاب*/
        WebElement letter = driver.findElement(letterPereview);
        letter.click();
    }
    public void copyLetter (){   /*صور الخطاب*/
        WebElement letter = driver.findElement(lettersCopy);
        letter.click();
    }
    public void openAttachments (){   /*فتح المرفقات */
        WebElement attachments = driver.findElement(attachmentsBtn);
        attachments.click();
    }

    public void openMessage (){   /*فتح الرسالة */
        WebElement message = driver.findElement(messageText);
        message.click();
    }
}
