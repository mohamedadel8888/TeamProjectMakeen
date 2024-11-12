package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountsPage extends BaseComp {
    protected WebDriver driver;

    private By accountsEmployeeNames = By.cssSelector("div.PersonalAccount > div:nth-child(2)");
    private By accountType = By.cssSelector("div.PersonalAccount > div:nth-child(1)");
    private By enterAccountButton = By.id("userovtAccount");
    public PersonalAccountsPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }


}
