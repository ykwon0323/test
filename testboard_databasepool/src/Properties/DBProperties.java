package Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBProperties {
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	String user="hk";
	String password="1234";
	protected Connection conn = null;
	protected PreparedStatement psmt = null;
	protected Statement stmt = null;
	protected ResultSet rs = null;
	protected String sql;
	
	
	protected void dbLoading() {
		//드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패 ");
		}
	}
	protected void dbConnecting() {
		//DB연결
		
		try {
			conn=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("db 연결 실패 ");
		}
	}
	protected void dbClose() {
		//DB닫기 구현
		 try {if (rs!= null) rs.close();} catch (Exception e) {} 
	      try {if (stmt!= null) stmt.close();} catch (Exception e) {} 
	      try {if (psmt!= null) psmt.close();} catch (Exception e) {} 
	      try {if (conn!= null) conn.close();} catch (Exception e) {} 
	      System.out.println("DB접속 정상 종료");
	}
}
