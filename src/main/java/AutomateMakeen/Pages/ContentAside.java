package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContentAside extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;
    public ContentAside(WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "(//i[@class='fa fa-angle-double-down'])[1]")
    WebElement empAffairArrowWebElement;

    @FindBy (id = "s_m_65")
    WebElement adminStructureWebElement;

    @FindBy(id = "cph_main_btn_AddElement")
    WebElement addNewDeptWebElement;

}
