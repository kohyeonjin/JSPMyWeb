package com.myweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.myweb.user.model.UserVO;
import com.myweb.user.service.UserService;
import com.myweb.user.service.UserServiceImpl;


@WebServlet("*.user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public UserController() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String path = uri.substring(request.getContextPath().length());
		
		System.out.println(path);
		
		//서비스 영역 선언
		UserService service = new UserServiceImpl();
		
		if(path.equals("/user/join.user")) { //가입화면
			
			//화면으로 이동 기본값 - forward 형식이 되어야함
			request.getRequestDispatcher("user_join.jsp").forward(request, response);
		
		}else if(path.equals("/user/login.user")) { //로그인화면
			
			request.getRequestDispatcher("user_login.jsp").forward(request, response);
			
		}else if(path.equals("/user/joinForm.user")){ //회원가입
			
			int result = service.join(request, response);
			
			System.out.println("실행결과" + result );
			
			if(result==1) { //아이디 중복
				request.setAttribute("msg", "아이디가 중복되었습니다");
				request.getRequestDispatcher("user_join.jsp").forward(request, response);
			}else { //회원가입 성공
				response.sendRedirect("login.user"); //MVC2 방식에서 리다이렉트는 다시 컨트롤러를 태우는데 사용
			}
			
		}else if(path.equals("/user/loginForm.user")){ //로그인처리
			
			UserVO vo = service.login(request, response);
			if(vo!=null) { //로그인성공
				//서블릿에서는 request.getSession 현재세션을 얻을 수 있습니다.
				HttpSession session = request.getSession();
				session.setAttribute("user_id",vo.getId() );
				session.setAttribute("user_name",vo.getName() );
				
				response.sendRedirect(request.getContextPath()); // 홈화면
				
			}else { //로그인실패
				request.setAttribute("msg","아이디, 비밀번호를 확인하세요");
				request.getRequestDispatcher("user_login.jsp").forward(request, response);
			}
		}else if(path.equals("/user/logout.user")) { //로그아웃
			HttpSession session = request.getSession();
			session.invalidate();
			
			response.sendRedirect(request.getContextPath()); //홈화면
		}else if(path.equals("/user/mypage.user")) { //마이페이지 
			
			request.getRequestDispatcher("user_mypage.jsp").forward(request, response);
			
		}else if(path.equals("/user/update.user")) { //회원정보수정
			//여기에서 회원에 대한 데이터를 가지고 화면으로 나감
			/*
			 * 1.DAO에서는 id 기준으로 회원정보를 조회해서 UserVO에 저장
			 * 2.service 영역에서는 그대로 리턴해서 컨트롤러까지 회원정보를 가지고 나옴
			 * 3.컨트롤러에서는 vo를 request에 저장합니다.
			 * 4.화면에서 EL태그를 사용해서 values 속성에 찍어주면 됩니다.
			 */
			UserVO vo = null;
			vo = service.getUserInfo(request, response);
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("user_update.jsp").forward(request, response);
			
		}else if(path.equals("/user/updateForm.user")) { //회원정보수정
			
			int result = service.update(request, response);
			
			//0이면 실패, 1이면 성공
			if(result == 1) {
				
				
				//브라우저 화면에 직접 응답을 해주는 형태
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('업데이트에 성공했습니다');");
				out.println("location.href='mypage.user';");
				out.println("</script>");
			
			}else {
				response.sendRedirect("mypage.user");
			}
		}
		
	}
	
	

}
