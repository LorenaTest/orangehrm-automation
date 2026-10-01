package pages;

import enums.MainMenuOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final String MAIN_MENU_OPTION = "//aside//a[normalize-space()='%s']";

    private final By textTitleDashboard = By.xpath("//h6[normalize-space()='Dashboard']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardDisplayed() {
        return isVisible(textTitleDashboard);
    }

    public void clickOnMainMenuOption(MainMenuOption menuOption) {
        logStep("Abriendo opcion del menu: " + menuOption.asString());
        click(By.xpath(String.format(MAIN_MENU_OPTION, menuOption.asString())));
    }

    public PimPage goToPim() {
        clickOnMainMenuOption(MainMenuOption.PIM);
        return new PimPage(driver);
    }
}
