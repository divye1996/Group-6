package com.cg.ibs.spmgmt.service;

import java.util.ArrayList;

import com.cg.ibs.spmgmt.bean.ServiceProvider;
import com.cg.ibs.spmgmt.exception.*;

public interface ServiceProviderService {

	ServiceProvider generateIdPassword(ServiceProvider serviceprovider) throws RegisterException, IBSException;

	boolean storeSPDetails(ServiceProvider sp) throws RegisterException, IBSException;

	boolean validateLogin(String username, String password) throws IBSException;

	ServiceProvider getServiceProvider(String userid) throws IBSException;

	ArrayList<ServiceProvider> showPending() throws IBSException;
	
	ArrayList<ServiceProvider> showHistory() throws IBSException;

	void approveSP(ServiceProvider sp, boolean decision) throws IBSException;

	boolean validateAdminLogin(String adminID, String adminPassword) throws IBSException;

	boolean emptyData() throws IBSException;
	
	

}