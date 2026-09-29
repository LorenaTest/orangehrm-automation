package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver webDriver;
    private By userInput = By.xpath("//input[@name='username']");
    private By passwordInput = By.xpath("//input[@name='password']");
    private By loginButton = By.cssSelector("[type='submit']");

    public LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void typeUserName(String user){
        WebElement element = webDriver.findElement(userInput);
        element.sendKeys(user);
    }

    public void typePassword(String passWord){
        WebElement element = webDriver.findElement(passwordInput);
        element.sendKeys(passWord);
    }

    public PimPage clickOnLoginButton(){
        WebElement element = webDriver.findElement(loginButton);
        element.click();
        return new PimPage(webDriver);
    }

    public PimPage loginAs(String user, String passWord){
        typeUserName(user);
        typePassword(passWord);
        return clickOnLoginButton();
    }
}
