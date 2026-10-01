package base;

import com.aventstack.extentreports.Status;
import helpers.ConfigReader;
import helpers.ReportManager;
import helpers.ScreenShotHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    private static final String REPORT_PATH = "reports/ExtentReport.html";
    private static final String DEFAULT_BROWSER = "chrome";

    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    protected WebDriver driver;
    protected static final String ADMIN_USERNAME = ConfigReader.get("admin.username");
    protected static final String ADMIN_PASSWORD = ConfigReader.get("admin.password");

    @BeforeSuite
    public void initReport() {
        ReportManager.init(REPORT_PATH, "OrangeHRM - Alta de empleado");
    }

    @BeforeMethod
    public void setUp(Method method, Object[] testData, ITestContext context) {
        String browser = getBrowser(context);
        logger.info("Abriendo navegador: {}", browser);
        driver = createDriver(browser);
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("url"));

        String description = method.getName() + " " + Arrays.toString(testData) + " [" + browser + "]";
        ReportManager.getInstance().startTest(description, browser);
        ReportManager.logStep("Navegador " + browser + " abierto en " + ConfigReader.get("url"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        logger.info("Resultado de {}: {}", result.getName(), statusName(result.getStatus()));
        try {
            if (driver != null) {
                if (result.getStatus() == ITestResult.SUCCESS) {
                    ScreenShotHelper.takeScreenShotAndAddToHTMLReport(driver, Status.PASS, "Prueba exitosa");
                } else if (result.getStatus() == ITestResult.FAILURE) {
                    ScreenShotHelper.takeScreenShotAndAddToHTMLReport(driver, Status.FAIL,
                            "Prueba fallida: " + result.getThrowable().getMessage());
                } else {
                    ReportManager.getInstance().getTest().skip("Prueba omitida");
                }
            }
        } finally {
            if (driver != null) {
                logger.info("Cerrando navegador");
                driver.quit();
            }
        }
    }

    @AfterSuite(alwaysRun = true)
    public void flushReport() {
        ReportManager.getInstance().flush();
    }

    private String statusName(int status) {
        switch (status) {
            case ITestResult.SUCCESS:
                return "PASO";
            case ITestResult.FAILURE:
                return "FALLO";
            default:
                return "OMITIDA";
        }
    }

    private String getBrowser(ITestContext context) {
        String browser = context.getCurrentXmlTest().getParameter("browser");
        return browser == null ? DEFAULT_BROWSER : browser.toLowerCase();
    }

    private WebDriver createDriver(String browser) {
        switch (browser) {
            case "chrome":
                return new ChromeDriver(chromeSinGestorDeContrasenas());
            case "firefox":
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException(browser + " no soportado");
        }
    }

    private ChromeOptions chromeSinGestorDeContrasenas() {
        Map<String, Object> preferencias = new HashMap<>();
        preferencias.put("credentials_enable_service", false);
        preferencias.put("profile.password_manager_enabled", false);
        preferencias.put("profile.password_manager_leak_detection", false);

        ChromeOptions opciones = new ChromeOptions();
        opciones.setExperimentalOption("prefs", preferencias);
        opciones.addArguments("--disable-features=PasswordLeakDetection,AutofillServerCommunication");
        return opciones;
    }
}
