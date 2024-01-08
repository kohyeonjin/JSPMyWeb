package com.myweb.user.model;

import com.myweb.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

		private static UserDAO instance = new UserDAO();
		
		private UserDAO() {
			try {
			//1.드라이버 호출
			Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (Exception e) {
			}
		}
		
		public static UserDAO getInstance() {
			return instance;
		}
		////////////////////////////////////////////////////
		
		private String url = JdbcUtil.url;
		private String uid = JdbcUtil.uid;
		private String upw = JdbcUtil.upw;
		
		public int idCheck(String id) {
			int result = 0;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "select * from users where id =?";
			
			try {
				//conn객체생성
				conn = DriverManager.getConnection(url,uid,upw);
				//pstmt객체생성
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,id);
				//sql 실행
				rs = pstmt.executeQuery();
				
				if(rs.next()) { //true -> 값이 있다
					result = 1; //중복
				}else { //false -> 값이 없다
					result = 0; //중복x
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
			
	
			return result;
		}

		public void insertUser(UserVO vo) {
			
			 Connection conn= null;
			 PreparedStatement pstmt = null;
			 
			 String sql = "INSERT INTO USERS(ID,PW,NAME,EMAIL,ADDRESS,GENDER) "
			 		+ "VALUES(?,?,?,?,?,?)";
			 
			try {
				conn = DriverManager.getConnection(url,uid,upw);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,vo.getId() );
				pstmt.setString(2,vo.getPw());
				pstmt.setString(3,vo.getName() );
				pstmt.setString(4,vo.getEmail() );
				pstmt.setString(5,vo.getAddress());
				pstmt.setString(6,vo.getGender() );
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn,pstmt,null);
			}
		}

		public UserVO login(String id, String pw) {
			
			UserVO vo = null;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "SELECT * FROM USERS WHERE ID=? AND PW=?";
			
			try {
				conn = DriverManager.getConnection(url,uid,upw);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) { //로그인 성공(UserVO에 필요한 값을 저장)
					vo = new UserVO();
					vo.setId(id);
					vo.setName(rs.getString("name"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
			
			
			return vo;
		}

		public UserVO getUserinfo(String id) {
			UserVO vo = null;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "SELECT * FROM USERS WHERE ID = ?";
			
			try {
				conn = DriverManager.getConnection(url,uid,upw);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
				
				vo = new UserVO(rs.getString("id"),rs.getString("pw"),rs.getString("name"),rs.getString("email"),rs.getString("address"),rs.getString("gender"),null);
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
			
			
			return vo;
			
		}

		public int update(UserVO vo) {
			int result = 0;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			String sql = "UPDATE USERS SET PW = ?, NAME = ?, EMAIL = ?, ADDRESS = ?, GENDER = ?"
					+ "WHERE ID = ?";
			
			try {
				conn = DriverManager.getConnection(url,uid,upw);
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getPw());
				pstmt.setString(2, vo.getName());
				pstmt.setString(3, vo.getEmail());
				pstmt.setString(4, vo.getAddress());
				pstmt.setString(5, vo.getGender());
				pstmt.setString(6, vo.getId());
				
				
				result = pstmt.executeUpdate();
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, null);
			}
			
			
			return result;
		}
		
		public int delete(UserVO vo) {
			
			int result = 0;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
		
			String sql = "DELETE USERS WHERE ID=?";
			
			try {
				
				conn = DriverManager.getConnection(url,uid,upw);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getId());
				
				result = pstmt.executeUpdate();
					
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, null);
			}
			
			return result;
		}
}
