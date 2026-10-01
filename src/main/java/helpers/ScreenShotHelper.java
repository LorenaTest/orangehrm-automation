package helpers;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScreenShotHelper {

    private static final Logger logger = LogManager.getLogger(ScreenShotHelper.class);

    private ScreenShotHelper() {
    }

    public static String takeScreenShot(WebDriver webDriver) {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
    }

    public static void takeScreenShotAndAddToHTMLReport(WebDriver webDriver, Status status, String details) {
        String imageBase64 = takeScreenShot(webDriver);
        logger.info("Captura agregada al reporte: {}", details);
        ReportManager.getInstance().getTest().log(status, details,
                MediaEntityBuilder.createScreenCaptureFromBase64String(imageBase64).build());
    }
}
