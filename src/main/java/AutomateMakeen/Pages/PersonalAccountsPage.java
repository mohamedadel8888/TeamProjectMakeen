package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PersonalAccountsPage extends BaseComp {
    WebDriver driver;
    private WebDriverWait wait;

    //PageTitle
    private By accountsPageTitle = By.cssSelector(".PopUpHead span");

    //Sections
    private By accountSections = By.className("Sections");

    //Employee Names
    private By accountsEmployeeNames = By.cssSelector("div.PersonalAccount > div:nth-child(2)");

    //Account Type
    private By accountType = By.cssSelector("div.PersonalAccount > div:nth-child(1)");

    //Enter Main Account
    private By enterAccountButton = By.xpath("//div[@class='AllContant']/div[1]/div[@id='userovtAccount']");
    //private By enterAccountButton = By.id("userovtAccount");

    //User Notifications

    private By acceptNotification = By.xpath("//input[@value='قبول']");
    private By remindMeLaterButton1 = By.cssSelector("[value='ذكرنى لاحقا']");

    private By remindMeLaterButton = By.xpath("//input[@value='ذكرنى لاحقا'] | //input[@value='ذكرني لاحقا']");
    private By notificationViewedButton = By.cssSelector("[value='تم الاطلاع']");


    //-----------------------------Constructor------------------------------
    public PersonalAccountsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    public boolean getDelegateEmployeeName(String delegateName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountSections));
        List<WebElement> sections = driver.findElements(accountSections);
        for (WebElement section : sections) {
            List<WebElement> delegateElements = driver.findElements(accountsEmployeeNames);

            for (WebElement delegateElement : delegateElements) {
                if (delegateElement.getText().contains(delegateName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public HomePage enterMainAccount() {
        driver.findElement(enterAccountButton).click();
        return new HomePage(driver);
    }


    public HomePage enterDelegateAccountByName(String name) {
        int retryCount = 0;
        int maxRetries = 3;

        // Locate and click on the element in a retry loop
        while (retryCount < maxRetries) {
            try {
                driver.findElement(By.xpath("//div[@class='AllContant']//div[@class='PersonalAccount']/div[contains(.,'" + name + "')]/../../div[13]")).click();
                handleNotifications();
                break; // Exit loop if successful
            } catch (StaleElementReferenceException e) {
                retryCount++;
                if (retryCount == maxRetries) {
                    throw e; // Re-throw the exception after max retries
                }
            }
        }
        return new HomePage(driver);
    }


    private void handleNotifications() {
        while (true) {
            try {
                wait.until(ExpectedConditions.or(
                        ExpectedConditions.visibilityOfElementLocated(acceptNotification),
                        ExpectedConditions.visibilityOfElementLocated(remindMeLaterButton)
                ));

                // Check and click "Accept" or "Remind Me Later" button if found
                if (isElementPresentAndVisible(acceptNotification)) {
                    driver.findElement(acceptNotification).click();
                } else if (isElementPresentAndVisible(remindMeLaterButton)) {
                    driver.findElement(remindMeLaterButton).click();
                }
            } catch (TimeoutException | StaleElementReferenceException e) {
                // Exit loop if buttons are not found or elements become stale
                break;
            }
        }
    }

    private boolean isElementPresentAndVisible(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}





