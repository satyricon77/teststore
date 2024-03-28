package org.volovich.report;

import org.volovich.pages.base.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.volovich.utils.ScreenshotCapture.screeShotDestinationPath;

public class ExtentManager extends BasePage {
    public static ExtentReports extentReport;
    public static String extentReportMetaInfo;
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentReports getReport() {
        if (extentReport == null) {
            setupExtentReport("end_to_end_run_report");
        }
        return extentReport;
    }

    public static ExtentReports setupExtentReport(String testName) {
        extentReport = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/org/volovich/report/"
                + generateExtentReportMetaInfo(testName) + ".html");
        extentReport.attachReporter(spark);

        extentReport.setSystemInfo("Tester", "My Name");
        spark.config().setReportName("Regression Test");
        spark.config().setDocumentTitle("Test Results");
        spark.config().setTheme(Theme.DARK);

        return extentReport;
    }

    public static String generateExtentReportMetaInfo(String testName) {
        String date = new SimpleDateFormat("yyyy-MM-dd_HH--mm--ss").format(new Date());
        extentReportMetaInfo = testName + "_" + date;
        return extentReportMetaInfo;
    }

    public static void flushReport() {
        extentReport.flush();
    }

    public synchronized static ExtentTest getTest() {
        return extentTest.get();
    }

    public synchronized static ExtentTest createTest(String name, String description) {
        ExtentTest test = extentReport.createTest(name, description);
        extentTest.set(test);
        return getTest();
    }

    public synchronized static void log(String message) {
        getTest().info(message);
    }

    public synchronized static void pass(String message) {
        getTest().pass(message);
    }

    public synchronized static void fail(String message) {
        getTest().fail(message);
    }

    public synchronized static void attachScreenShot(){
        getTest().addScreenCaptureFromPath(screeShotDestinationPath);
    }
}







