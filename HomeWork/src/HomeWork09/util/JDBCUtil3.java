package HomeWork09.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

// db.properties파일의 내용으로 DB정보를 설정
// 2. ResourceBundle 객체 이용
public class JDBCUtil3 {

	static ResourceBundle bundle;

	static {

		bundle = ResourceBundle.getBundle("db");

		try {
			Class.forName(bundle.getString("driver"));

			System.out.println("드라이버 로딩 성공!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패!!!");
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(bundle.getString("url"), bundle.getString("username"),
					bundle.getString("password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// 자원반납
	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException ex) {
				// TODO: handle exception
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException ex) {
				// TODO: handle exception
			}
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException ex) {
				// TODO: handle exception
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException ex) {
				// TODO: handle exception
			}
	}
}
