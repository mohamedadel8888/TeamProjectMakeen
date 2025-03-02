package AutomateMakeen.Pages.Elite;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EliteHomePage extends BaseComp {
    protected WebDriver driver;
    private WebDriverWait exWait;
    public EliteHomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
    }


    private By mainPage = By.className("navbar-brand-logo");  /*ايقونة الشاشة الرئيسية */
    private By eliteStaticBar = By.id("div_statistic");   /* شريط الاحصائيات في الصفحة الرئيسية */
    private By userDropdown = By.id("navbarDropdown"); /* قائمة المستخدم مسجل الدخول  */

    private By sideMenu = By.cssSelector("li[class='layout_sidemenu-item close-sidebar-btn'] div[class='layout_sidemenu-link arrowed-item']");  /*القائمة الجانبية */
        private By mailLink = By.id("li_QElite_Mail");  /*  الــــبريـد */
            private By mailInbox = By.id("li_QElite_Mail_622"); /* الـــوارد  */
            private By mailSent  = By.id("li_QElite_Mail_624"); /* الـصادر   */
            private By createInternalMail = By.id("li_QElite_Mail_630"); /* إنشاء بريد داخلي */
            private By messagesCirculars = By.id("li_QElite_Mail_631"); /* رسائل وتعميمات  */
            private By archive = By.id("li_QElite_Mail_632"); /*  الأرشــيف */
            private By inboxRequests = By.id("li_QElite_Mail_633"); /*وارد طلبــات */
        private By HRLink = By.id("li_QElite_PRS"); /* الموارد الـبشرية */
            private By promotions = By.id("li_QElite_PRS_628"); /* الترقيات  */
            private By delegateRequests = By.id("li_QElite_PRS_634"); /*طلب تفويض */



    public WebElement getEliteHomePage(){
        return driver.findElement(eliteStaticBar);
    }
    public WebElement getClickToMainPage() {
        return driver.findElement(mainPage);
    }

    public CreateInternalMailPage goToCreateInternalMail(){ /*الدخول الى انشاء بريد داخلي  */
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(mainPage)));
        driver.findElement(sideMenu).click();
        List<WebElement> l1 = driver.findElements(By.cssSelector(".sidemenu__text"));
        List<WebElement> l2 = driver.findElements(By.xpath("//span[text()='بريد']/../../../div/ul/li"));
        List<WebElement> l3 = driver.findElements(By.xpath("//span[text()='الموارد البشرية']/../../../div/ul/li"));
        l1.get(0).click();
        l2.get(2).click();
        CreateInternalMailPage page1 = new CreateInternalMailPage(driver) ;
        return page1;
    }
    public SentPage goToSent(){ /*الدخول الى الصادر */
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(mainPage)));
        driver.findElement(sideMenu).click();
        List<WebElement> l1 = driver.findElements(By.cssSelector(".sidemenu__text"));
        List<WebElement> l2 = driver.findElements(By.xpath("//span[text()='بريد']/../../../div/ul/li"));
        List<WebElement> l3 = driver.findElements(By.xpath("//span[text()='الموارد البشرية']/../../../div/ul/li"));
        l1.get(0).click();
        l2.get(1).click();
        SentPage page2 = new SentPage(driver);
        return page2;
    }
    public InboxPage goToInbox(){      /*الدخول الى الوارد */
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(mainPage)));
        driver.findElement(sideMenu).click();
        List<WebElement> l1 = driver.findElements(By.cssSelector(".sidemenu__text"));
        List<WebElement> l2 = driver.findElements(By.xpath("//span[text()='بريد']/../../../div/ul/li"));
        l1.get(0).click();
        l2.get(0).click();
        InboxPage page3 = new InboxPage(driver);
        return page3;
    }
    public MsgsAndGeneralsPage goToMessagesAndGeneralizations(){      /*الدخول الى رسائل وتعميمات */
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(mainPage)));
        driver.findElement(sideMenu).click();
        List<WebElement> l1 = driver.findElements(By.cssSelector(".sidemenu__text"));
        List<WebElement> l2 = driver.findElements(By.xpath("//span[text()='بريد']/../../../div/ul/li"));
        l1.get(0).click();
        l2.get(3).click();
        MsgsAndGeneralsPage page4 = new MsgsAndGeneralsPage(driver);
        return page4;
    }
}