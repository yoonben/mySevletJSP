package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class ConnectionUtil {

	public ConnectionUtil() {
		
	}
	
	/**
	 * DB Connection을 반환합니다.
	 * @return
	 */
	public static Connection getConnection(){
		// 접속정보
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "jsp";
		String pw = "1234";
		
		Connection conn = null;
		
		// 커넥션 생성
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. 커넥션 생성
			conn = DriverManager.getConnection(url,id,pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("라이브러리 로드중 오류가 발생 하였습니다");
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * web.xml 파일의 컨텍스트 초기화 매개변수를 읽어서
	 * DB접속정보를 설정 
	 * @param application
	 * @return
	 */
	public static Connection getConnection(ServletContext application){
		Connection conn = null;
		
		String driver = application.getInitParameter("OracleDriver");
		String url = application.getInitParameter("OracleUrl");
		String id = application.getInitParameter("OracleId");
		String pw = application.getInitParameter("OravlePW");
		
		System.out.println(driver);
		System.out.println(url);
		System.out.println(id);
		System.out.println(pw);
		
		// 커넥션 생성
		try {
			// 1. 드라이버 로딩
			Class.forName(driver);
			
			// 2. 커넥션 생성
			conn = DriverManager.getConnection(url,id,pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("라이브러리 로드중 오류가 발생 하였습니다");
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * 커넥션 생성 테스트
	 * @param args
	 */
	public static void main(String[] args) {
		String sql = "SELECT to_char(sysdate,'yyyy-mm-dd') today from dual";
		
		
		try (Connection con = getConnection();
				PreparedStatement psmt = con.prepareStatement(sql);){
			
			ResultSet rs = psmt.executeQuery();// 쿼리문 실행
			
			rs.next();
			String today = rs.getString(1);
			
			System.out.println("오늘 날짜 : "+ today);
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void close(Connection conn, Statement state) {
		try {
			if (state != null && !state.isClosed())
				state.close();
			if (conn != null && !conn.isClosed())
				conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void close(Connection conn, Statement state, ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (state != null && !state.isClosed())
				state.close();
			if (conn != null && !conn.isClosed())
				conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
