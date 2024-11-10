package testcase;
//
//import Page.BigBasketPage;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//import java.util.concurrent.TimeUnit;
//import java.util.logging.Logger;
//
//public class BigBasketTest {
//
//    private WebDriver driver;
//    private BigBasketPage bigBasketPage;
//    private static final String TEST_URL = "https://www.bigbasket.com";
//    private static final Logger logger = Logger.getLogger(BigBasketTest.class.getName());
//
//    @BeforeClass
//    public void setUp() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//
//        bigBasketPage = new BigBasketPage(driver);
//        driver.get(TEST_URL);
//        logger.info("Opened BigBasket at " + TEST_URL);
//    }
//
//    @Test
//    public void testRetrieveProductData() {
//        String sectionName = "Best Sellers";
//        bigBasketPage.checkRequiredSection(sectionName);
//    }
//
//    @AfterClass
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//            logger.info("Browser session closed.");
//        }
//    }
//}

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class BigBasketTest {

    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
    private ExtentSparkReporter sparkReporter;

    @BeforeTest
    public void setup() {
        // Display confirmation dialog before opening Chrome
        int result = JOptionPane.showConfirmDialog(null, "Do you want to proceed with opening Chrome?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.NO_OPTION) {
            System.out.println("Test aborted by the user.");
            System.exit(0); // Exit the program if the user chooses 'No'
        }

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in");
        System.out.println("Opened Amazon homepage");
    }

    @Test
    public void testAmazonSearch() {
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("lg soundbar");
        searchBox.submit();
        System.out.println("Submitted search for 'lg soundbar'");

        try {
            Thread.sleep(2000); // Simple wait; replace with WebDriverWait if needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> products = driver.findElements(By.cssSelector(".s-main-slot .s-result-item"));
        Map<String, Integer> productPrices = new HashMap<>();

        for (WebElement product : products) {
            try {
                String name = product.findElement(By.cssSelector("h2")).getText();
                String priceString = product.findElement(By.cssSelector("a-price-whole")).getText().replace(",", "");
                int price = Integer.parseInt(priceString);
                productPrices.put(name, price);
            } catch (Exception e) {
                // Handling missing price
            }
        }

        List<Map.Entry<String, Integer>> sortedProducts = productPrices.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        for (Map.Entry<String, Integer> entry : sortedProducts) {
            String result = entry.getValue() + " " + entry.getKey();
            System.out.println(result);
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed");
        }
    }

//    public static void main(String[] args) {
//        SeleniumTest test = new SeleniumTest();
//        test.setup();
//        test.testAmazonSearch();
//        test.tearDown();
//    }
}
