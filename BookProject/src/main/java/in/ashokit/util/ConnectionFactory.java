package in.ashokit.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionFactory {

	private static DataSource ds = null;

	public static Connection getConnection() throws Exception {

		if (ds == null) {
			FileInputStream fis = new FileInputStream("C:\\Users\\govin\\eclipse-workspace-ee\\BookProject\\build\\classes\\in\\ashokit\\util\\dbconfig.properties");

			Properties p = new Properties();
			p.load(fis);

			String url = p.getProperty("db.url");
			String uname = p.getProperty("db.username");
			String pwd = p.getProperty("db.password");
			String driver = p.getProperty("db.driver");

			HikariConfig config = new HikariConfig();
			config.setUsername(uname);
			config.setPassword(pwd);
			config.setJdbcUrl(url);
			config.setDriverClassName(driver);

			ds = new HikariDataSource(config);

		}

//		Connection con = ds.getConnection();
//
//		return con;
		
		return ds.getConnection();
	}
}
