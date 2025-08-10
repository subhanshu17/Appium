/**
 * 
 */
package framework;

import java.time.Duration;
import java.util.*;
import org.openqa.selenium.NoSuchElementException;
import java.util.Map;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 */
public class Page {
	protected TestSession session = null;
	protected ElementFinder elementFinder = null;

	public Page(TestSession session, String pageObjectYamlFileName) {
		this.session = session;
		System.out.println("session value"+session.driver);
		elementFinder = new ElementFinder(session, pageObjectYamlFileName);
	}

	@SuppressWarnings("unchecked")
	public void launchUrl(String url) {
		System.out.println("Launch url Session --- " + session.driver);
		if (!url.startsWith("https://") || !!url.startsWith("http://")) {
			session.driver.get(((Map<String, Object>) session.applicationConfigs.get("applicationUrls"))
					.get(session.testConfigs.getTestEnvironment()).toString() + url);

		} else {
			session.driver.get(url);
		}
	}

	public void waitUntilElementIsClickable(int time, String xpath) {
		WebDriverWait wait = new WebDriverWait(session.driver, Duration.ofMillis(time));
		wait.until(ExpectedConditions.elementToBeClickable(elementFinder.element(xpath)));
	}

	public void waitUntilElementIsClickable(int time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(session.driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

//	public void waitUntilElementIsVisible(int time, String element) {
//		WebDriverWait wait = new WebDriverWait(session.driver, Duration.ofSeconds(time));
//		wait.until(ExpectedConditions.visibilityOf(elementFinder.element(element)));
////		scrollElementIntoView(elementFinder.element(element));
//	}

public void waitUntilElementIsVisible(int time, String element) {
	WebDriverWait wait = new WebDriverWait(session.driver, Duration.ofSeconds(time));
	wait.until(driver -> {
		WebElement el = elementFinder.element(element);
		return el != null && el.isDisplayed();
	});
}

	public void waitUntilElementIsInVisible(int time, String element) {
		WebDriverWait wait = new WebDriverWait(session.driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.invisibilityOf(elementFinder.element(element)));
	}

	public String getText(String xpath) {
		return elementFinder.element(xpath).getText();
	}

	public String getText(WebElement element) {
		return element.getText();
	}

	public String getCss(String xpath,String value) {
		return elementFinder.element(xpath).getCssValue(value);
	}

	public String getAttribute(String element,String value) {
		return elementFinder.element(element).getAttribute(value);
	}

	public String getAttribute(WebElement element,String value) {
		return element.getAttribute(value);
	}

	public String getCss(WebElement element,String value) {
		return element.getCssValue(value);
	}

	public void clickElement(String elementName) {
		//scrollElementIntoView(elementFinder.element(elementName));
		elementFinder.element(elementName).click();
	}

	public void clickElement(WebElement elementName) {
		elementName.click();
	}

	public void scroll(String direction, int pixel) {
		JavascriptExecutor js = (JavascriptExecutor) session.driver;
		switch (direction.toUpperCase()) {
		case "DOWN":
			js.executeScript("window.scrollBy(0, arguments[0])", pixel);
			break;
		case "UP":
			js.executeScript("window.scrollBy(0,arguments[0])", pixel * -1);
			break;
		default:
			System.out.println("Wrong direction is selected");
		}

	}

	public void clickElementUsingJs(String elementName) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) session.driver;
		jsExecutor.executeScript("arguments[0].click();", elementFinder.element(elementName));
	}

	public void clickElementUsingJs(WebElement elementName) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) session.driver;
		jsExecutor.executeScript("arguments[0].click();", elementName);
	}

	public String getJavascriptInnerText(String element) {
		JavascriptExecutor js = (JavascriptExecutor) session.driver;
		return (String) js.executeScript("return arguments[0].innerText;", elementFinder.element(element));
	}

	public String getJavascriptInnerText(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) session.driver;
		return (String) js.executeScript("return arguments[0].innerText;", element);
	}


	public void clickUsingActionClass(String elementName) {
		Actions action = new Actions(session.driver);
		action.moveToElement(elementFinder.element(elementName)).click();
	}

	public void scrollElementIntoView(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) session.driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		scroll("UP", 200);
	}

	public void scrollElementIntoMiddleOfPage(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) session.driver;
		// Get the element's top offset and height
		double elementTop = Double.parseDouble(
				jsExecutor.executeScript("return arguments[0].getBoundingClientRect().top", element).toString());
		double elementHeight = Double.parseDouble(
				jsExecutor.executeScript("return arguments[0].getBoundingClientRect().height", element).toString());
		// Get the viewport height
		double viewHeight = Double.parseDouble(jsExecutor
				.executeScript("return Math.max(document.documentElement.clientHeight, window.innerHeight || 0)")
				.toString());

		// Calculate the scroll position to center the element
		double scrollPosition = elementTop - (viewHeight / 2) + (elementHeight / 2);

		// Scroll to the calculated position
		jsExecutor.executeScript("window.scrollTo(0, arguments[0]);", scrollPosition);
	}

	protected void waitForElement(String elementName, int durationInSeconds) {
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < (durationInSeconds * 1000)) {
			// Timeout after given time in seconds
			try {
				elementFinder.element(elementName);
				Thread.sleep(10);
				// Element found, continue waiting
			} catch (NoSuchElementException e) {
				break;
				// Element not locatable, break the loop
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void clickOverlappedElement(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) session.driver;
		String scrollDirection = null;
		double elementTopCoordinate = Double.parseDouble(
				jsExecutor.executeScript("return arguments[0].getBoundingClientRect().top;", element).toString());
		double elementBottomCoordinate = Double.parseDouble(
				jsExecutor.executeScript("return arguments[0].getBoundingClientRect().bottom;", element).toString());
		double viewportHeight = Double.parseDouble(jsExecutor.executeScript("return window.innerHeight;").toString());
		if (elementTopCoordinate > viewportHeight) {
			scrollDirection = "DOWN";
		} else if (elementBottomCoordinate < 0) {
			scrollDirection = "UP";
		} else {
			scrollDirection = "UP";
		}
		while (true) {
			try {
				element.click();
				break;
			} catch (ElementClickInterceptedException e) {
				scroll(scrollDirection, 10);
			} catch (ElementNotInteractableException e) {
				scroll(scrollDirection, 10);
			}
		}
	}

	public void switchToSpecifiedTab(int tabNumber) {
		List<String> tabs = new ArrayList<>(session.driver.getWindowHandles());
		session.driver.switchTo().window(tabs.get(tabNumber));
	}

	public void closeSpecifiedTab(int tabNumber) {
		List<String> tabs = new ArrayList<>(session.driver.getWindowHandles());
		session.driver.switchTo().window(tabs.get(tabNumber));
		session.driver.close();
	}

	public boolean waitForNewWindow() {
		boolean flag = false;
		for (int i = 0; i < 10; i++) {
			Set<String> winId = session.driver.getWindowHandles();
			if (winId.size() > 1) {
				flag = true;
			}
		}
		return flag;
	}

	public void waitUntilPageIsFullyLoaded() {
		new WebDriverWait(session.driver, Duration.ofSeconds(30)).until(
				webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
	}

	public void refreshWebPage() {
		session.driver.navigate().refresh();
	}

	public void waitUntilElementIsVisible(int time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(session.driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(element));
	//	scrollElementIntoView(element);
	}

	public void waitUntilElementBecomesStale(int time,WebElement element) {
		WebDriverWait wait = new WebDriverWait(session.driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.stalenessOf(element));
	}

	public void waitUntilElementBecomesStale(int time,String element) {
		WebDriverWait wait = new WebDriverWait(session.driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.stalenessOf(elementFinder.element(element)));
	}

	public boolean isElementDisplayed(String element) {
		try {
			WebElement webElement = elementFinder.element(element);
			waitUntilElementIsVisible(30,webElement);
			return webElement.isDisplayed();
		} catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean isElementDisplayed(String element,int time) {
		try {
			waitUntilElementIsVisible(time,element);
			WebElement webElement = elementFinder.element(element);
			return webElement.isDisplayed();
		} catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean isElementDisplayed(WebElement element) {
		try {
			waitUntilElementIsVisible(30,element);
			return element.isDisplayed();
		} catch(Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	public boolean isElementDisplayed(WebElement element,int time) {
		try {
			waitUntilElementIsVisible(time,element);
			return element.isDisplayed();
		} catch(Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	public boolean isElementEnabled(String elementName) {
		return elementFinder.element(elementName).isEnabled();
	}

	public void scrollToElement(WebElement element) {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) session.driver;
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
}
