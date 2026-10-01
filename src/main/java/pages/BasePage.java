package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void waitUntilVisible(By elementBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
    }

    protected void waitPresenceOfElementLocated(By elementBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
    }


    protected boolean isVisible(By elementBy){
        try {
            waitUntilVisible(elementBy);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void waitUntilClickable(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitUntilLoaderDisappears() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By loader = By.cssSelector(".oxd-form-loader");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

    public void selectOption(By selectLocator, String option) {

        driver.findElement(selectLocator).click();
        By optionLocator = By.xpath(
                "//div[contains(@class,'oxd-select-option')]//span[normalize-space()='"
                        + option + "']"
        );

        driver.findElement(optionLocator).click();
    }

}
