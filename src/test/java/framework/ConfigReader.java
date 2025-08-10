/**
 * 
 */
package framework;

import static framework.util.YamlUtil.mapYamlToClass;
import static framework.util.YamlUtil.readYAMLIntoMap;

import java.io.File;
import java.util.Map;

import framework.model.config.AppiumConfigs;
import framework.model.config.ProjectConfigs;
import framework.model.config.TestConfigs;;

/**
 * 
 */
public class ConfigReader {
	private static String configFilesRootPath = System.getProperty("user.dir") + File.separatorChar + "src"
			+ File.separatorChar + "test" + File.separatorChar + "resources" + File.separatorChar + "ConfigFiles";

	/**
	 * 
	 * @return
	 */
	public static AppiumConfigs getAppiumConfigs() {
		String appiumConfigsPath = configFilesRootPath + File.separatorChar + "appiumServerConfiguration.yaml";
		return mapYamlToClass(appiumConfigsPath, AppiumConfigs.class);
	}

	/**
	 * 
	 * @return
	 */
	public static Map<String,Object> getProjectConfigs() {
		String projectConfigsPath = configFilesRootPath + File.separatorChar + "projectConfiguration.yaml";
		return readYAMLIntoMap(projectConfigsPath);
	}

	/**
	 * 
	 * @return
	 */
	public static TestConfigs getTestSetupConfigs() {
		String testConfigsPath = configFilesRootPath + File.separatorChar + "testConfiguration.yaml";
		TestConfigs testConfigs = mapYamlToClass(testConfigsPath, TestConfigs.class);
		if (System.getProperty("testEnvironment") != null && !System.getProperty("testEnvironment").isBlank()) {
			testConfigs.setTestEnvironment(System.getProperty("testEnvironment"));
		}
		if (System.getProperty("deviceType") != null && !System.getProperty("deviceType").isBlank()) {
			testConfigs.setDeviceType(System.getProperty("deviceType"));
		}
		if (System.getProperty("webBrowser") != null && !System.getProperty("webBrowser").isBlank()) {
			testConfigs.setWebBrowser(System.getProperty("webBrowser"));
		}
		if (System.getProperty("os") != null && !System.getProperty("os").isBlank()) {
			testConfigs.setOs(System.getProperty("os"));
		}
		if (System.getProperty("whereToRun") != null && !System.getProperty("whereToRun").isBlank()) {
			testConfigs.setWhereToRun(System.getProperty("whereToRun"));
		}
		if (System.getProperty("automationType") != null && !System.getProperty("automationType").isBlank()) {
			testConfigs.setAutomationType(System.getProperty("automationType"));
		}
		if (System.getProperty("appPackage") != null && !System.getProperty("appPackage").isBlank()) {
			testConfigs.setAppPackage(System.getProperty("appPackage"));
		}
		if (System.getProperty("appActivity") != null && !System.getProperty("appActivity").isBlank()) {
			testConfigs.setAppActivity(System.getProperty("appActivity"));
		}
		return testConfigs;
	}

	/**
	 * 
	 * @return
	 */
	public static Map<String, Object> getApplicationConfigs() {
		String appConfigsPath = configFilesRootPath + File.separatorChar + "applicationConfigs.yaml";
		return readYAMLIntoMap(appConfigsPath);
	}
}
