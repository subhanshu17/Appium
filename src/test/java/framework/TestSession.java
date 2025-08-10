/**
 * 
 */
package framework;

import static framework.ConfigReader.getAppiumConfigs;
import static framework.ConfigReader.getApplicationConfigs;
import static framework.ConfigReader.getTestSetupConfigs;
import static framework.DriverFactory.getDriver;
import java.util.Map;
import org.openqa.selenium.remote.RemoteWebDriver;

import framework.model.config.AppiumConfigs;
import framework.model.config.ProjectConfigs;
import framework.model.config.TestConfigs;

/**
 * 
 */
public class TestSession {
	public RemoteWebDriver driver = null;
	public TestConfigs testConfigs = null;
	public AppiumConfigs appiumConfigs = null;
	public ProjectConfigs projectConfigs = null;
	public Map<String, Object> applicationConfigs = null;

	public TestSession() {
		initConfigs();
		instantiateDriver(this);
	}

	@SuppressWarnings("unchecked")
	private void initConfigs() {
		testConfigs = getTestSetupConfigs();
		appiumConfigs = getAppiumConfigs();
		applicationConfigs = getApplicationConfigs();
		System.out.println("\n\n\n########  Running Tests on " + testConfigs.getTestEnvironment()
				+ " using application url " + ((Map<String, Object>) applicationConfigs.get("applicationUrls"))
						.get(testConfigs.getTestEnvironment())
				+ " #######\n\n\n");
	}

	private void instantiateDriver(TestSession session) {
		driver = getDriver(session);
	}

	public void close() {
		driver.quit();
	}
}