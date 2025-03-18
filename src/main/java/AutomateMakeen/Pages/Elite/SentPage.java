package AutomateMakeen.Pages.Elite;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SentPage extends BaseComp {
    protected WebDriver driver;
    private WebDriverWait exWait;

    public SentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
    }
    private By sentPage = By.cssSelector(".outbox"); /*صفحه الصادر */
    private By searchText = By.id("txt_recNameSrch"); /*شريط البحــث */
    private By searchButton = By.cssSelector(".search_bar__actions"); /*زر البحث */
    private By treatDetails = By.xpath("(//div[@class='info-comp']/span[@class='i-icon'])[1]"); /*تفاصيل المعاملة */
    private By sentToDetails = By.cssSelector("div[id='i_senderTo_info'] span[class='i-icon']"); /*تفاصيل المرسل اليه*/

    private By treatSubject = By.id("p_treatment_subject"); /*موضوع المعاملة */
        private By allDetails = By.xpath("//table[@class='info-comp-table-list']/tr/th[text()='']/following-sibling::td[1]"); /*ارجاع قيمة اي حقل في تفاصيل المعاملة بكتابة اسم الحقل بين ''*/
    private By dayFilter = By.cssSelector(".list_filter-link.active"); /*اليوم*/
    private By weekFilter =By.xpath("//a[contains(text(),'أسبوع')]"); /*اسبوع */
    private By allFilter = By.cssSelector(".list_filter-link[href='#'][filtervalue='0']"); /*الجميع */



    private By explanationBtn = By.id("btn_explanation"); /*الشروحات  */
    private By attachmentsBtn = By.id("btn_attachments"); /*المرفقات  */


    private By explanationTab = By.cssSelector(".outbox_explaination"); /*صفحه الشروحات   */
    private By attachmentsTab = By.cssSelector("#div_conatiner_attach"); /*صفحه المرفقات  */



    public WebElement getMailSentPage() {   /*ارجاع صفحه الصادر */
        WebElement mailSentPage = driver.findElement(sentPage);
        return mailSentPage;
    }
    public void mailSentSearch (String text){  /*   البحث عن معامله في الجميع */
        WebElement all = driver.findElement(allFilter);
        all.click();
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
    public String getTreatDirecting (){ /*ارجاع التوجيه */
        WebElement details = driver.findElement(treatDetails);
        Actions actions = new Actions(driver);
        actions.moveToElement(details).perform();
        WebElement treatDirecting = driver.findElement(By.xpath("//table[@class='info-comp-table-list']/tr/th[text()='التوجية']/following-sibling::td[1]"));
        String treatDirecting1 = treatDirecting.getText();
        return treatDirecting1;
    }




    public String getDepartmentName (){ /*ارجاع ادارة المرسل اليه */
        WebElement details = driver.findElement(sentToDetails);
        Actions actions = new Actions(driver);
        actions.moveToElement(details).perform();
        WebElement departmentField = driver.findElement(By.xpath("//table/tr/th[text()='الإدارة']/following-sibling::td"));
        String departmentName = departmentField.getText();
        return departmentName;
    }
    public String getRecieverName (){
        WebElement recieverNameField = driver.findElement(By.id("h_treat_dest"));
        String departmentName = recieverNameField.getText();
        return departmentName;
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
        WebElement directing = driver.findElement(By.xpath("//table[@class='info-comp-table-list']/tr/th[text()='التوجية']/following-sibling::td[1]"));
        String dir = directing.getText();
        return dir;
    }

    public void goToExplanation(){ /*فتح الشروحات */
        WebElement explanation = driver.findElement(explanationBtn);
        explanation.click();
    }
    public WebElement getExplanationTab (){ /* ارجاع تاب الشروحات */
        WebElement explanation1 = driver.findElement(explanationTab);
        return explanation1;
    }

    public void goToAttachments(){ /*فتح المرفقات */
        WebElement attachments1 = driver.findElement(attachmentsBtn);
        attachments1.click();
    }

    public WebElement getAttachmentsTab (){ /*ارجاع تاب المرفقات */
        WebElement attachments1 = driver.findElement(attachmentsTab);
        return attachments1;
    }









}
