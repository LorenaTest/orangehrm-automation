package login;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PimPage;

public class LoginTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        Thread.sleep(2000);
        PimPage productsPage = loginPage.loginAs("Admin","admin123");
        Thread.sleep(2000);

    }
}
