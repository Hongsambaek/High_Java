package HomeWork.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * db.properties 파일의 내용으로 DB정보를 설정하는 방법 
 * 방법1) Properties 객체 이용하기
 * 
 */
public class JDBCUtil2 {

	static Properties prop;

	static {

		try {
			prop = new Properties();

			prop.load(new FileInputStream("./res/db.properties"));
			Class.forName(prop.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"),
					prop.getProperty("password"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}
	}

	/**
	 * 자원반
	 * 
	 * @param conn
	 * @param stmt
	 * @param pstmt
	 * @param rs
	 */
	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		// 자원 반납
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException ex) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException ex) {
			}
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException ex) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException ex) {
			}

	}
}
