package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DelegatePage extends BaseComp {

    private WebDriver driver;
    //private WebDriverWait wait;

    private By delegatePageTitle = By.id("spn_title");
    private By addDelegateButton = By.id("btn_add_deleg");
    private By goBackButton = By.id("btnn_back");
    private By delegateReportButton = By.id("cph_main_btn_deleg_rep");

    private By emptyDelegates = By.id("dv_Empty");



    public DelegatePage(WebDriver driver){
        this.driver = driver;
    }

}
