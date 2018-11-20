package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import oracle.jdbc.pool.OracleDataSource;

public class ConnectionFactory {
	private static String url;
	private static String user;
	private static String password;
	private static String driverClassName;
	private static DataSource dataSource;
	static {
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.db.dbInfo");
			driverClassName = bundle.getString("driverClassName");
			url = bundle.getString("url");
			user = bundle.getString("user");
			password = bundle.getString("password");
			
//			OracleDataSource oracleDS = new OracleDataSource();
////			DriverManager(Simple JDBC) 와  DataSource(Pooling)의 차이
////			Simple JDBC 방식 : connection 이 필요할때 그 즉시 생성.
////			Pooling 방식: 미리 일정 갯수의 Connection 을 생성하고,
////					     Pool을 통해 관리하다, 필요할때 배분해서 사용.
//			oracleDS.setURL(url);
//			oracleDS.setUser(user);
//			oracleDS.setPassword(password);
//			dataSource = oracleDS;
			//DBMS에 종속되지 않는 폴링 시스템
			BasicDataSource basicDS = new BasicDataSource();
			basicDS.setDriverClassName(driverClassName);
			basicDS.setUrl(url);
			basicDS.setUsername(user);
			basicDS.setPassword(password);
			int initialSize = Integer.parseInt(bundle.getString("initialSize"));
			int maxActive = Integer.parseInt(bundle.getString("maxActive"));
			long maxWait = Long.parseLong(bundle.getString("maxWait"));
			basicDS.setInitialSize(5);
			basicDS.setMaxActive(10);
			basicDS.setMaxWait(3000);
			dataSource = basicDS;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
//		Connection conn = DriverManager.getConnection(url, user, password);
		Connection conn = dataSource.getConnection();
		return conn;
	}
}
