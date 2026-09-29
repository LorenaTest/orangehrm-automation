package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver webDriver;

    @BeforeMethod
    public void setUp(){
        webDriver = new ChromeDriver(chromeSinGestorDeContrasenas());
        webDriver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @AfterMethod
    public void tearDown(){
        if(webDriver != null)
            webDriver.quit();
    }

    private ChromeOptions chromeSinGestorDeContrasenas(){
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
