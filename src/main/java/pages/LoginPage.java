package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By userInput = By.xpath("//input[@name='username']");
    private final By passwordInput = By.xpath("//input[@name='password']");
    private final By loginButton = By.cssSelector("[type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String user) {
        logStep("Ingresando usuario: " + user);
        type(userInput, user);
    }

    public void enterPassword(String password) {
        logStep("Ingresando contrasena");
        type(passwordInput, password);
    }

    public HomePage clickOnLoginButton() {
        logStep("Clic en Login");
        click(loginButton);
        return new HomePage(driver);
    }

    public HomePage loginAs(String user, String password) {
        enterUsername(user);
        enterPassword(password);
        return clickOnLoginButton();
    }
}
