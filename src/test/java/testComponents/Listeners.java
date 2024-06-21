package testComponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.WebDriver;

import resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentReports extent=ExtentReporterNG.getReoportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> threadExtentTest = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		
	    test=extent.createTest(result.getMethod().getMethodName());
	    threadExtentTest.set(test);
	  }
	
	public void onTestSuccess(ITestResult result) {
	    
		test.log(Status.PASS, "Test got Passed");
	  }

	  /**
	   * Invoked each time a test fails.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#FAILURE
	   */
	  public  void onTestFailure(ITestResult result) {
		  threadExtentTest.get().fail(result.getThrowable());
	   
	   try {
		driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	} catch (Exception e) {
		e.printStackTrace();
	}
	   
	   String filepath = null;
	   	try {
	   		filepath = getScreenshot(result.getMethod().getMethodName(),driver);
	   			} 
	   	catch (IOException e) {
	   			e.printStackTrace();
	   			}
	   	threadExtentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	   
	  }

	  /**
	   * Invoked each time a test is skipped.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#SKIP
	   */
	  public  void onTestSkipped(ITestResult result) {
	    
	  }

	  /**
	   * Invoked each time a method fails but has been annotated with successPercentage and this failure
	   * still keeps it within the success percentage requested.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
	   */
	  public  void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    
	  }

	  /**
	   * Invoked each time a test fails due to a timeout.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   */
	  public  void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }

	  /**
	   * Invoked before running all the test methods belonging to the classes inside the &lt;test&gt;
	   * tag and calling all their Configuration methods.
	   *
	   * @param context The test context
	   */
	  public  void onStart(ITestContext context) {
	    
	  }

	  /**
	   * Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have
	   * run and all their Configuration methods have been called.
	   *
	   * @param context The test context
	   */
	  public  void onFinish(ITestContext context) {
	    extent.flush();
	  }
	

}
