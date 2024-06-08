package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;

public class ExtentManager {
    private static ExtentReports extent; // ExtentReports class object to create the report
    private static ExtentTest test;// ExtentTest class object to log the test steps
    private static ExtentSparkReporter htmlReporter;// ExtentHtmlReporter class object to create the HTML report
    private static String filePath = "/Users/kartikdixit/IdeaProjects/AutomationUI/src/test/resources/reports/extentReports.html";

    public static ExtentReports GetExtent(){
        if (extent != null)
            return extent; //avoid creating new instance of HTML file
        extent = new ExtentReports();
        extent.attachReporter(getHtmlReporter());
        return extent;
    }

    private static ExtentObserver getHtmlReporter() {
        htmlReporter = new ExtentSparkReporter(filePath);// create the HTML report file
        // make the charts visible on report open
        htmlReporter.config().setProtocol(Protocol.HTTPS); // Protocol.HTTPS will make the report URL as https://
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Automation Report");
        return htmlReporter;
    }
}