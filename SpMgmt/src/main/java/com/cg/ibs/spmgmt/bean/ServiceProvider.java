package com.cg.ibs.spmgmt.bean;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ServiceProvider{
	private String nameOfCompany;
	private String gstin;
	private String panNumber;
	private BigInteger accountNumber;
	private String bankName;
	private String companyAddress;
	private BigInteger mobileNumber;
	private String userId;
	private String password;
	private BigInteger spi;
	private String status="Pending"; 
	private Timestamp requestDate;

	public ServiceProvider() {
		super();
	}

	public ServiceProvider(String nameOfCompany, String gstin, String panNumber, BigInteger accountNumber,
			String bankName, String companyAddress, BigInteger mobileNumber, String userId, String password,
			BigInteger spi, String status, Timestamp requestDate) {
		super();
		this.nameOfCompany = nameOfCompany;
		this.gstin = gstin;
		this.panNumber = panNumber;
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.companyAddress = companyAddress;
		this.mobileNumber = mobileNumber;
		this.userId = userId;
		this.password = password;
		this.spi = spi;
		this.status = status;
		this.requestDate = requestDate;
	}

	public String getNameOfCompany() {
		return nameOfCompany;
	}

	public void setNameOfCompany(String nameOfCompany) {
		this.nameOfCompany = nameOfCompany;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public BigInteger getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(BigInteger accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public BigInteger getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(BigInteger mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigInteger getSpi() {
		return spi;
	}

	public void setSpi(BigInteger spi) {
		this.spi = spi;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}

	@Override
	public String toString() {
		return "ServiceProvider [nameOfCompany=" + nameOfCompany + ", gstin=" + gstin + ", panNumber=" + panNumber
				+ ", accountNumber=" + accountNumber + ", bankName=" + bankName + ", companyAddress=" + companyAddress
				+ ", mobileNumber=" + mobileNumber + ", spi=" + spi
				+ ", status=" + status + "]";
	}	
		
}
