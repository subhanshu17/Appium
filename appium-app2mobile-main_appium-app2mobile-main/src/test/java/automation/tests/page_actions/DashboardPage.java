package automation.tests.page_actions;

import framework.ConfigReader;
import framework.Page;
import framework.TestSession;

public class DashboardPage extends Page {

    private int waitInSeconds = Integer.parseInt(String.valueOf(ConfigReader.getProjectConfigs().get("waitTimeInSeconds")));
    public DashboardPage(TestSession session) {
        super(session, "dashboardPage");
    }

    public void clickWatchesIcon() {
        waitUntilElementIsVisible(waitInSeconds, "watchesIcon");
        clickElement(elementFinder.element("watchesIcon"));//.click();
    }
}
