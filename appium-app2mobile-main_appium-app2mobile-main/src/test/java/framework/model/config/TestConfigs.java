/**
 * 
 */
package framework.model.config;

/**
 * 
 */
public class TestConfigs {
	private String testEnvironment;
	private String webBrowser;
	private String deviceType;
	private String os;
	private String whereToRun;
	private String automationType;
	private String appPackage;
	private String appActivity;

	public String getTestEnvironment() {
		return testEnvironment;
	}

	public void setTestEnvironment(String testEnvironment) {
		this.testEnvironment = testEnvironment;
	}

	public String getWebBrowser() {
		return webBrowser;
	}

	public void setWebBrowser(String webBrowser) {
		this.webBrowser = webBrowser;
	}

	public String getAutomationType() {
		return automationType;
	}

	public void setAutomationType(String automationType) {
		this.automationType = automationType;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getWhereToRun() {
		return whereToRun;
	}

	public void setWhereToRun(String whereToRun) {
		this.whereToRun = whereToRun;
	}

	public String getAppPackage() {
		return appPackage;
	}

	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}

	public String getAppActivity() {
		return appActivity;
	}

	public void setAppActivity(String appActivity) {
		this.appActivity = appActivity;
	}

	@Override
	public String toString() {
		return "TestConfigs [testEnvironment=" + testEnvironment + ", webBrowser=" + webBrowser + ", automationType=" + automationType +", deviceType="
				+ deviceType + ", os=" + os + ", whereToRun=" + whereToRun + "]";
	}
}