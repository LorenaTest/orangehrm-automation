package helpers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class ReportManager {

    private static final Logger logger = LogManager.getLogger(ReportManager.class);
    private static ReportManager instance;
    private static final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();

    private final ExtentReports extentReport;

    private ReportManager(String reportPath, String reportName) {
        File reportFile = new File(reportPath);
        if (reportFile.getParentFile() != null) {
            reportFile.getParentFile().mkdirs();
        }

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFile);
        sparkReporter.config().setDocumentTitle("Automation Report " + reportName);
        sparkReporter.config().setReportName(reportName);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setEncoding("utf-8");

        extentReport = new ExtentReports();
        extentReport.attachReporter(sparkReporter);
        extentReport.setSystemInfo("Sitio", "https://opensource-demo.orangehrmlive.com/");
        extentReport.setSystemInfo("Java", System.getProperty("java.version"));
        extentReport.setSystemInfo("Sistema operativo", System.getProperty("os.name"));
        logger.info("Reporte inicializado en {}", reportFile.getAbsolutePath());
    }

    public static synchronized void init(String reportPath, String reportName) {
        if (instance == null) {
            instance = new ReportManager(reportPath, reportName);
        }
    }

    public static ReportManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Llamar a ReportManager.init antes de usar el reporte");
        }
        return instance;
    }

    public synchronized ExtentTest startTest(String testName, String... categories) {
        ExtentTest test = extentReport.createTest(testName).assignCategory(categories);
        currentTest.set(test);
        logger.info("Inicio de prueba: {}", testName);
        return test;
    }

    public ExtentTest getTest() {
        return currentTest.get();
    }

    public static void logStep(String message) {
        if (instance != null && currentTest.get() != null) {
            currentTest.get().info(message);
        }
    }

    public synchronized void flush() {
        extentReport.flush();
        logger.info("Reporte generado");
    }
}
