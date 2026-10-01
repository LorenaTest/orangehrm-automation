package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PimPage extends BasePage {

    private final By textTitlePim = By.xpath("//h6[normalize-space()='PIM']");
    private final By addEmployeeTab = By.xpath("//nav//a[normalize-space()='Add Employee']");
    private final By employeeListTab = By.xpath("//nav//a[normalize-space()='Employee List']");

    public PimPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return isVisible(textTitlePim);
    }

    public AddEmployeePage goToAddEmployee() {
        logStep("Abriendo Add Employee");
        click(addEmployeeTab);
        waitUntilLoaderDisappears();
        return new AddEmployeePage(driver);
    }

    public EmployeeListPage goToEmployeeList() {
        logStep("Abriendo Employee List");
        click(employeeListTab);
        waitUntilLoaderDisappears();
        return new EmployeeListPage(driver);
    }
}
