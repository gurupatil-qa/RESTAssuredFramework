package resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

	Logger log = LogManager.getLogger(Listeners.class);

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {

		log.info("Test Passed "+result);
	}

	public void onTestFailure(ITestResult result) {

		log.error("Test Failed "+result);
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

	}

}
