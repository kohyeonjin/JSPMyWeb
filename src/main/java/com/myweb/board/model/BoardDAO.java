package com.myweb.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

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
		String sql = "INSERT INTO BOARD1(BNO,WRITER,TITLE,CONTENT) VALUES(BOARD_SEQ.NEXTVAL,?,?,?)";
		
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

	public ArrayList<BoardVO> getList() {
		
		ArrayList<BoardVO> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM BOARD1 ORDER BY BNO DESC";
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bno = rs.getInt("BNO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String writer = rs.getString("WRITER");
				int hit = rs.getInt("HIT");
				Timestamp regdate = rs.getTimestamp("REGDATE");
				
				BoardVO vo = new BoardVO(bno,writer,title,content,hit,regdate);
				list.add(vo);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
		
		return list;
	}
	
	public BoardVO getCotent(String bno) {
		
		BoardVO vo = new BoardVO();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM BOARD1 WHERE BNO = ?";
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			
				int bno2 = rs.getInt("BNO");
				String writer = rs.getString("WRITER");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				int hit = rs.getInt("HIT");
				Timestamp regdate = rs.getTimestamp("REGDATE");
				
				vo.setBno(bno2);
				vo.setWriter(writer);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setHit(hit);
				vo.setRegdate(regdate);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
			
		
		
		return vo;
		
		
		}
		
	public int update(String bno ,String writer, String title, String content) {
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE BOARD1 SET WRITER = ? , TITLE = ? , CONTENT = ? WHERE BNO=?";
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setString(4, bno);
			
			result = pstmt.executeUpdate();
			 
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
		
		return result;
	}
	
	//삭제기능(삭제는 나중에 컬럼을 하나 만들고 사용하지 않음 Y,N)
	public void delete(String bno) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM BOARD1 WHERE BNO = ?";
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
	}
	//조회수작업
	
	public void hitUpdate(String bno) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE BOARD1 SET HIT = HIT+1 WHERE BNO = ?";
		
		try {
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bno);
			
			pstmt.executeUpdate();
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			                
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
	}
}
