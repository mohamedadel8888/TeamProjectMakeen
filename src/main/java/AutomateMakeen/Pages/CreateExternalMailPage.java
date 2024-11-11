package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateExternalMailPage extends BaseComp {
    protected WebDriver driver;
    private WebDriverWait exWait;
    public CreateExternalMailPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
        contentAside = new ContentAside(driver);
    }
    /******************************************
     * نوع المستند
     * *****************************************/

    @FindBy(id ="txt_doc_type_num")
    WebElement docTypeWebElement;

    @FindBy(id = "spnA_txt_doc_type_num")
    WebElement docTypeValidator;

    @FindBy(css = "b[id='spnA_txt_doc_type_num'] p[class='span_error']")
    WebElement errorMsgWebElement;

    @FindBy(css = "span[onclick='extInboxCreation.showPickupDocType()']")
    WebElement openDocTypeControl;

    @FindBy(id = "txt_doc_type_name")
    WebElement typeNameWebElement;
    /*
    * Documnet Type Methods:
    *  1- insertDocType
    *       - Return : Void
    *       - Param : String docType
    *       - Description : Insert String into 'نوع المستند ' Feild.
    */

    public void insertDocType(String docType){
        docTypeWebElement.sendKeys(docType);
    }

    /*  2- getDocType
     *       - Return : String
     *       - Param : Non
     *       - Description : Return the existing data in 'نوع المستند ' Feild
     *                       using getAttribute("value").
     */
    public String getDocType(){
        return docTypeWebElement.getAttribute("value");
    }
    /*  3- clearDocType
     *       - Return : void
     *       - Param : Non
     *       - Description : Clear the existing data in 'نوع المستند ' Feild.
     */
    public void clearDocType(){
        docTypeWebElement.clear();
    }
    /*  4- copyPasteToDocType
     *       - Return : void
     *       - Param : String docType
     *       - Description : this method send data into "الشرح" then press (Ctrl + a + x)
     *                      to Cut data then go to "نوع المستند" and press (Crtl + v).
     */
    public void copyPasteToDocType(String docType){
        cutPastAction(commentWebElement,docTypeWebElement,docType);
    }
    /*  5- getDocTypeValidatorState
     *       - return : 1- "Red Circle"
                        2- "Asterisk"
     *       - Param : Non
     *       - Description : this method return the status of "نوع المستند" field.
     */
    public String getDocTypeValidatorState(){
        return getValidatorState(docTypeValidator);
    }
    /*  6- getDocTypeErrorMsg
      *       - return : String
      *       - Param : Non
      *       - Description : this method return the error Msg by click
      *                       on the validator then return the error msg.
      */
    public String getDocTypeErrorMsg(){
        docTypeValidator.click();
        return errorMsgWebElement.getText();
    }

    public void docTypeControl(String txt){
        control(openDocTypeControl,txt);
    }

    public String getTypeName(){
        return typeNameWebElement.getAttribute("value");
    }

    /******************************************
     * نوع المستند
     * *****************************************/

    @FindBy(id = "txt_tr_comment")
    WebElement commentWebElement;
    public void insertComment(String comment){
        commentWebElement.sendKeys(comment);
    }
}
