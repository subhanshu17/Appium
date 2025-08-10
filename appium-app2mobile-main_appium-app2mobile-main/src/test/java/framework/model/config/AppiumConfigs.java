/**
 * 
 */
package framework.model.config;

/**
 * 
 */
public class AppiumConfigs {
	private String hostUrl;
	private String port;

	public String getHostUrl() {
		return hostUrl;
	}

	public String getPort() {
		return port;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "AppiumConfigs [hostUrl=" + hostUrl + ", port=" + port + "]";
	}

}
