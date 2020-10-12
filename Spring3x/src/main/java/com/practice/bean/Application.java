package com.practice.bean;

/**
 * This is main application bean which will declare and defined into metadata configuration file
 * @author manish.luste
 */
public class Application {

	private int appId;
	private String appName;
	
	public int getAppId() {
		return this.appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return this.appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	@Override
	public String toString() {
		return "Application [appId=" + this.appId + ", appName=" + this.appName + "]";
	}
	
	/**
	 * This custom method will be at the time of returning factory method
	 */
	public void displayApplicationData() {
		System.out.println("Application.displayApplicationData() : Application ID : "+ this.getAppId());
		System.out.println("Application.displayApplicationData() : Application Name : "+ this.getAppName());
	}
}
