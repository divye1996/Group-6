package com.cg.ibs.spmgmt.service;

import java.util.ArrayList;

import com.cg.ibs.spmgmt.bean.ServiceProvider;
import com.cg.ibs.spmgmt.exception.IBSException;

public interface IBSPortal {
	public ArrayList<ServiceProvider> getApprovedDetails() throws IBSException;

}