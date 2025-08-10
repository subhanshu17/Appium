package automation.tests.page_actions;

import framework.ConfigReader;
import framework.Page;
import framework.TestSession;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ScannerPage extends Page {

    private int waitInSeconds = Integer.parseInt(String.valueOf(ConfigReader.getProjectConfigs().get("waitTimeInSeconds")));
    public ScannerPage(TestSession session) {
        super(session, "scannerPage");
    }

    public String getPermissionsPopupTitle() {
        waitUntilElementIsVisible(waitInSeconds, "permissionsPopupTitle");
        return getText(elementFinder.element("permissionsPopupTitle"));
       // return elementFinder.element("permissionsPopupTitle").getText();
    }

    public void clickAllow() {
        waitUntilElementIsVisible(waitInSeconds, "allowButton");
        clickElement(elementFinder.element("allowButton"));
        //elementFinder.element("allowButton").click();
    }

    public void clickPermissionDropdown(String resourceType) {
        HashMap<String, String> replacementTexts = new HashMap<String, String>();
        replacementTexts.put("resourceType", resourceType);
        WebElement element = elementFinder.elementWithReplacementsInXpath("permissionDropdown", replacementTexts);
        waitUntilElementIsVisible(waitInSeconds,element);
        clickElement(element);
        //element.click();
    }

    public void clickPermit() {
        waitUntilElementIsVisible(waitInSeconds, "permitButton");
        clickElement(elementFinder.element("permitButton"));
        //elementFinder.element("permitButton").click();
    }

    public String getResourcePermissionPopupMessage() {
        waitUntilElementIsVisible(160, "resourcePermissionPopupMessage");
        return getText(elementFinder.element("resourcePermissionPopupMessage"));
      //  return elementFinder.element("resourcePermissionPopupMessage").getText();
    }

    public boolean isPermittedMessageDisplayed() {
       // waitUntilElementIsVisible(120, "permittedMessage");
        return isElementDisplayed("permittedMessage",waitInSeconds);
       // return elementFinder.element("permittedMessage").isDisplayed();
    }

    public void clickAllowOnlyUsingTheApp() {
        waitUntilElementIsVisible(waitInSeconds, "allowOnlyUsingAppButton");
        clickElement(elementFinder.element("allowOnlyUsingAppButton"));
       // elementFinder.element("allowOnlyUsingAppButton").click();
    }

    public void switchOnLocationToggle() {
        waitUntilElementIsVisible(waitInSeconds, "locationToggle");
        clickElement(elementFinder.element("locationToggle"));
       // elementFinder.element("locationToggle").click();
    }

    public void clickBackButton() {
        waitUntilElementIsVisible(waitInSeconds, "backButton");
        clickElement(elementFinder.element("backButton"));
       // elementFinder.element("backButton").click();
    }

    public String getBluetoothPermissionPopupMessage() {
        waitUntilElementIsVisible(waitInSeconds, "bluetoothPermissionMessage");
        return getText(elementFinder.element("bluetoothPermissionMessage"));
     //   return elementFinder.element("bluetoothPermissionMessage").getText();
    }

    public void clickSearchManuallyButton() {
        waitUntilElementIsVisible(waitInSeconds, "searchManuallyButton");
        clickElement(elementFinder.element("searchManuallyButton"));
        //elementFinder.element("searchManuallyButton").click();
    }

    public boolean isChooseYourWatchHeaderDisplayed() {
       // return isElementDisplayed(elementFinder.element("chooseYourWatchHeader"),waitInSeconds);
        boolean isDisplayed;
        try {
            waitUntilElementIsVisible(waitInSeconds, "chooseYourWatchHeader");
            isDisplayed = elementFinder.element("chooseYourWatchHeader").isDisplayed();
        }catch (Exception e) {
            isDisplayed = false;
        }
        return isDisplayed;
    }

    public String getDeviceName() throws InterruptedException {
        Thread.sleep(4000);
        waitUntilElementIsVisible(160, "deviceName");
        return getText(elementFinder.element("deviceName"));
       // return elementFinder.element("deviceName").getText();
    }

    public void selectDevice(String deviceName) throws InterruptedException {
        List<WebElement> elements = elementFinder.elements("dotIndicators");
        for(int i=0; i < elements.size();i++) {
            if(getDeviceName().equalsIgnoreCase(deviceName)) {
                break;
            }else {
                swipeDevice();
            }
        }
    }


    public void swipeDevice() {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 800, 1000));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 100, 1000));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        session.driver.perform(Arrays.asList(swipe));
    }

    public void clickContinue() {
        waitUntilElementIsVisible(waitInSeconds, "continueButton");
        clickElement(elementFinder.element("continueButton"));
       // elementFinder.element("continueButton").click();
    }

    public void clickCancelButton() {
        if(isElementDisplayed("cancelButton",waitInSeconds)) {
            clickElement("cancelButton");
        }
    }


    public boolean isYourWatchUpToDateMessageDisplayed() {
       // waitUntilElementIsVisible(60, "yourWatchIsUpToDate");
        return isElementDisplayed("yourWatchIsUpToDate",waitInSeconds);
    }

    public void clickOnOkButton() {
        waitUntilElementIsVisible(waitInSeconds, elementFinder.element("okButton"));
        clickElement(elementFinder.element("okButton"));
        //elementFinder.element("okButton").click();
    }
}
