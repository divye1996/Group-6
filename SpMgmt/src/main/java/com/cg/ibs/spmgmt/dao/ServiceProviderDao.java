package com.cg.ibs.spmgmt.dao;

import java.util.ArrayList;
import java.util.List;

import com.cg.ibs.spmgmt.bean.BankAdmin;
import com.cg.ibs.spmgmt.bean.ServiceProvider;
import com.cg.ibs.spmgmt.exception.IBSException;
import com.cg.ibs.spmgmt.exception.RegisterException;

public interface ServiceProviderDao {
		ServiceProvider addServiceProvider(ServiceProvider serviceProvider) throws IBSException;
		ServiceProvider updateServiceProvider(ServiceProvider serviceProvider) throws IBSException;
		ServiceProvider getServiceProviderById(String userId) throws IBSException;
		ArrayList<ServiceProvider> getServiceProviders() throws IBSException;
		ArrayList<ServiceProvider> getPendingServiceProviders() throws IBSException;
		ArrayList<ServiceProvider> getApprovedServiceProviders() throws IBSException;
		ArrayList<ServiceProvider> getDisapprovedServiceProviders() throws IBSException;
		ArrayList<ServiceProvider> getApprovedDisapprovedServiceProviders() throws IBSException;
		BankAdmin getBankAdmin(String bankId) throws IBSException;
}
//boolean storeServiceProviderData(ServiceProvider serviceProvider) throws RegisterException;
//
//ArrayList<ServiceProvider> fetchPendingSp();
//
//boolean checkLogin(String username, String password) throws IBSException;
//
//void approveStatus(ServiceProvider serviceProvider) throws IBSException;
//
//boolean checkUserID(String userId);
//
//ArrayList<ServiceProvider> fetchApprovedSp() ;
//
//ArrayList<ServiceProvider> fetchHistory();
//
//boolean checkAdminLogin(String adminID, String adminPassword) throws IBSException;
//
//ServiceProvider getServiceProvider(String uid) throws IBSException;
//
//boolean emptyData();