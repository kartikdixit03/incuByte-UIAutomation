package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static WebDriver driverTools;
    protected ExtentReports extent;
    protected static ThreadLocal<ExtentTest> mainLogger = new ThreadLocal<>();
    private ExtentSparkReporter sparkReporter;
    protected Properties prop = new Properties();

    public static ExtentTest getLogger() {
        return mainLogger.get();
    }

    @BeforeTest
    public void setup() {
        // Initialize ExtentReports and SparkReporter
        sparkReporter = new ExtentSparkReporter("extentReport.html");
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Test Execution Report");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Load properties from config file
        try {
            FileReader fr = new FileReader(System.getProperty("user.dir") + "/src/test/resources/configfiles/config.properties");
            prop.load(fr);
        } catch (IOException e) {
            System.out.println("Failed to load config properties");
            e.printStackTrace();
        }

        // Set up WebDriver based on browser type
        String browser = prop.getProperty("browser");
        String testUrl = prop.getProperty("testUrl");

        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            driverTools = new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            driverTools = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser specified in properties file.");
        }

        // Navigate to the test URL and configure browser settings
        driverTools.get(testUrl);
        driverTools.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driverTools.manage().window().maximize();
    }

    @BeforeMethod
    public void startTestLogging(Method method) {
        // Create a new ExtentTest instance for each test method and assign it to mainLogger
        ExtentTest test = extent.createTest(method.getName());
        mainLogger.set(test);
        log("Starting test: " + method.getName());
        log("Browser setup and navigation to URL: " + prop.getProperty("testUrl"));
    }

    @BeforeMethod
    public void resetSession() {
        driverTools.manage().deleteAllCookies();
        ((JavascriptExecutor) driverTools).executeScript("window.localStorage.clear();");
        ((JavascriptExecutor) driverTools).executeScript("window.sessionStorage.clear();");
        driverTools.navigate().refresh(); // Refresh to ensure a fresh session
//        driverTools.get(prop.getProperty("testUrl")); // Navigate to the base URL
    }

    @AfterTest
    public void tearDown() {
        if (driverTools != null) {
            driverTools.quit();
            log("Browser closed.");
        }

        // Finalize the Extent Report
        extent.flush();

        // Open the Extent Report automatically after tests
        File htmlFile = new File("extentReport.html");
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException e) {
            System.out.println("Failed to open Extent Report");
            e.printStackTrace();
        }
    }

    // Custom log method to add messages to Extent Report
    protected void log(String message) {
        getLogger().info(message);
    }

    // Method to log a step in the report for detailed logging
    protected void step(String stepDescription) {
        log(stepDescription); // Use log method to log each step
    }
}
