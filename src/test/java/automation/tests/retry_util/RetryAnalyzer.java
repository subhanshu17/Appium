/**
 * 
 */
package automation.tests.retry_util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * 
 */

public class RetryAnalyzer implements IRetryAnalyzer {

	private int retryCount = 1;
	private static final int MAX_RETRY_COUNT = 2;

	public boolean retry(ITestResult result) {
		if (retryCount < MAX_RETRY_COUNT) {
			retryCount++;
			return true;
		}
		return false;
	}
}
