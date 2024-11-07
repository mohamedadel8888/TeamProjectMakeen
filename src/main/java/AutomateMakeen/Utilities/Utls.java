package AutomateMakeen.Utilities;

import AutomateMakeen.Base.BaseComp;
import AutomateMakeen.Pages.HomePage;
import AutomateMakeen.Pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class Utls extends BaseComp {
    private WebDriver driver;
    public Utls(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
    public HomePage login(String userID , String userPasswd){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clearAllFeild();
        loginPage.loginUserWithRemMe(userID,userPasswd);
        return new HomePage(driver);
    }
}
