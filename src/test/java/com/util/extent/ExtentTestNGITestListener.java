package com.util.extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import macaca.client.MacacaClient;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

/**
 * Created by sammy on 16-12-20.
 */
public class ExtentTestNGITestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance("test-output/extent.html");
    private static ThreadLocal test = new ThreadLocal();
    public static MacacaClient driver;

    @Override
    public synchronized void onStart(ITestContext context) {
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        test.set(extent.createTest(result.getMethod().getMethodName()));
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        ((ExtentTest)test.get()).pass("Test passed");
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        ((ExtentTest)test.get()).fail(result.getThrowable());
        File directory = new File("test-output");
        try {
            String screenPath = directory.getCanonicalPath() + "/";
            File file = new File(screenPath);
            if (!file.exists()){
                file.mkdirs();
            }
            String fileName = result.getMethod().getMethodName() + ".png";
            driver.saveScreenshot(screenPath + fileName);
            ((ExtentTest)test.get()).addScreenCaptureFromPath(screenPath + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        ((ExtentTest)test.get()).skip(result.getThrowable());
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }
}