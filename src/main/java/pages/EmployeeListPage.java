package pages;

import Models.Employee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmployeeListPage extends BasePage {

    private static final String AUTOCOMPLETE_OPTION = "//div[@role='listbox']//div[@role='option'][contains(normalize-space(),'%s')]";
    private static final String RESULT_ROW = "//div[contains(@class,'oxd-table-body')]//div[@role='row']"
            + "[.//div[@role='cell'][normalize-space()='%s']"
            + " and .//div[@role='cell'][normalize-space()='%s']"
            + " and .//div[@role='cell'][normalize-space()='%s']]";

    private final By employeeInformationTitle = By.xpath("//h5[normalize-space()='Employee Information']");
    private final By employeeNameInput = By.xpath("//label[normalize-space()='Employee Name']/ancestor::div[contains(@class,'oxd-input-group')]//input");
    private final By searchButton = By.xpath("//button[@type='submit' and normalize-space()='Search']");
    private final By recordsFoundLabel = By.xpath("//span[contains(normalize-space(),'Found')]");

    public EmployeeListPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return isVisible(employeeInformationTitle);
    }

    public void enterEmployeeName(String name) {
        logStep("Escribiendo nombre en el buscador: " + name);
        type(employeeNameInput, name);
    }

    public void selectSuggestion(String text) {
        logStep("Seleccionando sugerencia: " + text);
        click(By.xpath(String.format(AUTOCOMPLETE_OPTION, text)));
    }

    public void clickSearch() {
        logStep("Clic en Search");
        click(searchButton);
        waitUntilLoaderDisappears();
        waitUntilVisible(recordsFoundLabel);
        logStep("Resultado: " + getRecordsFoundText());
    }

    public void searchEmployee(Employee employee) {
        waitUntilVisible(employeeInformationTitle);
        enterEmployeeName(employee.getFirstName());
        selectSuggestion(employee.getFullName());
        clickSearch();
    }

    public String getRecordsFoundText() {
        return driver.findElement(recordsFoundLabel).getText();
    }

    public boolean isEmployeeInResults(Employee employee) {
        By employeeRow = By.xpath(String.format(RESULT_ROW,
                employee.getEmployeeId(),
                employee.getFirstName() + " " + employee.getMiddleName(),
                employee.getLastName()));
        boolean found = isVisible(employeeRow);
        logStep("Empleado " + employee.getEmployeeId() + (found ? " encontrado" : " no encontrado") + " en la grilla");
        return found;
    }
}
