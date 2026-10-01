package pages;

import Models.Employee;
import com.aventstack.extentreports.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddEmployeePage extends BasePage{
    private final By firstNameInput = By.xpath("//input[@name='firstName']");
    private final By middleNameInput = By.xpath("//input[@name='middleName']");
    private final By lastNameInput = By.xpath("//input[@name='lastName']");
    private final By employeeIdInput = By.xpath("//label[normalize-space()='Employee Id']/following::input[1]");
    private final By usernameInput = By.xpath("//label[normalize-space()='Username']/following::input[1]");
    private final By passwordInput = By.xpath("//label[normalize-space()='Password']/following::input[1]");
    private final By confirmPasswordInput = By.xpath("//label[normalize-space()='Confirm Password']/following::input[1]");
    private final By enabledStatusRadio = By.xpath("//label[normalize-space()='Enabled']/span[contains(@class,'oxd-radio-input')]");
    private final By saveButton = By.xpath( "//button[@type='submit']");
    private final By createLoginDetailsSwitch= By.xpath("//p[normalize-space()='Create Login Details']/following-sibling::div//span[contains(@class,'oxd-switch-input')]");

    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }

    public void enableCreateLoginDetails() {
        waitUntilClickable(createLoginDetailsSwitch);
        WebElement switchElement = driver.findElement(createLoginDetailsSwitch);
        if (!switchElement.isSelected()) {
            switchElement.click();
        }
    }

    public void enableStatus(){
        waitUntilClickable(enabledStatusRadio);
        WebElement radioElement = driver.findElement(enabledStatusRadio);
        if (!radioElement.isSelected()) {
            radioElement.click();
        }
    }

    public boolean createNewEmployeeWithUserDetails(Employee employee){
        enableCreateLoginDetails();
        fillEmployeeUserDetails(employee);
        driver.findElement(saveButton).click();
        SuccessToastModalPage successToast = new SuccessToastModalPage(driver);
        boolean isCreatedPersonalDetailsSuccessful = false;
        boolean isCreatedCustomFieldsSuccessful = false;
        boolean isCreatedAddAttachmentSuccessful = false;

        if(successToast.isSuccessfullySaved()){
            String fullName = employee.getFirstName() + " " + employee.getLastName();
            PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage(driver);
            boolean isDisplayedPersonalDetails = personalDetailsPage.isDisplayedEmployeeName(fullName);

            if(isDisplayedPersonalDetails){
                isCreatedPersonalDetailsSuccessful = personalDetailsPage.fillPersonalDetails(employee);
            }

            if(isCreatedPersonalDetailsSuccessful){
                CustomFieldsPage customFieldsPage = new CustomFieldsPage(driver);
                isCreatedCustomFieldsSuccessful = customFieldsPage.fillCustomFields(employee);
            }

            if(isCreatedCustomFieldsSuccessful){
                AddAttachmentPage addAttachmentPage = new AddAttachmentPage(driver);
                addAttachmentPage.clickAddAttachment();
                isCreatedAddAttachmentSuccessful = addAttachmentPage.fillAddAttachment(employee);
            }

        }
        return isCreatedAddAttachmentSuccessful;
    }

    public void fillEmployeeUserDetails(Employee employee){
        waitUntilVisible(firstNameInput);
        driver.findElement(firstNameInput).sendKeys(employee.getFirstName());
        driver.findElement(middleNameInput).sendKeys(employee.getMiddleName());
        driver.findElement(lastNameInput).sendKeys(employee.getLastName());
        driver.findElement(employeeIdInput).clear();
        driver.findElement(employeeIdInput).sendKeys(employee.getEmployeeId());
        driver.findElement(usernameInput).sendKeys(employee.getUsername());
        driver.findElement(passwordInput).sendKeys(employee.getPassword());
        driver.findElement(confirmPasswordInput).sendKeys(employee.getPassword());
        enableStatus();
    }

}
