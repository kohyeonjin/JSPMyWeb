package com.myweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.user.model.UserDAO;

public class UserServiceImpl implements UserService {

	@Override
	public int join(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email =request.getParameter("email");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		
		System.out.println("넘어온값:" + id);
		
		//가입에 대한 프로세스 -> id가 존재하는지 확인 -> insert
		
		UserDAO dao = UserDAO.getInstance();
		int result = dao.idCheck(id);
		
		if(result == 1) { //아이디 중복
			return result;
		}else { //회원가입 진행
			return 0;
		}
		
			
	}

}
