/**
 * 
 */
package automation.tests;

import static framework.ActionsManager.*;
import static framework.SessionManager.getSession;
import static framework.SessionManager.setSession;

import java.io.ByteArrayInputStream;
import java.io.File;

import automation.tests.page_actions.*;
import io.qameta.allure.Attachment;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import framework.TestSession;
import framework.interfaces.POMInterface;

import io.qameta.allure.Allure;

/**
 * 
 */
@Listeners({AllureTestNg.class})
public class BaseTests implements POMInterface, ITestListener {
	@BeforeMethod
	public synchronized void setup() {
		setSession(new TestSession());
		initPageActions(getSession());
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
//		if (ITestResult.FAILURE == result.getStatus()) {
//			attachScreenshot(getSession().driver, result.getName());
//		}
		if (ITestResult.FAILURE == result.getStatus()) {
			// Attach screenshot
			byte[] screenshot = ((TakesScreenshot) getSession().driver).getScreenshotAs(OutputType.BYTES);
			Allure.addAttachment(result.getName() + " - screenshot", new ByteArrayInputStream(screenshot));
		}
		// if (result.getStatus() == 2) {
		// 	// Capture screenshot
		// 	byte[] screenshot = captureScreenshot();
		// 	// Attach screenshot to Allure report
		// 	String screenshotName = result.getName().replaceAll(" ", "_") + "_screenshot.png";
		// 	Allure.addAttachment(screenshotName, new ByteArrayInputStream(screenshot));
		// }
		getSession().close();
	}

	public static byte[] captureScreenshot() {
		// Capture screenshot as byte array
		return ((TakesScreenshot) getSession().driver).getScreenshotAs(OutputType.BYTES);
	}

	protected void attachScreenshot() {
		byte[] screenshot = captureScreenshot();
		Allure.addAttachment("screenshot", new ByteArrayInputStream(screenshot));
	}

	/**
	 * To instantiate all page action classes
	 */
	@Override
	public synchronized void initPageActions(TestSession session) {
		setLoginPageAction(new LoginPage(session));
		setOtpPageAction(new OtpPage(session));
		setScannerPageAction(new ScannerPage(session));
		setDashboardPageAction(new DashboardPage(session));
		setWatchesPageAction(new WatchesPage(session));
		setProfileSettingsPageAction(new ProfileSettingPage(session));
	}

	@Attachment(value = "{screenshotName}", type = "image/png")
	public static byte[] attachScreenshot(WebDriver driver, String screenshotName) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	@BeforeSuite
	public void deleteReports() {
		File folder = new File(System.getProperty("user.dir") + "/target/allure-results");
		if (folder.exists()) {
			for (File file : folder.listFiles()) {
				file.delete();
			}
			folder.delete();
			System.out.println("✅ Deleted 'allure-results' using Java File loop.");
		} else {
			System.out.println("No 'allure-results' folder found.");
		}
	}
}
