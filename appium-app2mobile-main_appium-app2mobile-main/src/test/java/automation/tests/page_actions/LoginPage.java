package automation.tests.page_actions;

import framework.ConfigReader;
import framework.Page;
import framework.TestSession;
import org.openqa.selenium.*;

public class LoginPage extends Page {

    private int waitInSeconds = Integer.parseInt(String.valueOf(ConfigReader.getProjectConfigs().get("waitTimeInSeconds")));
    public LoginPage(TestSession session) {
        super(session, "loginPage");
    }

    public void clickTermsAndConditionsRadioButton() {
        waitUntilElementIsVisible(waitInSeconds, "termsAndConditionsRadioButton");
        clickElement(elementFinder.element("termsAndConditionsRadioButton"));//.click();
    }

    public void skipSignup() {
        waitUntilElementIsVisible(waitInSeconds, "skipToSignupButton");
        clickElement(elementFinder.element("skipToSignupButton"));
    }

    public void clickAcceptButton() {
        waitUntilElementIsVisible(waitInSeconds, "acceptButton");
        clickElement(elementFinder.element("acceptButton"));
        //elementFinder.element("acceptButton").click();
    }

    public void skipThisStep() {
        waitUntilElementIsVisible(waitInSeconds, "skipThisStepButton");
        clickElement(elementFinder.element("skipThisStepButton"));
       // elementFinder.element("skipThisStepButton").click();
    }

    public void enterMobileNumber(String mobileNumber) {
        waitUntilElementIsVisible(waitInSeconds,"mobileNumberInput");
        WebElement element = elementFinder.element("mobileNumberInput");
        element.sendKeys(mobileNumber);
    }

    public void registerWithSms() {
        waitUntilElementIsVisible(waitInSeconds, "registerWithSmsButton");
        clickElement(elementFinder.element("registerWithSmsButton"));
       // elementFinder.element("registerWithSmsButton").click();
    }
}
