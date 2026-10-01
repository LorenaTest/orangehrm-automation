package pages;

import Models.Employee;
import Models.PersonalDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalDetailsPage extends BasePage{

    private final By firstNameInput = By.xpath("//input[@name='firstName']");
    private By middleNameInput = By.xpath("//input[@name='middleName']");
    private By lastNameInput = By.xpath("//input[@name='lastName']");
    private By employeeIdInput = By.xpath("//label[normalize-space()='Employee Id']/following::input[1]");
    private final By otherIdInput = By.xpath("//label[normalize-space()='Other Id']/following::input[1]");
    private final By driversLicenseNumberInput = By.xpath("//label[normalize-space()=\"Driver's License Number\"]/following::input[1]");
    private final By licenseExpiryDateInput = By.xpath("//label[normalize-space()='License Expiry Date']/ancestor::div[contains(@class,'oxd-input-group')]//input");
    private final By nationalitySelect = By.xpath("//label[normalize-space()='Nationality']/following::div[contains(@class,'oxd-select-text-input')][1]");
    private final By maritalStatusSelect = By.xpath("//label[normalize-space()='Marital Status']/following::div[contains(@class,'oxd-select-text-input')][1]");
    private final By dateOfBirthInput = By.xpath("//label[normalize-space()='Date of Birth']/ancestor::div[contains(@class,'oxd-input-group')]//input");
    private final By maleRadio = By.xpath("//label[normalize-space()='Male']//span[contains(@class,'oxd-radio-input')]");
    private final By femaleRadio = By.xpath("//label[normalize-space()='Female']//span[contains(@class,'oxd-radio-input')]");
    private final By saveButton = By.xpath("//button[@type='submit' and normalize-space()='Save']");

    public PersonalDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean fillPersonalDetails(Employee employee){
        waitUntilVisible(firstNameInput);
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
        SuccessToastModalPage successToast = new SuccessToastModalPage(driver);
        boolean isSuccessfullySaved= successToast.isSuccessfullyUpdated();;
        waitUntilLoaderDisappears();
        return isSuccessfullySaved;
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
