package pages;

import helpers.ReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    private static final Duration TIMEOUT = Duration.ofSeconds(30);
    private final By formLoader = By.cssSelector(".oxd-form-loader");
    private final By spinner = By.cssSelector(".oxd-loading-spinner");

    protected final Logger logger = LogManager.getLogger(getClass());
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void logStep(String message) {
        logger.info(message);
        ReportManager.logStep(message);
    }

    protected WebDriverWait getWait() {
        return new WebDriverWait(driver, TIMEOUT);
    }

    protected void waitUntilVisible(By elementBy) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(elementBy));
    }

    protected void waitUntilClickable(By locator) {
        getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected boolean isVisible(By elementBy) {
        try {
            waitUntilVisible(elementBy);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected void click(By locator) {
        waitUntilLoaderDisappears();
        waitUntilClickable(locator);
        driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        waitUntilVisible(locator);
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void replaceText(By locator, String text) {
        waitUntilVisible(locator);
        WebElement element = driver.findElement(locator);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"),
                Keys.DELETE);
        element.sendKeys(text);
    }

    protected void waitUntilLoaderDisappears() {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(spinner));
    }
}
