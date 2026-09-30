package pages;

import Models.Employee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PimPage extends BasePage {

    private final By textTitlePim= By.xpath("//h6[normalize-space()='PIM']");
    private final By buttonAddEmployee= By.xpath("//a[normalize-space()='Add Employee']");

    public PimPage(WebDriver driver) {
        super(driver);
    }

    public boolean titleIsDisplayed(){
        return  isVisible(textTitlePim);
    }

    public boolean createNewEmployee(Employee employee){
        waitUntilVisible(buttonAddEmployee);
        driver.findElement(buttonAddEmployee).click();
        AddEmployeePage addEmployeePage = new AddEmployeePage(driver);
        return addEmployeePage.createNewEmployeeWithUserDetails(employee);
    }
}
