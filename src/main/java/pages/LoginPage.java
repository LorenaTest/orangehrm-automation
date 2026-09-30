package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

    private final By userInput = By.xpath("//input[@name='username']");
    private final By passwordInput = By.xpath("//input[@name='password']");
    private final By loginButton = By.cssSelector("[type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String user){
        waitUntilVisible(userInput);
        WebElement element = driver.findElement(userInput);
        element.sendKeys(user);
    }

    public void enterPassword(String password){
        waitUntilVisible(passwordInput);
        WebElement element = driver.findElement(passwordInput);
        element.sendKeys(password);
    }

    public HomePage clickOnLoginButton(){
        waitUntilVisible(loginButton);
        WebElement element = driver.findElement(loginButton);
        element.click();
        return new HomePage(driver);
    }

    public boolean loginAs(String user, String password){
        enterUsername(user);
        enterPassword(password);
        return clickOnLoginButton().titleIsDisplayed();
    }
}
