package login;

import Models.Employee;
import base.BaseTest;
import enums.MainMenuOption;
import helpers.JsonTestDataHelper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PimPage;
import utils.RandomUtil;

import java.io.FileNotFoundException;

public class LoginTest extends BaseTest {

    private static final String EMPLOYEE_TEST_DATA_PATH = "resources/testdata/";

    @Test(description = "login with valid credentials and create new employee from a data file",
            dataProvider = "employeeDataProvider")
    public void testSuccessfulLoginAndCreateNewEmployee(Employee employee) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        boolean  isLoginSuccessful= loginPage.loginAs("Admin","admin123");
        Assert.assertTrue(isLoginSuccessful, "Las credenciales son incorrectas");

        HomePage homePage = new HomePage(driver);
        boolean isDisplayedTitle= homePage.clickOnMainMenuOption(MainMenuOption.PIM);
        Assert.assertTrue(isDisplayedTitle, "La pagina actual no es la esperada");

        PimPage pimPage = new PimPage(driver);
        Employee newEmployee = setUserDetails(employee);
        boolean isEmployeeCreatedSuccessfully = pimPage.createNewEmployee(newEmployee);
        Assert.assertTrue(isEmployeeCreatedSuccessfully, "El empleado no fue creado con exito");
    }

    private Employee setUserDetails(Employee employee){
        String employeeId = RandomUtil.generateEmployeeId();
        String username = RandomUtil.generateUsername(
                employee.getFirstName(),
                employee.getLastName()
        );
        String password = RandomUtil.generatePassword();

        employee.setEmployeeId(employeeId);
        employee.setUsername(username);
        employee.setPassword(password);

        return employee;
    }

    @DataProvider(name = "credentials")
    public Object[][] credentials() {
        return new Object[][] {
                { "Admin", "admin123" },
        };
    }

    @DataProvider(name = "employeeDataProvider")
    public Object[] employeeDataProvider() throws FileNotFoundException {

        return JsonTestDataHelper.getInstance().getTestData(EMPLOYEE_TEST_DATA_PATH + "employeeData.json", Employee.class);
    }
}
