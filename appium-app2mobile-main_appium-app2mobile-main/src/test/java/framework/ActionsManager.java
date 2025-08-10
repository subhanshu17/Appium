/**
 * 
 */
package framework;

import automation.tests.page_actions.*;

/**
 * 
 */
public class ActionsManager {
	private static ThreadLocal<LoginPage> loginPageThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<OtpPage> otpPageThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<ScannerPage> scannerPageThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<DashboardPage> dashboardPageThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<WatchesPage> watchesPageThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<ProfileSettingPage> profileSettingPageThreadLocal = new ThreadLocal<>();

	public static LoginPage getLoginPageAction() {
		return loginPageThreadLocal.get();
	}

	public static void setLoginPageAction(LoginPage loginPage) {
		loginPageThreadLocal.set(loginPage);
	}

	public static OtpPage getOtpPageAction() {
		return otpPageThreadLocal.get();
	}

	public static void setOtpPageAction(OtpPage otpPage) {
		otpPageThreadLocal.set(otpPage);
	}

	public static ScannerPage getScannerPageAction() {
		return scannerPageThreadLocal.get();
	}

	public static void setScannerPageAction(ScannerPage scannerPage) {
		scannerPageThreadLocal.set(scannerPage);
	}

	public static DashboardPage getDashboardPageAction() {
		return dashboardPageThreadLocal.get();
	}

	public static void setDashboardPageAction(DashboardPage dashboardPage) {
		dashboardPageThreadLocal.set(dashboardPage);
	}

	public static WatchesPage getWatchesPageAction() {
		return watchesPageThreadLocal.get();
	}

	public static void setWatchesPageAction(WatchesPage watchesPage) {
		watchesPageThreadLocal.set(watchesPage);
	}

	public static ProfileSettingPage getProfileSettingsPageAction() {
		return profileSettingPageThreadLocal.get();
	}

	public static void setProfileSettingsPageAction(ProfileSettingPage profileSettingPage) {
		profileSettingPageThreadLocal.set(profileSettingPage);
	}

}
