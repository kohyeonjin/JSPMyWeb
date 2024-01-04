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
}
