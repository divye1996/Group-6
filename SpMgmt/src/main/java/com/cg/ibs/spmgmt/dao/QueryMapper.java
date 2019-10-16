package com.cg.ibs.spmgmt.dao;

public interface QueryMapper {

	public static final String SEL_ALL_SERVICE_PROVIDERS="SELECT * FROM Service_Providers";
	public static final String SEL_ALL_SERVICE_PROVIDERS_BY_USER_ID="SELECT * FROM Service_Providers WHERE User_ID=?";
	public static final String INS_SERVICE_PROVIDER="INSERT INTO Service_Providers(User_ID, Company_Name, Company_Address, Gst_in, PAN, Mobile_Number, Bank_Name, Account_Number, Password, Status, SPI, Request_Date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String UPD_SERVICE_PROVIDERS="UPDATE Service_Providers SET Status=? WHERE USER_ID=?";
	public static final String DEL_SERVICE_PROVIDERS="DELETE FROM Service_Providers WHERE USER_ID=?";
	public static final String SEL_ALL_PENDING_SERVICE_PROVIDERS="SELECT * FROM Service_Providers WHERE status='Pending'";
	public static final String SEL_ALL_APPROVED_SERVICE_PROVIDERS="SELECT * FROM Service_Providers WHERE status='Approved'";
	public static final String SEL_ALL_DISAPPROVED_SERVICE_PROVIDERS="SELECT * FROM Service_Providers WHERE status='Disapproved'";
	public static final String SEL_ALL_APPROVED_DISAPPROVED_SERVICE_PROVIDERS="SELECT * FROM Service_Providers WHERE status='Approved' or status='Disapproved";
	public static final String BANK_ADMIN="SELECT * FROM Bank_Admins";
}

