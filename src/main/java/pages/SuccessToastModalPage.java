package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SuccessToastModalPage extends BasePage{

    private static final String SUCCESS_TOAST = "//div[contains(@class,'oxd-toast--success')]";

    private final By successTitle = By.xpath(SUCCESS_TOAST + "//p[normalize-space()='Success']");
    private final By successSavedMessage = By.xpath(SUCCESS_TOAST + "//*[normalize-space()='Successfully Saved']");
    private final By successUpdatedMessage = By.xpath(SUCCESS_TOAST + "//*[normalize-space()='Successfully Updated']");
    private final By closeButton = By.xpath(SUCCESS_TOAST + "//div[contains(@class,'oxd-toast-close')]");

    public SuccessToastModalPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSuccessfullySaved() {

        waitUntilVisible(successTitle);
        WebElement title = driver.findElement(successTitle);
        WebElement message = driver.findElement(successSavedMessage);

        return title.getText().equals("Success")
                && message.getText().equals("Successfully Saved");
    }

    public boolean isSuccessfullyUpdated() {

        waitUntilVisible(successTitle);
        WebElement title = driver.findElement(successTitle);
        WebElement message = driver.findElement(successUpdatedMessage);

        return title.getText().equals("Success")
                && message.getText().equals("Successfully Updated");
    }
}
