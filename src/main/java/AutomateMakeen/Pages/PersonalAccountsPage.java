package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PersonalAccountsPage extends BaseComp {
    protected WebDriver driver;

    //PageTitle
    private By accountsPageTitle = By.cssSelector(".PopUpHead span");

    //Sections
    private By accountSections = By.className("Sections");

    //Employee Names
    private By accountsEmployeeNames = By.cssSelector("div.PersonalAccount > div:nth-child(2)");

    //Account Type
    private By accountType = By.cssSelector("div.PersonalAccount > div:nth-child(1)");

    //Enter Account
    private By enterAccountButton = By.id("userovtAccount");

    //-----------------------------Constructor------------------------------
    public PersonalAccountsPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }



    public boolean getDelegateEmployeeName(String delegateName) {
        List<WebElement> sections = driver.findElements(accountSections);
        for (WebElement section : sections) {
            List<WebElement>delegateElements = driver.findElements(accountsEmployeeNames);

            for(WebElement delegateElement : delegateElements) {
                if(delegateElement.getText().contains(delegateName)) {
                    return true;
                }
            }
        }
        return false;
    }


}
