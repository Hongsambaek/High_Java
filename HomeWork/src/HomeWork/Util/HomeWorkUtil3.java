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
import java.util.ResourceBundle;

/**
 * db.properties파일의 내용으로 DB정보를 설정하는 방법
 * 방법1) ResourceBundle 객체 이용하기
 * @author PC-27
 *
 */
public class HomeWorkUtil3 {
	
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
			return DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("username"),
					bundle.getString("password"));
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			return null;

		}
	}
	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if(rs != null) try {rs.close();} catch(SQLException e) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
		if(stmt != null) try {stmt.close();} catch(SQLException e) {}
		if(conn != null) try {conn.close();} catch(SQLException e) {}
	}
}
