package testcase;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TestClass extends BaseTest {
    private static final Logger logger = Logger.getLogger(TestClass.class.getName());

    @Test
    public static void loginTest() throws InterruptedException {
        logger.info("Starting loginTest");
        clickSignIn();
        enterEmail("kartikdixit37093@gmail.com");
        clickNext();
    }

    private static void clickSignIn() {
        driverTools.findElement(By.linkText("Sign in")).click();
        logger.info("Clicked on Sign in button");
    }

    private static void enterEmail(String email) throws InterruptedException {
        WebElement inputID = driverTools.findElement(By.id("login_id"));
        logger.info("Found the email input field");
        Thread.sleep(4000);
        logger.info("Waiting for 4 seconds");
        inputID.sendKeys(email);
        logger.info("Entered the email id");
    }

    private static void clickNext() {
        driverTools.findElement(By.xpath("//button[contains(@class,'btn blue') and contains(@id,'nextbtn')]")).click();
        logger.info("Clicked on Next button");
    }
}


//public static WebDriver driverTool;
//
//    @BeforeTest
//    public static void Setup() {
//        try {
//            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver");
//            System.out.println("Chromedriver path: " + System.getProperty("webdriver.chrome.driver"));
//            driverTool = new ChromeDriver();
//            driverTool.get("https://www.adidas.co.in/");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void TestSteps() {
//        WebElement element = driverTool.findElement(By.xpath("//button[contains(@class,'_user_icon' )]"));
//        element.click();
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e)e {
//            throw new RuntimeException(e);
//        }
//        WebElement email = driverTool.findElement(By.xpath("//input[@name='email'] "));
//        email.sendKeys("kartikdixit37093@gmail.com");
//        List<WebElement> checkBoxes =   driverTool.findElements(By.xpath("//input[contains(@tye,'checkbox')]"));
//        for(int i =0 ; i<checkBoxes.size();i++ ){
//            System.out.println(checkBoxes.size());
//            checkBoxes.get(i).click();
//        }
//        driverTool.findElement(By.xpath("//button[contains(@type,'submit')]")).click();
////        WebElement password =   driverTool.findElement(By.xpath("//input[@name='email'] "));
////        password.sendKeys("11915171@kartik");
////        System.out.println("Element clicked successfully!");
//
//    }