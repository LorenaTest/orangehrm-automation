package pages;

import enums.MainMenuOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    private final By textTitleDashboard= By.xpath("//h6[normalize-space()='Dashboard']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean titleIsDisplayed(){
        return  isVisible(textTitleDashboard);
    }

    public boolean clickOnMainMenuOption(MainMenuOption menuOption){
        By optionBy = By.linkText(menuOption.asString());
        waitUntilVisible(optionBy);
        driver.findElement(optionBy).click();

        return new PimPage(driver).titleIsDisplayed();
    }
}
