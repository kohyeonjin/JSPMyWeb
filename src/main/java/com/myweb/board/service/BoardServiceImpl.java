package com.myweb.board.service;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardVO;

public class BoardServiceImpl implements BoardService {

	BoardDAO dao = BoardDAO.getInstance();
	
	@Override
	public void regist(HttpServletRequest request, HttpServletResponse response) {
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		dao.insert(writer, title, content);
		
		
	}

	@Override
	public ArrayList<BoardVO> getList(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<BoardVO> list= dao.getList();
		return list;
	}

	@Override
	public BoardVO getContent(HttpServletRequest request, HttpServletResponse response) {
		
		String bno = request.getParameter("bno");
		BoardVO vo = dao.getCotent(bno);
		
		return vo;
	}

	@Override
	public int update(HttpServletRequest request, HttpServletResponse response) {
			
			String bno = request.getParameter("bno");
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			int result = dao.update(bno,writer,title,content);
			
		return result;
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		
			String bno = request.getParameter("bno");
			dao.delete(bno);
	
	}

	@Override
	public void hitUpdate(HttpServletRequest request, HttpServletResponse response) {
		
		
		//쿠키 OR 세션을 사용해서 조회수 중복을 막음
		//Cookie cookie = new Cookie(키,값)
		//cookie.setMaxAge(30)
		//response.addCookie(cookie)
		
		String bno = request.getParameter("bno");
		
		String cooValue = "";
		boolean flag = true; // 코드의 실행여부
		//기존 쿠키가 있었는지 확인
		Cookie[] arr = request.getCookies();
		if(arr != null) {
			for(Cookie c : arr) {
				if(c.getName().equals("hit")) { //hit 쿠키가 있다.
					cooValue = c.getValue();
					if(c.getValue().contains(bno)) {
						flag = false;
					}
				}
			}
		}
		
		if(flag) { //if문을 실행 안함
			dao.hitUpdate(bno);
			cooValue += bno + "-";
		}
		
		
		
		Cookie coo = new Cookie("hit", cooValue);
		coo.setMaxAge(30);
		response.addCookie(coo);
		
		
	}
		

}
