package com.cg.ibs.spmgmt.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.ibs.spmgmt.bean.BankAdmin;
import com.cg.ibs.spmgmt.bean.ServiceProvider;
import com.cg.ibs.spmgmt.exception.IBSException;
import com.cg.ibs.spmgmt.exception.IBSExceptionInterface;
import com.cg.ibs.spmgmt.exception.RegisterException;
import com.cg.ibs.spmgmt.util.ConnectionProvider;

public class ServiceProviderDaoImpl implements ServiceProviderDao {

	// ServiceProvider DataBase

	// Bank Administrative Database
	BankAdmin admin1 = new BankAdmin("id1", "pass1");
	BankAdmin admin2 = new BankAdmin("id2", "pass2");
	@Override
	public ServiceProvider addServiceProvider(ServiceProvider serviceProvider) throws IBSException {
		try (Connection con = ConnectionProvider.getInstance().getConnection();
				PreparedStatement pst = con.prepareStatement(QueryMapper.INS_SERVICE_PROVIDER)) {

			pst.setString(1, serviceProvider.getUserId());
			pst.setString(2, serviceProvider.getNameOfCompany());
			pst.setString(3, serviceProvider.getCompanyAddress());
			pst.setString(4, serviceProvider.getGstin());
			pst.setString(5, serviceProvider.getPanNumber());
			pst.setBigDecimal(6, new BigDecimal(serviceProvider.getMobileNumber()));
			pst.setString(7, serviceProvider.getBankName());
			pst.setBigDecimal(8, new BigDecimal(serviceProvider.getAccountNumber()));
			pst.setString(9, serviceProvider.getPassword());
			pst.setString(10, serviceProvider.getStatus());
			pst.setBigDecimal(11, new BigDecimal(serviceProvider.getSpi()));
			pst.setTimestamp(12, serviceProvider.getRequestDate());
			pst.executeUpdate();
		} catch (SQLException | IOException e) {
			// logger to log e
			throw new IBSException("Adding Service Provider  to Database Failed!");
		}
		return serviceProvider;
	}

	@Override
	public ServiceProvider updateServiceProvider(ServiceProvider serviceProvider) throws IBSException {
		try (Connection con = ConnectionProvider.getInstance().getConnection();
				PreparedStatement pst = con.prepareStatement(QueryMapper.UPD_SERVICE_PROVIDERS)) {
			pst.setString(10, serviceProvider.getStatus());
			pst.executeUpdate();
		} catch (SQLException | IOException e) {
			// logger to log e
			throw new IBSException("Updating Service Provider in Database Failed!");
		}
		return serviceProvider;
	}

	@Override
	public ServiceProvider getServiceProviderById(String userId) throws IBSException {
		ServiceProvider serviceProvider = null;
		try (Connection con = ConnectionProvider.getInstance().getConnection();
				PreparedStatement pst = con.prepareStatement(QueryMapper.SEL_ALL_SERVICE_PROVIDERS_BY_USER_ID)) {
			pst.setString(1, userId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				serviceProvider = new ServiceProvider();
				serviceProvider.setUserId(rs.getString(1));
				serviceProvider.setNameOfCompany(rs.getString(2));
				serviceProvider.setCompanyAddress(rs.getString(3));
				serviceProvider.setGstin(rs.getString(4));
				serviceProvider.setPanNumber(rs.getString(5));
				serviceProvider.setMobileNumber((rs.getBigDecimal(6).toBigInteger()));
				serviceProvider.setBankName(rs.getString(7));
				serviceProvider.setPassword(rs.getString(8));
				serviceProvider.setAccountNumber((rs.getBigDecimal(9).toBigInteger()));
				serviceProvider.setStatus(rs.getString(10));
				serviceProvider.setSpi((rs.getBigDecimal(11).toBigInteger()));
				serviceProvider.setRequestDate(rs.getTimestamp(12));
			}
		} catch (SQLException | IOException e) {
			// logger to log e
			throw new IBSException("Getting Service Provider from Database Failed!");
		}
		return serviceProvider;
	}

	@Override
	public ArrayList<ServiceProvider> getServiceProviders() throws IBSException {
		ArrayList<ServiceProvider> serviceProviders = null;
		ServiceProvider serviceProvider = null;
		try (Connection con = ConnectionProvider.getInstance().getConnection();
				PreparedStatement pst = con.prepareStatement(QueryMapper.SEL_ALL_SERVICE_PROVIDERS)) {
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				serviceProvider = new ServiceProvider();
				serviceProvider.setUserId(rs.getString(1));
				serviceProvider.setNameOfCompany(rs.getString(2));
				serviceProvider.setCompanyAddress(rs.getString(3));
				serviceProvider.setGstin(rs.getString(4));
				serviceProvider.setPanNumber(rs.getString(5));
				serviceProvider.setMobileNumber((rs.getBigDecimal(6).toBigInteger()));
				serviceProvider.setBankName(rs.getString(7));
				serviceProvider.setPassword(rs.getString(8));
				serviceProvider.setAccountNumber((rs.getBigDecimal(9).toBigInteger()));
				serviceProvider.setStatus(rs.getString(10));
				serviceProvider.setSpi((rs.getBigDecimal(11).toBigInteger()));
				serviceProvider.setRequestDate(rs.getTimestamp(12));
				serviceProviders.add(serviceProvider);
			}
		} catch (SQLException | IOException e) {
			// logger to log e
			throw new IBSException("Getting All Service Providers from Database Failed!");
		}
		return serviceProviders;
	}

	@Override
	public ArrayList<ServiceProvider> getPendingServiceProviders() throws IBSException {
		ArrayList<ServiceProvider> serviceProviders = null;
		ServiceProvider serviceProvider = null;
		try (Connection con = ConnectionProvider.getInstance().getConnection();
				PreparedStatement pst = con.prepareStatement(QueryMapper.SEL_ALL_PENDING_SERVICE_PROVIDERS)) {
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				serviceProvider = new ServiceProvider();
				serviceProvider.setUserId(rs.getString(1));
				serviceProvider.setNameOfCompany(rs.getString(2));
				serviceProvider.setCompanyAddress(rs.getString(3));
				serviceProvider.setGstin(rs.getString(4));
				serviceProvider.setPanNumber(rs.getString(5));
				serviceProvider.setMobileNumber((rs.getBigDecimal(6).toBigInteger()));
				serviceProvider.setBankName(rs.getString(7));
				serviceProvider.setPassword(rs.getString(8));
				serviceProvider.setAccountNumber((rs.getBigDecimal(9).toBigInteger()));
				serviceProvider.setStatus(rs.getString(10));
				serviceProvider.setSpi((rs.getBigDecimal(11).toBigInteger()));
				serviceProvider.setRequestDate(rs.getTimestamp(12));
				serviceProviders.add(serviceProvider);
			}
		} catch (SQLException | IOException e) {
			// logger to log e
			throw new IBSException("Get Pending list from Database Failed!");
		}
		return serviceProviders;
	}

	@Override
	public ArrayList<ServiceProvider> getApprovedServiceProviders() throws IBSException {
		ArrayList<ServiceProvider> serviceProviders = null;
		ServiceProvider serviceProvider = null;
		try (Connection con = ConnectionProvider.getInstance().getConnection();
				PreparedStatement pst = con.prepareStatement(QueryMapper.SEL_ALL_APPROVED_SERVICE_PROVIDERS)) {
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				serviceProvider = new ServiceProvider();
				serviceProvider.setUserId(rs.getString(1));
				serviceProvider.setNameOfCompany(rs.getString(2));
				serviceProvider.setCompanyAddress(rs.getString(3));
				serviceProvider.setGstin(rs.getString(4));
				serviceProvider.setPanNumber(rs.getString(5));
				serviceProvider.setMobileNumber((rs.getBigDecimal(6).toBigInteger()));
				serviceProvider.setBankName(rs.getString(7));
				serviceProvider.setPassword(rs.getString(8));
				serviceProvider.setAccountNumber((rs.getBigDecimal(9).toBigInteger()));
				serviceProvider.setStatus(rs.getString(10));
				serviceProvider.setSpi((rs.getBigDecimal(11).toBigInteger()));
				serviceProvider.setRequestDate(rs.getTimestamp(12));
				serviceProviders.add(serviceProvider);
			}
		} catch (SQLException | IOException e) {
			// logger to log e
			throw new IBSException("Get Approved List from Databse Failed!");
		}
		return serviceProviders;
	}

	@Override
	public ArrayList<ServiceProvider> getDisapprovedServiceProviders() throws IBSException {
		ArrayList<ServiceProvider> serviceProviders = null;
		ServiceProvider serviceProvider = null;
		try (Connection con = ConnectionProvider.getInstance().getConnection();
				PreparedStatement pst = con.prepareStatement(QueryMapper.SEL_ALL_DISAPPROVED_SERVICE_PROVIDERS)) {
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				serviceProvider = new ServiceProvider();
				serviceProvider.setUserId(rs.getString(1));
				serviceProvider.setNameOfCompany(rs.getString(2));
				serviceProvider.setCompanyAddress(rs.getString(3));
				serviceProvider.setGstin(rs.getString(4));
				serviceProvider.setPanNumber(rs.getString(5));
				serviceProvider.setMobileNumber((rs.getBigDecimal(6).toBigInteger()));
				serviceProvider.setBankName(rs.getString(7));
				serviceProvider.setPassword(rs.getString(8));
				serviceProvider.setAccountNumber((rs.getBigDecimal(9).toBigInteger()));
				serviceProvider.setStatus(rs.getString(10));
				serviceProvider.setSpi((rs.getBigDecimal(11).toBigInteger()));
				serviceProvider.setRequestDate(rs.getTimestamp(12));
				serviceProviders.add(serviceProvider);
			}
		} catch (SQLException | IOException e) {
			// logger to log e
			throw new IBSException("Get Disapproved List from Database Failed!");
		}
		return serviceProviders;
	}

	@Override
	public ArrayList<ServiceProvider> getApprovedDisapprovedServiceProviders() throws IBSException {
		ArrayList<ServiceProvider> serviceProviders = null;
		ServiceProvider serviceProvider = null;
		try (Connection con = ConnectionProvider.getInstance().getConnection();
				PreparedStatement pst = con
						.prepareStatement(QueryMapper.SEL_ALL_APPROVED_DISAPPROVED_SERVICE_PROVIDERS)) {
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				serviceProvider = new ServiceProvider();
				serviceProvider.setUserId(rs.getString(1));
				serviceProvider.setNameOfCompany(rs.getString(2));
				serviceProvider.setCompanyAddress(rs.getString(3));
				serviceProvider.setGstin(rs.getString(4));
				serviceProvider.setPanNumber(rs.getString(5));
				serviceProvider.setMobileNumber((rs.getBigDecimal(6).toBigInteger()));
				serviceProvider.setBankName(rs.getString(7));
				serviceProvider.setPassword(rs.getString(8));
				serviceProvider.setAccountNumber((rs.getBigDecimal(9).toBigInteger()));
				serviceProvider.setStatus(rs.getString(10));
				serviceProvider.setSpi((rs.getBigDecimal(11).toBigInteger()));
				serviceProvider.setRequestDate(rs.getTimestamp(12));
				serviceProviders.add(serviceProvider);
			}
		} catch (SQLException | IOException e) {
			// logger to log e
			throw new IBSException("Get Approved/Disapproved from Database Failed!");
		}
		return serviceProviders;
	}

	@Override
	public BankAdmin getBankAdmin(String bankId) throws IBSException {
		BankAdmin admin = null;
		try (Connection con = ConnectionProvider.getInstance().getConnection();
				PreparedStatement pst = con.prepareStatement(QueryMapper.BANK_ADMIN)) {
			pst.setString(1, bankId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				BankAdmin admin2 = new BankAdmin();
				admin.setAdminID(rs.getString(1));
				admin.setAdminPassword(rs.getString(2));
			}
		} catch (SQLException | IOException e) {
			// logger to log e
			throw new IBSException("Getting Service Provider from Database Failed!");
		}
		return admin;
	}
	// @Override
	// public boolean delServiceProvider(String userId) throws IBSException {
	// try(
	// Connection con = ConnectionProvider.getInstance().getConnection();
	// PreparedStatement pst =
	// con.prepareStatement(QueryMapper.UPD_SERVICE_PROVIDERS)){
	// pst.setString(10, serviceProvider.getStatus());
	// pst.executeUpdate();
	// } catch (SQLException | IOException e) {
	// //logger to log e
	// throw new IBSException("Sve Operation Failed!");
	// }
	// return true;
	// }
	// DataBase Initialization
	// private static Map<String, ServiceProvider> spMap = new HashMap<>();
	// private static Map<String, BankAdmin> bankMap = new HashMap<>();
	// private static ArrayList<ServiceProvider> pendingList = new ArrayList<>();
	// private static ArrayList<ServiceProvider> approvedList = new
	// ArrayList<ServiceProvider>();
	// private static ArrayList<ServiceProvider> historyList = new
	// ArrayList<ServiceProvider>();
	//
	//
	// public static Map<String, ServiceProvider> getSpMap() {
	// return spMap;
	// }
	//
	// static{
	// ServiceProvider sp1 = new ServiceProvider("Reliance", "35AABCD1429B1ZX",
	// "LUUPSC8510",
	// new BigInteger("3124129001111"), "HDFC", "Pune Maharashtra", new
	// BigInteger("8552072230"), "Relia",
	// "12345678", new BigInteger("1000001"), "Approved", LocalDateTime.of(2019, 10,
	// 11, 14, 10, 15));
	// spMap.put("Relia", sp1);
	//
	// ServiceProvider sp2 = new ServiceProvider("Airtel", "45CVBCD1429K8FG",
	// "LUUPDF1234",
	// new BigInteger("3124129001234"), "SBI", "Mumbai Maharashtra", new
	// BigInteger("8500285458"), "Airte",
	// "87654321", null, "Pending", LocalDateTime.of(2019, 10, 11, 14, 10, 30));
	// spMap.put("Airte", sp2);
	//// pendingList.add(sp2);
	// }
	// {
	// bankMap.put(admin1.getAdminID(), admin1);
	// bankMap.put(admin2.getAdminID(), admin2);
	// }
	//
	// // Storing Service Provider Details in HashMap
	// @Override
	// public boolean storeServiceProviderData(ServiceProvider serviceProvider)
	// throws RegisterException {
	// if (serviceProvider != null &&
	// !(spMap.containsKey(serviceProvider.getUserId()))) {
	// spMap.put(serviceProvider.getUserId(), serviceProvider);
	// return true;
	// } else {
	// throw new RegisterException(IBSExceptionInterface.ALREADY_EXISTS_MESSAGE);//
	// }
	// }
	//
	// // All the pending Service Providers being stored in TreeMap sorted on the
	// basis
	// // of Date and Time of registration
	// @Override
	// public ArrayList<ServiceProvider> fetchPendingSp() {
	// for (ServiceProvider serviceProvider : spMap.values()) {
	// if (serviceProvider.getStatus().equalsIgnoreCase("pending")) {
	// pendingList.add(serviceProvider);
	// }
	// }
	// return pendingList;
	// }
	//
	// // Function to check the Login credentials of User
	// @Override
	// public boolean checkLogin(String userId, String password) throws IBSException
	// {
	// boolean result = false;
	// int counter = 0;
	// for (ServiceProvider serviceProvider : spMap.values()) {
	// if (serviceProvider.getUserId().equals(userId)) {
	// counter = 1;
	// if (serviceProvider.getPassword().equals(password)) {
	// result = true;
	// } else {
	// throw new IBSException(IBSExceptionInterface.INCORRECT_PASSWORD_MESSAGE);//
	// }
	// }
	//
	// }
	// if (counter == 0) {
	// throw new IBSException(IBSExceptionInterface.INCORRECT_USERID_MESSAGE);//
	// }
	// return result;
	// }
	//
	// // Changing the status of User based on the Bank Administrative's decision
	// @Override
	// public void approveStatus(ServiceProvider serviceProvider) throws
	// IBSException {
	// spMap.replace(serviceProvider.getUserId(), serviceProvider);
	// }
	//
	// // Function to see if the userID is present in Map or not
	// @Override
	// public boolean checkUserID(String userId) {
	// boolean result;
	// if (spMap.containsKey(userId)) {
	// result = false;
	// } else
	// result = true;
	// return result;
	// }
	//
	// // All the Approved Service Provider Details for Remittance Management
	// @Override
	// public ArrayList<ServiceProvider> fetchApprovedSp() {
	// for (ServiceProvider serviceProvider : spMap.values()) {
	// if (serviceProvider.getStatus().equalsIgnoreCase("Approved")) {
	// approvedList.add(serviceProvider);
	// }
	// }
	// return approvedList;
	// }
	//
	// // Administrative Login credentials check
	// @Override
	// public boolean checkAdminLogin(String adminID, String adminPassword) throws
	// IBSException {
	// boolean result = false;
	// for (BankAdmin bankAdmin : bankMap.values()) {
	// if (bankAdmin.getAdminID().equals(adminID) &&
	// bankAdmin.getAdminPassword().equals(adminPassword)) {
	// result = true;
	// }
	// }
	// return result;
	// }
	//
	// // Function to get details of Service Provider stored in HashMap
	// @Override
	// public ServiceProvider getServiceProvider(String uid) throws IBSException {
	// if (spMap.containsKey(uid)) {
	// return spMap.get(uid);
	// }
	// return null;
	// }
	//
	// // Default empty Data Function
	// @Override
	// public boolean emptyData() {
	// return spMap.isEmpty();
	// }
	//
	// @Override
	// public ArrayList<ServiceProvider> fetchHistory() {
	// for (ServiceProvider serviceProvider : spMap.values()) {
	// if (serviceProvider.getStatus().equalsIgnoreCase("Approved")) {
	// historyList.add(serviceProvider);
	// }
	// if (serviceProvider.getStatus().equalsIgnoreCase("Disapproved")) {
	// historyList.add(serviceProvider);
	// }
	// }
	//
	// return historyList;
}
