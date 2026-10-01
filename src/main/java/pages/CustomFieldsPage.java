package pages;

import Models.Employee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomFieldsPage extends BasePage{

    private static final String INPUT_GROUP_BY_LABEL =
            "//label[normalize-space()='%s']/ancestor::div[contains(@class,'oxd-input-group')]";

    private final By customFieldsTitle =
            By.xpath("//h6[normalize-space()='Custom Fields']");
    private final By bloodType =
            By.xpath(String.format(INPUT_GROUP_BY_LABEL, "Blood Type") +
                            "//div[contains(@class,'oxd-select-text-input')]");

    private final By bloodTypeArrow =
            By.xpath(String.format(INPUT_GROUP_BY_LABEL, "Blood Type") +
                            "//i[contains(@class,'oxd-select-text--arrow')]");

    private final By testField =
            By.xpath(String.format(INPUT_GROUP_BY_LABEL, "Test_Field") +
                            "//input");

    private final By saveButton =
            By.xpath("//button[@type='submit' and normalize-space()='Save']");

    public CustomFieldsPage(WebDriver driver) {
        super(driver);
    }

    public void selectBloodType(String bloodType) {
        waitUntilClickable(this.bloodType);
        driver.findElement(this.bloodType).click();
        By option = By.xpath("//div[@role='option' and normalize-space()='" + bloodType + "']");
        driver.findElement(option).click();
    }

    public void enterTestField(String value) {
        waitUntilVisible(testField);
        driver.findElement(testField).sendKeys(value);
    }

    public void clickSave() {
        waitUntilClickable(saveButton);
        driver.findElement(saveButton).click();
    }

    public boolean fillCustomFields(Employee employee){
        waitUntilLoaderDisappears();
        waitUntilVisible(customFieldsTitle);
        selectBloodType(employee.getCustomFields().getBloodType());
        enterTestField(employee.getCustomFields().getTestField());
        clickSave();
        waitUntilLoaderDisappears();
        SuccessToastModalPage successToast = new SuccessToastModalPage(driver);
        boolean isSuccessfullyUpdated = successToast.isSuccessfullyUpdated();

        waitUntilLoaderDisappears();
        return isSuccessfullyUpdated;
    }
}
