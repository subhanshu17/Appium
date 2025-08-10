
package framework;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import framework.model.config.AppiumConfigs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

/**
 * OS - MAC, Linux, Windows
 * 
 */
public class DriverFactory {

	public static RemoteWebDriver getDriver(TestSession session) {
		RemoteWebDriver driver = null;
		DesiredCapabilities caps = new DesiredCapabilities();
		switch (session.testConfigs.getWhereToRun().toUpperCase()) {
		case "LOCAL":
			switch (session.testConfigs.getDeviceType().toUpperCase()) {
			case "MOBILE":
				switch (session.testConfigs.getOs().toUpperCase()) {
					case "ANDROID":
						switch (session.testConfigs.getAutomationType().toUpperCase()) {

							case "WEBBASED":
								caps.setCapability("platformName", "Android");
								caps.setCapability("appium:automationName", "UIAutomator2");
								caps.setCapability("appium:deviceName", System.getProperty("deviceName"));
								caps.setCapability("appium:platformVersion", System.getProperty("platformVersion"));
								caps.setCapability("browserName", "Chrome");
								caps.setCapability("chromedriverExecutable",
										System.getProperty("user.dir") + File.separatorChar + "src" + File.separatorChar + "test"
												+ File.separatorChar + "resources" + File.separatorChar + "Drivers"
												+ File.separatorChar + "chromedriver");
								System.out.println("ANDROID MOBILE Setting up Capability");
								try {
									AppiumConfigs appiumConfigs = session.appiumConfigs;
									System.out
											.println("Appium Url:\t" + appiumConfigs.getHostUrl() + ":" + appiumConfigs.getPort());
									System.out.println("Capabilties:\t" + caps);
									driver = new AppiumDriver(new URL(appiumConfigs.getHostUrl() + ":" + appiumConfigs.getPort()),
											caps);
								} catch (MalformedURLException e) {
									e.printStackTrace();
								}
								break;
							case "APPBASED":
								UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
								uiAutomator2Options.setPlatformName("Android");
								uiAutomator2Options.setDeviceName(System.getProperty("deviceName"));
								uiAutomator2Options.setPlatformVersion(System.getProperty("platformVersion"));
								uiAutomator2Options.ignoreHiddenApiPolicyError();
								uiAutomator2Options.setAppPackage(session.testConfigs.getAppPackage());
								uiAutomator2Options.setAppActivity(session.testConfigs.getAppActivity());
								uiAutomator2Options.noReset();
								uiAutomator2Options.setCapability("platformName", "Android");
								uiAutomator2Options.setCapability("platformName", "Android");
								caps.setCapability("newCommandTimeout", 60000);
								System.out.println("ANDROID MOBILE Setting up Capability");
								try {
									AppiumConfigs appiumConfigs = session.appiumConfigs;
									System.out
											.println("Appium Url:\t" + appiumConfigs.getHostUrl() + ":" + appiumConfigs.getPort());
									System.out.println("Capabilties:\t" + caps);
									driver = new AppiumDriver(new URL(appiumConfigs.getHostUrl() + ":" + appiumConfigs.getPort()),
											uiAutomator2Options);
								} catch (MalformedURLException e) {
									e.printStackTrace();
								}
								break;
						}
						break;
				case "IOS":
					caps.setCapability("platformName", "iOS");
					caps.setCapability("appium:automationName", "XCUITest");
					caps.setCapability("appium:deviceName", System.getProperty("deviceName"));
					caps.setCapability("appium:platformVersion", System.getProperty("platformVersion"));
					caps.setCapability("appium:udid", "00008120-000149E011E0201E");
					caps.setCapability("browserName", "Safari");
					try {
						AppiumConfigs appiumConfigs = session.appiumConfigs;
						System.out
								.println("Appium Url:\t" + appiumConfigs.getHostUrl() + ":" + appiumConfigs.getPort());
						System.out.println("Capabilties:\t" + caps);
						driver = new AppiumDriver(new URL(appiumConfigs.getHostUrl() + ":" + appiumConfigs.getPort()),
								caps);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					break;
				case "EMULATE_CHROME":
					ChromeOptions options = new ChromeOptions();
					if (System.getProperty("headless") != null
							&& System.getProperty("headless").equalsIgnoreCase("true")) {
						options.addArguments("--headless=chrome");
					}
					driver = new ChromeDriver(options);
					Dimension windowDimension = new Dimension(400, 642);
					driver.manage().window().setSize(windowDimension);
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + session.testConfigs.getDeviceType());
				}

				break;
			case "DESKTOP":
				switch (session.testConfigs.getWebBrowser().toUpperCase()) {
				case "CHROME":
					ChromeOptions options = new ChromeOptions();
					if (System.getProperty("headless") != null
							&& System.getProperty("headless").equalsIgnoreCase("true")) {
						options.addArguments("window-size=1400,800");
						options.addArguments("--headless=chrome");
						// options.addArguments("--no-sandbox");
						// options.addArguments("--headless=chrome");
						// options.addArguments("--disable-dev-shm-usage");
					}
					driver = new ChromeDriver(options);
					break;
				case "SAFARI":
					driver = new SafariDriver();
					break;
				case "FIREFOX":
					driver = new FirefoxDriver();
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + session.testConfigs.getWebBrowser());
				}
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + session.testConfigs.getDeviceType());
			}
			break;
		case "SAUCELABS":
			URL url = null;
			String saucelabsUserName = System.getProperty("saucelabsUserName");
			String saucelabsAccessKey = System.getProperty("saucelabsAccessKey");

			MutableCapabilities sauceCap = new MutableCapabilities();
			sauceCap.setCapability("platformName", session.testConfigs.getOs());
			sauceCap.setCapability("browserName", session.testConfigs.getWebBrowser());
			sauceCap.setCapability("appium:deviceName", System.getProperty("deviceName"));
			sauceCap.setCapability("appium:platformVersion", System.getProperty("platformVersion"));
			sauceCap.setCapability("appium:automationName", "XCUITest");
			MutableCapabilities sauceOptions = new MutableCapabilities();
			sauceOptions.setCapability("username", saucelabsUserName);
			sauceOptions.setCapability("accessKey", saucelabsAccessKey);
			sauceOptions.setCapability("appiumVersion", "2.0.0");
			sauceOptions.setCapability("deviceOrientation", "PORTRAIT");
			sauceCap.setCapability("sauce:options", sauceOptions);

			try {
				url = new URL("https://" + saucelabsUserName + ":" + saucelabsAccessKey
						+ "@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			driver = new IOSDriver(url, sauceCap);
		}

		System.out.println("before return ios driver" + driver);
		return driver;

	}
}
