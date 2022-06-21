package com.awinbv.rockzent.generalUtility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * 
 * @author Chinmaya
 *
 */
public class LisImplementClass implements ITestListener {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports reports;
	public ExtentTest test;
	public String dateName = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
	
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		
		EventFiringWebDriver eDriver = new EventFiringWebDriver( BaseClass.sDriver );
		File srcFile = eDriver.getScreenshotAs(OutputType.FILE);
		String destPath = System.getProperty("user.dir") + "/Screenshots/" + methodName + dateName + ".png";
		File destFile = new File(destPath);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			test.log(Status.FAIL, "TEST CASE FAILED: " + result.getName());
			test.log(Status.FAIL, "REASON OF FAILURE: " + result.getThrowable());
			try {
				test.addScreenCaptureFromPath(destPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void onStart(ITestContext context) {
		htmlReporter = new ExtentHtmlReporter("./ExtentReports/Rockzent_"+dateName+".html");
		htmlReporter.config().setDocumentTitle("Rockzent Report"); //title of the report
		htmlReporter.config().setTheme(Theme.DARK); //set the theme
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		htmlReporter.config().setReportName("Regression");

		reports.setSystemInfo("AppURL", "http://localhost:8888");
		reports.setSystemInfo("Env", "Test QA");
		reports.setSystemInfo("Build", "1.01");
		reports.setSystemInfo("Reporter name", "Chinmaya");
	}
	
	public void onTestStart(ITestResult result) 
	{
		test=reports.createTest(result.getMethod().getMethodName());

	}
	
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Case PASSED: " + result.getMethod().getMethodName());
		}
	
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getMethod().getMethodName());
	  }
	
	public void onFinish(ITestContext context) {
		reports.flush();
	}

}
