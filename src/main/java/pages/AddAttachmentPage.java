package pages;

import Models.Employee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AddAttachmentPage extends BasePage{

    private static final String INPUT_GROUP_BY_LABEL = "//label[normalize-space()='%s']/ancestor::div[contains(@class,'oxd-input-group')]";

    private final By addAttachmentTitle = By.xpath("//h6[normalize-space()='Add Attachment']");
    private final By selectFile = By.xpath(String.format(INPUT_GROUP_BY_LABEL, "Select File") + "//input[@type='file']");
    private final By comment = By.xpath(String.format(INPUT_GROUP_BY_LABEL, "Comment") + "//textarea");
    private final By cancelButton = By.xpath("//button[@type='button' and normalize-space()='Cancel']");
    private final By saveButton = By.xpath("//button[@type='submit' and normalize-space()='Save']");
    private final By addButton = By.xpath("//h6[normalize-space()='Attachments']/following-sibling::button[normalize-space()='Add']");
    private final By recordFound = By.xpath("//span[contains(normalize-space(), 'Record Found')]");

    public AddAttachmentPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddAttachment() {
        waitUntilClickable(addButton);
        driver.findElement(addButton).click();
    }

    public void uploadFile(String filePath) {
        Path absolutePath = Paths.get(filePath).toAbsolutePath();
        waitPresenceOfElementLocated(selectFile);
        driver.findElement(selectFile).sendKeys(absolutePath.toString());
    }

    public void enterComment(String text) {
        waitUntilVisible(comment);
        driver.findElement(comment).sendKeys(text);
    }

    public void clickSave() {
        waitUntilClickable(saveButton);
        driver.findElement(saveButton).click();
    }

    public boolean isRecordFoundDisplayed() {
        waitUntilVisible(recordFound);
        return driver.findElement(recordFound).isDisplayed();
    }

    public boolean fillAddAttachment(Employee employee){
        waitUntilVisible(addAttachmentTitle);
        uploadFile(employee.getAddAttachment().getFilePath());
        enterComment(employee.getAddAttachment().getComment());
        clickSave();
        SuccessToastModalPage successToast = new SuccessToastModalPage(driver);
        boolean isSuccessfullySaved = successToast.isSuccessfullyUpdated();
        waitUntilLoaderDisappears();
        return isSuccessfullySaved;
    }
}
