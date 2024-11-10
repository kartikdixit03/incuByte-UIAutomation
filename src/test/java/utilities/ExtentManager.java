package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;

public class ExtentManager {
    private static ExtentReports extent; // ExtentReports object for generating reports
    private static ExtentSparkReporter htmlReporter; // Reporter for creating HTML reports
    private static String filePath = "/Users/kartikdixit/IdeaProjects/AutomationUI/src/test/resources/reports/extentReports.html";

    public static ExtentReports GetExtent() {
        if (extent == null) {
            extent = new ExtentReports();
            extent.attachReporter(getHtmlReporter());
        }
        return extent;
    }

    private static ExtentSparkReporter getHtmlReporter() {
        htmlReporter = new ExtentSparkReporter(filePath);
        htmlReporter.config().setProtocol(Protocol.HTTPS);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Automation Report");
        return htmlReporter;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}