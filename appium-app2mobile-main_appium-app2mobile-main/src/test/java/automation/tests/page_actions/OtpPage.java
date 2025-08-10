package automation.tests.page_actions;

import framework.ConfigReader;
import framework.Page;
import framework.TestSession;

public class OtpPage extends Page {

    private int waitInSeconds = Integer.parseInt(String.valueOf(ConfigReader.getProjectConfigs().get("waitTimeInSeconds")));
    public OtpPage(TestSession session) {
        super(session, "otpPage");
    }

    public String getOtpPopupTitle() {
        waitUntilElementIsVisible(waitInSeconds, "otpPopupTitle");
        return getText(elementFinder.element("otpPopupTitle"));
        //return elementFinder.element("otpPopupTitle").getText();
    }

    public void clickAllow() {
        waitUntilElementIsVisible(waitInSeconds, "allowButton");
        clickElement(elementFinder.element("allowButton"));
        //elementFinder.element("allowButton").click();
    }

    public void clickContinueButton() {
        waitUntilElementIsVisible(waitInSeconds, "otpContinueButton");
        clickElement(elementFinder.element("otpContinueButton"));
        //elementFinder.element("otpContinueButton").click();
    }
}
