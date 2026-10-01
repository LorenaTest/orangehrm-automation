package pages;

import Models.Employee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddEmployeePage extends BasePage {

    private static final String INPUT_BY_LABEL = "//label[normalize-space()='%s']/ancestor::div[contains(@class,'oxd-input-group')]//input";
    private static final String STATUS_RADIO = "//label[normalize-space()='%s']/span[contains(@class,'oxd-radio-input')]";

    private final By addEmployeeTitle = By.xpath("//h6[normalize-space()='Add Employee']");
    private final By firstNameInput = By.name("firstName");
    private final By middleNameInput = By.name("middleName");
    private final By lastNameInput = By.name("lastName");
    private final By employeeIdInput = By.xpath(String.format(INPUT_BY_LABEL, "Employee Id"));
    private final By createLoginDetailsSwitch = By.xpath("//p[normalize-space()='Create Login Details']/following-sibling::div//span[contains(@class,'oxd-switch-input')]");
    private final By usernameInput = By.xpath(String.format(INPUT_BY_LABEL, "Username"));
    private final By passwordInput = By.xpath(String.format(INPUT_BY_LABEL, "Password"));
    private final By confirmPasswordInput = By.xpath(String.format(INPUT_BY_LABEL, "Confirm Password"));
    private final By saveButton = By.xpath("//button[@type='submit' and normalize-space()='Save']");
    private final By successToast = By.xpath("//div[contains(@class,'oxd-toast--success')]");

    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return isVisible(addEmployeeTitle);
    }

    public void fillEmployeeName(Employee employee) {
        logStep("Cargando nombre: " + employee.getFullName());
        type(firstNameInput, employee.getFirstName());
        type(middleNameInput, employee.getMiddleName());
        type(lastNameInput, employee.getLastName());
    }

    public void fillEmployeeId(String employeeId) {
        logStep("Cargando Employee Id: " + employeeId);
        replaceText(employeeIdInput, employeeId);
    }

    public void enableCreateLoginDetails() {
        logStep("Activando Create Login Details");
        if (driver.findElements(usernameInput).isEmpty()) {
            click(createLoginDetailsSwitch);
        }
    }

    public void fillLoginDetails(Employee employee) {
        logStep("Cargando usuario " + employee.getUsername() + " con estado " + employee.getStatus());
        type(usernameInput, employee.getUsername());
        click(By.xpath(String.format(STATUS_RADIO, employee.getStatus())));
        type(passwordInput, employee.getPassword());
        type(confirmPasswordInput, employee.getPassword());
    }

    public void clickSave() {
        logStep("Guardando empleado");
        click(saveButton);
    }

    public void createEmployee(Employee employee) {
        waitUntilVisible(addEmployeeTitle);
        waitUntilLoaderDisappears();
        fillEmployeeName(employee);
        fillEmployeeId(employee.getEmployeeId());
        enableCreateLoginDetails();
        fillLoginDetails(employee);
        clickSave();
    }

    public boolean isSavedSuccessfully() {
        try {
            waitUntilVisible(successToast);
            getWait().until(ExpectedConditions.urlContains("viewPersonalDetails"));
            waitUntilLoaderDisappears();
            logStep("Empleado guardado");
            return true;
        } catch (Exception e) {
            logger.error("El empleado no se guardo: {}", e.getMessage());
            return false;
        }
    }
}
