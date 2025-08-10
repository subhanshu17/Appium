package framework;

public class SessionManager {
	private static ThreadLocal<TestSession> sessionThreadLocal = new ThreadLocal<TestSession>();

	public static TestSession getSession() {
		return sessionThreadLocal.get();
	}

	public static void setSession(TestSession session) {
		sessionThreadLocal.set(session);
	}

	public static void removeDriver() {
		sessionThreadLocal.remove();
	}

}
