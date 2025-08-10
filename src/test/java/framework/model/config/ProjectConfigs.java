/**
 * 
 */
package framework.model.config;

/**
 * 
 */
public class ProjectConfigs {
	private String pageObjectsRootPath;
	private String reportPath;
	private String waitTimeInSeconds;

	public String getPageObjectsRootPath() {
		return pageObjectsRootPath;
	}

	public void setPageObjectsRootPath(String pageObjectsRootPath) {
		this.pageObjectsRootPath = pageObjectsRootPath;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	public int getWaitTimeInSeconds() {
		return Integer.parseInt(reportPath);
	}

	public void setWaitTimeInSeconds(String waitTimeInSeconds) {
		this.waitTimeInSeconds = waitTimeInSeconds;
	}

	@Override
	public String toString() {
		return "ProjectConfigs [pageObjectsRootPath=" + pageObjectsRootPath + ", reportPath=" + reportPath + "]";
	}
}
