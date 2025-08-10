package automation.tests.regression;

import automation.tests.BaseTests;
import automation.tests.testdata_interfaces.ConnectToFastrackApp;
import framework.util.YamlUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static framework.ActionsManager.*;

public class FastrackSmartAutomation extends BaseTests implements ConnectToFastrackApp {

    String filePath = System.getProperty("user.dir")+"/src/test/resources/TestData/Mobile/connectToFastrack.yaml";
    final Map<String,Object> yamlManager = YamlUtil.readYAMLIntoMap(filePath);


    @Test (priority = 1)
    public void connectToFastTrackApp() throws InterruptedException {

        getLoginPageAction().skipSignup();
        getLoginPageAction().clickTermsAndConditionsRadioButton();
        getLoginPageAction().clickAcceptButton();
        getLoginPageAction().skipThisStep();
        getLoginPageAction().enterMobileNumber(String.valueOf(yamlManager.get("mobileNumber")));
        getLoginPageAction().registerWithSms();
        Assert.assertEquals(getOtpPageAction().getOtpPopupTitle(),otpPopupTitle,"Otp popup title is not matching");
        getOtpPageAction().clickAllow();
        getOtpPageAction().clickContinueButton();

        Assert.assertEquals(getScannerPageAction().getPermissionsPopupTitle(),permissionsPopupTitle,"Permissions popup title is not matching");
        getScannerPageAction().clickPermissionDropdown((String) yamlManager.get("cameraPermissionTitle"));
        getScannerPageAction().clickPermit();
        Assert.assertEquals(getScannerPageAction().getResourcePermissionPopupMessage(),cameraPermisssionPopupTitle,"Camera permission popup title is not matching");
        getScannerPageAction().clickAllow();
        Assert.assertTrue(getScannerPageAction().isPermittedMessageDisplayed(),"Permitted message is not displayed");
        getScannerPageAction().clickPermissionDropdown((String) yamlManager.get("cameraPermissionTitle"));
        getScannerPageAction().clickPermissionDropdown((String) yamlManager.get("notificationsPermissionTitle"));
        getScannerPageAction().clickPermit();
        Assert.assertEquals(getScannerPageAction().getResourcePermissionPopupMessage(),notificationsPermissionPopupTitle,"Notifications permission popup title is not matching");
        getScannerPageAction().clickAllow();
        Assert.assertTrue(getScannerPageAction().isPermittedMessageDisplayed(),"Permitted message is not displayed");
        getScannerPageAction().clickPermissionDropdown((String) yamlManager.get("notificationsPermissionTitle"));


        getScannerPageAction().clickPermissionDropdown((String) yamlManager.get("nearbyDevicesPermissionTitle"));
        getScannerPageAction().clickPermit();
        Assert.assertEquals(getScannerPageAction().getResourcePermissionPopupMessage(),nearbyDevicesPermissionPopupTitle,"Nearby devices permission popup title is not matching");
        getScannerPageAction().clickAllow();
//        getScannerPageAction().switchOnLocationToggle();
//        getScannerPageAction().clickBackButton();
        Assert.assertTrue(getScannerPageAction().isPermittedMessageDisplayed(),"Permitted message is not displayed");
        getScannerPageAction().clickPermissionDropdown((String) yamlManager.get("nearbyDevicesPermissionTitle"));

        getScannerPageAction().clickPermissionDropdown((String) yamlManager.get("bluetoothOffPermissionTitle"));
        getScannerPageAction().clickPermit();
        Assert.assertEquals(getScannerPageAction().getBluetoothPermissionPopupMessage(),bluetoothPermissionPopupTitle,"Bluetooth permission popup title is not matching");
        getScannerPageAction().clickAllow();

        getScannerPageAction().clickSearchManuallyButton();
       if(getScannerPageAction().isChooseYourWatchHeaderDisplayed()) {
           getScannerPageAction().selectDevice((String) yamlManager.get("deviceName"));
       }
       Assert.assertEquals(getScannerPageAction().getDeviceName(),(String) yamlManager.get("deviceName"),"Device is not matching");
        getScannerPageAction().clickContinue();
        getScannerPageAction().clickCancelButton();

       Assert.assertTrue(getScannerPageAction().isYourWatchUpToDateMessageDisplayed(),"Watch is not connected to mobile");
        getScannerPageAction().clickOnOkButton();
        Assert.assertTrue(getProfileSettingsPageAction().isHowDoWeCallYouDisplayed(),"How do we call you page is not displayed");
        getProfileSettingsPageAction().clickContinueButton();
        Assert.assertTrue(getProfileSettingsPageAction().isSelectYourGenderDisplayed(),"Select your gender page is not displayed");
        getProfileSettingsPageAction().clickContinueButton();
        Assert.assertTrue(getProfileSettingsPageAction().isBirthdayPromptDisplayed(),"When is your birthday page is not displayed");
        getProfileSettingsPageAction().clickContinueButton();
        Assert.assertTrue(getProfileSettingsPageAction().isHeightPromptDisplayed(),"How tall are you page is not displayed");
        getProfileSettingsPageAction().clickContinueButton();
        Assert.assertTrue(getProfileSettingsPageAction().isWeightPromptDisplayed(),"How much do you weigh page is not displayed");
        getProfileSettingsPageAction().clickContinueButton();
        Assert.assertTrue(getProfileSettingsPageAction().isSleepGoalPromptDisplayed(),"Sleep goal prompt page is not displayed");
        getProfileSettingsPageAction().clickContinueButton();
        Assert.assertTrue(getProfileSettingsPageAction().isMultisportGoalPromptDisplayed(),"Multisport goal prompt page is not displayed");
        getProfileSettingsPageAction().clickContinueButton();
        Assert.assertTrue(getProfileSettingsPageAction().isStepGoalPromptDisplayed(),"Step goal prompt page is not displayed");
        getProfileSettingsPageAction().clickContinueButton();
        getProfileSettingsPageAction().clickSkipButton();
        getProfileSettingsPageAction().closePermissionsPopup();




    }
}
