package automation.tests.page_actions;

import framework.ConfigReader;
import framework.Page;
import framework.TestSession;
import org.openqa.selenium.WebElement;

public class ProfileSettingPage extends Page {

    private int waitInSeconds = Integer.parseInt(String.valueOf(ConfigReader.getProjectConfigs().get("waitTimeInSeconds")));
    public ProfileSettingPage(TestSession session) {
        super(session, "profileSettingPage");
    }

    public boolean isHowDoWeCallYouDisplayed() {
        //WebElement element = elementFinder.element("howDoWeCallYou");
        return isElementDisplayed("howDoWeCallYou", waitInSeconds);
    }

    public void clickContinueButton() {
        WebElement element = elementFinder.element("continueButton");
        waitUntilElementIsVisible(waitInSeconds,element);
        clickElement(element);
    }

    public boolean isSelectYourGenderDisplayed() {
        //WebElement element = elementFinder.element("selectYourGender");
        return isElementDisplayed("selectYourGender", waitInSeconds);
    }

    public boolean isBirthdayPromptDisplayed() {
       // WebElement element = elementFinder.element("birthdayPrompt");
        return isElementDisplayed("birthdayPrompt", waitInSeconds);
    }

    public boolean isHeightPromptDisplayed() {
        //WebElement element = elementFinder.element("heightPrompt");
        return isElementDisplayed("heightPrompt", waitInSeconds);
    }

    public boolean isWeightPromptDisplayed() {
       // WebElement element = elementFinder.element("weightPrompt");
        return isElementDisplayed("weightPrompt", waitInSeconds);
    }

    public boolean isSleepGoalPromptDisplayed() {
        //WebElement element = elementFinder.element("sleepGoalPrompt");
        return isElementDisplayed("sleepGoalPrompt", waitInSeconds);
    }

    public boolean isMultisportGoalPromptDisplayed() {
        //WebElement element = elementFinder.element("multisportGoalPrompt");
        return isElementDisplayed("multisportGoalPrompt", waitInSeconds);
    }

    public boolean isStepGoalPromptDisplayed() {
       // WebElement element = elementFinder.element("stepGoalPrompt");
        return isElementDisplayed("stepGoalPrompt", waitInSeconds);
    }

    public void clickSkipButton() {
     //   WebElement element = elementFinder.element("skipButton");
        waitUntilElementIsVisible(waitInSeconds,"skipButton");
        clickElement("skipButton");
    }

    public void closePermissionsPopup() {
        //WebElement element = elementFinder.element("closeIcon");
        waitUntilElementIsVisible(waitInSeconds,"closeIcon");
        clickElement("closeIcon");
    }

}
