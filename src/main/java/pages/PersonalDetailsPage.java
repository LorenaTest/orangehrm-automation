package pages;

import Models.Employee;
import Models.PersonalDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalDetailsPage extends BasePage{

    private By firstNameInput = By.xpath("//input[@name='firstName']");
    private By middleNameInput = By.xpath("//input[@name='middleName']");
    private By lastNameInput = By.xpath("//input[@name='lastName']");
    private By employeeIdInput = By.xpath("//label[normalize-space()='Employee Id']/following::input[1]");
    private By otherIdInput = By.xpath("//label[normalize-space()='Other Id']/following::input[1]");
    private By driversLicenseNumberInput = By.xpath("//label[normalize-space()=\"Driver's License Number\"]/following::input[1]");
    private By licenseExpiryDateInput = By.xpath("//label[normalize-space()='License Expiry Date']/following::input[@placeholder='yyyy-dd-mm'][1]");
    private By nationalitySelect = By.xpath("//label[normalize-space()='Nationality']/following::div[contains(@class,'oxd-select-text-input')][1]");
    private By maritalStatusSelect = By.xpath("//label[normalize-space()='Marital Status']/following::div[contains(@class,'oxd-select-text-input')][1]");
    private By dateOfBirthInput = By.xpath("//label[normalize-space()='Date of Birth']/following::input[@placeholder='yyyy-dd-mm'][1]");
    private By maleRadio = By.xpath("//label[normalize-space()='Male']//span[contains(@class,'oxd-radio-input')]");
    private By femaleRadio = By.xpath("//label[normalize-space()='Female']//span[contains(@class,'oxd-radio-input')]");
    private By saveButton = By.xpath("//button[@type='submit' and normalize-space()='Save']");

    public PersonalDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void fillPersonalDetails(Employee employee){
        waitUntilVisible(firstNameInput);
        //driver.findElement(firstNameInput).sendKeys(employee.getFirstName());
        //.findElement(middleNameInput).sendKeys(employee.getMiddleName());
        //driver.findElement(lastNameInput).sendKeys(employee.getLastName());
        driver.findElement(otherIdInput).clear();
        driver.findElement(otherIdInput).sendKeys(employee.getPersonalDetails().getOtherId());
        driver.findElement(driversLicenseNumberInput).sendKeys(employee.getPersonalDetails().getDriversLicenseNumber());
        driver.findElement(licenseExpiryDateInput).sendKeys(employee.getPersonalDetails().getLicenseExpiryDate());
        selectNationality(employee.getPersonalDetails().getNationality());
        selectMaritalStatus(employee.getPersonalDetails().getMaritalStatus());
        driver.findElement(dateOfBirthInput).sendKeys(employee.getPersonalDetails().getDateOfBirth());
        selectGender(employee.getPersonalDetails().getGender());

        waitUntilClickable(saveButton);
        driver.findElement(saveButton).click();

    }

    public void selectGender(String gender){
        if(gender.equalsIgnoreCase("Female")) {
            driver.findElement(femaleRadio).click();
        } else {
            driver.findElement(maleRadio).click();
        }
    }

    public boolean isDisplayedEmployeeName(String fullName){

        By employeeName = By.xpath(
                "//div[contains(@class,'orangehrm-edit-employee-name')]//h6[normalize-space()='"
                        + fullName + "']"
        );

        waitUntilLoaderDisappears();
        waitUntilVisible(employeeName);
        return isVisible(employeeName);
    }

    public void selectNationality(String nationality) {
        selectOption(nationalitySelect, nationality);
    }

    public void selectMaritalStatus(String maritalStatus) {
        selectOption(maritalStatusSelect, maritalStatus);
    }
}
