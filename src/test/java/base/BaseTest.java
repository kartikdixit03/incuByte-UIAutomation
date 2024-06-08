package base;

import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

import org.testng.annotations.AfterSuite;
import utilities.ExtentManager;

public class BaseTest {

    public static WebDriver driverTools; // WebDriver class object to control the browser

    public static Properties prop = new Properties(); // Properties class object to read properties file

    public static FileReader fr; // FileReader class object to read the file

    public static final Logger logger = LogManager.getLogger(BaseTest.class);

    /**
     * This method is used to log the test steps in the extent report
     * @param result
     */

    @BeforeTest
    public void setUP() {
        if(driverTools == null) {
            try {
                fr = new FileReader(System.getProperty("user.dir") + "/src/test/resources/configfiles/config.properties");
                prop.load(fr);  // Load the properties file
            } catch (Exception e) {
                logger.error("Failed to read properties file", e);
            }
            LoggingPreferences logs = new LoggingPreferences();
            logs.enable(LogType.BROWSER, Level.INFO);  // browser logs
            logs.enable(LogType.DRIVER, Level.ALL); // driver logs

            String browser = prop.getProperty("browser");
            if(browser.equals("chrome")) {
                WebDriverManager.chromedriver().setup();
                driverTools = new ChromeDriver();
                driverTools.get(prop.getProperty("testUrl"));
            } else if(browser.equals("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driverTools = new FirefoxDriver();
                driverTools.get(prop.getProperty("testUrl"));
            } else {
                logger.warn("Invalid browser specified in properties file.");
            }
        }
    }

    @AfterSuite
    public void TearDown() {
        ExtentManager.GetExtent().flush();
        driverTools.quit();

        File htmlFile = new File("/Users/kartikdixit/IdeaProjects/AutomationUI/src/test/resources/reports/extentReports.html");
        try {
            Desktop.getDesktop().browse(htmlFile.toURI()); // Open the report in the default browser
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
