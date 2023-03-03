package TestsUI;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTests {
    protected WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.edgedriver().setup();
//        System.setProperty("webdriver.edge.driver",
//                "src/main/resources/browserDrivers/msedgedriver.exe");
//        driver = new EdgeDriver();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void teardown(ITestResult result){
//        if (result.getStatus() == ITestResult.FAILURE) {
//            FailureManager failureManager = new FailureManager(driver);
//            failureManager.takePngScreenshot(result.getName());
//        }
//        driver.quit();
        if (driver != null){
            driver.quit();
        }
    }

}
