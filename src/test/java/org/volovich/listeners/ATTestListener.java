package org.volovich.listeners;

import org.volovich.pages.base.BasePage;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.volovich.utils.report.ExtentManager;

import static org.volovich.utils.ScreenshotCapture.takeSnapShot;

public class ATTestListener extends BasePage implements ITestListener {

    public ATTestListener() {
        super();
    }

    @Override
    public synchronized void onTestStart(ITestResult context) {
        ExtentManager.getReport();
        ExtentManager.createTest(context.getName(), context.getName());
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        ExtentManager.getTest().fail(result.getThrowable());
        try {
            System.out.println("Test failed: " + result.getName());
            takeSnapShot(result.getMethod().getMethodName());
            ExtentManager.attachScreenShot();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        ExtentManager.flushReport();
    }
}
