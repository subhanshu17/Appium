package automation.tests.page_actions;

import framework.ConfigReader;
import framework.Page;
import framework.TestSession;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WatchesPage extends Page {

    private int waitInSeconds = Integer.parseInt(String.valueOf(ConfigReader.getProjectConfigs().get("waitTimeInSeconds")));
    public WatchesPage(TestSession session) {
        super(session, "watchesPage");
    }

    public void selectWatch() {
        waitUntilElementIsVisible(waitInSeconds, "watchSelection");
        clickElement(elementFinder.element("watchSelection"));
        //elementFinder.element("watchSelection").click();
    }

    public void deleteWatch() {
        if(isElementDisplayed("deleteWatch",waitInSeconds)) {
            clickElement("deleteWatch");
        }
//        try{
//            waitUntilElementIsVisible(60,"deleteWatch");
//            elementFinder.element("deleteWatch").click();
//        }catch (Exception e) {
//
//        }
    }

    public int getWatchesList() {
        List<WebElement> elements = List.of();
        try{
            waitUntilElementIsVisible(waitInSeconds,"deleteWatches");
            elements =  elementFinder.elements("deleteWatches");
        }catch (Exception e) {

        }
        return elements.size();
    }

    public void clickInstallWatch() {
        waitUntilElementIsVisible(waitInSeconds, elementFinder.element("installWatch"));
        clickElement(elementFinder.element("installWatch"));
       // elementFinder.element("installWatch").click();
    }

    public void clickOnInstallWatch() {
        waitUntilElementIsVisible(waitInSeconds, elementFinder.element("watchInstallation"));
        clickElement(elementFinder.element("watchInstallation"));
       // elementFinder.element("watchInstallation").click();
    }
}
