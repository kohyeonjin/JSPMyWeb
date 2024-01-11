package com.myweb.util.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//회원만 글을 쓸 수 있도록 걸러내는 필터 (글등록화면, 글등록)
@WebFilter({"/board/write.board","/board/registForm.board"})
public class BoardFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//httprequest 타입으로 다운캐스팅
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//세션을 얻는다
		HttpSession session = req.getSession();
		String user_id=(String)session.getAttribute("user_id");
		
		if(user_id == null) { //로그인X
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('회원만 이용가능한 서비스입니다');");
			out.println("location.href='list.board';");
			out.println("</script>");
			
			return; // 함수 종료
		}
		chain.doFilter(request, response);
		
	}

}
