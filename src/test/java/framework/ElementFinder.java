/**
 * 
 */
package framework;

import static framework.util.YamlUtil.readYAMLIntoMap;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;;

/**
 * 
 */
public class ElementFinder {
	private RemoteWebDriver driver;
	private Map<String, Object> pageObjectsMap = null;

	public ElementFinder(TestSession session, String pageName) {
		this.driver = session.driver;
		pageObjectsMap = readYAMLIntoMap("src" + File.separatorChar + "test" + File.separatorChar + "resources"
				+ File.separatorChar + "PageObjects" + File.separatorChar + session.testConfigs.getDeviceType()
				+ "_VIEW" + File.separatorChar + pageName + ".yaml");
	}

	@SuppressWarnings("unchecked")
	public WebElement element(String elementName) {
		Map<String, String> elementDetails = (Map<String, String>) pageObjectsMap.get(elementName);
		WebElement element = null;
		switch (elementDetails.get("findBy")) {
		case "xpath":
			element = driver.findElement(By.xpath(elementDetails.get("locator")));
			break;
		case "id":
			element = driver.findElement(By.id(elementDetails.get("locator")));
			break;
		case "cssSelector":
			element = driver.findElement(By.cssSelector(elementDetails.get("locator")));
			break;
		case "className":
			element = driver.findElement(By.className(elementDetails.get("locator")));
			break;
		case "linkText":
			element = driver.findElement(By.linkText(elementDetails.get("locator")));
			break;
		case "name":
			element = driver.findElement(By.name(elementDetails.get("locator")));
			break;
		case "partialLinkText":
			element = driver.findElement(By.partialLinkText(elementDetails.get("locator")));
			break;
		case "tagName":
			element = driver.findElement(By.tagName(elementDetails.get("locator")));
			break;
		default:
			System.out.println("Used strategy have not been configured in the framework");
		}
		return element;
	}

	@SuppressWarnings("unchecked")
	public WebElement elementWithReplacementsInXpath(String elementName, Map<String, String> replacements) {
		Map<String, String> elementDetails = (Map<String, String>) pageObjectsMap.get(elementName);
		WebElement element = null;
		String locator = elementDetails.get("locator");
		Object[] replacementTexts = replacements.keySet().toArray();
		for (int i = 0; i < replacements.size(); i++) {
			locator = locator.replaceFirst("\\{\\{" + replacementTexts[i].toString() + "\\}\\}",
					replacements.get(replacementTexts[i]));
		}
		if (elementDetails.get("findBy").equals("xpath")) {
			element = driver.findElement(By.xpath(locator));
		}
		return element;
	}

	@SuppressWarnings("unchecked")
	public List<WebElement> elements(String elementName) {
		Map<String, String> elementDetails = (Map<String, String>) pageObjectsMap.get(elementName);
		List<WebElement> elements = null;
		switch (elementDetails.get("findBy")) {
		case "xpath":
			elements = driver.findElements(By.xpath(elementDetails.get("locator")));
			break;
		case "id":
			elements = driver.findElements(By.id(elementDetails.get("locator")));
			break;
		case "cssSelector":
			elements = driver.findElements(By.cssSelector(elementDetails.get("locator")));
			break;
		case "className":
			elements = driver.findElements(By.className(elementDetails.get("locator")));
			break;
		case "linkText":
			elements = driver.findElements(By.linkText(elementDetails.get("locator")));
			break;
		case "name":
			elements = driver.findElements(By.name(elementDetails.get("locator")));
			break;
		case "partialLinkText":
			elements = driver.findElements(By.partialLinkText(elementDetails.get("locator")));
			break;
		case "tagName":
			elements = driver.findElements(By.tagName(elementDetails.get("locator")));
			break;
		default:
			System.out.println("Used strategy have not been configured in the framework");
		}
		return elements;
	}
}
