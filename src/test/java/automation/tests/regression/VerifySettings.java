package automation.tests.regression;

import automation.tests.BaseTests;
import framework.util.YamlUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static framework.ActionsManager.*;

public class VerifySettings extends BaseTests {

    String filePath = System.getProperty("user.dir")+"/src/test/resources/TestData/Mobile/installWatchInterface.yaml";
    final Map<String,Object> yamlManager = YamlUtil.readYAMLIntoMap(filePath);

    @Test(priority = 1)
    public void installWatchInterfaceThroughApp() throws InterruptedException {

        getDashboardPageAction().clickWatchesIcon();
        getWatchesPageAction().selectWatch();
        getWatchesPageAction().deleteWatch();
        Thread.sleep(1500);
        getWatchesPageAction().clickInstallWatch();
        getWatchesPageAction().clickOnInstallWatch();
        Thread.sleep(4000);
        getWatchesPageAction().selectWatch();
        Assert.assertEquals(getWatchesPageAction().getWatchesList(),Integer.parseInt(String.valueOf(yamlManager.get("watchesList"))),"Watches list is not displayed");
    }
}
