package com.greedy.mvc.common.jdbc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.greedy.mvc.common.config.ConfigLocation;

public class JDBCTemplate {
	
	/* Static 메소드로 하나 만들어서 사용 */

	public static Connection getConnection() {
		/* 실행 환경마다 드라이브 위치가 다르기 때문에 어떤 환경에서든 접근 할 수 있게 해주기
		   필터에서 해당 역할읗 한다. */
		// 보안상 직접 접근을 막기 위해서 사용함 (WEB-INF 내부는 접근이 안됨: 보안상의 이유)
		// 연결 객체를 만들기 위한 변수
		Connection con = null;
		
		// 연결에 필요한 정보를 담고 있는 객체
		Properties prop = new Properties();
		
		try {
			prop.load(new FileReader(ConfigLocation.CONNECTION_CONFIG_LOCATION));
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			
			Class.forName(driver);
			
			con = DriverManager.getConnection(url,prop);
		
		} catch(IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		return con;
	}
	
	public static void close(Connection con) {
		try {
			if(con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection con) {
		try {
			if(con != null && !con.isClosed()) {
				con.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con) {
		try {
			if(con != null && !con.isClosed()) {
				con.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
