package AutomateMakeen.Pages;
import AutomateMakeen.Base.BaseComp;
import AutomateMakeen.Pages.EditAccountPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CreateExternalEditAccountPage extends BaseComp {
    protected WebDriver driver;
    private WebDriverWait exWait;
    EditAccountPage editAccountPage;
    public CreateExternalEditAccountPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
        contentAside = new ContentAside(driver);
        editAccountPage = new EditAccountPage(driver);
    }




}
