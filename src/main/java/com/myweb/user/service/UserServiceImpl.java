package com.myweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.myweb.user.model.UserDAO;
import com.myweb.user.model.UserVO;

public class UserServiceImpl implements UserService {

	UserDAO dao = UserDAO.getInstance();
	
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
	
		int result = dao.idCheck(id);
		
		if(result == 1) { //아이디 중복
			return 1;
		}else { //회원가입 진행
			UserVO vo = new UserVO(id,pw,name,email,address,gender,null);
			dao.insertUser(vo); //회원가입
			return 0;
		}
		
			
	}

	
	@Override
	public UserVO login(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		UserVO vo = dao.login(id,pw);
		
		return vo;
	}


	@Override
	public UserVO getUserInfo(HttpServletRequest request, HttpServletResponse response) {
		
		UserVO vo = null;
		
		//회원 아이디는 세션에 있습니다
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("user_id");
		
		vo = dao.getUserinfo(id);
		
		return vo;
	}


	@Override
	public int update(HttpServletRequest request, HttpServletResponse response) {
			
		String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String gender = request.getParameter("gender");
			
			UserVO vo = new UserVO(id, pw, name, email, address, gender, null);
			int result = dao.update(vo);
			
			if(result == 1) { // 성공시에 세션의 값도 변경
				HttpSession session = request.getSession();
				session.setAttribute("user_name", name);
			}
			
		return result;
	}


	@Override
	public int delete(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("user_id");
		String pw = request.getParameter("pw");
		
		UserVO vo = dao.login(id, pw);
		
		int result = dao.delete(vo);
			
		return result;
	}


	}

