package pim;

import Models.Employee;
import base.BaseTest;
import helpers.EmployeeDataFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AddEmployeePage;
import pages.EmployeeListPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PimPage;

import java.io.FileNotFoundException;

public class CreateEmployeeTest extends BaseTest {

    private static final String EMPLOYEE_TEST_DATA = "resources/testdata/employeeData.json";

    @Test(description = "Crear un empleado con datos de usuario desde PIM y encontrarlo en el listado",
            dataProvider = "employees")
    public void createEmployeeAndFindItInEmployeeList(Employee employee) {
        HomePage homePage = new LoginPage(driver).loginAs(ADMIN_USERNAME, ADMIN_PASSWORD);
        Assert.assertTrue(homePage.isDashboardDisplayed(), "No se pudo iniciar sesion");

        PimPage pimPage = homePage.goToPim();
        Assert.assertTrue(pimPage.isDisplayed(), "No se abrio el modulo PIM");

        AddEmployeePage addEmployeePage = pimPage.goToAddEmployee();
        addEmployeePage.createEmployee(employee);
        Assert.assertTrue(addEmployeePage.isSavedSuccessfully(), "El empleado no se guardo: " + employee);

        EmployeeListPage employeeListPage = pimPage.goToEmployeeList();
        employeeListPage.searchEmployee(employee);
        Assert.assertTrue(employeeListPage.isEmployeeInResults(employee),
                "El empleado no aparece en la grilla de resultados: " + employee);
    }

    @DataProvider(name = "employees")
    public Object[][] employees() throws FileNotFoundException {
        return EmployeeDataFactory.loadUniqueEmployees(EMPLOYEE_TEST_DATA);
    }
}
