package com.cg.ibs.spmgmt.bean;

public class BankAdmin {
	private String adminID;			
	private String adminPassword;	
	
	public BankAdmin() {
		super();
	}
	public BankAdmin(String adminID, String adminPassword) {
		super();
		this.adminID = adminID;
		this.adminPassword = adminPassword;
	}
	public String getAdminID() {
		return adminID;
	}
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	@Override
	public String toString() {
		return "BankAdminBean [adminID=" + adminID + ", adminPassword=" + adminPassword + "]";
	}
	
}
