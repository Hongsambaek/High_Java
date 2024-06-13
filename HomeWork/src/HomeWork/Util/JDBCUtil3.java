package HomeWork.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * db.properties 파일의 내용으로 DB정보를 설정하는 방법 방법2) ResourceBundle 객체 이용하기
 * 
 */
public class JDBCUtil3 {

	static ResourceBundle bundle;

	static {
		bundle = ResourceBundle.getBundle("db");
		try {
			Class.forName(bundle.getString("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(bundle.getString("url"), bundle.getString("username"),
					bundle.getString("password"));
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
