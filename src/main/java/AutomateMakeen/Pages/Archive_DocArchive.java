package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Archive_DocArchive extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;

    public Archive_DocArchive(WebDriver driver){
        super(driver);
        this.driver = driver;
        //specific wait for every page
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
    }

    private By selectDeptDDL = By.className("pop_slc_txt");

    public void selectDept(String dept){
        driver.findElement(selectDeptDDL).click();
        driver.findElement(By.xpath("//tr/td[2]/a[contains(.,'"+dept+"')]")).click();
    }

}
