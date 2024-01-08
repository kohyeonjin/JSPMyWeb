package com.myweb.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.user.model.UserDAO;
import com.myweb.util.JdbcUtil;

public class BoardDAO {

	private static BoardDAO instance = new BoardDAO();
	
	//커넥션 풀에서 얻어온 디비객체 정보.
	private DataSource dataSource;
	
	
	private BoardDAO() {
		try {
//		//1.드라이버 호출
//		Class.forName("oracle.jdbc.driver.OracleDriver");
			
			InitialContext init = new InitialContext();
			
			dataSource = (DataSource)init.lookup("java:comp/env/jdbc/oracle");	
			
		} catch (Exception e) {
		}
	}
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	public void insert(String writer, String title, String content) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO BOARD(BNO,WRITER,TITLE,CONTENT) VALUES(BOARD_SEQ.NEXTVAL,?,?,?)";
		
		try {
			//드라이버호출문장
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//초기설정 값을 얻을 수 있는 객체
			
			//커넥션풀이 들어있는 객체
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
		
	}
	
}
