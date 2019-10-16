package com.cg.ibs.spmgmt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.cg.ibs.spmgmt.bean.BankAdmin;
import com.cg.ibs.spmgmt.bean.ServiceProvider;
import com.cg.ibs.spmgmt.dao.ServiceProviderDao;
import com.cg.ibs.spmgmt.dao.ServiceProviderDaoImpl;
import com.cg.ibs.spmgmt.exception.IBSException;
import com.cg.ibs.spmgmt.exception.*;

public class ServiceProviderServiceImpl implements ServiceProviderService, IBSPortal {
	ServiceProviderDao serviceProviderDao = new ServiceProviderDaoImpl();
	ServiceProvider serviceProvider = new ServiceProvider();
	static BigInteger spi = new BigInteger("1000000");

	// Function to generate User ID and Password of Service Provider
	@Override
	public ServiceProvider generateIdPassword(ServiceProvider serviceprovider) throws RegisterException, IBSException {
		String temp = serviceprovider.getNameOfCompany();
		int length = temp.length();
		String userId;
		boolean b = false;
		int count = 5; // make it unique
		do {
			userId = temp.substring(0, count);
			b = serviceProviderDao.getServiceProviderById(userId)==null;
			count++;
		} while (b != true && count < length);

		String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz"
				+ "!@#$%^&*_=+-/.?<>)";
		StringBuilder password = new StringBuilder(8);

		for (int i = 0; i < 8; i++) {
			int index = (int) (passwordSet.length() * Math.random());
			password.append(passwordSet.charAt(index));
		}

		String result = password.toString();
		serviceprovider.setUserId(userId);
		serviceprovider.setPassword(result);
		return serviceprovider;
	}

	// Function to store details of Service Provider
	@Override
	public boolean storeSPDetails(ServiceProvider sp) throws RegisterException, IBSException {
		boolean result = false;
		sp.setRequestDate(Timestamp.valueOf(LocalDateTime.now()));
		ServiceProvider storeResult = serviceProviderDao.addServiceProvider(sp); // used to store the SP details after registration																			
		if (storeResult!=null)
			result = true;
		return result;
	}

	// Function to validate Login of Service Provider
	@Override
	public boolean validateLogin(String username, String password) throws IBSException {
		boolean result = false;
		ServiceProvider serviceProvider = serviceProviderDao.getServiceProviderById(username);
		if(serviceProvider!=null) {
			result=(password.equals(serviceProvider.getPassword()));
		}
		return result;
	}

	// Function to get the details of Service Provider from DAO Layer where the
	// details are stored
	@Override
	public ServiceProvider getServiceProvider(String userid) throws IBSException {
		serviceProvider = serviceProviderDao.getServiceProviderById(userid);
		return serviceProvider;
	}

	// Function to show all the Pending Request of Service Providers to the Bank
	// Administrative
	@Override
	public ArrayList<ServiceProvider> showPending() throws IBSException {
		ArrayList<ServiceProvider> pendingList = serviceProviderDao.getPendingServiceProviders();
		return pendingList;
	}

	// Function to approve or disapprove the request of Service Provider by the Bank
	// Administrative
	@Override
	public void approveSP(ServiceProvider sp, boolean decision) throws IBSException {
		if (decision == true) {
			sp.setStatus("Approved");
			generateSpi(sp);
			sp.setSpi(spi);
		} else if (decision == false) {
			sp.setStatus("Disapproved");
		}
		serviceProviderDao.updateServiceProvider(sp);
	}

	// Function to get the details of all the Approved Service Providers to be used
	// by Remittance Management

	// Function to generate Unique SPI ID after the Service Provider gets approved
	private BigInteger generateSpi(ServiceProvider sp) {
		spi = spi.add(BigInteger.valueOf(1));
		return spi;
	}

	// Function to validate the login of bank Administrative
	@Override
	public boolean validateAdminLogin(String adminID, String adminPassword) throws IBSException {
		boolean validity=false;
		BankAdmin admin=null;
		admin=serviceProviderDao.getBankAdmin(adminID);
		if(admin!=null) {
			validity=(adminPassword.equals(admin.getAdminPassword()));
		}
		return validity;
	}

	// Default empty data function
	@Override
	public boolean emptyData() throws IBSException {
		return (serviceProviderDao.getServiceProviders()==null);
	}

	@Override
	public ArrayList<ServiceProvider> getApprovedDetails() throws IBSException {
		ArrayList<ServiceProvider> approvedList = serviceProviderDao.getApprovedServiceProviders();
		return approvedList;
	}

	@Override
	public ArrayList<ServiceProvider> showHistory() throws IBSException {
		ArrayList<ServiceProvider> historyList = serviceProviderDao.getApprovedDisapprovedServiceProviders();
		Collections.sort(historyList, (serviceProvider1, serviceProvider2) -> serviceProvider1.getRequestDate()
				.compareTo(serviceProvider2.getRequestDate()));
		return historyList;
	}
}
