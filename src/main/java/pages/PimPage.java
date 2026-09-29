package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PimPage {

    private WebDriver webDriver;
    private By userInput = By.name("username");
    private By passwordInput = By.name("password");
    private By loginButton = By.cssSelector("[type='submit']");

    public PimPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

}
