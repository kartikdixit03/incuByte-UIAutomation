package base;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Assert;
import org.testng.SkipException;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Assertion {

    /**
     * Asserts that the actual value matches the specified matcher condition.
     * Logs the assertion details in ExtentReports, including whether it passed or failed.
     *
     * @param reason  The reason or description of the assertion
     * @param actual  The actual value to be tested
     * @param matcher The Hamcrest matcher to apply to the actual value
     * @param <T>     The type of the actual value
     */
    public static <T> void assertThat(String reason, T actual, Matcher<T> matcher) {
        Description description = new StringDescription();
        description.appendText("\nExpected: ");
        description.appendDescriptionOf(matcher);
        description.appendText("\nActual  : ");
        description.appendValue(actual);
        description.appendText("\n");

        String detail = description.toString().replaceAll("<", "[").replaceAll(">", "]");

        boolean passed = matcher.matches(actual);
        if (passed) {
            BaseTest.getLogger().log(Status.PASS, "<h3>" + reason + "</h3>" + "<pre>" + detail + "</pre>");
        } else {
            BaseTest.getLogger().log(Status.FAIL, "<h3>" + reason + "</h3>" + "<pre>" + detail + "</pre>");
            captureScreenshot("Assertion Failure Screenshot");
        }

        Assert.assertThat(reason, actual, matcher);
    }

    /**
     * Fails the test with a specific reason and logs the failure in ExtentReports.
     *
     * @param reason The reason for failing the test
     */
    public static void fail(String reason) {
        BaseTest.getLogger().log(Status.FAIL, "<h3><font color='red'>" + reason + "</font></h3>");
        captureScreenshot("Failure Screenshot");
        Assert.fail(reason);
    }

    /**
     * Skips the test with a specific detail and logs the skip in ExtentReports.
     *
     * @param detail The reason or detail for skipping the test
     */
    public static void skip(String detail) {
        BaseTest.getLogger().log(Status.SKIP, "<h3><font color='orange'>" + detail + "</font></h3>");
        throw new SkipException(detail);
    }

    /**
     * Captures a screenshot and adds it to the Extent Report.
     *
     * @param screenshotName The name for the screenshot file
     */
    private static void captureScreenshot(String screenshotName) {
        WebDriver driver = BaseTest.driverTools; // Assume driverTools is accessible
        if (driver instanceof TakesScreenshot) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                String screenshotPath = "screenshots/" + screenshotName + ".png";
                Files.createDirectories(new File("screenshots").toPath());
                Files.copy(screenshot.toPath(), new File(screenshotPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                BaseTest.getLogger().addScreenCaptureFromPath(screenshotPath, screenshotName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
