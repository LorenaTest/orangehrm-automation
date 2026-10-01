package helpers;

import Models.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.RandomUtil;

import java.io.FileNotFoundException;

public class EmployeeDataFactory {

    private static final Logger logger = LogManager.getLogger(EmployeeDataFactory.class);

    private EmployeeDataFactory() {
    }

    public static Object[][] loadUniqueEmployees(String filePath) throws FileNotFoundException {
        Object[] employees = JsonTestDataHelper.getInstance().getTestData(filePath, Employee.class);
        Object[][] data = new Object[employees.length][1];
        for (int i = 0; i < employees.length; i++) {
            data[i][0] = makeUnique((Employee) employees[i]);
        }
        return data;
    }

    private static Employee makeUnique(Employee employee) {
        employee.setFirstName(employee.getFirstName() + RandomUtil.generateNameSuffix());
        employee.setEmployeeId(RandomUtil.generateEmployeeId());
        employee.setUsername(RandomUtil.generateUsername(employee.getFirstName(), employee.getLastName()));
        logger.info("Empleado de prueba: {}", employee);
        return employee;
    }
}
