package com.cg.ibs.spmgmt.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.naming.spi.DirStateFactory.Result;
import javax.print.attribute.ResolutionSyntax;

public class ConnectionProvider {

	private static final String PROPS = "db";
	private static ConnectionProvider instance=null;
	
	private String dbUrl;
	private String uid;
	private String pwd;

	private ConnectionProvider() throws IOException {
		ResourceBundle res = ResourceBundle.getBundle(PROPS);
		dbUrl = res.getString("dburl");
		uid = res.getString("uid");
		pwd = res.getString("pwd");
	}
	
	public static ConnectionProvider getInstance() throws IOException {
		if(instance==null) {
			synchronized (ConnectionProvider.class) {
				instance= new ConnectionProvider();
			}
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbUrl,uid,pwd);
	}
}
