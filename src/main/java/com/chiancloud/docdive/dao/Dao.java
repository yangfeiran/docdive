package com.chiancloud.docdive.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.chiancloud.docdive.config.ESConfig;

public class Dao {

	private static DruidDataSource ds = new DruidDataSource();
/*	static {
		ds.setDriverClassName("nl.anchormen.sql4es.jdbc.ESDriver");
		ds.setUrl("jdbc:sql4es://10.65.6.145:8300/vehicle_all");
		ds.setMaxActive(20);
		ds.setMinIdle(5);
		Properties info = new Properties();
		info.setProperty("cluster.name", "scgaedb");
		info.setProperty("fetch.size", "100000");
//		info.setProperty("es.hosts", "10.65.6.143,10.65.6.147");
		ds.setConnectProperties(info);
		
	}*/
	
	public static void init(ESConfig config) {
		ds.setDriverClassName(config.getDriver());
		ds.setUrl(config.getUrl());
		ds.setMaxActive(config.getMaxCon());
		ds.setMinIdle(config.getMinCon());
		Properties info = new Properties();
		info.setProperty("cluster.name", config.getClusterName());
		info.setProperty("fetch.size", String.valueOf(config.getFetchSize()));
		//info.setProperty("es.hosts", config.getEsHosts());
		ds.setConnectProperties(info);
	}


	public static Connection getConnection(){
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void closeAllConnetion() {
		ds.close();
	}
}
